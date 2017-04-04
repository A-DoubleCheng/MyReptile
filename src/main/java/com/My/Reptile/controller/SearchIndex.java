package com.My.Reptile.controller;

import com.My.Reptile.Model.Enterprise;
import com.google.gson.Gson;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 03/04/2017.
 */
public class SearchIndex{
    public static List search(String indexDir, String q) throws Exception {
        Gson gson = new Gson();
        List list = new ArrayList();
        Directory dir = FSDirectory.open(Paths.get(indexDir));// 打开目录
        IndexReader reader = DirectoryReader.open(dir);// 进行读取
        IndexSearcher is = new IndexSearcher(reader);// 索引查询器
        Analyzer analyzer = new StandardAnalyzer(); // 标准分词器
        QueryParser parser = new QueryParser("contents", analyzer);// 在哪查询，第一个参数为查询的Document，在Indexer中创建了
        Query query = parser.parse(q);// 对字段进行解析后返回给查询
        long start = System.currentTimeMillis();
        TopDocs hits = is.search(query, 10);// 开始查询，10代表前10条数据；返回一个文档
        long end = System.currentTimeMillis();
        System.out.println("匹配 " + q + " ，总共花费" + (end - start) + "毫秒" + "查询到"
                + hits.totalHits + "个记录");
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);// 根据文档的标识获取文档
            System.out.println(doc.get("path"));
            String searchString = new String(Files.readAllBytes(Paths.get(doc.get("path"))));
            Enterprise enterprise = gson.fromJson(searchString,Enterprise.class);
            list.add(enterprise);
        }
        reader.close();

        return list;
    }

}
