package com.solvd.airport.services.impl;

import com.solvd.airport.models.Payment;
import com.solvd.airport.utils.xmlParser.PaymentSAXParser;

import java.util.List;

public class XmlPaymentService {
    private final PaymentSAXParser parser = new PaymentSAXParser();

    public List<Payment> getPaymentsFromXml(String filePath) {
        return parser.parse(filePath);
    }
}
