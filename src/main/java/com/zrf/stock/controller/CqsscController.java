package com.zrf.stock.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zrf.stock.service.CqsscServiceI;

@Controller
@RequestMapping(value="/cqssc")
public class CqsscController {
	
	@Resource
	private CqsscServiceI service;
	
	@RequestMapping(value="/getCurrentNum", produces="text/html;charset=UTF-8")
	@ResponseBody
	private String getCurrentNum(){
		return service.getCurrentNum();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
