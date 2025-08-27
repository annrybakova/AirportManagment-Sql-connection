-- Big query

SELECT
    p.name AS passenger_name,
    p.email,
    p.passport_number,
    a.name AS airline_name,
    dep_air.name AS departure_airport,
    arr_air.name AS arrival_airport,
    f.departure_time,
    f.arrival_time,
    t.seat_number,
    t.price AS ticket_price,
    l.weight AS luggage_weight,
    r.distance_km,
    d.delay_minutes,
    d.reason AS delay_reason,
    g.gate_number,
    s.name AS staff_name,
    ro.role_name AS staff_role,
    b.booking_date,
    pay.amount AS payment_amount,
    pay.payment_date,
    c.name AS crew_name,
    ro2.role_name AS crew_role
FROM Tickets t
JOIN Passengers p ON p.passenger_id = t.passenger_id
JOIN Flights f ON f.flight_id = t.flight_id
JOIN Airlines a ON a.airline_id = f.airline_id
JOIN Airports dep_air ON dep_air.airport_id = f.departure_airport_id
JOIN Airports arr_air ON arr_air.airport_id = f.arrival_airport_id
LEFT JOIN Luggage l ON l.passenger_id = p.passenger_id
LEFT JOIN Routes r ON r.departure_airport_id = f.departure_airport_id
                  AND r.arrival_airport_id = f.arrival_airport_id
LEFT JOIN Delays d ON d.flight_id = f.flight_id
LEFT JOIN Gates g ON g.airport_id = f.departure_airport_id
LEFT JOIN Staff s ON s.airport_id = f.departure_airport_id
LEFT JOIN Roles ro ON ro.role_id = s.role_id
LEFT JOIN Bookings b ON b.passenger_id = p.passenger_id
                    AND b.flight_id = f.flight_id
LEFT JOIN Payments pay ON pay.booking_id = b.booking_id
LEFT JOIN CrewRoles cr ON cr.role_id = ro.role_id
LEFT JOIN Crew c ON c.crew_id = cr.crew_id
LEFT JOIN Roles ro2 ON ro2.role_id = cr.role_id
ORDER BY f.flight_id, t.ticket_id;

-- Inner join

-- total ticket revenue by flight
SELECT f.flight_id, SUM(t.price) AS total_revenue
FROM Flights f
JOIN Tickets t ON t.flight_id = f.flight_id
GROUP BY f.flight_id;

-- list passengers per flight
SELECT f.flight_id, p.passenger_id, p.name
FROM Flights f
JOIN Tickets t ON t.flight_id = f.flight_id
JOIN Passengers p ON p.passenger_id = t.passenger_id;

-- avg delay minutes by airline
SELECT a.name AS airline, AVG(d.delay_minutes) AS avg_delay
FROM Airlines a
JOIN Flights f ON f.airline_id = a.airline_id
JOIN Delays d ON d.flight_id = f.flight_id
GROUP BY a.name;

-- ticket + booking date (only booked tickets)
SELECT p.name, f.flight_id, b.booking_date
FROM Passengers p
JOIN Tickets t ON t.passenger_id = p.passenger_id
JOIN Flights f ON f.flight_id = t.flight_id
JOIN Bookings b ON b.passenger_id = p.passenger_id AND b.flight_id = f.flight_id;

-- min/max luggage weight per flight
SELECT f.flight_id, MIN(l.weight) AS min_w, MAX(l.weight) AS max_w
FROM Flights f
JOIN Tickets t ON t.flight_id = f.flight_id
JOIN Luggage l ON l.passenger_id = t.passenger_id
GROUP BY f.flight_id;

-- Left join

-- passenger - luggage (include passengers without luggage)
SELECT p.passenger_id, p.name, l.luggage_id, l.weight
FROM Passengers p
LEFT JOIN Luggage l ON l.passenger_id = p.passenger_id;

-- airlines - flights count 
SELECT a.name AS airline, COUNT(f.flight_id) AS flights_count
FROM Airlines a
LEFT JOIN Flights f ON f.airline_id = a.airline_id
GROUP BY a.name;

-- airports - staff (include airports with no staff)
SELECT ap.airport_id, ap.name AS airport, s.staff_id, s.name AS staff_name
FROM Airports ap
LEFT JOIN Staff s ON s.airport_id = ap.airport_id;

-- airports - gates (include airports with no gates)
SELECT ap.airport_id, ap.name, g.gate_number
FROM Airports ap
LEFT JOIN Gates g ON g.airport_id = ap.airport_id;

