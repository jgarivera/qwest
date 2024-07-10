# Use Bulma

## Context and Problem Statement

There are many ways to approach how a Thymeleaf template is styled.

## Decision Drivers

- Aesthetic design
- Simple build step
- Productive developer experience

## Considered Options

- Tailwind
- Bootstrap

## Decision Outcome

Chosen option: Thymeleaf with Bulma CSS. It's the simplest approach. Using Tailwind CSS is quite cumbersome in
a Thymeleaf template since its utility-style classes is more granular and requires a true hot-reloading development
experience to be productive. This hot-reloading experience isn't present in Spring Boot. It does have a devtools
extension which supports LiveReload. LiveReload requires a browser extension and frequent project rebuild to see
template changes. Using Bulma's "ready-baked" components and elements is more productive to use with LiveReload
than Tailwind CSS. Moreover, Tailwind requires a Node.js build step for tree-shaking its unused CSS
classes. Bulma might need one as well, but it's less crucial for it.

Bootstrap is also another alternative, but Bulma seems to fit out-of-the-box the aesthetic I want for the web app.
