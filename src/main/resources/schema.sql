-- Table user
CREATE TABLE IF NOT EXISTS user (
    user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL, -- UNIQUE assure que chaque valeur dans la colonne est unique
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Table film
CREATE TABLE IF NOT EXISTS film (
    film_id INT NOT NULL PRIMARY KEY, -- L'identifiant du film provient de l'API externe
    title VARCHAR(255) NOT NULL,
    synopsis TEXT, -- Le type TEXT permet de stocker de longs textes
    release_date DATE,
    production VARCHAR(255),
    image_url VARCHAR(255)
);

-- Table actor
CREATE TABLE IF NOT EXISTS actor (
    actor_id INT NOT NULL PRIMARY KEY, -- L'identifiant de l'acteur provient de l'API externe
    name VARCHAR(255) NOT NULL,
    birthdate DATE,
    birthplace VARCHAR(255),
    popularity INT,
    gender VARCHAR(50),
    biography TEXT,
    image_url VARCHAR(255)
);

-- Table category
CREATE TABLE IF NOT EXISTS category (
    category_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    UNIQUE (user_id, name)
);


-- Table watched_film
CREATE TABLE IF NOT EXISTS watched_film (
    user_id INT NOT NULL,
    film_id INT NOT NULL,
    rating INT,
    watch_date DATE,
    comment TEXT,
    PRIMARY KEY (user_id, film_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (film_id) REFERENCES film(film_id)
);

-- Table favorite_film
CREATE TABLE IF NOT EXISTS favorite_film (
    user_id INT NOT NULL,
    film_id INT NOT NULL,
    PRIMARY KEY (user_id, film_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (film_id) REFERENCES film(film_id)
);

-- Table film_category
CREATE TABLE IF NOT EXISTS film_category (
    film_id INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (film_id, category_id),
    FOREIGN KEY (film_id) REFERENCES film(film_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE CASCADE
);