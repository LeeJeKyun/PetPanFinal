package admin.quartz.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="quartzService")
public class QuartzService {

	@Autowired private Job deleteMessage;
	@Autowired private SimpleJobLauncher jobLauncher;
	
	public void 
	
}
