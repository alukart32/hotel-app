INSERT INTO inprog.payments(booking_id, user_id, date, total)
SELECT booking_id, user_id, real_check_in as date, inprog.booking_price(booking_id)
FROM inprog.bookings;