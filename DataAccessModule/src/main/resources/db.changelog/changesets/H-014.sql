CREATE VIEW inprog.rooms_view AS
SELECT R.room_id,
       R.floor,
       R.number,
       C.category_id,
       C.name as category_name,
       R.type_id,
       twin_bed,
       places,
       inprog.daily_price(R.room_id) :: INTEGER
FROM inprog.rooms AS R
         JOIN inprog.categories AS C ON R.category_id = C.category_id
         JOIN inprog.types AS T on T.type_id = R.type_id;