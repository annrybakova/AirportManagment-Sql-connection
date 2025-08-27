DROP DATABASE IF EXISTS airport;
CREATE DATABASE airport;
USE airport;

DROP TABLE IF EXISTS Delays;
DROP TABLE IF EXISTS Payments;
DROP TABLE IF EXISTS Bookings;
DROP TABLE IF EXISTS CrewRoles;
DROP TABLE IF EXISTS Crew;
DROP TABLE IF EXISTS Staff;
DROP TABLE IF EXISTS Roles;
DROP TABLE IF EXISTS Luggage;
DROP TABLE IF EXISTS Tickets;
DROP TABLE IF EXISTS Flights;
DROP TABLE IF EXISTS Routes;
DROP TABLE IF EXISTS Gates;
DROP TABLE IF EXISTS Passengers;
DROP TABLE IF EXISTS Airlines;
DROP TABLE IF EXISTS Airports;

CREATE TABLE Passengers (
    passenger_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(150),
    passport_number VARCHAR(50),
    PRIMARY KEY (passenger_id)
);

CREATE TABLE Airlines (
    airline_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    country VARCHAR(100),
    PRIMARY KEY (airline_id)
);

CREATE TABLE Airports (
    airport_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    city VARCHAR(100),
    country VARCHAR(100),
    PRIMARY KEY (airport_id)
);

CREATE TABLE Flights (
    flight_id INT NOT NULL AUTO_INCREMENT,
    airline_id INT,
    departure_airport_id INT,
    arrival_airport_id INT,
    departure_time DATETIME,
    arrival_time DATETIME,
    PRIMARY KEY (flight_id),
    CONSTRAINT fk_flight_airline FOREIGN KEY (airline_id) REFERENCES Airlines(airline_id),
    CONSTRAINT fk_flight_departure_airport FOREIGN KEY (departure_airport_id) REFERENCES Airports(airport_id),
    CONSTRAINT fk_flight_arrival_airport FOREIGN KEY (arrival_airport_id) REFERENCES Airports(airport_id)
);

CREATE TABLE Tickets (
    ticket_id INT NOT NULL AUTO_INCREMENT,
    passenger_id INT,
    flight_id INT,
    seat_number VARCHAR(10),
    price DECIMAL(10,2),
    PRIMARY KEY (ticket_id),
    CONSTRAINT fk_ticket_passenger FOREIGN KEY (passenger_id) REFERENCES Passengers(passenger_id),
    CONSTRAINT fk_ticket_flight FOREIGN KEY (flight_id) REFERENCES Flights(flight_id)
);

CREATE TABLE Luggage (
    luggage_id INT NOT NULL AUTO_INCREMENT,
    passenger_id INT,
    weight DECIMAL(5,2),
    PRIMARY KEY (luggage_id),
    CONSTRAINT fk_luggage_passenger FOREIGN KEY (passenger_id) REFERENCES Passengers(passenger_id)
);

CREATE TABLE Gates (
    gate_id INT NOT NULL AUTO_INCREMENT,
    airport_id INT,
    gate_number VARCHAR(10),
    PRIMARY KEY (gate_id),
    CONSTRAINT fk_gate_airport FOREIGN KEY (airport_id) REFERENCES Airports(airport_id)
);

CREATE TABLE Roles (
    role_id INT NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(100),
    PRIMARY KEY (role_id)
);

CREATE TABLE Staff (
    staff_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    role_id INT,
    airport_id INT,
    PRIMARY KEY (staff_id),
    CONSTRAINT fk_staff_role FOREIGN KEY (role_id) REFERENCES Roles(role_id),
    CONSTRAINT fk_staff_airport FOREIGN KEY (airport_id) REFERENCES Airports(airport_id)

);

CREATE TABLE Crew (
    crew_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    PRIMARY KEY (crew_id)
);

CREATE TABLE CrewRoles (
    crew_id INT,
    role_id INT,
    CONSTRAINT fk_crewroles_crew FOREIGN KEY (crew_id) REFERENCES Crew(crew_id),
    CONSTRAINT fk_crewroles_role FOREIGN KEY (role_id) REFERENCES Roles(role_id)

);

