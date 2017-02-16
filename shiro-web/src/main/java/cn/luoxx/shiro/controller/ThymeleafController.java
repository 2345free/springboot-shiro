package cn.luoxx.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class ThymeleafController {

	@RequestMapping("/jsp")
	public String jsp(Model model){
		model.addAttribute("msg", "hello jsp...!");
		return "index";
	}
	
	@RequestMapping("/th")
	public String th(Model model){
		model.addAttribute("msg", "hello thymeleaf!");
		return "index";
	}
}
