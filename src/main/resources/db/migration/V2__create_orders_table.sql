CREATE TABLE orders (
    id UUID PRIMARY KEY,
    moment TIMESTAMP WITH TIME ZONE NOT NULL,
    status INTEGER NOT NULL,
    client_id UUID NOT NULL,
    CONSTRAINT fk_order_client FOREIGN KEY (client_id) REFERENCES users (id)
);