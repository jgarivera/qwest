# Use Thymeleaf

## Context and Problem Statement

The front-end of a Spring Boot web application can be approached in different ways.

## Decision Drivers

- Simplicity
- Integration with Spring
- Flexibility to work with different front-end libraries

## Considered Options

- React
- Vaadin

## Decision Outcome

Chosen option: Thymeleaf. Thymeleaf is simple and has excellent Spring integration. It's only capable of server-side
rendering and might present challenges in creating rich experiences; which a JavaScript front-end library like React
could easily achieve.

Using React with Spring immediately warrants a Node.js and NPM package setup to start developing with it. It also means
a separate JavaScript/TypeScript codebase needs to be maintained. React also expects a REST API from the Spring back-end
for integration.

Vaadin is another promising alternative. Front-end development using Vaadin is done solely using Java. This can lead to
productivity gains since the back-end programming language is the same for the front-end. However, there might be also a
risk of vendor lock-in. There are paid features in Vaadin and the free front-end components may be limited. By default,
its components seem to have an established look-and-feel appropriate for traditional back-office dashboards. The qwest
web app needs a lively aesthetic. Time will need to be allotted to customize its theme. Thymeleaf allows you to pair it
with any CSS framework which makes it easier to achieve a certain kind of aesthetic with minimal amount of code.