CREATE TABLE Routes (
    route_id INT NOT NULL AUTO_INCREMENT,
    departure_airport_id INT,
    arrival_airport_id INT,
    distance_km DECIMAL(8,2),
    PRIMARY KEY (route_id),
	CONSTRAINT fk_route_departure_airport FOREIGN KEY (departure_airport_id) REFERENCES Airports(airport_id),
    CONSTRAINT fk_route_arrival_airport FOREIGN KEY (arrival_airport_id) REFERENCES Airports(airport_id)

);

CREATE TABLE Bookings (
    booking_id INT NOT NULL AUTO_INCREMENT,
    passenger_id INT,
    flight_id INT,
    booking_date DATETIME,
    PRIMARY KEY (booking_id),
    CONSTRAINT fk_booking_passenger FOREIGN KEY (passenger_id) REFERENCES Passengers(passenger_id),
    CONSTRAINT fk_booking_flight FOREIGN KEY (flight_id) REFERENCES Flights(flight_id)

);

CREATE TABLE Payments (
    payment_id INT NOT NULL AUTO_INCREMENT,
    booking_id INT,
    amount DECIMAL(10,2),
    payment_date DATETIME,
    PRIMARY KEY (payment_id),
    CONSTRAINT fk_payment_booking FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id)
);

CREATE TABLE Delays (
    delay_id INT NOT NULL AUTO_INCREMENT,
    flight_id INT,
    delay_minutes INT,
    reason VARCHAR(255),
    PRIMARY KEY (delay_id),
    CONSTRAINT fk_delay_flight FOREIGN KEY (flight_id) REFERENCES Flights(flight_id)
);

INSERT INTO Passengers (name, email, passport_number) VALUES
('Chipolino', 'chipolino@example.com', 'P123456'),
('Onion Prince', 'onion.prince@example.com', 'P654321'),
('Tomato General', 'tomato.general@example.com', 'P111222'),
('Pea Soldier', 'pea.soldier@example.com', 'P333444');

INSERT INTO Airlines (name, country) VALUES
('Vegetable Airlines', 'Italy'),
('Fruit Express', 'France');

INSERT INTO Airports (name, city, country) VALUES
('Veggie International', 'VeggieTown', 'Italy'),
('Fruit National', 'FruitCity', 'France');

INSERT INTO Flights (airline_id, departure_airport_id, arrival_airport_id, departure_time, arrival_time) VALUES
(1, 1, 2, '2025-08-15 08:00:00', '2025-08-15 10:00:00'),
(2, 2, 1, '2025-08-15 15:00:00', '2025-08-15 17:00:00');

INSERT INTO Tickets (passenger_id, flight_id, seat_number, price) VALUES
(1, 1, 'A1', 120.00),
(2, 1, 'A2', 120.00),
(3, 2, 'B1', 150.00),
(4, 2, 'B2', 150.00);

INSERT INTO Luggage (passenger_id, weight) VALUES
(1, 15.5),
(2, 12.0),
(3, 20.3),
(4, 10.0);

INSERT INTO Gates (airport_id, gate_number) VALUES
(1, 'G1'),
(2, 'H2');

INSERT INTO Roles (role_name) VALUES
('Pilot'),
('Flight Attendant'),
('Ground Staff');

INSERT INTO Staff (name, role_id, airport_id) VALUES
('Captain Carrot', 1, 1),
('Miss Lettuce', 2, 2),
('Mr. Cucumber', 3, 1);

INSERT INTO Crew (name) VALUES
('Broccoli Captain'),
('Strawberry Attendant');

INSERT INTO CrewRoles (crew_id, role_id) VALUES
(1, 1),
(2, 2);

INSERT INTO Routes (departure_airport_id, arrival_airport_id, distance_km) VALUES
(1, 2, 1200.50),
(2, 1, 1200.50);

INSERT INTO Bookings (passenger_id, flight_id, booking_date) VALUES
(1, 1, '2025-08-10 09:00:00'),
(2, 1, '2025-08-10 09:05:00');

INSERT INTO Payments (booking_id, amount, payment_date) VALUES
(1, 120.00, '2025-08-10 09:10:00'),
(2, 120.00, '2025-08-10 09:15:00');

INSERT INTO Delays (flight_id, delay_minutes, reason) VALUES
(1, 15, 'Strong wind'),
(2, 30, 'Technical check');