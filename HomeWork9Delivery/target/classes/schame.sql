CREATE TABLE IF NOT EXISTS deliveries (
                                          id          uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                          product_id  uuid NOT NULL,
                                          address     text NOT NULL,
                                          created_at  timestamp NOT NULL DEFAULT now(),
                                          status      varchar(32) NOT NULL DEFAULT 'PENDING'
);
CREATE EXTENSION IF NOT EXISTS pgcrypto;

ALTER TABLE deliveries
    ALTER COLUMN id SET DEFAULT gen_random_uuid();
