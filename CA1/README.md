# IE - CA1


# About Maven

## Create Initial Structure
    mvn archetype:generate -DgroupId=com.github.mohajel.IE.CA1 -DartifactId=CA1 -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false

## Run Project
    mvn exec:java



# Commit Convention

we use this commit conventions:

    <type>(optional scope): <description>

## Type

can be 

feat --> for adding feature

fix --> for fixing bug

docs --> for adding to readme / report file

refactor --> for refactor!

test --> for test!

chore --> for changes to build process, tools and libraries

## Example

### simple feature adding
    feat: feature  a added

### adding feature with importance
    feat!: feature b added

### adding feature with specifying scope
    feat(api): feature c added




