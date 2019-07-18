CREATE TABLE inprog.rooms_facilities
(
    room_id     INTEGER NOT NULL,
    facility_id INTEGER NOT NULL
);

ALTER TABLE inprog.rooms_facilities
    ADD FOREIGN KEY ("room_id") REFERENCES inprog.rooms ("room_id");

ALTER TABLE inprog.rooms_facilities
    ADD FOREIGN KEY ("facility_id") REFERENCES inprog.facilities ("facility_id");