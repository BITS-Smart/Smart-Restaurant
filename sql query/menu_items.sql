INSERT INTO public.menu_items
(id, created_at, description, food_category, ingredients, is_enabled, is_veg, name, price, updated_at, restaurant_id_id)
VALUES(1, '2021-01-10 18:48:29.036', 'For the mornings when you crave something simple and satisfying. This homestyle poha is sure to sate your hunger and help you kickstart your day with joy!', 0, 'poha, potato, onion', true, true, 'Homestyle Poha', 50.0, NULL, 1);
INSERT INTO public.menu_items
(id, created_at, description, food_category, ingredients, is_enabled, is_veg, name, price, updated_at, restaurant_id_id)
VALUES(2, '2021-01-10 18:50:06.931', 'Semolina and assorted veggies cooked with traditional Indian masalas in ghee served with coconut chutney = A Fuss-free Breakfast Classic! Contains Gluten and Dairy', 0, 'glueten, dairy', true, true, 'Vegetable Upma', 50.0, NULL, 1);
INSERT INTO public.menu_items
(id, created_at, description, food_category, ingredients, is_enabled, is_veg, name, price, updated_at, restaurant_id_id)
VALUES(3, '2021-01-11 20:37:26.566', 'The Classic Indulgent Aloo Paratha made with Rich and Fresh ingredients. Served with Curd and Pickle.', 0, 'potato', true, true, 'Aloo Paratha with Curd and Pickle', 80.0, NULL, 1);
INSERT INTO public.menu_items
(id, created_at, description, food_category, ingredients, is_enabled, is_veg, name, price, updated_at, restaurant_id_id)
VALUES(4, '2021-01-11 20:40:24.692', 'Maple syrup is the exquisite addition to the winning combo of bananas and peanut butter. Enjoy your breakfast american style! Contains Nuts and Eggs', 0, 'Nuts and Eggs', true, false, 'Pancake with Peanut Butter and Maple Syrup', 130.0, NULL, 1);
INSERT INTO public.menu_items
(id, created_at, description, food_category, ingredients, is_enabled, is_veg, name, price, updated_at, restaurant_id_id)
VALUES(7, '2021-01-11 20:45:35.681', '1 paneer sabji, 1 veg sabji, 4 butter roti, dal fry, jeera rice, gulab jamun (1 piece), butter milk, salad.', 2, NULL, true, true, 'Veg. Thali', 200.0, NULL, 1);
INSERT INTO public.menu_items
(id, created_at, description, food_category, ingredients, is_enabled, is_veg, name, price, updated_at, restaurant_id_id)
VALUES(8, '2021-01-11 20:45:35.692', 'Chole bhatoora + cold drink 250 ml ) + 1 gulab jamun

', 2, NULL, true, true, 'Chole Bhatoora', 200.0, NULL, 1);
INSERT INTO public.menu_items
(id, created_at, description, food_category, ingredients, is_enabled, is_veg, name, price, updated_at, restaurant_id_id)
VALUES(9, '2021-01-11 20:45:35.697', 'Soya Chaap Masala ( 250 Ml ) + 3 Plain Roti + 2 Pc Corn Tikki + Rose Sharbat + 1 Pc Gulab Jamun.', 2, NULL, true, true, 'Soya Chaap Combo', 220.0, NULL, 1);
INSERT INTO public.menu_items
(id, created_at, description, food_category, ingredients, is_enabled, is_veg, name, price, updated_at, restaurant_id_id)
VALUES(10, '2021-01-11 20:45:35.702', '2 pc chic lollipop, chic curry , 2 roti, egg bhurji, dal , rice , papad ,pickle , gulab jamun, rose sharbat.

', 2, NULL, true, false, 'Non Veg Thali', 300.0, NULL, 1);
INSERT INTO public.menu_items
(id, created_at, description, food_category, ingredients, is_enabled, is_veg, name, price, updated_at, restaurant_id_id)
VALUES(11, '2021-01-11 20:45:35.712', '1 pc boil fry , egg masala , 2 roti, dal , rice , 1 gulab jamun', 2, NULL, true, false, 'Egg Thali', 250.0, NULL, 1);
INSERT INTO public.menu_items
(id, created_at, description, food_category, ingredients, is_enabled, is_veg, name, price, updated_at, restaurant_id_id)
VALUES(12, '2021-01-11 20:45:35.719', '1 pc fish fry , fish curry, 2 roti, rice, dry veg,papad, pickle gulab jamun', 2, NULL, true, false, 'Goan Fish Thali', 300.0, NULL, 1);
INSERT INTO public.menu_items
(id, created_at, description, food_category, ingredients, is_enabled, is_veg, name, price, updated_at, restaurant_id_id)
VALUES(5, '2021-01-11 20:40:24.701', '3 Egg Omelette topped with Onion, Tomato, Chili and Coriander provided with Buttered Toast.

', 0, 'Eggs', true, false, 'Masala Vegetable Egg Omelette with Toast', 115.0, NULL, 1);
INSERT INTO public.menu_items
(id, created_at, description, food_category, ingredients, is_enabled, is_veg, name, price, updated_at, restaurant_id_id)
VALUES(6, '2021-01-11 20:40:24.748', '3 Egg Omelette topped with Mushroom, Cheese and Chili served with Buttered Toast.', 0, 'Eggs', true, false, 'Mushroom Cheese Egg Omelette with Toast', 135.0, NULL, 1);
INSERT INTO public.menu_items
(id, created_at, description, food_category, ingredients, is_enabled, is_veg, name, price, updated_at, restaurant_id_id)
VALUES(13, '2021-01-13 00:04:35.878', 'Enjoy our delicious coffee made with freshly roasted coffee beans', 4, NULL, true, true, 'Coffee', 20.0, NULL, 1);
INSERT INTO public.menu_items
(id, created_at, description, food_category, ingredients, is_enabled, is_veg, name, price, updated_at, restaurant_id_id)
VALUES(14, '2021-01-13 00:04:36.488', 'Ek garam chai ki pyali ho!!', 3, NULL, true, true, 'Tea', 15.0, NULL, 1);
INSERT INTO public.menu_items
(id, created_at, description, food_category, ingredients, is_enabled, is_veg, name, price, updated_at, restaurant_id_id)
VALUES(15, '2021-01-13 00:04:36.494', 'Enjoy kadak chai made with ginger mint and other spices', 4, NULL, true, true, 'Masala tea', 20.0, NULL, 1);

--Because of specifying id in the above queries the sequence used is not updated. Following query will bring the sequence of menu_item primary key id to sync
ALTER SEQUENCE menu_items_id_seq RESTART WITH 16;
