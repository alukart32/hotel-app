CREATE OR REPLACE FUNCTION inprog.daily_price(room_id INTEGER)
    RETURNS NUMERIC AS
$$
DECLARE
    prices_record RECORD;
    daily_price   NUMERIC;
    room_id_param ALIAS FOR room_id;
BEGIN
    IF room_id_param ISNULL THEN
        RAISE EXCEPTION 'Param |room_id| can`t be NULL';
    END IF;
    IF (SElECT R.room_id FROM inprog.rooms as R WHERE R.room_id = room_id_param) ISNULL THEN
        RAISE EXCEPTION 'Room with id = % not found', room_id_param;
    END IF;
    SELECT SUM(facility_price) as facility_price, type_price, category_multiplier
    FROM inprog.prices_table(room_id_param) AS P
    GROUP BY (P.type_price, P.category_multiplier)
    into prices_record;
    daily_price := 0;
    IF (prices_record.facility_price IS NOT NULL) THEN
        daily_price := daily_price + prices_record.facility_price;
    END IF;
    IF (prices_record.type_price IS NOT NULL) THEN
        daily_price := daily_price + prices_record.type_price;
    END IF;
    RETURN daily_price * prices_record.category_multiplier;
END;
$$ LANGUAGE 'plpgsql';