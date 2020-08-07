package com.markqt.bpm;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.spring.boot.starter.test.helper.StandaloneInMemoryTestConfiguration;
import org.junit.Before;
import org.junit.Rule;

public abstract class AbstractBpmTestCase {
	@Rule
	public ProcessEngineRule rule = new StandaloneInMemoryTestConfiguration().rule();
	protected RuntimeService runtimeService;
	protected TaskService taskService;

	@Before
	public void init() {
		runtimeService = rule.getRuntimeService();
		taskService = rule.getTaskService();
	}

}
