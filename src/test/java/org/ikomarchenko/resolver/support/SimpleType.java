package org.ikomarchenko.resolver.support;

import org.ikomarchenko.resolver.api.PhpType;

public class SimpleType implements PhpType {
    private final String name;

    public SimpleType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
