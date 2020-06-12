INSERT INTO tbl_invoices(id, number_invoice, description, customer_id, create_at, state)
VALUES(1, '001', 'venta 001', 1, '2020-01-23',  'CREATED');

INSERT INTO tbl_invoices(id, number_invoice, description, customer_id, create_at,  state)
VALUES(2, '002', 'venta 002', 1, '2020-02-23',  'CREATED');

INSERT INTO tbl_invoices(id, number_invoice, description, customer_id, create_at,  state)
VALUES(3, '003', 'venta 003', 1, '2020-03-23',  'CREATED');



INSERT INTO tbl_invoice_items(id, quantity, price, product_id)
VALUES(1, 2, 280.80, 1);

INSERT INTO tbl_invoice_items(id, quantity, price, product_id)
VALUES(2, 3, 230.80, 1);

INSERT INTO tbl_invoice_items(id, quantity, price, product_id)
VALUES(3, 4, 100.80, 1);

