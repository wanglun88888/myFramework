package Util.quartzEvent;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class CommandQuartzJob implements Serializable {

	private static final long serialVersionUID = 122323233244334343L;
	private static final Logger logger = LoggerFactory
			.getLogger(CommandQuartzJob.class);

/*	public static DeviceBO deviceBO;// 必须保存为静态，否则不能被存在序列化后的文件中。但get和set方法不能是
	
	public DeviceBO getDeviceBO() {
		return deviceBO;
	}

	public void setDeviceBO(DeviceBO deviceBOs) {
		deviceBO = deviceBOs;
	}
*/
	
	

	/**
	 * 执行设备策略。先获取该策略下的所有要执行的属性和属性值，在对需要执行的所有设备循环执行控制命令。
	 * @param strategyId 执行设备策略的大策略ID
	 * @param devStrategyName 设备策略名。保证在大策略下的唯一性。
	 * @return
	 * @throws InterruptedException 
	 */
	public String quartzExecuteCommand(int strategyId , String devStrategyName) throws InterruptedException {
		System.out.println("test quartz"+new Date()+strategyId+"   "+devStrategyName);
		return "success";
	}
}
