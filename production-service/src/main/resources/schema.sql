-- Create acts table
CREATE TABLE IF NOT EXISTS acts
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    title VARCHAR(255),
    performer_id BIGINT,
    CONSTRAINT pk_acts PRIMARY KEY (id),
    CONSTRAINT fk_acts_performer FOREIGN KEY (performer_id) REFERENCES performers (id)
);

-- Create awan_chats table
CREATE TABLE IF NOT EXISTS awan_chats
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    question VARCHAR(5000),
    answer VARCHAR(5000),
    application_user_user_id BIGINT,
    CONSTRAINT pk_awan_chats PRIMARY KEY (id),
    CONSTRAINT fk_awan_chats_user FOREIGN KEY (application_user_user_id) REFERENCES users (user_id)
);

-- Create casts table
CREATE TABLE IF NOT EXISTS casts
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    manifest_id BIGINT,
    CONSTRAINT pk_casts PRIMARY KEY (id),
    CONSTRAINT fk_casts_manifest FOREIGN KEY (manifest_id) REFERENCES manifests (id)
);

-- Create costumes table
CREATE TABLE IF NOT EXISTS costumes
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    act_id BIGINT,
    CONSTRAINT pk_costumes PRIMARY KEY (id),
    CONSTRAINT fk_costumes_act FOREIGN KEY (act_id) REFERENCES acts (id)
);

-- Create garments table
CREATE TABLE IF NOT EXISTS garments
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    description VARCHAR(255),
    is_done BIT(1),
    costume_id BIGINT,
    CONSTRAINT pk_garments PRIMARY KEY (id),
    CONSTRAINT fk_garments_costume FOREIGN KEY (costume_id) REFERENCES costumes (id)
);

-- Create manifests table
CREATE TABLE IF NOT EXISTS manifests
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    title VARCHAR(255),
    year BIGINT,
    production_id BIGINT,
    CONSTRAINT pk_manifests PRIMARY KEY (id),
    CONSTRAINT fk_manifests_production FOREIGN KEY (production_id) REFERENCES productions (id)
);

-- Create measurements table
CREATE TABLE IF NOT EXISTS measurements
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    height DOUBLE NOT NULL,
    shoe_size DOUBLE NOT NULL,
    jacket_size DOUBLE NOT NULL,
    pant_size DOUBLE NOT NULL,
    head DOUBLE NOT NULL,
    CONSTRAINT pk_measurements PRIMARY KEY (id)
);

-- Create performers table
CREATE TABLE IF NOT EXISTS performers
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    phone_nr VARCHAR(255),
    department VARCHAR(255),
    cast_id BIGINT,
    measurements_id BIGINT NOT NULL,
    CONSTRAINT pk_performers PRIMARY KEY (id),
    CONSTRAINT uc_performers_email UNIQUE (email),
    CONSTRAINT uc_performers_phone UNIQUE (phone_nr),
    CONSTRAINT fk_performers_cast FOREIGN KEY (cast_id) REFERENCES casts (id),
    CONSTRAINT fk_performers_measurements FOREIGN KEY (measurements_id) REFERENCES measurements (id)
);

-- Create productions table
CREATE TABLE IF NOT EXISTS productions
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    year BIGINT,
    title VARCHAR(255),
    in_rotation BIT(1),
    description VARCHAR(255),
    application_user_user_id BIGINT,
    CONSTRAINT pk_productions PRIMARY KEY (id),
    CONSTRAINT fk_productions_user FOREIGN KEY (application_user_user_id) REFERENCES users (user_id)
);

-- Create roles table
CREATE TABLE IF NOT EXISTS roles
(
    role_id BIGINT AUTO_INCREMENT NOT NULL,
    authority VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (role_id)
);

-- Create users table
CREATE TABLE IF NOT EXISTS users
(
    user_id BIGINT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255),
    performer_id BIGINT,
    CONSTRAINT pk_users PRIMARY KEY (user_id),
    CONSTRAINT uc_users_username UNIQUE (username),
    CONSTRAINT fk_users_performer FOREIGN KEY (performer_id) REFERENCES performers (id)
);
