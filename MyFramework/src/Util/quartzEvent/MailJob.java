package Util.quartzEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import BO.User.*;


public class MailJob {

	private static final long serialVersionUID = 122323233244334343L;
	private static final Logger logger = LoggerFactory.getLogger(MailJob.class);
	private static UserBO userBO;



	public UserBO getUserBO() {
		return userBO;
	}

	public void setUserBO(UserBO userBO) {
		MailJob.userBO = userBO;
	}

	public void mailEventLog() {
		// 设置查询事件日志的日期，从前一天的00:00:00到23:59:59
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(new Date());
		endDate.add(endDate.DAY_OF_MONTH, 1);
		endDate.set(endDate.HOUR_OF_DAY, 23);
		endDate.set(endDate.MINUTE, 59);
		endDate.set(endDate.SECOND, 59);

		Calendar startDate = Calendar.getInstance();
		startDate.setTime(new Date());
		startDate.add(startDate.DAY_OF_MONTH, -1);
		startDate.set(startDate.HOUR_OF_DAY, 0);
		startDate.set(startDate.MINUTE, 0);
		startDate.set(startDate.SECOND, 0);

		String attachFilePath = "";
		List<String> mailList = null;
		/*
		for (int i = 0; i < mailList.size(); i++) {
			// 生成事件日志excel
			attachFilePath = eventLogBO.GetEventLogFile(enterpriseList.get(i)
					.getEnterpriseId(), startDate, endDate, 0, 0, false);
			System.out.println(attachFilePath);

			// 设置email的详细信息
			MailSenderInfo mailSenderInfo = new MailSenderInfo();
			mailSenderInfo.setMailServerHost(enterpriseList.get(i)
					.getSmtpserver());
			mailSenderInfo.setMailServerPort(enterpriseList.get(i)
					.getSmtpport());
			mailSenderInfo
					.setFromAddress(enterpriseList.get(i).getAdminEmail());


			mailSenderInfo.setUserName(enterpriseList.get(i).getAdminEmail());
			mailSenderInfo
					.setPassword(enterpriseList.get(i).getAdminEmailPwd());
			mailSenderInfo.setValidate(true);

			mailSenderInfo.setSubject("事件日志报表");
			mailSenderInfo.setContent("请见附件");
			mailSenderInfo.setAttachFileNames(new String[] { attachFilePath });
			//如果附件有内容
			if (attachFilePath != null) {

				List<User> userList = null;//将事件日志发送给相应企业的所有的企业管理员
				try {
					userList = userBO
							.searchTopAdminUserByEnterpriseID(enterpriseList
									.get(i).getEnterpriseId());
				} catch (BOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int j = 0; j < userList.size(); j++) {
					mailSenderInfo.setToAddress(new String[] { userList.get(j)
							.getEmail() });
					System.out.println(MailSender.sendTextMail(mailSenderInfo));
				}
			}
		}
		*/
	}

	public void sendMail() {
		// 这里执行定时调度业务
		System.out.println("邮件发送事件日志！" + new Date());
		this.mailEventLog();
	}

}
