CREATE VIEW inprog.reservations_history_view AS
SELECT
      R.room_id, R.floor, R.number, R.category_name as category_name, R.daily_price
      , R.twin_bed, R.places, R.type_id, R.category_id
      ,B.booking_id, B.user_id, B.check_in, B.check_out, B.real_check_in, B.real_check_out
      , P.paid, P.date, P.total
FROM inprog.rooms_view AS R
         JOIN inprog.bookings AS B ON R.room_id = B.room_id
         JOIN inprog.payments AS P on P.booking_id = B.booking_id;
