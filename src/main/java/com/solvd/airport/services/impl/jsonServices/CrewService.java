package com.solvd.airport.services.impl.jsonServices;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.airport.models.Crew;
import com.solvd.airport.models.CrewRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.File;
import java.io.IOException;
import java.util.List;

public class CrewService {
    private static final Logger logger = LogManager.getLogger(CrewService.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public List<Crew> loadCrews() {
        try {
            List<Crew> crews = mapper.readValue(
                    new File("src/main/resources/json/crew.json"),
                    new TypeReference<List<Crew>>() {}
            );
            logger.info("Loaded crews: {}", crews);
            return crews;
        } catch (IOException e) {
            logger.error("Error reading crew.json", e);
            throw new RuntimeException(e);
        }
    }

    public List<CrewRole> loadCrewRoles() {
        try {
            List<CrewRole> crewRoles = mapper.readValue(
                    new File("src/main/resources/json/crew_roles.json"),
                    new TypeReference<List<CrewRole>>() {}
            );
            logger.info("Loaded crew roles: {}", crewRoles);
            return crewRoles;
        } catch (IOException e) {
            logger.error("Error reading crewRoles.json", e);
            throw new RuntimeException(e);
        }
    }
}

