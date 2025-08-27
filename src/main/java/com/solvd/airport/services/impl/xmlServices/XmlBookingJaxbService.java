package com.solvd.airport.services.impl.xmlServices;

import com.solvd.airport.models.Booking;
import com.solvd.airport.models.Bookings;
import jakarta.xml.bind.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class XmlBookingJaxbService {
    private static final Logger logger = LogManager.getLogger(XmlBookingJaxbService.class);

    public List<Booking> readBookings(String xmlPath, String xsdPath) {
        try (InputStream xml = Files.newInputStream(Path.of(xmlPath));
                InputStream xsd = Files.newInputStream(Path.of(xsdPath))) {

            JAXBContext ctx = JAXBContext.newInstance(Bookings.class);
            Unmarshaller um = ctx.createUnmarshaller();

            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new StreamSource(xsd));
            um.setSchema(schema);

            Bookings bookings = (Bookings) um.unmarshal(xml);
            List<Booking> list = bookings.getItems();
            logger.info("JAXB: validated and loaded {} bookings from {}", list.size(), xmlPath);
            return list;
        } catch (SAXException e) {
            throw new RuntimeException("Schema problem: " + e.getMessage(), e);
        } catch (JAXBException e) {
            throw new RuntimeException("Unmarshalling problem: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("I/O problem: " + e.getMessage(), e);
        }
    }

    public void writeBookings(List<Booking> bookings, String xmlPath, String xsdPath) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(Bookings.class);
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            Bookings wrapper = new Bookings();
            wrapper.setItems(bookings);

            Path out = Path.of(xmlPath);
            Files.createDirectories(out.getParent());
            m.marshal(wrapper, out.toFile());
            logger.info("JAXB: wrote {} bookings to {}", bookings.size(), xmlPath);

            // Validation
            readBookings(xmlPath, xsdPath);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException("Marshalling problem: " + e.getMessage(), e);
        }
    }
}