-- bookings - payments (include unpaid bookings)
SELECT b.booking_id, b.passenger_id, pmt.amount, pmt.payment_date
FROM Bookings b
LEFT JOIN Payments pmt ON pmt.booking_id = b.booking_id;

-- roles - crew via CrewRoles (include roles without crew)
SELECT r.role_name, c.name AS crew_name
FROM Roles r
LEFT JOIN CrewRoles cr ON cr.role_id = r.role_id
LEFT JOIN Crew c ON c.crew_id = cr.crew_id;

-- flights with delays (show flights even if no delay)
SELECT f.flight_id, d.delay_minutes, d.reason
FROM Flights f
LEFT JOIN Delays d ON d.flight_id = f.flight_id;

-- flight ticket revenue (0 if none)
SELECT f.flight_id, COALESCE(SUM(t.price),0) AS revenue
FROM Flights f
LEFT JOIN Tickets t ON t.flight_id = f.flight_id
GROUP BY f.flight_id;

-- flights - routes (distance may be NULL)
SELECT f.flight_id, r.distance_km
FROM Flights f
LEFT JOIN Routes r
  ON r.departure_airport_id = f.departure_airport_id
 AND r.arrival_airport_id   = f.arrival_airport_id;
 
-- Right join

-- tickets right join flights (all flights)
SELECT f.flight_id, t.ticket_id
FROM Tickets t
RIGHT JOIN Flights f ON f.flight_id = t.flight_id;

-- luggage right join passengers (all passengers)
SELECT p.passenger_id, p.name, l.luggage_id, l.weight
FROM Luggage l
RIGHT JOIN Passengers p ON p.passenger_id = l.passenger_id;

-- payments right join bookings (all bookings)
SELECT b.booking_id, pmt.payment_id, pmt.amount
FROM Payments pmt
RIGHT JOIN Bookings b ON b.booking_id = pmt.booking_id;

-- routes right join flights (all flights)
SELECT f.flight_id, r.distance_km
FROM Routes r
RIGHT JOIN Flights f
  ON r.departure_airport_id = f.departure_airport_id
 AND r.arrival_airport_id   = f.arrival_airport_id;

-- staff right join airports (all airports)
SELECT ap.airport_id, ap.name, s.staff_id, s.name AS staff_name
FROM Staff s
RIGHT JOIN Airports ap ON ap.airport_id = s.airport_id;

-- gates right join airports (all airports)
SELECT ap.airport_id, ap.name, g.gate_number
FROM Gates g
RIGHT JOIN Airports ap ON ap.airport_id = g.airport_id;

-- flights right join airlines (all airlines)
SELECT a.name AS airline, f.flight_id
FROM Flights f
RIGHT JOIN Airlines a ON a.airline_id = f.airline_id;

-- crewroles right join roles (all roles)
SELECT r.role_name, cr.crew_id
FROM CrewRoles cr
RIGHT JOIN Roles r ON r.role_id = cr.role_id;

-- staff-role (show all roles)
SELECT r.role_name, s.staff_id, s.name AS staff_name
FROM Staff s
RIGHT JOIN Roles r ON r.role_id = s.role_id;

-- delays right join flights (all flights)
SELECT f.flight_id, d.delay_minutes, d.reason
FROM Delays d
RIGHT JOIN Flights f ON f.flight_id = d.flight_id;

-- Full join

-- flights - tickets (all flights and all tickets)
SELECT f.flight_id, t.ticket_id
FROM Flights f LEFT JOIN Tickets t ON t.flight_id = f.flight_id
UNION
SELECT f.flight_id, t.ticket_id
FROM Flights f RIGHT JOIN Tickets t ON t.flight_id = f.flight_id
WHERE f.flight_id IS NULL;

-- passengers - luggage
SELECT p.passenger_id, l.luggage_id
FROM Passengers p LEFT JOIN Luggage l ON l.passenger_id = p.passenger_id
UNION
SELECT p.passenger_id, l.luggage_id
FROM Passengers p RIGHT JOIN Luggage l ON l.passenger_id = p.passenger_id
WHERE p.passenger_id IS NULL;

-- bookings - payments
SELECT b.booking_id, pmt.payment_id
FROM Bookings b LEFT JOIN Payments pmt ON pmt.booking_id = b.booking_id
UNION
SELECT b.booking_id, pmt.payment_id
FROM Bookings b RIGHT JOIN Payments pmt ON pmt.booking_id = b.booking_id
WHERE b.booking_id IS NULL;

