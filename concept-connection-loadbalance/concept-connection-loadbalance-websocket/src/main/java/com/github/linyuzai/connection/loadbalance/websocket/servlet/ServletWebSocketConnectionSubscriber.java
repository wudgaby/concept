package com.github.linyuzai.connection.loadbalance.websocket.servlet;

import com.github.linyuzai.connection.loadbalance.core.concept.Connection;
import com.github.linyuzai.connection.loadbalance.core.server.ConnectionServer;
import com.github.linyuzai.connection.loadbalance.websocket.concept.WebSocketConnectionSubscriber;
import com.github.linyuzai.connection.loadbalance.websocket.concept.WebSocketLoadBalanceConcept;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.net.URI;
import java.util.function.Consumer;

public class ServletWebSocketConnectionSubscriber extends WebSocketConnectionSubscriber {

    @Override
    public void doSubscribe(ConnectionServer server, WebSocketLoadBalanceConcept concept, Consumer<Connection> consumer) {
        StandardWebSocketClient client = new StandardWebSocketClient();
        ServletWebSocketSubscriberHandler handler = new ServletWebSocketSubscriberHandler(concept);
        URI uri = getUri(server);
        WebSocketConnectionManager manager =
                new WebSocketConnectionManager(client, handler, uri.toString());
        manager.startInternal();
        WebSocketSession session = handler.getSession();
        ServletWebSocketConnection connection =
                new ServletWebSocketConnection(session, Connection.Type.SUBSCRIBER);
        connection.getMetadata().put(ConnectionServer.class, server);
        setDefaultMessageEncoder(connection);
        setDefaultMessageDecoder(connection);
        consumer.accept(connection);
    }

    @Override
    public String getType() {
        return "servlet";
    }
}
