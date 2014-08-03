insert into crm_user (id, version, username, password, first_name, last_name) values (1, 0, 'edward.l', 'edward.l', 'Edward', 'Legaspi');
insert into crm_user (id, version, username, password, first_name, last_name) values (2, 0, 'carlo.b', 'carlo.b', 'Carlo', 'Benitez');
insert into crm_user (id, version, username, password, first_name, last_name) values (3, 0, 'edmond.l', 'edmond.l', 'Edmond', 'Legaspi');
insert into crm_user (id, version, username, password, first_name, last_name) values (4, 0, 'carlo.l', 'carlo.l', 'Carlo', 'Liwanag');

--add promo code
insert into crm_promo_code (id, version, description, created, code, percent, date_active, date_expiry) values (1, 0, 'Promo (Caffee 20% Off)', now(), '1231', .1, now(), (select date(now() + interval '1' day)));
insert into crm_promo_code (id, version, description, created, code, percent, date_active, date_expiry) values (2, 0, 'Promo (Caffee 10% Off)', now(), '1232', .1, now(), (select date(now() + interval '1' day)));
insert into crm_promo_code (id, version, description, created, code, percent, date_active, date_expiry) values (3, 0, 'Promo (Caffee 5% Off)', now(), '1233', .1, now(), (select date(now() + interval '1' day)));
insert into crm_promo_code (id, version, description, created, code, percent, date_active, date_expiry) values (4, 0, 'Promo (Caffee 4% Off)', now(), '1234', .1, now(), (select date(now() + interval '1' day)));
insert into crm_promo_code (id, version, description, created, code, percent, date_active, date_expiry) values (5, 0, 'Promo (Caffee 7% Off)', now(), '1235', .1, now(), (select date(now() + interval '1' day)));
insert into crm_promo_code (id, version, description, created, code, percent, date_active, date_expiry) values (6, 0, 'Promo (Caffee 9% Off)', now(), '1236', .1, now(), (select date(now() + interval '1' day)));
insert into crm_promo_code (id, version, description, created, code, percent, date_active, date_expiry) values (7, 0, 'Promo (Caffee 12% Off)', now(), '1237', .1, now(), (select date(now() + interval '1' day)));
insert into crm_promo_code (id, version, description, created, code, percent, date_active, date_expiry) values (8, 0, 'Promo (Caffee 15% Off)', now(), '1238', .1, now(), (select date(now() + interval '1' day)));
insert into crm_promo_code (id, version, description, created, code, percent, date_active, date_expiry) values (9, 0, 'Promo (Caffee 17% Off)', now(), '1239', .1, now(), (select date(now() + interval '1' day)));
insert into crm_promo_code (id, version, description, created, code, percent, date_active, date_expiry) values (10, 0, 'Promo (Caffee 25% Off)', now(), '1111', .1, now(), (select date(now() + interval '1' day)));