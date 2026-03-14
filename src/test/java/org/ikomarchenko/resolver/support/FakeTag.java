package org.ikomarchenko.resolver.support;

import org.ikomarchenko.resolver.api.DocTag;

public class FakeTag implements DocTag {

    private final String value;

    public FakeTag(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