-- flights - routes
SELECT f.flight_id, r.route_id
FROM Flights f LEFT JOIN Routes r
  ON r.departure_airport_id = f.departure_airport_id AND r.arrival_airport_id = f.arrival_airport_id
UNION
SELECT f.flight_id, r.route_id
FROM Flights f RIGHT JOIN Routes r
  ON r.departure_airport_id = f.departure_airport_id AND r.arrival_airport_id = f.arrival_airport_id
WHERE r.route_id IS NULL;

-- airlines - flights
SELECT a.airline_id, f.flight_id
FROM Airlines a LEFT JOIN Flights f ON f.airline_id = a.airline_id
UNION
SELECT a.airline_id, f.flight_id
FROM Airlines a RIGHT JOIN Flights f ON f.airline_id = a.airline_id
WHERE a.airline_id IS NULL;

-- airports - gates
SELECT ap.airport_id, g.gate_id
FROM Airports ap LEFT JOIN Gates g ON g.airport_id = ap.airport_id
UNION
SELECT ap.airport_id, g.gate_id
FROM Airports ap RIGHT JOIN Gates g ON g.airport_id = ap.airport_id
WHERE ap.airport_id IS NULL;

-- airports - staff
SELECT ap.airport_id, s.staff_id
FROM Airports ap LEFT JOIN Staff s ON s.airport_id = ap.airport_id
UNION
SELECT ap.airport_id, s.staff_id
FROM Airports ap RIGHT JOIN Staff s ON s.airport_id = ap.airport_id
WHERE ap.airport_id IS NULL;

-- roles - staff
SELECT r.role_id, s.staff_id
FROM Roles r LEFT JOIN Staff s ON s.role_id = r.role_id
UNION
SELECT r.role_id, s.staff_id
FROM Roles r RIGHT JOIN Staff s ON s.role_id = r.role_id
WHERE r.role_id IS NULL;

-- roles - crewroles
SELECT r.role_id, cr.crew_id
FROM Roles r LEFT JOIN CrewRoles cr ON cr.role_id = r.role_id
UNION
SELECT r.role_id, cr.crew_id
FROM Roles r RIGHT JOIN CrewRoles cr ON cr.role_id = r.role_id
WHERE r.role_id IS NULL;

-- crew - crewroles
SELECT c.crew_id, cr.role_id
FROM Crew c LEFT JOIN CrewRoles cr ON cr.crew_id = c.crew_id
UNION
SELECT c.crew_id, cr.role_id
FROM Crew c RIGHT JOIN CrewRoles cr ON cr.crew_id = c.crew_id
WHERE c.crew_id IS NULL;

-- Group by

-- ticket revenue per airline
SELECT a.name AS airline, SUM(t.price) AS revenue
FROM Airlines a
JOIN Flights f ON f.airline_id = a.airline_id
JOIN Tickets t ON t.flight_id = f.flight_id
GROUP BY a.name;

-- avg luggage weight per flight
SELECT f.flight_id, AVG(l.weight) AS avg_luggage_kg
FROM Flights f
JOIN Tickets t ON t.flight_id = f.flight_id
JOIN Luggage l ON l.passenger_id = t.passenger_id
GROUP BY f.flight_id;

-- delays per reason
SELECT d.reason, COUNT(*) AS occurrences, SUM(d.delay_minutes) AS total_minutes
FROM Delays d
GROUP BY d.reason;

-- Having

-- flights with revenue > 200
SELECT f.flight_id, SUM(t.price) AS revenue
FROM Flights f
JOIN Tickets t ON t.flight_id = f.flight_id
GROUP BY f.flight_id
HAVING SUM(t.price) > 200;

-- airlines with avg delay >= 20
SELECT a.name AS airline, AVG(d.delay_minutes) AS avg_delay
FROM Airlines a
JOIN Flights f ON f.airline_id = a.airline_id
JOIN Delays d ON d.flight_id = f.flight_id
GROUP BY a.name
HAVING AVG(d.delay_minutes) >= 20;

-- flights whose total luggage > 25 kg
SELECT f.flight_id, SUM(l.weight) AS total_luggage
FROM Flights f
JOIN Tickets t ON t.flight_id = f.flight_id
JOIN Luggage l ON l.passenger_id = t.passenger_id
GROUP BY f.flight_id
HAVING SUM(l.weight) > 25;