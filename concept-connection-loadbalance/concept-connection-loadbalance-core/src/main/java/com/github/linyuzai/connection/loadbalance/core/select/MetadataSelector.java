package com.github.linyuzai.connection.loadbalance.core.select;

import com.github.linyuzai.connection.loadbalance.core.concept.Connection;

import java.util.Objects;

public class MetadataSelector extends MessageHeaderSelector {

    public MetadataSelector(String name) {
        super(name);
    }

    @Override
    public boolean match(Connection connection, String header) {
        return Objects.equals(connection.getMetadata().get(getName()), header);
    }
}
