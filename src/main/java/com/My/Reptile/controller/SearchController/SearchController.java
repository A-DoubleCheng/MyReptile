package com.My.Reptile.controller.SearchController;

import com.My.Reptile.Param.SearchParam;
import com.My.Reptile.controller.SearchIndex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Administrator on 28/03/2017.
 */
@RequestMapping(value = "/Search")
@Controller
public class SearchController {

    @RequestMapping(value = "/ParamPage.html",method = RequestMethod.GET)
    public String toSearchPage(Model model,SearchParam searchParam) throws Exception {

        searchParam.setIndexDir("D:/Lucene_index");

        List list = SearchIndex.search(searchParam.getIndexDir(),searchParam.getQ());
        model.addAttribute("list",list);
        model.addAttribute("q",searchParam.getQ());

        return "/SearchPage/SearchPage";
    }

}
