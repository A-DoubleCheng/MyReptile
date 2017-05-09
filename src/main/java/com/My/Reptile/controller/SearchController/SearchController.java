package com.My.Reptile.controller.SearchController;

import com.My.Reptile.Param.PageParam;
import com.My.Reptile.Param.SearchParam;
import com.My.Reptile.controller.SearchIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 28/03/2017.
 */
@RequestMapping(value = "/Search")
@Controller
public class SearchController {
    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpServletRequest request;
    /*这里是ajax页面，主要用来显示搜索查询的数据信息*/
    @RequestMapping(value = "/AjaxPage.html",method = RequestMethod.GET)
    public String toSearchPage(Model model, SearchParam searchParam, PageParam pageParam) throws Exception {
        String pageIndex = request.getParameter("pageIndex");
        /*如果搜索查询的起始页默认为第一页*/
        if(pageIndex == null){
            pageIndex = "1";
        }
        /*分页每页默认显示5条数据*/
        pageParam.setPageSize(5);

        /*如果使用者用户输入空字符串处理，进行重定向跳转，跳转首页*/
        if(searchParam.getParamQ().equals("")){
           return redirectTo("/");
        }
        /*判断用户搜索查询的类型，新闻还是书籍*/
        if(searchParam.getType().equals("News")){
            searchParam.setIndexDir("D:/Lucene_index_Zol");
        }else if(searchParam.getType().equals("Db")){
            searchParam.setIndexDir("D:/Lucene_index_Db");
        }
        /*调用search方法进行搜索查询，并返回搜索查询的数据*/
        Map<String, Object> map = SearchIndex.search(searchParam.getIndexDir(),searchParam.getParamQ(),pageParam.getPageIndex(), pageParam.getPageSize());
        /*返回的map中拿到数据信息列表*/
        ArrayList list = (ArrayList) map.get("newsList");
        if(list.size() == 0){
            list = new ArrayList();
        }
        model.addAttribute("list",list);
        model.addAttribute("q",searchParam.getParamQ());//将上个页面用户输入的关键词，继续显示在搜索框
        model.addAttribute("pageSize",pageParam.getPageSize());//分页的大小
        model.addAttribute("pageIndex",pageIndex);//当前处于第几页
        model.addAttribute("tip",map.get("tip"));//搜索查询结果的提示内容
        model.addAttribute("PageTotal",map.get("PageTotal"));//总共有多少个数据

        return "/SearchPage/SearchDivPage";
    }

    @RequestMapping(value = "/ParamPage.html",method = RequestMethod.GET)
    public String toSearchPage(Model model, SearchParam searchParam) throws Exception {

        /*如果使用者用户输入空字符串处理，进行重定向跳转，跳转首页*/
        if(searchParam.getParamQ().equals("")){
            return redirectTo("/");
        }

        model.addAttribute("Type",searchParam.getType());
        model.addAttribute("ParamQ",searchParam.getParamQ());

        return "/SearchPage/SearchPage";
    }

    /*重定向封装*/
    public String redirectTo(String url){
        StringBuffer rto = new StringBuffer("redirect:");
        rto.append(url);
        return rto.toString();
    }

}
