package org.misha;

import org.misha.yaml.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class YamlConfigRunner {

    public static Configuration run() throws IOException {
        try (InputStream in = Files.newInputStream(Paths.get("config/app-conf.yaml"))) {
            return new Yaml().loadAs(in, Configuration.class);
        }
    }
}
