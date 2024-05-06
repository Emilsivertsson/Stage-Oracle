-- Insert initial roles
INSERT INTO roles (role_id, authority) VALUES
                                           (1, 'ADMIN'),
                                           (2, 'USER');

-- Insert measurements
INSERT INTO measurements (id, height, shoe_size, jacket_size, pant_size, head) VALUES
                                                                                   (1, 1.0, 1.0, 1.0, 1.0, 1.0),
                                                                                   (2, 189, 45, 50, 52, 62);

-- Insert casts and manifests
INSERT INTO manifests (id, title, year, production_id) VALUES
                                                           (1, 'Manifest admin', 2026, 1),
                                                           (2, 'Manifest 1', 2021, 2);

INSERT INTO casts (id, name, manifest_id) VALUES
                                              (1, 'admin cast', 1),
                                              (2, 'Choir', 2);

-- Insert performers
INSERT INTO performers (id, first_name, last_name, email, phone_nr, department, cast_id, measurements_id) VALUES
                                                                                                              (1, 'admin', 'Doe', 'admin@admin.com', '123456789', 'admin', 1, 1),
                                                                                                              (2, 'Jane', 'Doe', 'Jane@Doe.com', '123456789', 'Choir', 2, 2);

-- Insert acts
INSERT INTO acts (id, title, performer_id) VALUES
                                               (1, 'Act admin', 1),
                                               (2, 'Act 1', 2);

-- Insert costumes
INSERT INTO costumes (id, name, act_id) VALUES
                                            (1, 'admin costume', 1),
                                            (2, 'Black costume', 2);

-- Insert garments
INSERT INTO garments (id, name, description, is_done, costume_id) VALUES
                                                                      (1, 'Black jacket', 'its black', FALSE, 1),
                                                                      (2, 'Black jacket', 'its black', TRUE, 2);

-- Insert productions and users
INSERT INTO productions (id, year, title, in_rotation, description, application_user_user_id) VALUES
                                                                                                  (1, 2026, 'Production admin', TRUE, 'Description admin', 1),
                                                                                                  (2, 2005, 'Hamlet', TRUE, 'Producer: William Shakespeare, director: John Doe', 2);

INSERT INTO users (user_id, username, password) VALUES
                                                                  (1, 'admin', 'encoded_password2'),
                                                                  (2, 'user', 'encoded_password1');

-- Insert awan_chats
INSERT INTO awan_chats (id, question, answer, application_user_user_id) VALUES
                                                                            (1, 'admin', 'admin', 1),
                                                                            (2, 'Jane', 'Doe', 2);
