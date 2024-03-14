CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users
(
    id           UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    email        varchar(160) NOT NULL UNIQUE,
    password     varchar(160) NOT NULL,
    company_name varchar(60)
);