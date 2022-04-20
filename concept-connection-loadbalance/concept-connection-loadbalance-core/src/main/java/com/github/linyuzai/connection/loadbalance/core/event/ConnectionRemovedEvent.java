package com.github.linyuzai.connection.loadbalance.core.event;

import com.github.linyuzai.connection.loadbalance.core.concept.Connection;

public class ConnectionRemovedEvent extends AbstractConnectionEvent {

    public ConnectionRemovedEvent(Connection connection) {
        super(connection);
    }
}
