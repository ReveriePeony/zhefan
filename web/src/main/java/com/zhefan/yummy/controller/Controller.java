package com.zhefan.yummy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhefan.yummy.base.BaseController;

/**
 * 
 * @author ReverirNight@Foxmail.com
 * @datetime 2018年10月29日
 *
 */
@RequestMapping("/")
public class Controller extends BaseController {

    @GetMapping(value = "error")
    public ModelAndView error() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("/404.html");
        return modelAndView;
    }
    
}
