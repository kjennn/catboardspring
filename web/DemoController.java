package com.gsitm.career.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/view")
    public ModelAndView view(ModelAndView modelAndView) {
        modelAndView.setViewName("demo/index");
        return modelAndView;
    }

}
