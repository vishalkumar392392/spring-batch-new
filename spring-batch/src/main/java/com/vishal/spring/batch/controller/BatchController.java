package com.vishal.spring.batch.controller;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishal.spring.batch.service.BatchService;

@RestController
@RequestMapping("/api/job")
public class BatchController {

	@Autowired
	private BatchService batchService;

	@Autowired
	private JobOperator jobOperator;

	@GetMapping("/start/{jobName}")
	public String startJob(@PathVariable String jobName) throws JobExecutionAlreadyRunningException,
			JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

		batchService.startJob();

		return "Job Started...";
	}

	@GetMapping("/stop/{exectionId}")
	public String stopJob(@PathVariable Long exectionId) throws Exception {

		jobOperator.stop(exectionId);

		return "Job Stopped...";
	}

}
