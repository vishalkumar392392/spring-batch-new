package com.vishal.spring.batch.listener;

import javax.sql.DataSource;

import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.vishal.spring.batch.modal.StudentJDBC;

@Component
public class SkipListener {

	@Autowired
	@Qualifier("seconddatasource")
	private DataSource universityDataSource;

	@OnSkipInRead
	public void skipInRead(Throwable th) {
		System.out.println("skipInRead....");
		captureFailedRecord("read", th.getMessage());
	}

	@OnSkipInProcess
	public void skipInProcess(StudentJDBC studentJDBC, Throwable th) {
		System.out.println("skipInProcess....");
		captureFailedRecord("process", studentJDBC.toString());
	}

	public void captureFailedRecord(String type, String message) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(universityDataSource);
		String sql = "INSERT INTO failedRecordsInfo (typeOfprocess, message) VALUES(?,?)";
		jdbcTemplate.update(sql, type, message);
	}

}
