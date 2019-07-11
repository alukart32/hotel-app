CREATE TABLE "authorities" (
  "authority_id" SERIAL PRIMARY KEY,
  "name" varchar(16) UNIQUE
);

CREATE TABLE "users" (
  "user_id" SERIAL PRIMARY KEY,
  "authority_id" integer,
  "login" varchar(32) UNIQUE,
  "password" varchar(88),
  "first_name" varchar(32),
  "second_name" varchar(32),
  "middle_name" varchar(32),
  "birth_date" datetime,
  "email" varchar(32) UNIQUE
);

CREATE TABLE "rooms" (
  "room_id" SERIAL PRIMARY KEY,
  "number" integer,
  "floor" integer,
  "category_id" integer,
  "facility_id" integer,
  "type_id" integer
);

CREATE TABLE "categories" (
  "category_id" SERIAL PRIMARY KEY,
  "name" varchar(16) UNIQUE,
  "price_multplier" decimal
);

CREATE TABLE "types" (
  "type_id" SERIAL PRIMARY KEY,
  "twin_bed" boolean,
  "places" integer,
  "price" integer
);

CREATE TABLE "facilities" (
  "facility_id" SERIAL PRIMARY KEY,
  "name" varchar(32),
  "price" integer
);

CREATE TABLE "bookings" (
  "booking_id" SERIAL PRIMARY KEY,
  "user_id" integer,
  "room_id" integer,
  "check_in" datetime,
  "check_out" datetime,
  "real_check_in" datetime,
  "real_check_out" datetime
);

ALTER TABLE "users" ADD FOREIGN KEY ("authority_id") REFERENCES "authorities" ("authority_id");

ALTER TABLE "rooms" ADD FOREIGN KEY ("category_id") REFERENCES "categories" ("category_id");

ALTER TABLE "rooms" ADD FOREIGN KEY ("facility_id") REFERENCES "facilities" ("facility_id");

ALTER TABLE "rooms" ADD FOREIGN KEY ("type_id") REFERENCES "types" ("type_id");

ALTER TABLE "bookings" ADD FOREIGN KEY ("room_id") REFERENCES "rooms" ("room_id");

ALTER TABLE "bookings" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("user_id");