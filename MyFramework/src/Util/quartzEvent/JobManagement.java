package Util.quartzEvent;



import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.WebUtils;

import BO.Quartz.SchedulerService;
import UI.BaseAction;


public class JobManagement extends BaseAction{
	
	/**
	 * Servlet implementation class JobProcessServlet
	 */
		private static final long serialVersionUID = 1L;
		private ApplicationContext ctx;
		private SchedulerService schedulerService;
		private String triggerName;
		private String cronExpression;
		private String group;
		private String timeVal;//每val执行一次
		private String timeType;//val的单位，秒，分，小时
		private List<String> jobList;
		private List<Map<String, Object>> triggerList;
		private int hourTiming;
		private int minuteTiming;
		private boolean weekdays;
		private boolean weekends;
		
		public int getHourTiming() {
			return hourTiming;
		}

		public void setHourTiming(int hourTiming) {
			this.hourTiming = hourTiming;
		}

		public int getMinuteTiming() {
			return minuteTiming;
		}

		public void setMinuteTiming(int minuteTiming) {
			this.minuteTiming = minuteTiming;
		}

		public boolean isWeekdays() {
			return weekdays;
		}

		public void setWeekdays(boolean weekdays) {
			this.weekdays = weekdays;
		}

		public boolean isWeekends() {
			return weekends;
		}

		public void setWeekends(boolean weekends) {
			this.weekends = weekends;
		}

		public List getJobList() {
			return jobList;
		}

		public void setJobList(List jobList) {
			this.jobList = jobList;
		}

		public List<Map<String, Object>> getTriggerList() {
			return triggerList;
		}
		
		public void setTriggerList(List<Map<String, Object>> triggerList) {
			this.triggerList = triggerList;
		}

		/**
		 * @see HttpServlet#HttpServlet()
		 */
		public JobManagement() {
			super();
		}
		
		public SchedulerService getSchedulerService() {
			return schedulerService;
		}

		public void setSchedulerService(SchedulerService schedulerService) {
			this.schedulerService = schedulerService;
		}

		public String getTriggerName() {
			return triggerName;
		}

		public void setTriggerName(String triggerName) {
			this.triggerName = triggerName;
		}

		public String getCronExpression() {
			return cronExpression;
		}

		public void setCronExpression(String cronExpression) {
			this.cronExpression = cronExpression;
		}

		public String getGroup() {
			return group;
		}

		public void setGroup(String group) {
			this.group = group;
		}

		public String getTimeVal() {
			return timeVal;
		}

		public void setTimeVal(String timeVal) {
			this.timeVal = timeVal;
		}

		public String getTimeType() {
			return timeType;
		}

		public void setTimeType(String timeType) {
			this.timeType = timeType;
		}
		@Override
		public Map<String, Object> getResultMap() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setResultMap(Map<String, Object> resultMap) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Integer getResult() {
			// TODO Auto-generated method stub
			return result;
		}

		@Override
		public void setResult(Integer result) {
			// TODO Auto-generated method stub
			this.result = result;
		}

		@Override
		public String getToken() {
			// TODO Auto-generated method stub
			return token;
		}

		@Override
		public void setToken(String token) {
			// TODO Auto-generated method stub
			this.token = token;
		}

		/**
		 * 将接收到的时间转换成cronTrigger认可的表达式
		 * int minuteTiming 分钟
		 * int hourTiming 小时
		 * boolean weekdays 工作日
		 * boolean weekends 周末
		 * @return
		 */
		public String valueCronExpression() {
			String cronString = "0 ";
			cronString += String.valueOf(minuteTiming)+" ";
			cronString += String.valueOf(hourTiming)+" ? * ";
			
			if(weekdays&&weekends){
				cronString +="*";
			}else if(weekends){
				cronString +="1,7";
			}else{
				cronString +="2-6";
			} 
			return cronString;
		}
		/**
		 * 添加Simple Trigger
		 * 
		 * @param request
		 * @param response
		*/ 
		public void addSimpleTrigger(HttpServletRequest request, HttpServletResponse response) throws IOException {
			/**
			// 获取界面以p_参数
			Map<String, Object> filterMap = WebUtils.getParametersStartingWith(request, "p_");//获取重复次数p_repeatCount，重复间隔p_repeatInterval
		
			if (StringUtils.isEmpty(filterMap.get(Constant.STARTTIME))) {
				response.getWriter().println(1);
			}

			// 添加任务调试
			schedulerService.schedule(filterMap);

			// response.setContentType("text/xml;charset=utf-8");
			response.getWriter().println(0);
*/
		}

