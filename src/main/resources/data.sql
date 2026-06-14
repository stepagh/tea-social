-- Очищаем таблицу перед заливкой
TRUNCATE TABLE teas RESTART IDENTITY CASCADE;

-- Добавляем разнообразные популярные сорта чая строго под твой энум TeaType
INSERT INTO teas (name, type) VALUES ('Пуэр Шу Гунтин (Дворцовый)', 'PU_ERH');
INSERT INTO teas (name, type) VALUES ('Пуэр Шен Лао Бань Чжан ', 'PU_ERH');
INSERT INTO teas (name, type) VALUES ('Да Хун Пао (Большой Красный Халат)', 'OOLONG_TEA');
INSERT INTO teas (name, type) VALUES ('Те Гуань Инь Ван (Владыка Железной Бодхисаттвы)', 'OOLONG_TEA');
INSERT INTO teas (name, type) VALUES ('Си Ху Лун Цзин (Колодец Дракона с озера Сиху)', 'GREEN_TEA');
INSERT INTO teas (name, type) VALUES ('Японская Сенча Удзи', 'GREEN_TEA');
INSERT INTO teas (name, type) VALUES ('Матча Церемониальная (Uji Matcha)', 'GREEN_TEA');
INSERT INTO teas (name, type) VALUES ('Габа Алишань (Янтарная)', 'OOLONG_TEA');
INSERT INTO teas (name, type) VALUES ('Бай Хао Инь Чжэнь (Серебряные Иглы с Белым Ворсом)', 'WHITE_TEA');
INSERT INTO teas (name, type) VALUES ('Чжэнь Шань Сяо Чжун (Лапсанг Сушонг копченый)', 'RED_TEA');
INSERT INTO teas (name, type) VALUES ('Ассам Меленг (Индийский черный)', 'RED_TEA');
INSERT INTO teas (name, type) VALUES ('Тайваньский Улун Дун Дин', 'OOLONG_TEA');