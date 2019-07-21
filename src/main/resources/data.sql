INSERT INTO PRODUCT (id, name)
VALUES (1, 'Arroz'),
       (2, 'Feijão'),
       (3, 'Aspirador de pó'),
       (4, 'Batedeira'),
       (5, 'Liquidificador'),
       (6, 'Estante'),
       (7, 'Mesa'),
       (8, 'Sofá');

INSERT INTO CATEGORY (id, name)
VALUES (1, 'Alimentos'),
       (2, 'Eletrodomésticos'),
       (3, 'Móveis'),
       (4, 'Queima de estoque');

INSERT INTO PRODUCT_CATEGORIES (product_id, category_id)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 3),
       (7, 3),
       (8, 3),
       (3, 4),
       (5, 4),
       (7, 4),
       (8, 4);