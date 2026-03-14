package org.ikomarchenko.resolver.support;

import org.ikomarchenko.resolver.api.PhpDocBlock;
import org.ikomarchenko.resolver.api.PhpVariable;

public class FakeVariable implements PhpVariable {

    private final String name;
    private final PhpDocBlock docBlock;

    public FakeVariable(String name, PhpDocBlock docBlock) {
        this.name = name;
        this.docBlock = docBlock;
    }

    @Override
    public PhpDocBlock getDocBlock() {
        return docBlock;
    }

    @Override
    public String getName() {
        return name;
    }
}
