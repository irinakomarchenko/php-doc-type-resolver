# PHP Doc Type Resolver

This project implements a lightweight static analysis component that determines the type of a PHP variable using its `@var` documentation block.

The resolver inspects the PHPDoc associated with a variable and analyzes all `@var` tags found in that doc block.

## Resolution Logic

First, the resolver retrieves the doc block of the inspected variable.  
If the variable has no doc block, the resolver returns `mixed`.

Next, all `@var` tags are extracted and parsed.  
Each tag contains:

- a declared type (`User`, `string|int`, etc.)
- an optional variable name (`$log`)

If a tag specifies a variable name, it is applied only when it matches the inspected variable.

If multiple tags exist, the resolver prefers the tag that explicitly matches the variable name.  
If no named tag matches, an unnamed tag may be used as a fallback.

If no suitable tag is found, the resolver returns `mixed`.

## Components

The implementation consists of three main classes.

`DocBlockTypeResolver` handles the main type resolution logic.

`VarTagParser` parses raw `@var` tag values.

`ParsedVarTag` represents parsed tag data.

Unit tests verify both parsing and resolution behavior.

## Running Tests

The project uses Maven and JUnit.

Run all tests:
```bash
mvn test
```
## Continuous Integration

The project includes a minimal CI pipeline using GitHub Actions.

On every push and pull request, the workflow:

- checks out the repository
- installs Java
- runs `mvn test`

This ensures that all unit tests pass before changes are merged.
