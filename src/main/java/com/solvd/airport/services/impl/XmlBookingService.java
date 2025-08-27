package com.solvd.airport.services.impl;

import com.solvd.airport.models.Booking;
import com.solvd.airport.utils.xmlParser.BookingSAXParser;

import java.util.List;

public class XmlBookingService {
    private final BookingSAXParser parser = new BookingSAXParser();

    public List<Booking> getBookingsFromXml(String filePath) {
        return parser.parse(filePath);
    }
}
