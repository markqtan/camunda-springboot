package com.markqt.bpm;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.spring.boot.starter.test.helper.StandaloneInMemoryTestConfiguration;
import org.springframework.context.annotation.Bean;

public class BpmUnitTestConfig {

	@Bean
	StandaloneInMemoryTestConfiguration cfg() {
		return new StandaloneInMemoryTestConfiguration();
	}

	@Bean
	ProcessEngineRule rule(StandaloneInMemoryTestConfiguration cfg) {
		return cfg.rule();
	}

	@Bean
	ProcessEngine processEngine(StandaloneInMemoryTestConfiguration cfg) {
		return cfg.buildProcessEngine();
	}

	@Bean
	RuntimeService runtimeService(ProcessEngine pe) {
		return pe.getRuntimeService();
	}

	@Bean
	TaskService taskService(ProcessEngine pe) {
		return pe.getTaskService();
	}
}
