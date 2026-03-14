package org.ikomarchenko.resolver.model;

import java.util.Objects;

public final class ParsedVarTag {

    private final String typeText;
    private final String variableName;

    public ParsedVarTag(String typeText, String variableName) {
        this.typeText = Objects.requireNonNull(typeText);
        this.variableName = variableName;
    }

    public String getTypeText() {
        return typeText;
    }

    public String getVariableName() {
        return variableName;
    }

    public boolean isNamed() {
        return variableName != null && !variableName.isBlank();
    }
}
