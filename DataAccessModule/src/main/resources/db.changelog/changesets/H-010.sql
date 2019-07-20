CREATE OR REPLACE FUNCTION inprog.booking_price(booking_id INTEGER)
    RETURNS NUMERIC AS
$$
DECLARE
    days_count       INTEGER;
    room_id_param    INTEGER;
    booking_interval INTERVAL;
    booking_id_param ALIAS FOR booking_id;
BEGIN
    SELECT (B.check_out - B.check_in)
    FROM inprog.bookings as B
    WHERE (B.booking_id = booking_id_param)
    into booking_interval;
    SELECT B.room_id
    FROM inprog.bookings as B
    WHERE B.booking_id = booking_id_param
    into room_id_param;
    days_count := extract(amountOfReservedDays from booking_interval) :: INTEGER;
    RETURN inprog.total_price(room_id_param, days_count);
END;
$$ LANGUAGE 'plpgsql';