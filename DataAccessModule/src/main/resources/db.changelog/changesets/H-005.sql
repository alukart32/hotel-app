CREATE TABLE inprog.payments (
  payment_id integer PRIMARY KEY,
  booking_id integer,
  user_id integer,
  date timestamp,
  total decimal
);

ALTER TABLE inprog.payments ADD FOREIGN KEY (booking_id) REFERENCES inprog.bookings (booking_id);

ALTER TABLE inprog.payments ADD FOREIGN KEY (user_id) REFERENCES inprog.users (user_id);