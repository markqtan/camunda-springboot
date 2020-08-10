package com.markqt.bpm;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.mockito.Expressions;
import org.camunda.bpm.extension.mockito.ProcessExpressions;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.ProcessCoverageInMemProcessEngineConfiguration;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.spring.boot.starter.test.helper.StandaloneInMemoryTestConfiguration;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.markqt.bpm.service.HelloDelegate;
import com.markqt.bpm.service.HelloService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {HelloProcessTest.TestConfig.class})
public class HelloProcessTest {//extends AbstractBpmTestCase {
	
	static class TestConfig extends BpmUnitTestConfig {
		
		@Bean HelloDelegate helloDelegate() {
			return new HelloDelegate();
		}
		@Bean
		HelloService helloService() {
			return Mockito.mock(HelloService.class);
		}
	}
	@Autowired
	@Rule
	public ProcessEngineRule rule ;
	@Autowired
	RuntimeService runtimeService;
    
	@Autowired
	HelloService helloService;
	@Autowired
	HelloDelegate helloDelegate;

	@Before
	public void init() {
		Mockito.when(helloService.hi(Mockito.anyString())).thenReturn("Hello There");
		Expressions.registerInstance("helloDelegate", helloDelegate);
	}
	
	@Test
	@Deployment(resources = {"bpmn/hello.bpmn"})
	public void test() {
		Map<String, Object> params = new HashMap<>();
		params.put("input", "Mark");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("hello", params);
		System.out.println(pi);
		assertNotNull(pi);
	}
	

}
