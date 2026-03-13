package org.ikomarchenko.resolver.api;

import java.util.List;

public interface TypeFactory {
    PhpType createType(String type);
    UnionType createUnionType(List<PhpType> types);
}
