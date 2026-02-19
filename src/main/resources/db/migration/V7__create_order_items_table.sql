CREATE TABLE order_items (
                             order_id UUID NOT NULL,
                             product_id UUID NOT NULL,
                             quantity INTEGER NOT NULL,
                             price DOUBLE PRECISION NOT NULL,

                             PRIMARY KEY (order_id, product_id),

                             CONSTRAINT fk_orderitem_order
                                 FOREIGN KEY (order_id)
                                     REFERENCES orders (id)
                                     ON DELETE CASCADE,

                             CONSTRAINT fk_orderitem_product
                                 FOREIGN KEY (product_id)
                                     REFERENCES products (id)
                                     ON DELETE CASCADE
);