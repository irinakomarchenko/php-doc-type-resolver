package org.ikomarchenko.resolver.service;

import org.ikomarchenko.resolver.model.ParsedVarTag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class VarTagParserTest {

    private final VarTagParser parser = new VarTagParser();

    @Test
    void shouldParseSimpleType() {
        ParsedVarTag result = parser.parse("User");

        assertEquals("User", result.getTypeText());
        assertNull(result.getVariableName());
    }

    @Test
    void shouldParseUnionType() {
        ParsedVarTag result = parser.parse("string|int");

        assertEquals("string|int", result.getTypeText());
        assertNull(result.getVariableName());
    }

    @Test
    void shouldParseNamedTag() {
        ParsedVarTag result = parser.parse("Logger $log");

        assertEquals("Logger", result.getTypeText());
        assertEquals("$log", result.getVariableName());
    }

    @Test
    void shouldReturnNullForBlankValue() {
        ParsedVarTag result = parser.parse("   ");

        assertNull(result);
    }
}
