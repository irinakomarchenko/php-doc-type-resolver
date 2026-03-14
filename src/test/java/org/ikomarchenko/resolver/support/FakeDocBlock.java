package org.ikomarchenko.resolver.support;

import org.ikomarchenko.resolver.api.DocTag;
import org.ikomarchenko.resolver.api.PhpDocBlock;

import java.util.List;

public class FakeDocBlock implements PhpDocBlock {

    private final List<DocTag> tags;

    public FakeDocBlock(List<DocTag> tags) {
        this.tags = tags;
    }

    @Override
    public List<DocTag> getTagsByName(String tagName) {
        return tags;
    }
}
