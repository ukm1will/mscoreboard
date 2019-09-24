package TestAPI;


import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class TestAPIService {
    @Test
    public void ShouldReturnListOfGolfersInCompetition() throws IOException {

        URL url = new URL("http://localhost:9090/singlecompetition");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String responseBody = br.lines().collect(Collectors.joining());
        conn.disconnect();

        System.out.println(responseBody);

    }
}

