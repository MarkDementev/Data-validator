### CI status:
[![Actions Status](https://github.com/MarkDementev/java-project-78/workflows/Java%20CI/badge.svg)](https://github.com/MarkDementev/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/87268fe839c368dc2811/maintainability)](https://codeclimate.com/github/MarkDementev/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/87268fe839c368dc2811/test_coverage)](https://codeclimate.com/github/MarkDementev/java-project-78/test_coverage)
### Hexlet tests and linter status:
[![Actions Status](https://github.com/MarkDementev/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/MarkDementev/java-project-78/actions)

# Overview

This project contains functionality for strings, numbers and maps validation. Map validation provides structure verification functionality.

## How to use:

```sh
//create Validator object
Validator validator = new Validator();
```
```sh
//choose specific validation schema and create its object
StringSchema schema = validator.string();
NumberSchema schema = validator.number();
MapSchema schema = validator.map();
```
```sh
//form methods calling chain to fill schema by validation checks
//for example:
schema.required().minLength(5);
```
```sh
//use isValid(Object validatingObject) to validate
schema.isValid("Dementev"); // true
```

### String validation methods:
required() — adds a constraint to the schema that prevents null or an empty string from being used as a value. String data type required;

minLength(int minLength) — adds a minimum length validation for the string. The string must be equal to or longer than the specified number;

contains(String textToContains) — adds a restriction on the content of the string. The string must contain a specific substring.

### Integer validation methods:
required() — adds a constraint to the schema that prevents null or an empty string from being used as a value. Integer data type required;

positive() — adds a constraint - the number must be positive;

range(int newStartRange, int newEndRange) — adds a valid range that the value of a number must fall within, including bounds.

### Map validation methods:
required() — adds a constraint to the schema that prevents null from being used as a value. Map data type required;

sizeof() — adds a limit to the size of the map. The number of key-value pairs in the Map object must be equal to the specified;

shape(Map<String, BaseSchema> newSchemas) - used to define the properties of a Map object and create a schema to validate their values.

## Validation examples:

```sh
Validator v = new Validator();
```
```sh
// Strings validation:
StringSchema schema = v.string().required();

schema.isValid("what does the fox say"); // true
schema.isValid(""); // false
```
```sh
// Numbers validation:
NumberSchema schema = v.number().required().positive();

schema.isValid(-10); // false
schema.isValid(10); // true
```
```sh
// Map validation with structure verification functionality:
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "");
human2.put("age", null);
schema.isValid(human1); // false
```

