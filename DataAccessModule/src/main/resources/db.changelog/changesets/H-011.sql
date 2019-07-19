CREATE SEQUENCE inprog.payment_id_seq;
ALTER TABLE inprog.payments
    ALTER payment_id SET DEFAULT nextval('inprog.payment_id_seq');


ALTER TABLE inprog.payments
    ALTER booking_id SET NOT NULL;
ALTER TABLE inprog.payments
    ALTER user_id SET NOT NULL;
ALTER TABLE inprog.payments
    ALTER date SET NOT NULL;