CREATE OR REPLACE FUNCTION inprog.is_free(room_id INTEGER, check_in TIMESTAMP, check_out TIMESTAMP )
    RETURNS BOOLEAN AS
$$
DECLARE
    room_id_param ALIAS FOR room_id;
    fromDate ALIAS FOR check_in;
    toDate ALIAS FOR check_out;
	  occupied NUMERIC DEFAULT 0;
BEGIN
    IF room_id_param ISNULL THEN
        RAISE EXCEPTION 'Param |room_id| can`t be NULL';
    END IF;

    IF fromDate > toDate THEN
      RAISE EXCEPTION 'Param |check_in| can`t be greater than param |check_out|';
    END IF;

    SELECT COUNT(*) INTO occupied
    FROM inprog.bookings AS B
    WHERE
	    B.room_id = room_id_param
		    AND
	    (B.check_in, B.check_out) overlaps (fromDate, toDate)
	  ;

    IF occupied > 0 THEN
      RETURN FALSE ;
    ELSE
      RETURN TRUE ;
    END IF;
END;
$$
LANGUAGE 'plpgsql';