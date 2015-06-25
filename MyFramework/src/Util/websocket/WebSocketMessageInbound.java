package Util.websocket;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WsOutbound;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import Model.dao.UserDAO;
import Model.po.User;


public class WebSocketMessageInbound extends MessageInbound {
	public static ApplicationContext appFactory = new FileSystemXmlApplicationContext(
			new String[] { "classpath:applicationContext.xml" });

	private static UserDAO userDAO = UserDAO
			.getFromApplicationContext(appFactory);
	private static final long serialVersionUID = 1L;
	private User user;
	private User toUser;
	private int toId = 0;
	private String toName;
	public WebSocketMessageInbound(User user,String toId,String toName){
		this.user = user;
		if(toId!=null&&toId.length()>0){
			this.toId = Integer.parseInt(toId);
			this.toUser = userDAO.findById(this.toId);
		}
		this.toName = toName;
	}
	public WebSocketMessageInbound(User user){
		this.user = user;
	}
	public User getUser(){
		return this.user;
	}
	public User getToUser(){
		return this.toUser;
	}

	@Override
	protected void onOpen(WsOutbound outbound) {
		JSONObject result = new JSONObject();
		//将用户信息告诉其他所有人
//		result.element("type","user_join");
//		result.element("user",this.user);
//		WebSocketMessageInboundPool.sendMessage(result.toString());
//		result = new JSONObject();
		//获取在线用户
//		result.element("type","get_online_user");
//		result.element("list", WebSocketMessageInboundPool.getOnlineUser());
//		WebSocketMessageInboundPool.addMessageInbound(this);
//		WebSocketMessageInboundPool.sendMessageToUser(this.user,result.toString());
		//获取对方在线状态
		WebSocketMessageInboundPool.addMessageInbound(this);
		if(toId>0){
			Set<Integer> sets =  WebSocketMessageInboundPool.getOnlineUser();
			String isOnline = "user_offline";
			if(sets.contains(toId)){
				isOnline = "user_online";
			}
			WebSocketMessageInboundPool.sendMessageToUser(this.user,isOnline);
			WebSocketMessageInboundPool.sendMessageToUser(this.toUser,isOnline);
		}
	}
	@Override
	protected void onClose(int status) {
		// 触发关闭事件，在连接池中移除连接  
        int r = WebSocketMessageInboundPool.removeMessageInbound(this);    
        //向在线用户发送当前用户退出的消息  ,为了防止重复发送,先判断其是否在线。
        if(r==1&&this.toId>0){
        	WebSocketMessageInboundPool.sendMessageToUser(this.toUser,"user_leave");
        }
	}
	@Override
	protected void onBinaryMessage(ByteBuffer message) throws IOException {
		throw new UnsupportedOperationException("Binary message not supported.");
	}

	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
		 //向所有在线用户发送消息
		String[] msgs = message.toString().split(",");
		String toId = msgs[0].split(":")[1];
		String msg = msgs[2].split(":")[1];
		String toName = msgs[1].split(":")[1];
		String info = user.getUsername() + "&nbsp;" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + ":&nbsp;" + msg;
        int id = Integer.parseInt(toId);
		User user = userDAO.findById(id);
		int r = WebSocketMessageInboundPool.sendMessageToUser(user,msg);
		/*
		if(r==1){
			this.saveChat(user.getUsername(), toName, msg, user.getId(), id);
		}
		*/
	}
	/*
	protected void saveChat(String fromName,String toName,String content,int fromId,int toId){
		Chat chat = new Chat();
		chat.setContent(content);
		chat.setReceideUserName(toName);
		chat.setSendUserName(fromName);
		chat.setStatus(1);
		chat.setTime(new Date());
		chat.setReceiveUserId(toId);
		chat.setSendUserId(fromId);
		chatDAO.save(chat);
	}
	*/

}
