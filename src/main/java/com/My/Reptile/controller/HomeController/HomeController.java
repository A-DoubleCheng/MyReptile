package com.My.Reptile.controller.HomeController;
import com.My.Reptile.controller.result.JsonResult;
import com.My.Reptile.controller.utils.HttpClientUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.My.Reptile.Model.YunMap;
import com.My.Reptile.controller.result.ApiResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model) throws Exception {

        return "/SearchIndex/ReptilIndex";
    }

    @RequestMapping("/test")
    public String test(Model model) throws Exception {

        return "/SearchIndex/test";
    }

}
