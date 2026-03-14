package org.ikomarchenko.resolver.service;

import org.ikomarchenko.resolver.model.ParsedVarTag;

public final class VarTagParser {

    public ParsedVarTag parse(String rawValue) {
        if (rawValue == null || rawValue.isBlank()) {
            return null;
        }

        String trimmedValue = rawValue.trim();
        String[] parts = trimmedValue.split("\\s+");

        if (parts.length == 0 || parts[0].isBlank()) {
            return null;
        }

        String typeText = parts[0];
        String variableName = null;

        if (parts.length >= 2 && parts[1].startsWith("$")) {
            variableName = parts[1];
        }

        return new ParsedVarTag(typeText, variableName);
    }
}
