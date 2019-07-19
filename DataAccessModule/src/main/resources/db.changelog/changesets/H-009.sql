CREATE OR REPLACE FUNCTION inprog.total_price(room_id INTEGER, days_count INTEGER)
    RETURNS NUMERIC AS
$$
BEGIN
    IF room_id ISNULL THEN
        RAISE EXCEPTION 'Param |room_id| can`t be NULL';
    END IF;
    IF days_count ISNULL THEN
        RAISE EXCEPTION 'Param |days_count| can`t be NULL';
    END IF;
    RETURN inprog.daily_price(room_id) * days_count;
END;
$$ LANGUAGE 'plpgsql';