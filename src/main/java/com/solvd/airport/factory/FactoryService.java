package com.solvd.airport.factory;

import com.solvd.airport.services.impl.AirportService;
import com.solvd.airport.services.impl.FlightService;
import com.solvd.airport.services.impl.PassengerService;
import com.solvd.airport.services.impl.StaffService;
import com.solvd.airport.services.impl.TicketService;
import com.solvd.airport.services.interfaces.*;
import com.solvd.airport.services.mybatisimpl.*;
import com.solvd.airport.utils.MyBatisUtil;

public class FactoryService {
    private static final boolean USE_MYBATIS = true;

    public static IAirportService createAirportService() {
        if (USE_MYBATIS) {
            return new AirportServiceMyBatis();
        } else {
            return new AirportService();
        }
    }

    public static IFlightService createFlightService() {
        if (USE_MYBATIS) {
            return new FlightServiceMyBatis();
        } else {
            return new FlightService();
        }
    }

    public static IPassengerService createPassengerService() {
        if (USE_MYBATIS) {
            return new PassengerServiceMyBatis();
        } else {
            return new PassengerService();
        }
    }

    public static IStaffService createStaffService() {
        if (USE_MYBATIS) {
            return new StaffServiceMyBatis();
        } else {
            return new StaffService();
        }
    }

    public static ITicketService createTicketService() {
        if (USE_MYBATIS) {
            return new TicketServiceMyBatis();
        } else {
            return new TicketService();
        }
    }
}
