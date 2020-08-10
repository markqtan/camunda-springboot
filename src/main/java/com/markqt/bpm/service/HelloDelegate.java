package com.markqt.bpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloDelegate {

	@Autowired
	private HelloService helloService;
	public void hi() {
		String msg = helloService.hi("Mark");
		System.out.println("helloService: "+msg);
	}
}
