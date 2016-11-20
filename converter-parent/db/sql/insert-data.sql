-- noinspection SqlNoDataSourceInspectionForFile
INSERT INTO users (name, date_of_birth, created_on, password, email, address, zip_code, city, country) VALUES ('user1', '2000-11-11', '2000-11-11', 'user', 'mkyong@gmail.com', 'Adresa', '78000', 'BL', 'BA');
insert into conversions (source_currency, target_currency, rate, user_id) values ('CHF', 'EUR',1.5, 1);