		/**
		 * 根据Cron表达式添加Cron Trigger，
		 * 
		 * @param triggerName 定时器名称
		 * @param cronExpression 定时表达式
		 * @return result:1,添加成功;0,添加失败
		 */
		public String addCronTriggerByExpression() throws Exception {

			// 获取界面以参数HttpServletRequest request, HttpServletResponse response
//			triggerName = request.getParameter("triggerName");
//			cronExpression = request.getParameter("cronExpression");
			if (StringUtils.isEmpty(triggerName) || StringUtils.isEmpty(cronExpression)) {
				result = 0;
			}
//			jobList = null;
//			for(int i=0; i<2;i++){
//				jobList.add(String.valueOf(i));
//			}
			//将jobList换成String 类型
			String jobs = "1,2,3";
//			for(int i =0;i<jobList.size();i++){
//				jobs += jobList.get(i).toString()+",";
//			}
			// 添加任务调试
			cronExpression = this.valueCronExpression();
			cronExpression = "0/10 * * ? * *";
			schedulerService.schedule(triggerName, cronExpression,jobs);
			result = 1;
			return SUCCESS;
			// response.setContentType("text/xml;charset=utf-8");
		}

		/**
		 * 根据类型添加CronTrigger，
		 * @param triggerName 定时器名称
		 * @param timeType 定时类型:每（秒、分、时、天）执行一次
		 * @param timeVal 间隔时间
		 * @return result:1,添加成功;0,添加失败
		 */
		public String addCronTriggerByType() throws Exception {

			// 获取界面以参数
//			triggerName = request.getParameter("triggerName");
//			timeVal = request.getParameter("val");
//			timeType = request.getParameter("selType");
			if (StringUtils.isEmpty(triggerName) || StringUtils.isEmpty(timeVal) || NumberUtils.toLong(timeVal) < 0 || NumberUtils.toLong(timeVal) > 59) {
				triggerName=UUID.randomUUID().toString();
			}
			
			String expression = null;
			if (StringUtils.equals(timeType, "second")) {
				// 每多秒执行一次
				expression = "0/" + timeVal + " * * ? * * *";
			} else if (StringUtils.equals(timeType, "minute")) {
				// 每多少分执行一次
				expression = "0 0/" + timeVal + " * ? * * *";
			}else if (StringUtils.equals(timeType, "hour")) {
				// 每多少分执行一次
				expression = "0 0 0/"+ timeVal + "* ? * *";
			}
			else if (StringUtils.equals(timeType, "day")) {
				// 每多少分执行一次
				expression = "0 0 0 0/" + timeVal + " * ? *";
			}

			// 添加任务调试
			schedulerService.schedule(triggerName, expression);

			// response.setContentType("text/xml;charset=utf-8");
			result = 1;
			return SUCCESS;

		}
 
		/**
		 * 取得所有Trigger
		 * @return triggerList 所有Trigger列表
		 * @throws ServletException
		 * @throws IOException
		 */
		public String getQrtzTriggers() throws Exception {
			triggerList = this.schedulerService.getQrtzTriggers();
			result = 1;
			return SUCCESS;
		}
		
		/**
		 * 根据名称和组别暂停Tigger
		 * 
		 * @param triggerName
		 * @param group
		 * @return result:1,暂停成功；0,暂停失败
		 * @throws IOException
		 */
		public String pauseTrigger() throws Exception {
			// request.setCharacterEncoding("UTF-8");
			schedulerService.pauseTrigger(triggerName, group);
			result = 1;
			return SUCCESS;
		}

		/**
		 * 根据名称和组别重新开始Tigger
		 * 
		 * @param triggerName 
		 * @param group
		 * @return result:1,重启成功;0,重启失败
		 * @throws IOException
		 */
		public String resumeTrigger() throws Exception {
			// request.setCharacterEncoding("UTF-8");
			schedulerService.resumeTrigger(triggerName, group);
			result = 1;
			return SUCCESS;
		}

		/**
		 * 根据名称和组别删除Tigger
		 * 
		 * @param triggerName
		 * @param group
		 * @return result:1,删除成功;0,删除失败
		 * @throws IOException
		 * 
		 */

		public String removeTrigdger() throws Exception {
			// request.setCharacterEncoding("UTF-8");
			
			boolean rs = schedulerService.removeTrigger(triggerName, group);
			if (rs) {
				result = 1;
			} else {
				result = 0;
			}
			return SUCCESS;
		}
		public static void main(String[] args) {
			ApplicationContext springContext = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"});
			SchedulerService schedulerService = (SchedulerService)springContext.getBean("schedulerService");
			schedulerService.schedule("3", "0/10 * * ? * * *", "3,allen");
		}

		@Override
		public String getMessage() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setMessage(String message) {
			// TODO Auto-generated method stub
			
		}

}
