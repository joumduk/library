# library

database 
sql table

create table `library`.admin
(
	id INT not null primary key,
	name VARCHAR(100) not null,
	username VARCHAR(100) not null,
	password VARCHAR(200) not null
);

insert admin (id, name, username, password) values (1,'admin','admin','admin');


create table `library`.book
(
	id INT not null primary key,
	title VARCHAR(47) not null,
	author VARCHAR(26) not null,
	pages INT not null,
	image VARCHAR(44) not null,
	quantity INT default 0 not null
);

create table `library`.rent
(
	id INT not null primary key,
	id_student INT not null,
	status BIT(1) default b'0' not null,
	expire_date DATE,
	rent_date DATE
);


create table `library`.rent_item
(
	id_rent_item INT not null primary key,
	id_rent INT not null,
	id_book INT not null,
	quantity INT not null,
	book_name VARCHAR(100) not null,
	status BIT(1) default b'0' not null
);

create table `library`.student
(
	id INT not null primary key,
	name VARCHAR(100) not null,
	student_id VARCHAR(100) not null,
	phone VARCHAR(100) not null,
	email VARCHAR(100) not null
);


and

after that import books.csv file intobook table

Login 
	ID :admin
	pass: admin
