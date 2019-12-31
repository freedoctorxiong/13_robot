package com.yscoco.robot.websocket;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/websocket/test/zky")
@Component
@Slf4j
public class MovieWebSocket {


    private static ConcurrentHashMap<String, Session> map = new ConcurrentHashMap<String, Session>();

    //private static ConcurrentHashMap<String, List<Session>> map2 = new ConcurrentHashMap<String, List<Session>>();

    /**
     * 新的WebSocket请求开启
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("一个WebSocket开启" + session.getId());
        map.put(session.getId(), session);
        System.out.println(map.values().size());
        try {
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @OnMessage
    public void OnMessage(Session session, final String message) throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(message);
        log.info(jsonObject+"-----------jsonObject-----------");
        /*int handle = jsonObject.getInteger("handle");
        log.info(handle + "------------handle----------");
        String tableNumber = jsonObject.getString("tableNumber");
        log.info(tableNumber + "------------tableNumber----------");
        switch (handle) {
            case 0:
                //扫码加入组
                if (map2.contains(tableNumber)) {
                    List<Session> str = map2.get(tableNumber);
                    str.add(session);
                    map2.put(tableNumber, str);
                } else {
                    ArrayList<Session> str = new ArrayList<Session>();
                    str.add(session);
                    map2.put(tableNumber, str);
                }
                log.info(map2.size()+"------------map.size------------");
                break;
            case 1:
                //点菜
                List<Session> str = map2.get(tableNumber);
                log.info(str.size()+"---------str----------------");
                for (Session s : str) {
                    s.getBasicRemote().sendText(message);
                }
                break;

            default:
                //买单
        }*/
        session.getBasicRemote().sendText(message);
        System.out.println("一个WebSocket发送消息" + session.getId() + "," + message);
    }

    /**
     * WebSocket请求关闭
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("一个WebSocket关闭" + session.getId());
        try {
            map.remove(session.getId());
            session.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable thr, Session session) {
        map.remove(session.getId());
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.error("websocket error." + thr);
    }

    public static void sendMessage(String message) throws IOException {
        Collection<Session> sessionList = map.values();
        for (Session session : sessionList) {
            System.out.println(session.getId());
            session.getBasicRemote().sendText(message);
        }
    }
}