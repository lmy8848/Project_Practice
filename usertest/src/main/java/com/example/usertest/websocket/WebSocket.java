package com.example.usertest.websocket;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * @author NJQ-PC
 * @date 2022/10/17 19:20
 * description:
 */
@Component
@ServerEndpoint(value = "/websocket/{username}")
public class WebSocket {
    /**
     * @author NJQ-PC
     * @date 2022/10/17 19:23
     * description:   日志
     */
    private static final Logger log = LoggerFactory.getLogger(WebSocket.class);

    //在线人数
    public static int onlineCount = 0;
    /**
     * @author NJQ-PC
     * @date 2022/10/17 19:22
     * description:   客户端连接对象
     */
    public static Map<String, WebSocket> clients = new HashMap<String, WebSocket>();
    /**
     * @author NJQ-PC
     * @date 2022/10/17 19:22
     * description:   连接会话
     */
    public Session session;
    /**
     * @author NJQ-PC
     * @date 2022/10/17 19:23
     * description:   用户名
     */
    public String  username;
    //获取用户名
    public String getUsername() {
        return username;
    }

    /**
     * @author NJQ-PC
     * @date 2022/10/17 19:25
     * description:
     * @param username: 用户名
     * @param session:  会话对象
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {
        this.username = username;
        this.session = session;
        //添加在线人数
        addOnlineCount();
        clients.put(session.getId(), this);
        //输出日志
        log.warn("已连接:"+username);
    }

    @OnClose
    public void onClose() throws IOException {
        //连接状态更新
        log.warn("断开"+username);
        //移除用户websocket对象
        clients.remove(session.getId());
        //在线人数更新
        subOnlineCount();
    }

    //收到客户端消息后调用的方法
    /**
     * @author NJQ-PC
     * @date 2022/10/17 19:31
     * description:
     * @param message: Server到client 消息内容
     */

    @OnMessage
    public void onMessage(String message) throws IOException {
        //测试是否连接
        if(message.equals("ping")) {
            this.session.getAsyncRemote().sendText("pang");
        }else if("close".equals(message)) {
            //关闭连接
            onClose();
            this.session.close();
        }else {
//            log.warn(this.username+"收到 "+message);
            //发送消息内容JSON格式
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username",getUsername());
            jsonObject.put("message",message);
            sendMessageTo(jsonObject);
        }
    }
    /**
     * @author NJQ-PC
     * @date 2022/10/17 19:34
     * description:
     * @param session: 会话对象
     * @param error:  websocket会话错误抓获
     */
    @OnError
    public void onError(Session session, Throwable error) {
        //打印异常
        error.printStackTrace();
    }

    /**
     * @author NJQ-PC
     * @date 2022/10/17 19:35
     * description:
     * @param json:  消息内容发送client
     */


    public static void sendMessageTo(JSONObject json) {
//        log.warn("sendMsg:"+json);
        //向在线所有人发送消息
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(json.toJSONString());
        }
    }

    public static void clearMessageFromOpen(String message) {
        try {
            JSONObject json = JSONObject.parseObject(message);
            if(json.get("uuid") != null) {
            }
        } catch (Exception e) {
            log.error("Error:"+e.getMessage());
        }
    }

    //处理高并发
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }
    //获取客户对象
    public static synchronized Map<String, WebSocket> getClients() {
        return clients;
    }

}