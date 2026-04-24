-- products

insert into products(code, name)
values 
('158', 'Galletas de chocolate blanco'),
('958', 'Galletas de avena')
;


-- stores 
insert into stores(code, name)
values 
('978', 'Tienda Santa Ana'),
('156', 'Tienda Eras de Renueva')
;

-- stocks
insert into stocks(store_code, product_code, quantity)
values 
('978', '158', 2),
('978', '958', 3),
('156', '158', 6)
;