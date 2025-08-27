package com.solvd.airport.utils.xmlParser;

import com.solvd.airport.models.Payment;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PaymentSAXParser extends DefaultHandler {
    private List<Payment> payments = new ArrayList<>();
    private Payment payment;
    private StringBuilder data;

    public List<Payment> parse(String filePath) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(filePath), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("Payment")) {
            payment = new Payment();
        }
        data = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        data.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (payment != null) {
            switch (qName) {
                case "paymentId":
                    payment.setPaymentId(Integer.parseInt(data.toString()));
                    break;
                case "bookingId":
                    payment.setBookingId(Integer.parseInt(data.toString()));
                    break;
                case "amount":
                    payment.setAmount(Double.parseDouble(data.toString()));
                    break;
                case "paymentDate":
                    payment.setPaymentDate(data.toString());
                    break;
                case "Payment":
                    payments.add(payment);
                    break;
            }
        }
    }
}
