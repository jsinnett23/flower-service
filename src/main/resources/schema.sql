CREATE SCHEMA IF NOT EXISTS flowers;
CREATE TABLE IF NOT EXISTS flowers.customers (
    username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255),
    email VARCHAR(255)
    );
