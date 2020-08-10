package com.markqt.bpm.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

	public String hi(String name) {
		String msg = "Hi, " + name +".";
		System.out.println(msg);
		return msg;
	}
}
