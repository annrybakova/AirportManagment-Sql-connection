package com.solvd.airport.utils.xmlParser;

import com.solvd.airport.models.Booking;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BookingSAXParser extends DefaultHandler {
    private List<Booking> bookings = new ArrayList<>();
    private Booking booking;
    private StringBuilder data;

    public List<Booking> parse(String filePath) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(filePath), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("Booking")) {
            booking = new Booking();
        }
        data = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        data.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (booking != null) {
            switch (qName) {
                case "bookingId":
                    booking.setBookingId(Integer.parseInt(data.toString()));
                    break;
                case "passengerId":
                    booking.setPassengerId(Integer.parseInt(data.toString()));
                    break;
                case "flightId":
                    booking.setFlightId(Integer.parseInt(data.toString()));
                    break;
                case "bookingDate":
                    booking.setBookingDate(data.toString());
                    break;
                case "Booking":
                    bookings.add(booking);
                    break;
            }
        }
    }
}
