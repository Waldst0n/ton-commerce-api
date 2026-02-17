CREATE TABLE payments (
      id UUID PRIMARY KEY,
      moment TIMESTAMP WITH TIME ZONE NOT NULL,
      order_id UUID NOT NULL UNIQUE, -- O UNIQUE é obrigatório para o @OneToOne
      CONSTRAINT fk_payment_order FOREIGN KEY (order_id) REFERENCES orders (id)
);