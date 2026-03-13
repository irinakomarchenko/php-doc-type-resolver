package org.ikomarchenko.resolver.service;

import org.ikomarchenko.resolver.api.PhpType;
import org.ikomarchenko.resolver.api.PhpVariable;
import org.ikomarchenko.resolver.api.TypeFactory;

import java.util.Objects;

public class DocBlockTypeResolver {

    private final TypeFactory typeFactory;

    public DocBlockTypeResolver(TypeFactory typeFactory) {
        this.typeFactory = Objects.requireNonNull(typeFactory);
    }

    public PhpType inferTypeFromDoc(PhpVariable variable) {
        Objects.requireNonNull(variable);

        return typeFactory.createType("mixed");

    }
}
