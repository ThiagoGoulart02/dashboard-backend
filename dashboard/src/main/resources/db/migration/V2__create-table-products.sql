CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE products(
    id_product UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_user UUID NOT NULL REFERENCES users(id_user),
    title varchar(160),
    amount int,
    price numeric,
    description varchar(360)
);