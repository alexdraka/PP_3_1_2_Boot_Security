# # Run after tables are created
#
# insert into users(username, password) values('admin', '$2a$12$FIaS8EIvZ4cb77egd7cBKexyS.6k0i7isWYyQRd5eD73B5ElwqkVu');
# insert into users(username, password) values('user', '$2a$12$lLAt6MbgeigLy/zwmuv7zeKGTZGQZIXRaztjeOc5XdnbWNJGqx6f6');
# insert into roles(name) values('ADMIN');
# insert into roles(name) values('USER');
# insert into roles(name) values('DEVELOPER');
# insert into roles(name) values('TESTER');
# insert into users_roles(users_id, role_id) values(1, 1);
# insert into users_roles(users_id, role_id) values(2, 2);
# #
# #
# # # create table authorities (username varchar(255) unique, authority varchar(255), CONSTRAINT fk_roles_users foreign key (username) references users (username));
