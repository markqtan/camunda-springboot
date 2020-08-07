package com.markqt.bpm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	private ProcessEngine engine;
	
	@GetMapping("/hello")
	Object hello() {
		System.out.println(engine);
		RuntimeService rt = engine.getRuntimeService();
		RepositoryService repo = engine.getRepositoryService();
		HistoryService hs = engine.getHistoryService();
		repo.createDeployment().addClasspathResource("bpmn/hello.bpmn").deploy();
		
		Map<String, Object> params = new HashMap<>();
		params.put("input", "Mark");
		ProcessInstance pi = rt.startProcessInstanceByKey("hello", params);
		System.out.println(pi);
		
		String executionId = hs.createHistoricDetailQuery().processInstanceId(pi.getProcessInstanceId()).list().get(0).getExecutionId();
		System.out.println("executionid: " + executionId);
		List<HistoricVariableInstance> vars = hs.createHistoricVariableInstanceQuery().executionIdIn(executionId).list();
		System.out.println("vars: " + vars);
		vars.forEach(hi->{System.out.println(hi.getName() +"=" + hi.getValue());});
		return "OK";
	}
}
