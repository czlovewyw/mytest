drop database if exists SpringWebFlowTutorial;
create database SpringWebFlowTutorial;
use SpringWebFlowTutorial;

drop table if exists users;
drop table if exists group_authorities;
drop table if exists group_members;
drop table if exists authorities;
drop table if exists groups;

drop table if exists hotel;
drop table if exists booking_amenities;
drop table if exists booking;

create table users(
    username varchar(50) not null primary key,
    password varchar(100) not null,
    enabled boolean not null,
    name varchar(50)
);

create table authorities(
    authority varchar(50) not null,
    username varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users(username)
);
create unique index index_authorities_username on authorities (username,authority);

create table groups(
    id int(11) primary key auto_increment,
    group_name varchar(50) not null
);
create table group_authorities(
    group_id int(11) not null,
    authority varchar(50) not null,

    constraint fk_group_authorities_group foreign key(group_id) references groups(id)
);

create table group_members(
    id int(11) primary key auto_increment,
    username varchar(50) not null,
    group_id int(11) not null,

    constraint fk_group_members_group foreign key(group_id) references groups(id)
);

create table hotel(
    id bigint(20) not null primary key auto_increment,
    price decimal(6,2),
    name varchar(30) not null,
    address varchar(50) not null,
    city varchar(20) not null,
    state varchar(20),
    zip varchar(10) not null,
    country varchar(20)
);

create table booking(
    id bigint(20) not null primary key auto_increment,
    checkinDate date not null,
    checkoutDate date,
    creditCard varchar(16) not null,
    creditCardName varchar(50) not null,
    creditCardExpiryMonth smallint(2) not null,
    creditCardExpiryYear smallint(4) not null,
    smoking boolean not null,
    beds int(10),
    username varchar(50),
    hotel_id bigint(20),

    constraint fk_booking_users_username foreign key (username) references users(username),
    constraint fk_booking_hotel_id foreign key (hotel_id) references hotel(id)
);

create table booking_amenities(
    booking_id bigint(20) not null,
    amenity varchar(20),
    constraint fk_booking_amenities_booking_id foreign key (booking_id) references booking(id)
);

-- insert groups
insert into groups(group_name) values ('Guests');
insert into groups(group_name) values ('Users');
insert into groups(group_name) values ('Administrators');


-- insert group_authorities,assign roles to groups.
insert into group_authorities(group_id, authority) select id, 'ROLE_GUEST' from groups where group_name='Guests';
insert into group_authorities(group_id, authority) select id, 'ROLE_USER' from groups where group_name='Users';
insert into group_authorities(group_id, authority) select id, 'ROLE_USER' from groups where group_name='Administrators';
insert into group_authorities(group_id, authority) select id, 'ROLE_ADMIN' from groups where group_name='Administrators';

-- insert users
insert into users(username, password, enabled) values ('keith', 'c804b38512622e8ba204cf6fb9c46db4d4b909b3', true);
insert into users(username, password, enabled) values ('erwin', '28c4e59bbb1f65575df6832b26aada8128607974', true);
insert into users(username, password, enabled) values ('jeremy', 'e7c918c9e365e0081fb30b670a167d6c6f96e2c4', true);
insert into users(username, password, enabled) values ('scott', 'e7b34dbbc2362f64fe5cb15cbb0be4de67363ec9', true);
insert into users(username, password, enabled) values ('admin', 'a40546cc4fd6a12572828bb803380888ad1bfdab', true);


-- insert group_members,assign users to groups.
insert into group_members(group_id, username) select id, 'keith' from groups where group_name='Users';
insert into group_members(group_id, username) select id, 'erwin' from groups where group_name='Users';
insert into group_members(group_id, username) select id, 'jeremy' from groups where group_name='Users';
insert into group_members(group_id, username) select id, 'scott' from groups where group_name='Users';
insert into group_members(group_id, username) select id, 'admin' from groups where group_name='Administrators';


