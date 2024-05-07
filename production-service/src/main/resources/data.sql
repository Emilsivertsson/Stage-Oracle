INSERT INTO roles (role_id, authority)
VALUES
    (1, 'ADMIN'),
    (2, 'USER')
ON DUPLICATE KEY UPDATE authority=VALUES(authority);

INSERT INTO measurements (id, height, shoe_size, jacket_size, pant_size, head)
VALUES
    (1, 1.0, 1.0, 1.0, 1.0, 1.0),
    (2, 189, 45, 50, 52, 62)
ON DUPLICATE KEY UPDATE height=VALUES(height), shoe_size=VALUES(shoe_size), jacket_size=VALUES(jacket_size), pant_size=VALUES(pant_size), head=VALUES(head);

INSERT INTO users (user_id, username, password)
VALUES
    (1, 'admin', '$2a$12$bmGRGyuAcx9ejuWHrUdaL.mbqhDcU4kIfoa62CIF9CX0.vsQ5nOMK'),
    (2, 'user', '$2a$12$z.UuwHuzIAVlKY4ZNZYlUeTuz9f0C8pH3KpwgrWb2H1kM/JeMa6EG')
ON DUPLICATE KEY UPDATE username=VALUES(username), password=VALUES(password);

INSERT INTO productions (id, year, title, in_rotation, description, application_user_user_id)
VALUES
    (1, 2026, 'Production admin', TRUE, 'Description admin', 1),
    (2, 2005, 'Hamlet', TRUE, 'Producer: William Shakespeare, director: John Doe', 2),
    (3,2006,'Romeo and Juliet',TRUE,'Producer: William Shakespeare, director: Jane Doe"',2),
    (4,2007,'Macbeth',false,'Producer: William Shakespeare, director: John Doe',2),
    (5,2008,'Othello',TRUE,'Producer: William Shakespeare, director: Jill Doe',2),
    (6,2009,'King Lear',false,'Producer: William Shakespeare, director: John Doe',2)

ON DUPLICATE KEY UPDATE year=VALUES(year), title=VALUES(title), in_rotation=VALUES(in_rotation), description=VALUES(description), application_user_user_id=VALUES(application_user_user_id);

INSERT INTO manifests (id, title, year, production_id)
VALUES
    (1, 'Manifest admin', 2026, 1),
    (2, '2005 version', 2005, 2),
    (3, '2009 version', 2009, 2),
    (4, '2012 version', 2012, 2),
    (5, '2006 version', 2006, 3),
    (6, '2009 version', 2009, 3)
ON DUPLICATE KEY UPDATE title=VALUES(title), year=VALUES(year), production_id=VALUES(production_id);

INSERT INTO casts (id, name, manifest_id)
VALUES
    (1, 'admin cast', 1),
    (2, 'Mens Choir', 2),
    (3, 'Womens Choir', 2),
    (4, 'Childrens Choir', 2),
    (5, 'Dancers', 3),
    (6, 'Actors', 3),
    (7, 'Extras', 3)
ON DUPLICATE KEY UPDATE name=VALUES(name), manifest_id=VALUES(manifest_id);

INSERT INTO performers (id, first_name, last_name, email, phone_nr, department, cast_id, measurements_id)
VALUES
    (1, 'admin', 'Doe', 'admin@admin.com', '123456789', 'admin', 1, 1),
    (2, 'Jacky', 'Doe', 'Jacky@Doe.com', '123456789', 'Choir', 2, 2)
ON DUPLICATE KEY UPDATE first_name=VALUES(first_name), last_name=VALUES(last_name), email=VALUES(email), phone_nr=VALUES(phone_nr), department=VALUES(department), cast_id=VALUES(cast_id), measurements_id=VALUES(measurements_id);

INSERT INTO acts (id, title, performer_id)
VALUES(1, 'Act admin', 1),
      (2, 'Act 1', 2),
      (3, 'Act 2', 2)
ON DUPLICATE KEY UPDATE title=VALUES(title), performer_id=VALUES(performer_id);

INSERT INTO costumes (id, name, act_id)
VALUES
    (1, 'admin costume', 1),
    (2, 'Soldier costume', 2),
    (3, 'King costume', 3)
ON DUPLICATE KEY UPDATE name=VALUES(name), act_id=VALUES(act_id);


INSERT INTO garments (id, name, description, is_done, costume_id)
VALUES
    (1, 'Black jacket', 'its black', FALSE, 1),
    (2, 'Soldier jacket', 'Grimy, black and red with gold buttons', false, 2),
    (3, 'Soldier trousers', 'Holes on knees, black with red trimmings', FALSE, 2),
    (4, 'Solder hat', 'Black with a brim, emblem on front', TRUE, 2),
    (5, 'King robe', 'Red velvet with gold trimmings', false, 3),
    (6, 'King crown', 'Gold with rubies', TRUE, 3)
ON DUPLICATE KEY UPDATE name=VALUES(name), description=VALUES(description), is_done=VALUES(is_done), costume_id=VALUES(costume_id);


INSERT INTO awan_chats (id, question, answer, application_user_user_id)
VALUES
    (1, 'admin', 'admin', 1),
    (2, 'what does soldiers wear?', 'What a great question!\n\nThe type of clothing and gear that soldiers wear can vary depending on their branch, rank, and specific mission or environment. However, here are some general items that many soldiers typically wear:\n\n**Uniform:**\n\n* Army Combat Uniform (ACU) - a camouflage-patterned uniform used for everyday wear in combat zones.\n* Army Service Uniform (ASU) - a more formal uniform worn for official events, ceremonies, and daily duties.\n\n**Outerwear:**\n\n* Camouflage poncho or rain jacket\n* Parka or insulated coat\n* Windbreaker or lightweight jacket\n\n**Headgear:**\n\n* Kevlar helmet (e.g., Advanced Combat Helmet or Modular Integrated Communication Helmet)\n* Beret or patrol cap (depending on the branch and rank)\n\n**Footwear:**\n\n* Combat boots (e.g., Blackhawk or Timberland-style boots)\n* Jungle boots or tactical shoes (for jungle or desert environments)\n* Snow boots (for winter operations)\n\n**Body Armor:**\n\n* Interceptor Body Armor (IBA) vest or plate carrier\n* Modular Tactical Vest (MTV) with ceramic plates\n\n**Other Gear:**\n\n* Web belt or nylon belt\n* Holster for pistol or rifle\n* Magazine pouches and ammunition cans\n* First aid kit or medical pouch\n* Water bottle or hydration pack\n* Sleeping bag or bivvy sack (for field operations)\n\n**Specialized Gear:**\n\n* Night vision goggles or binoculars\n* Thermal imaging devices (e.g., thermal scopes or cameras)\n* Hearing protection (e.g., earplugs or earmuffs)\n* Gas mask or chemical warfare agent protective equipment (CWPE)\n* Environmental protective gear (e.g., cold-weather gloves or hot-weather headgear)\n\nPlease note that this is not an exhaustive list, and actual gear may vary depending on the soldiers unit, mission, and environment. Additionally, some gear may be specific to certain branches or countries militaries.', 2)
ON DUPLICATE KEY UPDATE question=VALUES(question), answer=VALUES(answer), application_user_user_id=VALUES(application_user_user_id);
