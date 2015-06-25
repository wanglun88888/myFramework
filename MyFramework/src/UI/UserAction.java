package UI;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import BO.User.UserBO;

public class UserAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1738912455160480816L;
	private String username;
	private String password;
	private UserBO userBO;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserBO getUserBO() {
		return userBO;
	}
	public void setUserBO(UserBO userBO) {
		this.userBO = userBO;
	}
	@Override
	public Map<String, Object> getResultMap() {
		// TODO Auto-generated method stub
		return resultMap;
	}
	@Override
	public void setResultMap(Map<String, Object> resultMap) {
		// TODO Auto-generated method stub
		this.resultMap = resultMap;
	}
	@Override
	public Integer getResult() {
		// TODO Auto-generated method stub
		return result;
	}
	@Override
	public void setResult(Integer result) {
		this.result = result;
	}
	@Override
	public String getToken() {
		// TODO Auto-generated method stub
		return token;
	}
	@Override
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
	@Override
	public void setMessage(String message) {
		this.message = message;
	}
	public String login(){
		System.out.println("test.login");
		List user = userBO.login(username, password);
		if(user!=null&&user.size()>0){
			Map session = ActionContext.getContext().getSession();
			session.put("LOGINUSER", user.get(0));
			resultMap.put("USER", user.get(0));
			result=1;
		}else result = 0;
		resultMap.put("result", result);
		return "success";
	}
}
