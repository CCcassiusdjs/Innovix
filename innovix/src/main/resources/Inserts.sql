INSERT INTO public.person_type (id, access_level, description) VALUES
                                                                   ('employee', 10, 'Full Access'),
                                                                   ('customer', 1, 'Limited Access');

INSERT INTO public.person (name, street, zip_code, person_type_id) VALUES
                                                                       ('João Silva', 'Rua Alegre, 123', '90000-000', 'customer'),
                                                                       ('Maria Souza', 'Av. Brasil, 456', '90000-001', 'employee');

INSERT INTO public.product (description, image, is_promotion_active, minimum_quantity_for_promotion, name, promotion_end_date, promotion_price, promotion_type, quantity) VALUES
                                                                                                                                                                              ('Produto A', 'image_a.jpg', false, NULL, 'Produto A', NULL, NULL, NULL, 100),
                                                                                                                                                                              ('Produto B', 'image_b.jpg', true, 10, 'Produto B', '2024-12-31', 50.00, 'Desconto', 200);

INSERT INTO public.inventory (last_update, location, quantity, cod_prod) VALUES
                                                                             ('2023-04-15 14:00:00', 'Depósito Central', 50, 4),
                                                                             ('2023-04-15 14:00:00', 'Depósito Secundário', 150, 5);

INSERT INTO public.orders (company_name, recipient, sender, ship_cost, state, cod_customer) VALUES
                                                                                                ('Empresa XYZ', 'João Silva', 'Empresa XYZ', 30.00, 'RS', 4),
                                                                                                ('Empresa ABC', 'Maria Souza', 'Empresa ABC', 45.00, 'RS', 5);

INSERT INTO public.order_items (discount, quantity, cod_order, cod_prod) VALUES
                                                                             (10.00, 2, 3, 4),
                                                                             (0.00, 1, 4, 5);

INSERT INTO public.sale (date, end_date, start_date, type) VALUES
    ('2023-04-15', '2023-04-20', '2023-04-10', 'Promoção de Páscoa');

INSERT INTO public.sale_items (description, price, quantity, cod_prod, cod_sale) VALUES
    ('Item promocional A', 45.00, 3, 4, 1);

INSERT INTO public.taxes (description, state, value, cod_order) VALUES
                                                                    ('ICMS', 'RS', 17.00, 1),
                                                                    ('ISS', 'RS', 5.00, 2);

INSERT INTO public.taxes (description, state, value) VALUES
                                                                    ('ICMS', 'AC', 18.00),
                                                                    ('ICMS', 'AL', 18.00),
                                                                    ('ICMS', 'AP', 18.00);

