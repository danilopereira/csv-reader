import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {

        String csvFile = "src/main/resources/users2.csv";

        CSVReader reader = null;
        List<CBOCreateUserRequest> requests = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(csvFile), ',');
            String[] line;
            while ((line = reader.readNext()) != null) {
                CBOCreateUserRequest cboCreateUserRequest = new CBOCreateUserRequest();
                cboCreateUserRequest.setClientId(Arrays.asList(Long.valueOf(line[0])));
                cboCreateUserRequest.setName(line[1]);
                cboCreateUserRequest.setEmail(line[2]);

                requests.add(cboCreateUserRequest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        requests.forEach(cboCreateUserRequest -> {
            RestTemplate restTemplate = new RestTemplate();
            System.out.println("Criando Usuário do ClientID: "+ cboCreateUserRequest.getClientId().get(0));
            restTemplate.postForObject("http://localhost:8086/api/v3/cbo/user", cboCreateUserRequest,Object.class);
        });
    }

}