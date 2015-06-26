package Util.quartzEvent;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;




public class ExecuteQuartzJob extends QuartzJobBean {
	private static Logger log = Logger.getLogger(ExecuteQuartzJob.class);
	private CommandQuartzJob commandQuartzJob;

	public CommandQuartzJob getCommandQuartzJob() {
		return commandQuartzJob;
	}

	public void setCommandQuartzJob(CommandQuartzJob commandQuartzJob) {
		this.commandQuartzJob = commandQuartzJob;
	}

	protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
		System.out.println("timing");
		String[] jobs = jobexecutioncontext.getTrigger().getDescription().split(",");
		
		if(jobs.length>0){
			int strategyId = Integer.parseInt(jobs[0]);
			String devStrategyName = jobs[1];
			try {
				commandQuartzJob.quartzExecuteCommand(strategyId,devStrategyName);
			}  catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	} 
}
