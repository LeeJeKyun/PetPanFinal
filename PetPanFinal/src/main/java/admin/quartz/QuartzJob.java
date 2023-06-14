package admin.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import admin.quartz.service.QuartzService;

public class QuartzJob extends QuartzJobBean {
	
	private QuartzService quartzService;


	public void setQuartzService(QuartzService quartzService) {
		this.quartzService = quartzService;
	}


	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		quartzService.MessageDelete();
	}
	
	
	
}
