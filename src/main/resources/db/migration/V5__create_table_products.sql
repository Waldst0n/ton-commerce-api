CREATE TABLE products (
      id UUID PRIMARY KEY,
      name VARCHAR(255) NOT NULL,
      description TEXT,
      price DOUBLE PRECISION,
      img_url VARCHAR(255)
);
