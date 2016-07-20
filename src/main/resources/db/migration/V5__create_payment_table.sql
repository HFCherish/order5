CREATE TABLE payments (
  order_id BIGINT NOT NULL,
  amount DOUBLE NOT NULL,
  type VARCHAR(255) DEFAULT "CASH",
  FOREIGN KEY (order_id) REFERENCES orders(id)
);