package com.megazone.demo.controller;

import com.google.gson.Gson;
import com.megazone.demo.exrepository.ExternalDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoController {
	
	@Autowired
	ExternalDemoRepository demoRepository;

	@Autowired
	Gson gson;
	
	@RequestMapping("/index")
    public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
    }

	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}



	@RequestMapping("/external")
	@ResponseBody
    public String external() {
	        return gson.toJson(demoRepository.findAll());
    }


	@RequestMapping("/internal")
	@ResponseBody
	public String internal() {
		return gson.toJson(demoRepository.findAll());
	}
}
