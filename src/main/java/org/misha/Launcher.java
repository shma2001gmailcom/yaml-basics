package org.misha;

import org.apache.log4j.Logger;
import org.misha.yaml.ConnectionFactory;
import org.misha.yaml.User;
import org.misha.yaml.YamlRepoImpl;

import java.io.IOException;
import java.util.HashMap;

import static org.apache.commons.lang3.RandomStringUtils.*;

public class Launcher {
    private static final Logger log = Logger.getLogger(Launcher.class);
    public static void main(String... args) throws IOException {
        YamlPrinter.main(new String[]{null});
        YamlRepoImpl repo = new YamlRepoImpl(new ConnectionFactory());
        User user = makeUser();
        repo.save(user);
        log.info(repo.findById("Pit"));
        log.info(repo.findAll());
    }

    private static User makeUser() {
        return User.getBuilder().addName("Pit").addAge(7).addAddress(new HashMap<String, String>() {
            {
                put("country name", "USA");
                put("city name", "Washington D.C.");
                put("zip code", randomNumeric(5));
                put("street name", randomAlphabetic(5));
                put("house number", randomNumeric(2) + "/" + randomNumeric(2));
                put("room number", null);
            }
        }).addRoles("author", "editor").build();
    }
}
