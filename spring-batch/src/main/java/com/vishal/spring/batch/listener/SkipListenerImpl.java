package com.vishal.spring.batch.listener;

import javax.sql.DataSource;

import org.springframework.batch.core.SkipListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import com.vishal.spring.batch.modal.StudentJDBC;

public class SkipListenerImpl implements SkipListener<StudentJDBC, StudentJDBC> {
	
	@Autowired
	@Qualifier("seconddatasource")
	private DataSource universityDataSource;

	@Override
	public void onSkipInRead(Throwable t) {
		// TODO Auto-generated method stub
		captureFailedRecord("read", t.toString());
	}

	@Override
	public void onSkipInWrite(StudentJDBC item, Throwable t) {
		// TODO Auto-generated method stub
		captureFailedRecord("write", item.toString());
	}

	@Override
	public void onSkipInProcess(StudentJDBC item, Throwable t) {
		// TODO Auto-generated method stub
		captureFailedRecord("process", item.toString());
	}
	
	public void captureFailedRecord(String type, String message) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(universityDataSource);
		String sql = "INSERT INTO failedRecordsInfo (typeOfprocess, message) VALUES(?,?)";
		jdbcTemplate.update(sql, type, message);
	}

}
