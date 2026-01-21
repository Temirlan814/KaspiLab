
CREATE TABLE IF NOT EXISTS products (
                                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                        name TEXT NOT NULL,
                                        price INT NOT NULL,
                                        address TEXT
);
CREATE EXTENSION IF NOT EXISTS pgcrypto;

ALTER TABLE products
    ALTER COLUMN id SET DEFAULT gen_random_uuid();
