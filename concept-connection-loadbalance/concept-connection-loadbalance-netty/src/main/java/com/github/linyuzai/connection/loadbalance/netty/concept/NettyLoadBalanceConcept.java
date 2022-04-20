package com.github.linyuzai.connection.loadbalance.netty.concept;

import com.github.linyuzai.connection.loadbalance.core.concept.AbstractConnectionLoadBalanceConcept;
import com.github.linyuzai.connection.loadbalance.core.concept.ConnectionFactory;
import com.github.linyuzai.connection.loadbalance.core.event.ConnectionEventPublisher;
import com.github.linyuzai.connection.loadbalance.core.message.MessageFactory;
import com.github.linyuzai.connection.loadbalance.core.proxy.ConnectionProxy;
import com.github.linyuzai.connection.loadbalance.core.select.ConnectionSelector;
import com.github.linyuzai.connection.loadbalance.core.server.ConnectionServerProvider;

import java.util.List;

public class NettyLoadBalanceConcept extends AbstractConnectionLoadBalanceConcept {
    public NettyLoadBalanceConcept(ConnectionServerProvider connectionServerProvider, ConnectionProxy connectionProxy, List<ConnectionFactory> connectionFactories, List<ConnectionSelector> connectionSelectors, List<MessageFactory> messageFactories, ConnectionEventPublisher eventPublisher) {
        super(connectionServerProvider, connectionProxy, connectionFactories, connectionSelectors, messageFactories, eventPublisher);
    }
}
