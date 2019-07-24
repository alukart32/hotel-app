CREATE OR REPLACE FUNCTION inprog.is_free(room_id INTEGER, check_in TIMESTAMP, check_out TIMESTAMP )
    RETURNS BOOLEAN AS
$$
DECLARE
    room_id_param ALIAS FOR room_id;
    fromDate ALIAS FOR check_in;
    toDate ALIAS FOR check_out;
    rooms NUMERIC DEFAULT 0;
BEGIN
    IF room_id_param ISNULL THEN
        RAISE EXCEPTION 'Param |room_id| can`t be NULL';
    END IF;

    SELECT COUNT(*) INTO rooms
    FROM inprog.bookings AS B
    WHERE
        B.room_id = room_id_param
      AND (
        ((fromDate <= B.check_in::date) AND (toDate >= B.check_out::date))
          OR
        ((fromDate >= B.check_in::date) AND (fromDate <= B.check_out::date))
          OR
        ((toDate >= B.check_in::date) AND (toDate <= B.check_out::date))
        );

    IF rooms > 0 THEN
      RETURN FALSE ;
    ELSE
      RETURN TRUE ;
    END IF;
END;
$$
LANGUAGE 'plpgsql';