-- insert some test datas to Hotel
insert into hotel (id, price, name, address, city, state, zip, country) values (1, 199, 'Westin Diplomat', '3555 S. Ocean Drive', 'Hollywood', 'FL', '33019', 'USA');
insert into hotel (id, price, name, address, city, state, zip, country) values (2, 60, 'Jameson Inn', '890 Palm Bay Rd NE', 'Palm Bay', 'FL', '32905', 'USA');
insert into hotel (id, price, name, address, city, state, zip, country) values (3, 199, 'Chilworth Manor', 'The Cottage, Southampton Business Park', 'Southampton', 'Hants', 'SO16 7JF', 'UK');
insert into hotel (id, price, name, address, city, state, zip, country) values (4, 120, 'Marriott Courtyard', 'Tower Place, Buckhead', 'Atlanta', 'GA', '30305', 'USA');
insert into hotel (id, price, name, address, city, state, zip, country) values (5, 180, 'Doubletree', 'Tower Place, Buckhead', 'Atlanta', 'GA', '30305', 'USA');
insert into hotel (id, price, name, address, city, state, zip, country) values (6, 450, 'W Hotel', 'Union Square, Manhattan', 'NY', 'NY', '10011', 'USA');
insert into hotel (id, price, name, address, city, state, zip, country) values (7, 450, 'W Hotel', 'Lexington Ave, Manhattan', 'NY', 'NY', '10011', 'USA');
insert into hotel (id, price, name, address, city, state, zip, country) values (8, 250, 'Hotel Rouge', '1315 16th Street NW', 'Washington', 'DC', '20036', 'USA');
insert into hotel (id, price, name, address, city, state, zip, country) values (9, 300, '70 Park Avenue Hotel', '70 Park Avenue', 'NY', 'NY', '10011', 'USA');
insert into hotel (id, price, name, address, city, state, zip, country) values (10, 300, 'Conrad Miami', '1395 Brickell Ave', 'Miami', 'FL', '33131', 'USA');
insert into hotel (id, price, name, address, city, state, zip, country) values (11, 80, 'Sea Horse Inn', '2106 N Clairemont Ave', 'Eau Claire', 'WI', '54703', 'USA');
insert into hotel (id, price, name, address, city, state, zip, country) values (12, 90, 'Super 8 Eau Claire Campus Area', '1151 W Macarthur Ave', 'Eau Claire', 'WI', '54701', 'USA');
insert into hotel (id, price, name, address, city, state, zip, country) values (13, 160, 'Marriot Downtown', '55 Fourth Street', 'San Francisco', 'CA', '94103', 'USA');
insert into hotel (id, price, name, address, city, state, zip, country) values (14, 200, 'Hilton Diagonal Mar', 'Passeig del Taulat 262-264', 'Barcelona', 'Catalunya', '08019', 'Spain');
insert into hotel (id, price, name, address, city, state, zip, country) values (15, 210, 'Hilton Tel Aviv', 'Independence Park', 'Tel Aviv', '', '63405', 'Israel');
insert into hotel (id, price, name, address, city, state, zip, country) values (16, 240, 'InterContinental Tokyo Bay', 'Takeshiba Pier', 'Tokyo', '', '105', 'Japan');
insert into hotel (id, price, name, address, city, state, zip, country) values (17, 130, 'Hotel Beaulac', ' Esplanade Lopold-Robert 2', 'Neuchatel', '', '2000', 'Switzerland');
insert into hotel (id, price, name, address, city, state, zip, country) values (18, 140, 'Conrad Treasury Place', 'William & George Streets', 'Brisbane', 'QLD', '4001', 'Australia');
insert into hotel (id, price, name, address, city, state, zip, country) values (19, 230, 'Ritz Carlton', '1228 Sherbrooke St', 'West Montreal', 'Quebec', 'H3G1H6', 'Canada');
insert into hotel (id, price, name, address, city, state, zip, country) values (20, 460, 'Ritz Carlton', 'Peachtree Rd, Buckhead', 'Atlanta', 'GA', '30326', 'USA');
insert into hotel (id, price, name, address, city, state, zip, country) values (21, 220, 'Swissotel', '68 Market Street', 'Sydney', 'NSW', '2000', 'Australia');
insert into hotel (id, price, name, address, city, state, zip, country) values (22, 250, 'Meli White House', 'Albany Street', 'Regents Park London', '', 'NW13UP', 'Great Britain');
insert into hotel (id, price, name, address, city, state, zip, country) values (23, 210, 'Hotel Allegro', '171 West Randolph Street', 'Chicago', 'IL', '60601', 'USA');









