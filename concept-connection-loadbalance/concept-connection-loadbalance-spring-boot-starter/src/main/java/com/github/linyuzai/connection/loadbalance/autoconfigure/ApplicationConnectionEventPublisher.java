package com.github.linyuzai.connection.loadbalance.autoconfigure;

import com.github.linyuzai.connection.loadbalance.core.event.DefaultConnectionEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@AllArgsConstructor
public class ApplicationConnectionEventPublisher extends DefaultConnectionEventPublisher {

    private ApplicationEventPublisher publisher;

    @Override
    public void publish(Object event) {
        super.publish(event);
        try {
            publisher.publishEvent(event);
        } catch (Throwable e) {
            handlePublishError(event, e);
        }
    }
}
