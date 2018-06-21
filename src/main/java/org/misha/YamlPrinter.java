package org.misha;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.misha.yaml.User;

import java.io.File;

import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

public class YamlPrinter {
private static final Logger log = Logger.getLogger(YamlPrinter.class);

    public static void main(String[] args) {
        try {
            log.info(ReflectionToStringBuilder.toString(
                    new ObjectMapper(new YAMLFactory()).readValue(new File("config/test-user.yaml"), User.class),
                    MULTI_LINE_STYLE
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}