package BO.Quartz;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.CronExpression;

public interface SchedulerService {
	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * @param cronExpression  Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	 */
	void schedule(String cronExpression,String jobList);
	
	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * @param name  Quartz CronTrigger名称
	 * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	 */
	void schedule(String name,String cronExpression,String jobList);
	
	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * @param name  Quartz CronTrigger名称
	 * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	   * @param group Quartz CronExpression Group
	 */
	 void schedule(String name, String cronExpression,String group,String jobList);
	
	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * @param cronExpression Quartz CronExpression
	 */
	void schedule(CronExpression cronExpression,String jobList);
	
	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * @param name Quartz CronTrigger名称
	 * @param cronExpression Quartz CronExpression
	 */
	void schedule(String name,CronExpression cronExpression,String jobList);
	
	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * @param name Quartz CronTrigger名称
	 * @param cronExpression Quartz CronExpression
	   * @param group Quartz CronExpression Group
	 */
	void schedule(String name, CronExpression cronExpression,String group,String jobList);
	
	/**
	 * 在startTime时执行调试一次
	 * @param startTime 调度开始时间
	 */
	void schedule(Date startTime);	
	
	/**
	 * 在startTime时执行调试一次
	 * @param startTime 调度开始时间
	  * @param group Quartz SimpleTrigger Group
	 */
	void schedule(Date startTime,String group);
	
	/**
	 * 在startTime时执行调试一次
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 */
	void schedule(String name,Date startTime);
	
	/**
	 * 在startTime时执行调试一次
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	  * @param group Quartz SimpleTrigger Group
	 */
	void schedule(String name, Date startTime,String group);
	
	/**
	 * 在startTime时执行调试，endTime结束执行调度
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 */
	void schedule(Date startTime,Date endTime);	

	/**
	 * 在startTime时执行调试，endTime结束执行调度
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param group Quartz SimpleTrigger Group
	 */
	void schedule(Date startTime, Date endTime,String group) ;
	
	/**
	 * 在startTime时执行调试，endTime结束执行调度
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 */
	void schedule(String name,Date startTime,Date endTime);
	
	/**
	 * 在startTime时执行调试，endTime结束执行调度
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param group Quartz SimpleTrigger Group
	 */
	void schedule(String name, Date startTime, Date endTime,String group) ;
	
	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 */
	void schedule(Date startTime,Date endTime,int repeatCount);	
	
	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 * @param group Quartz SimpleTrigger Group
	 */
	void schedule(Date startTime, Date endTime, int repeatCount,String group);
	
	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 */
	void schedule(String name,Date startTime,Date endTime,int repeatCount);
	
	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 * @param group Quartz SimpleTrigger Group
	 */
	void schedule(String name, Date startTime, Date endTime, int repeatCount,String group);
	
	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 * @param repeatInterval 执行时间隔间
	 */
	void schedule(Date startTime,Date endTime,int repeatCount,long repeatInterval) ;
	
	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 * @param repeatInterval 执行时间隔间
	 *  @param group Quartz SimpleTrigger Group
	 */
	void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval,String group);
	
	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 * @param repeatInterval 执行时间隔间
	 */
	void schedule(String name,Date startTime,Date endTime,int repeatCount,long repeatInterval);
	
	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * @param name Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime 调度结束时间
	 * @param repeatCount 重复执行次数
	 * @param repeatInterval 执行时间隔间
	 *  @param group Quartz SimpleTrigger Group
	 */
	void schedule(String name ,Date startTime, Date endTime, int repeatCount, long repeatInterval, String group);
	
	/**
	 * Trigger 参数,以com.sundoctor.example.Constant常量为键封装的Map
	 * @param map
	 */
	void schedule(Map<String,String> map) ;
	
	/**
	 * 取得所有调度Triggers
	 * @return
	 */
	List<Map<String, Object>> getQrtzTriggers();
	
	/**
	 * 通过trigger的描述来获取trigger
	 * @param description trigger的描述
	 * @return 虽然返回map,但实际上应该只有一个trigger
	 */
	Map<String, Object> getQrtzTriggersByDescription(String description);
	
	/**
	 * 开始所有定时
	 */
	void StartAllTrigger();
	
	/**
	 * 暂停所有定时
	 */
	void pauseAllTrigger();
	/**
	 * 根据名称和组别暂停Tigger
	 * @param triggerName
	 * @param group
	 */
	void pauseTrigger(String triggerName,String group);
	
	/**
	 * 恢复Trigger
	 * @param triggerName
	 * @param group
	 */
	void resumeTrigger(String triggerName,String group);
	
	/**
	 * 删除Trigger
	 * @param triggerName
	 * @param group
	 */
	boolean removeTrigger(String triggerName,String group);
}
