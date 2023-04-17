USE wishlist;

INSERT INTO wishes (wishName, price, description, link) VALUES ('Playsation 5', 4500, 'The new generation of playstation has arrived, and i need to be a part of it', 'https://www.elgiganten.dk/product/gaming/spillekonsol-tilbehor/playstation/playstation-spillekonsol/playstation-5-2022/533978?gclid=CjwKCAjw_YShBhAiEiwAMomsEL59jZvnNW8Ple_lrhNHgv3Dr09nYu52nhe2jn8uxBj1jWd0G7b_ehoCTP4QAvD_BwE&gclsrc=aw.ds');
INSERT INTO wishes (wishName, price, description, link) VALUES ('Jord', 29.95, '8kg jord, tak', 'https://www.silvan.dk/park-pottemuld?id=7280-9719208&gclid=CjwKCAjw_YShBhAiEiwAMomsECSvzUiKo3kCG7yt_3nm4a_DUWnhlPij1msV_z5HJr6grw5B_oG8ZRoCs3sQAvD_BwE');
INSERT INTO wishes (wishName, price, description, link) VALUES ('Creatine', 135, 'creatine pure monohydrate', 'https://www.bodylab.dk/shop/creatine-4147p.html?gclid=CjwKCAjw_YShBhAiEiwAMomsEHg-PxNvI0FjcXlQodgs2MVROdbaI3NjpS0HftbgGfvS8oe63expCRoCncIQAvD_BwE&gclsrc=aw.ds');
INSERT INTO wishes (wishName, price, description, link) VALUES ('Abonnement til fitness', 269, 'Fitness world', 'https://www.fitnessworld.com/dk2/priser');
INSERT INTO wishes (wishName, price, description, link) VALUES ('Cykellygte', 399, 'Thansen cykellygter wuhu!!', 'https://www.thansen.dk/cykel/udstyr-og-tilbehor/cykellygter/kryptonite-forlygte-incite-x6-60-lux/n-244697757/pn-234053505');

INSERT INTO users (email, username) VALUES ('minho@yahoo.dk', 'Minho');
INSERT INTO users (email, username) VALUES ('simsebang@yahoo.dk', 'Mr.Bang');

INSERT INTO wishlist (wishlistName, username) VALUES ('Jane Bat Mitzvah', 'Minho');
INSERT INTO wishlist (wishlistName, username) VALUES ('Bisættelse Mark', 'Mr.Bang');

INSERT INTO wishlist_wishes (wishlist_id, wish_id) VALUES (1, 3);
INSERT INTO wishlist_wishes (wishlist_id, wish_id) VALUES (1, 2);
INSERT INTO wishlist_wishes (wishlist_id, wish_id) VALUES (2, 1);
INSERT INTO wishlist_wishes (wishlist_id, wish_id) VALUES (2, 4);
INSERT INTO wishlist_wishes (wishlist_id, wish_id) VALUES (2, 5);
