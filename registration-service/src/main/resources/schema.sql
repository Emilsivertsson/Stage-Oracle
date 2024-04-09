CREATE TABLE if not exists measurements
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    height DOUBLE NOT NULL,
    shoe_size DOUBLE NOT NULL,
    jacket_size DOUBLE NOT NULL,
    pant_size DOUBLE NOT NULL,
    head DOUBLE NOT NULL,
    CONSTRAINT pk_measurement PRIMARY KEY (id)
);

CREATE TABLE if not exists performers
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    first_name      VARCHAR(255) NULL,
    last_name       VARCHAR(255) NULL,
    email           VARCHAR(255) NULL,
    phone_nr        VARCHAR(255) NULL,
    department      VARCHAR(255) NULL,
    measurement_id  BIGINT NOT NULL,
    CONSTRAINT pk_performer PRIMARY KEY (id),
    CONSTRAINT uc_performer_email UNIQUE (email),
    CONSTRAINT uc_performer_phone UNIQUE (phone_nr)
);

CREATE TABLE if not exists role
(
    role_id   INT NOT NULL,
    authority VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (role_id)
);

CREATE TABLE if not exists user_role_junction
(
    role_id INT NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT pk_user_role_junction PRIMARY KEY (role_id, user_id),
    CONSTRAINT fk_user_role_junction_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_user_role_junction_role FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

CREATE TABLE if not exists user
(
    user_id      INT NOT NULL,
    username     VARCHAR(255) NULL,
    password     VARCHAR(255) NULL,
    performer_id BIGINT NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id),
    CONSTRAINT uc_user_username UNIQUE (username),
    CONSTRAINT fk_user_performer FOREIGN KEY (performer_id) REFERENCES performers (id)
);