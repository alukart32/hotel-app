CREATE OR REPLACE FUNCTION inprog.daily_price(room_id INTEGER)
    RETURNS NUMERIC AS
$$
DECLARE
    prices_table RECORD;
    daily_price  NUMERIC;
    room_id_param ALIAS FOR room_id;
BEGIN
    IF room_id_param ISNULL THEN
        RAISE EXCEPTION 'Param |room_id| can`t be NULL';
    END IF;
    IF (SElECT R.room_id FROM inprog.rooms as R WHERE R.room_id = room_id_param) ISNULL THEN
        RAISE EXCEPTION 'Room with id = % not found', room_id_param;
    END IF;
    SELECT F.price          as facility_price,
           T.price          as type_price,
           price_multiplier as category_multiplier
    FROM inprog.rooms as R
             JOIN inprog.categories as C on R.category_id = C.category_id and R.room_id = room_id_param
             JOIN inprog.types as T on R.type_id = T.type_id
             LEFT JOIN (SELECT RF.room_id, F.price
                        FROM inprog.facilities as F
                                 JOIN inprog.rooms_facilities as RF on RF.facility_id = F.facility_id) as F
                       ON F.room_id = room_id_param
    into prices_table;
    daily_price := 0;
    IF (prices_table.facility_price IS NOT NULL) THEN
        daily_price := daily_price + prices_table.facility_price;
    END IF;
    IF (prices_table.type_price IS NOT NULL) THEN
        daily_price := daily_price + prices_table.type_price;
    END IF;
    RETURN daily_price * prices_table.category_multiplier;
END;
$$ LANGUAGE 'plpgsql';
