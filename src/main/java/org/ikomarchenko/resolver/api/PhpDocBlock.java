package org.ikomarchenko.resolver.api;

import java.util.List;

public interface PhpDocBlock {
    List<DocTag> getTagsByName(String tagName);
}
