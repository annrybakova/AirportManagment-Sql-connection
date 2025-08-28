package com.solvd.airport.models;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Bookings")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bookings {

    @XmlElement(name = "Booking")
    private List<Booking> items = new ArrayList<>();

    public List<Booking> getItems() {
        return items;
    }

    public void setItems(List<Booking> items) {
        this.items = items;
    }
}
