INSERT INTO tbl_categories(id, name) VALUES(1, 'shoes');
INSERT INTO tbl_categories(id, name) VALUES(2, 'books');
INSERT INTO tbl_categories(id, name) VALUES(3, 'electronics');

INSERT INTO tbl_products(id, name, description, stock, price, status, create_at, category_id)
VALUES(1,'adidas model ultimo', 'negros', 200, 1200, 'CREATED', '2020-01-20', 1);

INSERT INTO tbl_products(id, name, description, stock, price, status, create_at, category_id)
VALUES(2,'El Patito feo', 'Cuentos infantil', 210, 1350.20, 'CREATED', '2020-01-10', 2);


INSERT INTO tbl_products(id, name, description, stock, price, status, create_at, category_id)
VALUES(3,'Estufa Panasonic', 'Electrodoméstico', 100, 3000.00, 'CREATED', '2020-01-25', 3);