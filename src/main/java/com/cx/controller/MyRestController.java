package com.cx.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cx.service.ActivitiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "MyRestController ")
@RestController
@RequestMapping(value = "/test", produces = {"application/json;charset=UTF-8"})
public class MyRestController {
	@Autowired
	private ActivitiService myService;
	
	@Autowired
	private TaskService taskService;
	
	@ApiOperation(value = "开启流程实例")
	@RequestMapping(value = "/process/{key}", method = RequestMethod.GET)
	public void startProcessInstance(@PathVariable String key) {
		myService.startProcess(key);
	}
	
	@ApiOperation(value = "获取流程信息")
	@RequestMapping(value = "/getCurTask/{key}", method = RequestMethod.GET)
	public List<Task> getCurTask(@PathVariable String key) {
		List<Task> list = taskService.createTaskQuery().processInstanceBusinessKey(key).list();
		System.out.println("办理人"+list.get(0).getAssignee());
		return list;
	}
	
	//获取当前人的任务
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
		List<Task> tasks = myService.getTasks(assignee);
		List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
		for (Task task : tasks) {
			dtos.add(new TaskRepresentation(task.getId(), task.getName(),task.getProcessVariables()));
		}
		return dtos;
	}
	
	//获取当前人的任务
	@RequestMapping(value = "/taskById", method = RequestMethod.GET)
	public String getTaskById(@RequestParam String taskId) {
		Task task =  taskService.createTaskQuery().taskId(taskId).singleResult();
		Map<String,Object> vars = taskService.getVariables(taskId);
        for (String variableName : vars.keySet()) {
            String val = (String) vars.get(variableName);
            System.out.println(variableName + " = " +val);
        }
		return "333";
	}
	
	//开启流程实例
	@RequestMapping(value = "/setVariable/{taskId}", method = RequestMethod.GET)
	public String setVariable(@PathVariable String taskId) {
		myService.setVariable(taskId);
		return "ok";
	}
	
	
	//完成任务
	@RequestMapping(value = "/complete/{taskId}/{status}", method = RequestMethod.GET)
	public String complete(@PathVariable int status, @PathVariable String taskId) {
		myService.completeTasks(status, taskId);
		return "ok";
	}
	
	//Task的dto
	static class TaskRepresentation
	
	{
		private String id;
		private String name;
		private Map map;
		
		public Map getMap() {
			return map;
		}

		public void setMap(Map map) {
			this.map = map;
		}

		public TaskRepresentation(String id, String name,Map map) {
			this.id = id;
			this.name = name;
			this.map = map;
		}
		
		public String getId() {
			return id;
		}
		
		public void setId(String id) {
			this.id = id;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
	}
}
