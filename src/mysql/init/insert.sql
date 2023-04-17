USE wishList2;

INSERT INTO users (email, username) VALUES ('minho@yahoo.dk', 'Minho');
INSERT INTO users (email, username) VALUES ('simsebang@yahoo.dk', 'Mr.Bang');

INSERT INTO wishlist (wishlistName, users_id) VALUES ('Jane Bat Mitzvah', 1);
INSERT INTO wishlist (wishlistName, users_id) VALUES ('Bisættelse Mark', 2);

INSERT INTO wishes (wishName, price, description, link, wishlist_id) VALUES ('Playsation 5', 4500, 'The new generation of playstation has arrived, and i need to be a part of it', 'https://www.elgiganten.dk', 1);
INSERT INTO wishes (wishName, price, description, link, wishlist_id) VALUES ('Jord', 29.95, '8kg jord, tak', 'https://www.silvan.dk', 1);
INSERT INTO wishes (wishName, price, description, link, wishlist_id) VALUES ('Creatine', 135, 'creatine pure monohydrate', 'https://www.bodylab.dk', 2);
INSERT INTO wishes (wishName, price, description, link, wishlist_id) VALUES ('Abonnement til fitness', 269, 'Fitness world', 'https://www.fitnessworld.com', 2);
INSERT INTO wishes (wishName, price, description, link, wishlist_id) VALUES ('Cykellygte', 399, 'Thansen cykellygter wuhu!!!', 'https://www.thansen.dk', 1);
