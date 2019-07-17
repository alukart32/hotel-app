CREATE TABLE inprog.authorities (
  "authority_id" SERIAL PRIMARY KEY,
  "name" varchar(16) UNIQUE
);

CREATE TABLE inprog.users (
  "user_id" SERIAL PRIMARY KEY,
  "authority_id" integer,
  "login" varchar(32) UNIQUE,
  "password" varchar(88),
  "first_name" varchar(32),
  "second_name" varchar(32),
  "middle_name" varchar(32),
  "birth_date" date ,
  "email" varchar(32) UNIQUE
);

CREATE TABLE inprog.rooms (
  "room_id" SERIAL PRIMARY KEY,
  "number" integer,
  "floor" integer,
  "category_id" integer,
  "facility_id" integer,
  "type_id" integer
);

CREATE TABLE inprog.categories (
  "category_id" SERIAL PRIMARY KEY,
  "name" varchar(16) UNIQUE,
  "price_multiplier" decimal
);

CREATE TABLE inprog.types (
  "type_id" SERIAL PRIMARY KEY,
  "twin_bed" boolean,
  "places" integer,
  "price" integer
);

CREATE TABLE inprog.facilities (
  "facility_id" SERIAL PRIMARY KEY,
  "name" varchar(32),
  "price" integer
);

CREATE TABLE inprog.bookings (
  "booking_id" SERIAL PRIMARY KEY,
  "user_id" integer,
  "room_id" integer,
  "check_in" timestamp ,
  "check_out" timestamp ,
  "real_check_in" timestamp ,
  "real_check_out" timestamp
);

ALTER TABLE inprog.users ADD FOREIGN KEY ("authority_id") REFERENCES inprog.authorities ("authority_id");

ALTER TABLE inprog.rooms ADD FOREIGN KEY ("category_id") REFERENCES inprog.categories ("category_id");

ALTER TABLE inprog.rooms ADD FOREIGN KEY ("facility_id") REFERENCES inprog.facilities ("facility_id");

ALTER TABLE inprog.rooms ADD FOREIGN KEY ("type_id") REFERENCES inprog.types ("type_id");

ALTER TABLE inprog.bookings ADD FOREIGN KEY ("room_id") REFERENCES inprog.rooms ("room_id");

ALTER TABLE inprog.bookings ADD FOREIGN KEY ("user_id") REFERENCES inprog.users ("user_id");