package com.My.Reptile.controller.HomeController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    /*WolfEagle搜索检索引擎的第一个页面处理*/
    @RequestMapping("/")
    public String index(Model model) throws Exception {

        return "/SearchIndex/ReptilIndex";
    }

}
