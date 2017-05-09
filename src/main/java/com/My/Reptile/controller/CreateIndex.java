package com.My.Reptile.controller;
import com.My.Reptile.controller.utils.MyIkAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
public class CreateIndex {
    public static void main(String[] args) throws IOException {
        /*豆瓣图书*/
//        String dirPath = "D:/Lucene_data_Db";
//        String indexPath = "D:/Lucene_index_Db";
        /*zol的新闻*/
        String dirPath = "D:/Lucene_data_Zol";
        String indexPath = "D:/Lucene_index_Zol";
        createIndex(dirPath, indexPath);
    }
    /**
     * 创建索引 
     * @param dirPath       需要读取的文件所在文件目录 
     * @param indexPath     索引存放目录 
     * @throws IOException
     */
    public static void createIndex(String dirPath, String indexPath) throws IOException {
        createIndex(dirPath, indexPath, false);
    }
    /**
     * 创建索引 
     * @param dirPath         需要读取的文件所在文件目录 
     * @param indexPath       索引存放目录 
     * @param createOrAppend  始终重建索引/不存在则追加索引 
     * @throws IOException
     */
    public static void createIndex(String dirPath, String indexPath, boolean createOrAppend) throws IOException {
        long start = System.currentTimeMillis();//开始时间
        Directory dir = FSDirectory.open(Paths.get(indexPath, new String[0]));//从索引路径拿到Dir对象
        Path docDirPath = Paths.get(dirPath, new String[0]);//从文件路径拿到Path对象
//        Analyzer analyzer = new StandardAnalyzer();//标准分词器
        Analyzer analyzer = new MyIkAnalyzer();//使用IK中文分词器
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);//根据分词器实例化索引生成的配置

        if (createOrAppend) {
            indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);//设置索引生成的配置，新建
        } else {
            indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);//设置索引生成的配置，追加
        }
        IndexWriter writer = new IndexWriter(dir, indexWriterConfig);//通过索引生成配置、Dir对象实例化索引写入器对象
        indexDocs(writer, docDirPath);//参数，索引生成对象、文件路径的Path对象
        writer.close();
        long end = System.currentTimeMillis();
        System.out.println("Time consumed:" + (end - start) + " ms");
    }

    /**
     *
     * @param writer
     *            索引写入器 
     * @param path
     *            文件路径 
     * @throws IOException
     */
    public static void indexDocs(final IndexWriter writer, Path path) throws IOException {
        // 如果是目录，查找目录下的文件  
        if (Files.isDirectory(path, new LinkOption[0])) {
            System.out.println("directory");
            Files.walkFileTree(path, new SimpleFileVisitor() {
                @Override
                public FileVisitResult visitFile(Object file,
                                                 BasicFileAttributes attrs) throws IOException {
                    Path path = (Path)file;
                    System.out.println(path.getFileName());
                    indexDoc(writer, path, attrs.lastModifiedTime().toMillis());//读取文件创建索引
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            indexDoc(writer, path,
                    Files.getLastModifiedTime(path, new LinkOption[0])
                            .toMillis());
        }
    }
    /**
     * 读取文件创建索引 
     *
     * @param writer
     *            索引写入器 
     * @param file
     *            文件路径 
     * @param lastModified
     *            文件最后一次修改时间 
     * @throws IOException
     */
    public static void indexDoc(IndexWriter writer, Path file, long lastModified)
            throws IOException {
        InputStream stream = Files.newInputStream(file, new OpenOption[0]);//打开流
        Document doc = new Document();//实例化索引Doc对象

        Field pathField = new StringField("path", file.toString(), Field.Store.YES);

        doc.add(pathField);
        doc.add(new LongPoint("modified", lastModified));
        doc.add(new TextField("contents", new BufferedReader(
                new InputStreamReader(stream, StandardCharsets.UTF_8))));
//        System.out.println(new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8)));
        if (writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
            System.out.println("adding " + file);
            writer.addDocument(doc);
        } else {
            System.out.println("updating " + file);
            writer.updateDocument(new Term("path", file.toString()), doc);
        }
        writer.commit();
    }
}  