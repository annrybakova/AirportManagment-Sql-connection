# Airport Management System

This project is a **Java-based Airport Management System** that demonstrates the use of:

- **JDBC** with a custom **Connection Pool** for database interactions  
- **DAO layer** to separate data access logic  
- **Service layer** to encapsulate business logic  
- **Models** with getters/setters  
- **XML parsing** with **SAX Parser** (for Booking and Payment tables)  
- **Logging** with **SLF4J + Logback**  
- **Main class** that interacts only with services  

---

## Project Structure
src/main/java/com/solvd/airport
│── dao/ # DAO interfaces & JDBC implementations
│── models/ # Models (Airline, Airport, Flight, Passenger, Booking, Payment, etc.)
│── services/ # Business logic, XML services
│── utils/ # ConnectionPool, XML parsers
│── App.java # Entry point of the app


---

## Technologies Used
- **Java 21**
- **JDBC**
- **MySQL**
- **SAX Parser** for XML
- **Apache Commons Lang 3** (for toString)

---

## Database Schema
The project includes tables such as:
- `Airlines`
- `Airports`
- `Flights`
- `Passengers`
- `Bookings`
- `Payments`

Two of these tables (`Bookings`, `Payments`) are also represented in **XML** and parsed with **SAX Parser**.

---

## How to Run

### 1. Clone repository
```bash
git clone https://github.com/annrybakova/Java/Airport%20(Sql%20connection).git
cd Airport%20(Sql%20connection)
```
### 2. Set up database

Run the SQL scripts from 
```bash
resources/db/init.sql
```
to create and populate the database.
### 3. Configure database connection

Edit resources/database.properties:
```bash
db.url=jdbc:mysql://localhost:3306/airport
db.user=root
db.password=your_password
```
### 4. Build & run
```bash
mvn clean install
java -cp target/Airport-1.0-SNAPSHOT.jar com.solvd.App
```