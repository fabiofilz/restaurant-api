insert into cuisine (id, name) values (1, 'Thai');
insert into cuisine (id, name) values (2, 'Indian');

insert into restaurant (id, name, delivery_fee, cuisine_id) values (1, 'Thai Gourmet', 10, 1);
insert into restaurant (id, name, delivery_fee, cuisine_id) values (2, 'Thai Delivery', 9.50, 1);
insert into restaurant (id, name, delivery_fee, cuisine_id) values (3, 'Tuk Tuk indian food', 15, 2);

insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

insert into payment_type (id, description) values (1, 'Credit card');
insert into payment_type (id, description) values (2, 'Debit card');
insert into payment_type (id, description) values (3, 'Cash');

insert into access_control (id, name, description) values (1, 'SEARCH_cuisineS', 'Allow search by type of cuisines');
insert into access_control (id, name, description) values (2, 'EDIT_cuisine', 'Allow edit type of cuisines');
