package org.ikomarchenko.resolver.service;

import org.ikomarchenko.resolver.api.DocTag;
import org.ikomarchenko.resolver.api.PhpDocBlock;
import org.ikomarchenko.resolver.api.PhpType;
import org.ikomarchenko.resolver.api.PhpVariable;
import org.ikomarchenko.resolver.api.TypeFactory;
import org.ikomarchenko.resolver.model.ParsedVarTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DocBlockTypeResolver {

    private static final String VAR_TAG_NAME = "var";
    private static final String MIXED = "mixed";

    private final TypeFactory typeFactory;
    private final VarTagParser varTagParser;

    public DocBlockTypeResolver(TypeFactory typeFactory, VarTagParser varTagParser) {
        this.typeFactory = Objects.requireNonNull(typeFactory);
        this.varTagParser = Objects.requireNonNull(varTagParser);
    }

    public PhpType inferTypeFromDoc(PhpVariable variable) {
        Objects.requireNonNull(variable);

        PhpDocBlock docBlock = variable.getDocBlock();
        if (docBlock == null) {
            return mixedType();
        }

        List<DocTag> varTags = docBlock.getTagsByName(VAR_TAG_NAME);
        if (varTags == null || varTags.isEmpty()) {
            return mixedType();
        }

        ParsedVarTag unnamedCandidate = null;
        String variableName = variable.getName();

        for (DocTag tag : varTags) {
            if (tag == null) {
                continue;
            }


            ParsedVarTag parsedTag = varTagParser.parse(tag.getValue());
            if (parsedTag == null) {
                continue;
            }

            if (parsedTag.isNamed()) {
                if (parsedTag.getVariableName().equals(variableName)) {
                    return buildType(parsedTag.getTypeText());
                }
            } else if (unnamedCandidate == null) {
                unnamedCandidate = parsedTag;
            }
        }

        if (unnamedCandidate != null) {
            return buildType(unnamedCandidate.getTypeText());
        }

        return mixedType();
    }

    private PhpType buildType(String typeText) {
        if (typeText == null || typeText.isBlank()) {
            return mixedType();
        }

        String[] typeParts = typeText.split("\\|");
        List<PhpType> types = new ArrayList<>();

        for (String typePart : typeParts) {
            String normalizedType = typePart.trim();
            if (!normalizedType.isEmpty()) {
                types.add(typeFactory.createType(normalizedType));
            }
        }

        if (types.isEmpty()) {
            return mixedType();
        }

        if (types.size() == 1) {
            return types.get(0);
        }

        return typeFactory.createUnionType(types);
    }

    private PhpType mixedType() {
        return typeFactory.createType(MIXED);
    }
}
