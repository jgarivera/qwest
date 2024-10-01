ALTER TABLE challenges
ADD host_id UUID NOT NULL;

-- Cannot enforce referential integrity with `users` table
-- because it's in another module and bounded context.
