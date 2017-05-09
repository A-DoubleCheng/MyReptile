package com.My.Reptile.controller;

import com.My.Reptile.Model.News;
import com.My.Reptile.controller.utils.MyIkAnalyzer;
import com.alibaba.fastjson.JSON;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 03/04/2017.
 */
public class SearchIndex{
    public static Map search(String indexDir, String q ,int pageIndex, int pageSize) throws Exception {

        List list = new ArrayList();

        Directory dir = FSDirectory.open(Paths.get(indexDir));// 打开目录
        IndexReader reader = DirectoryReader.open(dir);// 进行读取
        IndexSearcher is = new IndexSearcher(reader);// 索引查询器
//        Analyzer analyzer = new StandardAnalyzer(); // 标准分词器
        Analyzer analyzer = new MyIkAnalyzer();//重写的IK中文分词器
        QueryParser parser = new QueryParser("contents", analyzer);// 在哪查询，第一个参数为查询的Document，在Index库建立的时候创建
        Query query = parser.parse(q);// 对字段进行解析后返回给查询

        long start = System.currentTimeMillis();
        TopDocs hits = is.search(query, Integer.MAX_VALUE);// 开始查询，10代表前10条数据,这里是查询最大数量数据；返回一个文档
        ScoreDoc [] sd = hits.scoreDocs;//通过hits获得所有的文档标识
        /*进行分页处理*/
        int startPage = (pageIndex - 1) * pageSize;//开始页
        int endPage = pageIndex * pageSize;//结尾页

        long end = System.currentTimeMillis();//用来计算处理时间
        /*用来显示查询耗时以及查询结果数量*/
        String tip = "匹配： <span style=\"color: red;\">" + q + "</span> ，总共花费:<span style=\"color: red;\">" + (end - start) + "</span> 毫秒" + "查询到:<span style=\"color: red;\">" + hits.totalHits + "</span>个记录";
        /*高亮处理部分*/
        QueryScorer scorer = new QueryScorer(query);//查询评分，获取评分后的查询对象
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);//拆分器，将原始文本拆分为一个个高亮片段
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter(
                "<b><font color='red'>","</font></b>"
        );//对查询格式化，设置高亮文本样式
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);//根据得分去格式化
        highlighter.setTextFragmenter(fragmenter);

        if(hits.totalHits < endPage){
            endPage = hits.totalHits;//因为要处理分页，如果查询到的结果小于每页显示的数据信息，结尾页就是查询到的最大结果
        }
        for(int i = startPage; i < endPage; i++) {
//        for (ScoreDoc scoreDoc : hits.scoreDocs) {
//            Document doc = is.doc(scoreDoc.doc);// 根据文档的标识获取文档
            Document doc = is.doc(sd[i].doc);// 使用is索引查询器的doc方法根据文档的标识获取文档
            System.out.println(doc.get("path"));//doc.get通过path拿到文件路径

            String searchString = new String(Files.readAllBytes(Paths.get(doc.get("path"))));
            /*内容实体进行赋值*/
            News news = new News();
            HashMap<String,Object> map = JSON.parseObject(searchString,HashMap.class);
            System.out.println(map);
            Object l = map.get("time");
            if(l instanceof List){
                news.setTime((String) ((List) l).get(0));
            }else if(l instanceof String){
                news.setTime((String) l);
            }

            news.setTitle((String) map.get("title"));
            news.setContent((String) map.get("content"));
            news.setUrl((String) map.get("url"));
            /*通过TokenStream流获取存储分词的信息*/
            TokenStream tokenStreamContent = analyzer.tokenStream("contents", new StringReader(news.getContent()));
            String content = highlighter.getBestFragment(tokenStreamContent, news.getContent());
            if(content != null){
                news.setContent(content);
            }

            TokenStream tokenStreamTitle = analyzer.tokenStream("contents", new StringReader(news.getTitle()));
            String title = highlighter.getBestFragment(tokenStreamTitle, news.getTitle());
            if(title != null){
                news.setTitle(title);
            }

            list.add(news);
        }
        reader.close();

        Map<String,Object> map = new HashMap();
        map.put("newsList", list);
        map.put("PageTotal",hits.totalHits/pageSize);
        map.put("tip",tip);
        return map;
    }

}
