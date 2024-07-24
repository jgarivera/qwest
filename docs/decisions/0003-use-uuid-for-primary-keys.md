# Use UUID for primary keys

## Context and Problem Statement

There are two ways to create a primary key in tables.

## Decision Drivers

- Database support
- Database performance impact
- Simpler programming model

## Considered Options

- Auto-incrementing big integers (`BIGSERIAL`)
- Natural keys

## Decision Outcome

Chosen option: UUID.

It has advantages:

- Independently generate UUIDs without risk of collision outside the database. This allows to easily create Entity
  objects that'll never have `null` IDs.
- Migrate data to different databases without renumbering IDs.
- [Natively supported](https://www.postgresql.org/docs/current/datatype-uuid.html) as a data
  type in PostgreSQL and can have a b-tree or hash index.

It has disadvantages:

- Larger index size.
- Total randomness causes slower query performance due to index fragmentation.

Auto-incrementing integers for IDs are simple, but my main issue with it is it relies on the database to get the next
ID. In JPA, this means using the `@GeneratedValue` annotation on top of the ID column in which its value will be
eventually set when it's persisted.

There's a point in time when the Entity has a `null` ID which messes up the contracts enforced by `equals`
and `hashCode`. It's ideal that whenever an Entity object is instantiated, it'll always have a non-null ID. There might
be ways to circumvent around `@GeneratedValue` such as using a service method to read the next sequence value from the
database to get the next ID. But as much as possible, I want to lessen the amount of round trips made to the database.

To mitigate some of the weaknesses of UUIDv4, [UUIDv7](https://www.rfc-editor.org/rfc/rfc9562#name-uuid-version-7) is a
promising solution. It features a time-ordered value field which improves database indexing performance. With this,
UUIDv7 conveys information when a database record is created. Using it as a client-facing ID needs to be
evaluated for potential security concerns.

To not be tightly coupled to a specific UUID implementation, a simple `UUIDFactory` functional interface is introduced
to produce `java.util.UUID` objects. This allows easy swapping between various UUID versions if needed.
The [uuid-creator](https://github.com/f4b6a3/uuid-creator) Java library provides an RFC 9562-compliant implementation of
UUIDv7.

A non-null ID approach in Entity objects will result to an unnecessary `SELECT` query performed by Hibernate.
This is eliminated by either implementing
a [version column (annotated with `@Version`)](https://youtu.be/exqfB1WaqIw?si=9P5EYueh6nFIVdPx&t=1504) or implementing
a [`BaseEntity<ID>` superclass](https://insights.orangeandbronze.com/changing-the-way-we-use-jpa/).

Natural keys will be also explored as the domain is continued to be understood. UUIDs will be the choice for surrogate
keys in this application.
