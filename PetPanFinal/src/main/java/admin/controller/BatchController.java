package admin.controller;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/batch")
public class BatchController {

	@Autowired private Job messageToAll;
	@Autowired private SimpleJobLauncher jobLauncher;
	
	@RequestMapping("/message")
	public void messageToAll(String content) {
		
		System.out.println(content);
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("a", "b")
				.addDate("date", new Date())
				.addString("content",content)
				.toJobParameters();
		
		try {
			jobLauncher.run(messageToAll, jobParameters);
		} catch (JobExecutionAlreadyRunningException e) {
			e.printStackTrace();
		} catch (JobRestartException e) {
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			e.printStackTrace();
		}
		
	}
	
}
