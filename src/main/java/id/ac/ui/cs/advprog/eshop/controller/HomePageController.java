package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageController {
    @GetMapping("")
    public String getHomePage(Model model){
        return "homePage";
    }
}
