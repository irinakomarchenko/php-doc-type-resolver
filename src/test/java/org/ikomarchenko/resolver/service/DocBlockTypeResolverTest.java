package org.ikomarchenko.resolver.service;

import org.ikomarchenko.resolver.api.DocTag;
import org.ikomarchenko.resolver.api.PhpType;
import org.ikomarchenko.resolver.api.PhpVariable;
import org.ikomarchenko.resolver.support.FakeDocBlock;
import org.ikomarchenko.resolver.support.FakeTag;
import org.ikomarchenko.resolver.support.FakeTypeFactory;
import org.ikomarchenko.resolver.support.FakeVariable;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DocBlockTypeResolverTest {

    private final DocBlockTypeResolver resolver =
            new DocBlockTypeResolver(new FakeTypeFactory(), new VarTagParser());

    @Test
    void shouldResolveStandardType() {
        PhpVariable variable = variable(
                "$user",
                tag("User")
        );

        PhpType type = resolver.inferTypeFromDoc(variable);

        assertEquals("User", type.toString());
    }

    @Test
    void shouldResolveUnionType() {
        PhpVariable variable = variable(
                "$id",
                tag("string|int")
        );

        PhpType type = resolver.inferTypeFromDoc(variable);

        assertEquals("string|int", type.toString());
    }

    @Test
    void shouldResolveNamedTag() {
        PhpVariable variable = variable(
                "$log",
                tag("Logger $log")
        );

        PhpType type = resolver.inferTypeFromDoc(variable);

        assertEquals("Logger", type.toString());
    }

    @Test
    void shouldReturnMixedWhenTagDoesNotMatchVariable() {
        PhpVariable variable = variable(
                "$guest",
                tag("Admin $adm")
        );

        PhpType type = resolver.inferTypeFromDoc(variable);

        assertEquals("mixed", type.toString());
    }

    @Test
    void shouldResolveCorrectTypeFromMultipleTags() {
        PhpVariable variable = variable(
                "$name",
                tag("int $id"),
                tag("string $name")
        );

        PhpType type = resolver.inferTypeFromDoc(variable);

        assertEquals("string", type.toString());
    }

    @Test
    void shouldReturnMixedWhenDocBlockIsMissing() {
        PhpVariable variable = new FakeVariable("$user", null);

        PhpType type = resolver.inferTypeFromDoc(variable);

        assertEquals("mixed", type.toString());
    }

    @Test
    void shouldUseUnnamedTagWhenNoNamedTagMatches() {
        PhpVariable variable = variable(
                "$user",
                tag("User")
        );

        PhpType type = resolver.inferTypeFromDoc(variable);

        assertEquals("User", type.toString());
    }

    private PhpVariable variable(String name, DocTag... tags) {
        return new FakeVariable(name, new FakeDocBlock(List.of(tags)));
    }

    private DocTag tag(String value) {
        return new FakeTag(value);
    }
}
