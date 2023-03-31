package com.spring.batch.migration.databasemigration.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MigrationSchedular {

	@Autowired
	private JobLauncher jobLauncher;

	@Qualifier("dataJob")
	@Autowired
	private Job job;

//	@Scheduled(cron = "*/10 * * * * *")
	public void startJdbcJob() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {

		Map<String, JobParameter<?>> params = new HashMap<>();
		params.put("currentTime", new JobParameter<>(System.currentTimeMillis(), Long.class));
		JobParameters jobParameters = new JobParameters(params);
		jobLauncher.run(job, jobParameters);
	}

}
