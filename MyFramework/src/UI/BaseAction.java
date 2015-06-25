package UI;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {
	/**
	 * 
	 */
	public Integer result; // 处理结果
	public String message; // 信息
	public String token; // 令牌
	public Map<String,Object> resultMap =new HashMap<String, Object>();

	public abstract Map<String, Object> getResultMap();

	public abstract void setResultMap(Map<String, Object> resultMap);

	protected Integer intParam(String param)
	{
		int result=Integer.valueOf(ServletActionContext.getRequest().getParameter(param));
		if(result==-1)
			return null;
		else {
				return result;
			}
		
	}
	
	protected String strParam(String param)
	{
		return String.valueOf(ServletActionContext.getRequest().getParameter(param));
		
	}
	
	protected Double doubleParam(String param)
	{
		return Double.valueOf(ServletActionContext.getRequest().getParameter(param));
		
	}
	// CSON:

	/**
	 * @return the result
	 */
	public abstract Integer getResult();

	/**
	 * @param result
	 *            the result to set
	 */
	public abstract void setResult(Integer result);

	/**
	 * @return the token
	 */
	public abstract String getToken();

	/**
	 * @param token
	 *            the token to set
	 */
	public abstract void setToken(String token);

	/**
	 * @return the error
	 */
	public abstract String getMessage();

	/**
	 * @param error
	 *            the error to set
	 */
	public abstract void setMessage(String message);

}
