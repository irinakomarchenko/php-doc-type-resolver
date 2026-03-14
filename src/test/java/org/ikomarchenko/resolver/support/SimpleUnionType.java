package org.ikomarchenko.resolver.support;

import org.ikomarchenko.resolver.api.PhpType;
import org.ikomarchenko.resolver.api.UnionType;

import java.util.List;
import java.util.stream.Collectors;

public class SimpleUnionType extends SimpleType implements UnionType {

    public SimpleUnionType(List<PhpType> types) {
        super(types.stream()
                .map(Object::toString)
                .collect(Collectors.joining("|")));
    }
}
