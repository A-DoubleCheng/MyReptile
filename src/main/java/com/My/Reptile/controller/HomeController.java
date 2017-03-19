package com.My.Reptile.controller;
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
    private String key = "29e0c24a6f869e1fd4057deeaba9517d";//测试Key
    private String tableid = "58ca4a937bbf195ae8b0dbfb";//地图表名

    @RequestMapping("/")
    public String index(Model model) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("key",key);//云图key
        params.put("tableid",tableid);//地图ID
        params.put("keywords","");
        params.put("city","上海");
       ApiResult apiRequest = JSONObject.parseObject(HttpClientUtils.get("http://yuntuapi.amap.com/datasearch/local",params), ApiResult.class);
       List<YunMap> yunMap = JSON.parseObject(apiRequest.getDatas(), List.class);
       model.addAttribute("yunMapList",yunMap);
        return "index";
    }

    @RequestMapping(value = "/reptilIndex",method = RequestMethod.GET)
    public String toDeleteEnt() throws Exception {

        return "reptilIndex";
    }


}
