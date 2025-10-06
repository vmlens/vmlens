package com.vmlens.inttest.projects.spring.batch;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.JobInstance;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.repository.dao.jdbc.JdbcJobExecutionDao;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import static org.mockito.Mockito.mock;

public class JdbcExecutionContextDaoTest {

	@Disabled
	@Test
	public void setJdbcTemplate() throws DuplicateJobException, InterruptedException {
		try (AllInterleavings allInterleavings = new AllInterleavings("jdbcJobExecutionDao")) {
			while (allInterleavings.hasNext()) {
				JobExecution jobExecution = new JobExecution(new JobInstance(11L, "testJob"), new JobParameters());
				JdbcJobExecutionDao jdbcJobExecutionDao = new JdbcJobExecutionDao();
				jdbcJobExecutionDao.setJdbcTemplate(mock(JdbcOperations.class));
				jdbcJobExecutionDao.setJobExecutionIncrementer(mock(DataFieldMaxValueIncrementer.class));
				JdbcOperations second = mock(JdbcOperations.class);
				Thread first = new Thread(() -> jdbcJobExecutionDao.saveJobExecution(jobExecution));
				first.start();
				jdbcJobExecutionDao.setJdbcTemplate(second);
				first.join();
			}
		}
	}

}
