package br.com.trackertemplateback.util;

import br.com.trackertemplateback.util.crypt.PasswordEncoder;

public class test {
    public static void main(String[] args) {

        System.out.println(PasswordEncoder.encode("abobrinha"));

/*        String jsonText = "{\"20211210\":{\"i\":0},\"20211211\":{\"i\":1}}";
        JSONObject jsonObject = new JSONObject(jsonText);

        jsonObject.keySet().forEach(keyStr ->
        {
            Object keyvalue = jsonObject.get(keyStr);
            System.out.println("key: "+ keyStr + " value: " + keyvalue);

            JSONObject test = (JSONObject) jsonObject.get(keyStr);
            System.out.println(test.get("i").toString());
        });*/
    }
}
/*
@Component
public class test {

    @Value("${springdoc.swagger-ui.path}")
    public static String testApplication;

    @Value("${spring.datasource.url}")
    public static String testApplicationDev;
}
*/