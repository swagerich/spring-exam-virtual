INSERT INTO users (user_name,password,first_name,last_name,email,phone,enabled,profile) VALUES ('swagerich','$2a$10$xGw4tjFfKfllkCqy.bYUVu.RH0G9L7nim3RDW64dRMCcx3bNXYMeC','erich','ivan','erick@gmail.com',12345679,true,'');
INSERT INTO users (user_name,password,first_name,last_name,email,phone,enabled,profile) VALUES ('jose','$2a$10$xGw4tjFfKfllkCqy.bYUVu.RH0G9L7nim3RDW64dRMCcx3bNXYMeC','jose','joseluis','jose@gmail.com',95826504,true,'perfil.png');
INSERT INTO roles(id,role_name)VALUES(1,'ROLE_USER');
INSERT INTO roles(id,role_name)VALUES(2,'ROLE_ADMIN');
INSERT INTO users_roles(user_id,role_id) VALUES (1,2);
INSERT INTO users_roles(user_id,role_id) VALUES (2,1);

