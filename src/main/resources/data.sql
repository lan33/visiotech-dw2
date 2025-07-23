-- Films
INSERT IGNORE INTO film (film_id, title, synopsis, release_date, production, image_url) VALUES 
(1001, 'Un ours dans le Jura', 'Un récit sauvage et contemplatif au cœur des montagnes jurassiennes.', '2022-11-12', 'Studio Nature', 'ours-jura.jpg'),
(1002, 'Kaamelott: The Second Chapter', 'La suite épique des aventures du roi Arthur et de ses chevaliers burlesques.', '2025-06-28', 'CALT Production', 'kaamelott2.jpg'),
(1003, 'Le Comte de Monte-Cristo', 'Adaptation cinématographique du célèbre roman de vengeance et d’honneur.', '2024-10-15', 'Pathé Films', 'monte-cristo.jpg'),
(1004, 'Bienvenue chez les Ch’tis', 'Un postier muté dans le Nord découvre une région chaleureuse et pleine de surprises.', '2008-02-27', 'Dany Boon Productions', 'chtis.jpg'),
(1005, 'Le Fabuleux Destin d’Amélie Poulain', 'Une jeune femme transforme la vie de son entourage dans le Paris des années 2000.', '2001-04-25', 'UGC Fox Distribution', 'amelie.jpg');

-- Utilisateurs
INSERT IGNORE INTO user (email, password, role) VALUES 
('alice@example.com', 'pass123', 'USER'),
('bob@example.com', 'pass123', 'USER');