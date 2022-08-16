package br.com.trackertemplateback.util.properties;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * Obtem os valores de um determinado arquivo de propriedades.
 *
 * @author Cedric Christian on 30/04/2021
 */
public final class GetProperties {

    public enum FilePropertiesEnum {
        APPLICATION("application.properties"),
        APPLICATIONDEV("application-dev.properties"),
        APPLICATIONPROD("application-prod.properties"),
        SWAGGERMESSAGE("swaggerMessage.properties");

        private String description;

        FilePropertiesEnum(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * Obtem o arquivo de propriedades.
     *
     * @param filePropertiesEnum Arquivo de propriedades que sera referenciado
     * @return Objeto contendo o arquivo de propriedades
     */
    public static Properties propertiesFile(FilePropertiesEnum filePropertiesEnum) {
        Properties properties = new Properties();

        try {
            URL resource = GetProperties.class.getClassLoader().getResource(filePropertiesEnum.getDescription());
            properties.load(new InputStreamReader(resource.openStream(), "UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
