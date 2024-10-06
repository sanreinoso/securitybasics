create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

INSERT INTO users values ('santy', '{noop}12345', '1');
INSERT INTO authorities values ('santy', 'read');

INSERT INTO users values ('admin', '{bcrypt}$2a$12$Y98a5LEausf/7Pst08AbZuGsN7RR571xDRl1LNL1DXTW0mQjgW73y', '1');
INSERT INTO authorities values ('admin', 'admin');

/*CUSTOM TABLES*/
CREATE TABLE customer ( id int NOT NULL AUTO_INCREMENT, email varchar(50) NOT NULL, pwd varchar(100) NOT NULL, role varchar(50) NOT NULL, PRIMARY KEY (id));
INSERT INTO customer values (null, 'santy@test.com', '{noop}12345', 'read');
INSERT INTO customer values (null, 'admin@test.com', '{bcrypt}$2a$12$Y98a5LEausf/7Pst08AbZuGsN7RR571xDRl1LNL1DXTW0mQjgW73y', 'admin');