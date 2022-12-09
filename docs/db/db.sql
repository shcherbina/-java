CREATE TABLE order_object (
	id integer PRIMARY KEY AUTOINCREMENT NOT NULL,
	taken_on datetime NOT NULL,
	book_id integer NOT NULL REFERENCES book(id),
	user_account integer NOT NULL REFERENCES user_account(id),
	return_on datetime NOT NULL
);

CREATE TABLE book (
	id integer PRIMARY KEY AUTOINCREMENT NOT NULL,
	name varchar  NOT NULL,
	author varchar  NOT NULL,
	page integer  NOT NULL,
	library_id integer NOT NULL REFERENCES library(id)
);

CREATE TABLE library (
	id integer PRIMARY KEY AUTOINCREMENT NOT NULL,
	telephone integer NOT NULL,
	address varchar NOT NULL,
	email varchar  NOT NULL
);

CREATE TABLE user_account (
	id integer PRIMARY KEY AUTOINCREMENT,
	first_name varchar  NOT NULL,
	last_name varchar NOT NULL,
	created datetime  NOT NULL,
	email varchar  NOT NULL,
	address varchar  NOT NULL,
	telephone integer  NOT NULL
);

