CREATE OR REPLACE FUNCTION inprog.prices_table(IN room_id INTEGER)
    RETURNS TABLE
            (
                category_multiplier NUMERIC,
                facility_price      NUMERIC,
                type_price          NUMERIC
            )
AS
$$
DECLARE
    room_id_param ALIAS FOR room_id;
BEGIN
    IF room_id_param ISNULL THEN
        RAISE EXCEPTION 'Param |room_id| can`t be NULL';
    END IF;

    IF (SElECT R.room_id FROM inprog.rooms as R WHERE R.room_id = room_id_param) ISNULL THEN
        RAISE EXCEPTION 'Room with id = % not found', room_id_param;
    END IF;

    RETURN QUERY (SELECT C.price_multiplier as category_multiplier, F.price as facility_price, T.price as type_price
                  FROM inprog.rooms as R
                           INNER JOIN inprog.types as T ON T.type_id = R.type_id AND R.room_id = room_id_param
                           INNER JOIN inprog.categories as C ON C.category_id = R.category_id
                           LEFT JOIN inprog.rooms_facilities as RF ON RF.room_id = R.room_id
                           LEFT JOIN inprog.facilities as F ON F.facility_id = RF.facility_id);

END;
$$ LANGUAGE 'plpgsql';