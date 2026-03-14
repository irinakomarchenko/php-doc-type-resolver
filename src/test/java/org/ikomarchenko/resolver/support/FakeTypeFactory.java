package org.ikomarchenko.resolver.support;

import org.ikomarchenko.resolver.api.PhpType;
import org.ikomarchenko.resolver.api.TypeFactory;
import org.ikomarchenko.resolver.api.UnionType;

import java.util.List;

public class FakeTypeFactory implements TypeFactory {

    @Override
    public PhpType createType(String typeName) {
        return new SimpleType(typeName);
    }

    @Override
    public UnionType createUnionType(List<PhpType> types) {
        return new SimpleUnionType(types);
    }
}
