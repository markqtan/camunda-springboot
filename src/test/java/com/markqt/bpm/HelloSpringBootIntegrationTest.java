package com.markqt.bpm;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {CamundaBpmSpringbootApplication.class})
public class HelloSpringBootIntegrationTest {
	 @Autowired
	 RuntimeService runtimeService;
	 
	@Test
//	@Deployment(resources = {"bpmn/hello.bpmn"})
	public void test() {
		Map<String, Object> params = new HashMap<>();
		params.put("input", "Mark");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("hello", params);
		System.out.println(pi);
		assertNotNull(pi);
	}
}
