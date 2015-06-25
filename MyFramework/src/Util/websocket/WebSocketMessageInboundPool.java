package Util.websocket;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Model.po.User;



public class WebSocketMessageInboundPool {
	
	//保存连接的MAP容器  
    private static final Map<Integer,WebSocketMessageInbound > connections = new HashMap<Integer,WebSocketMessageInbound>();  
      
    //向连接池中添加连接  
    public static void addMessageInbound(WebSocketMessageInbound inbound){  
        //添加连接  
        System.out.println("user : " + inbound.getUser().getUsername() + " join..");  
        connections.put(inbound.getUser().getId(), inbound);  
    }  
      
    //获取所有的在线用户  
    public static Set<Integer> getOnlineUser(){  
        return connections.keySet();  
    }  
      
    public static int removeMessageInbound(WebSocketMessageInbound inbound){  
        int result = 0;
    	//移除连接  
        System.out.println("user : " + inbound.getUser().getUsername() + " exit..");  
        if(connections.containsKey(inbound.getUser().getId())){
        	connections.remove(inbound.getUser().getId());  
        	result = 1;
        }
        return result ;
    }  
      
    public static int sendMessageToUser(User user,String message){  
        int result = 0;
    	try {  
            //向特定的用户发送数据  
            System.out.println("send message to user : " + user.getUsername() + " ,message content : " + message);  
            WebSocketMessageInbound inbound = connections.get(user.getId());  
            if(inbound != null){  
                inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));  
                inbound.getWsOutbound().flush();
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
    	result = 1;
    	return result;
    }  
      
    //向所有的用户发送消息  
    public static void sendMessage(String message){  
        try {  
            Set<Integer> keySet = connections.keySet();  
            for (int key : keySet) {  
                WebSocketMessageInbound inbound = connections.get(key);  
                if(inbound != null){  
                    System.out.println("send message to user : " + inbound.getUser().getUsername() + " ,message content : " + message);  
                    inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));  
                    inbound.getWsOutbound().flush();
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
