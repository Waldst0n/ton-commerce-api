CREATE TABLE products_categories (
     product_id UUID NOT NULL,
     category_id UUID NOT NULL,

     CONSTRAINT pk_products_categories
         PRIMARY KEY (product_id, category_id),

     CONSTRAINT fk_pc_product
         FOREIGN KEY (product_id)
             REFERENCES products(id)
             ON DELETE CASCADE,

     CONSTRAINT fk_pc_category
         FOREIGN KEY (category_id)
             REFERENCES categories(id)
             ON DELETE CASCADE
);
