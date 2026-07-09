package com.example.quote.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DemoController {
    @GetMapping("/bye/{name}")
    public String goodBye(@PathVariable String name, Model model) {
        model.addAttribute("name",name);
        return "quote";
    }
    
    
}
