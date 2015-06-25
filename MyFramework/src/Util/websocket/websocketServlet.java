package Util.websocket;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.ServerEndpoint;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

import Model.po.User;

import com.opensymphony.xwork2.ActionContext;



@ServerEndpoint(value="/websocket.servlet")
public class websocketServlet extends WebSocketServlet {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public User getUser(HttpServletRequest request){
/*		Map session = ActionContext.getContext().getSession();
		System.out.println("session=   "+session);
        User user = (Model.po.User) session.get("LOGINUSER");
        
        */
		User user = (User) request.getSession().getAttribute("LOGINUSER");
		System.out.println("user=   "+user);
		return user;
	}
	
	@Override
	protected StreamInbound createWebSocketInbound(String arg0,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("building..");
		String toId = request.getParameter("toId");
		String toName = request.getParameter("toName");
		if(toId==null&&toName==null){
			return new WebSocketMessageInbound(this.getUser(request));
		}
		return new WebSocketMessageInbound(this.getUser(request),toId,toName);
	}

}
