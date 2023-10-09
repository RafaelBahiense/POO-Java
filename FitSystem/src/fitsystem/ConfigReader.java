package fitsystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties props;

    public ConfigReader(String path) throws IOException {
        props = new Properties();
        var in = new FileInputStream(path);
        props.load(in);
        in.close();
    }

    public String getValue(String key) {
        return props.getProperty(key);
    }
}