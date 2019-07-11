CREATE TABLE  inprogress.roles (
    role_id INTEGER PRIMARY KEY,
    name VARCHAR(32)
);

CREATE TABLE  inprogress.users (
    user_id SERIAL PRIMARY KEY,
    login VARCHAR(32),
    role_id INTEGER,
    password VARCHAR(88),
    first_name VARCHAR(32),
    last_name VARCHAR(32),
    middle_name VARCHAR(32),
    FOREIGN KEY (role_id) REFERENCES  inprogress.roles (role_id)
);