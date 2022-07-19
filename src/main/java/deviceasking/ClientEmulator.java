package deviceasking;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static deviceasking.util.Util.getBytesFromUrl;
import static deviceasking.util.Util.getPropFromYaml;
/**
 * This class emulates client activity
 */
public class ClientEmulator {

    public static String INIT_ADRESS;

    public static List<String> argsList;

    static {
        try {
            Map<String, String> prop = getPropFromYaml("src/main/resources/application.yaml");
            INIT_ADRESS = prop.get("adress");
            argsList = List.of(prop.get("arg1"), prop.get("arg2"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        URL url = new URL(INIT_ADRESS);
        String contentAsString;
        try {
            contentAsString = new String(getBytesFromUrl(url));
        } catch (ConnectException e) {
            System.out.println("Please run DeviceServerApplication first");
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map> allDevicesInfo = objectMapper.readValue(contentAsString, List.class);
        System.out.println("Availible devices:");
        allDevicesInfo.forEach(System.out::println);

        System.out.println("====================================================");

        List<String> ids = allDevicesInfo.stream()
                .map(map -> map.get("id").toString())
                .collect(Collectors.toList());
        ids.add("non_existent_id");

        int idsAmount = ids.size();
        int argsAmount = argsList.size();

        System.out.println("Random asking:");
        String randomAdress;
        Random random = new Random();
        while (true) {
            randomAdress = String.format("%s%s/ask?param=%s", INIT_ADRESS,
                    ids.get(random.nextInt(idsAmount)),
                    argsList.get(random.nextInt(argsAmount)));

            url = new URL(randomAdress);
            contentAsString = new String(getBytesFromUrl(url));
            System.out.println(contentAsString);
            Thread.sleep(1000);
        }
    }
}
