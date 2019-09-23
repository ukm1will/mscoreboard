package backend;

import enums.DataResponseType;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.reflect.TypeToken;
import models.Competition;
import models.CompetitionURL;
import models.Golfer;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.StringHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestBackend {

    @Test
    public void doNothing() throws IOException {

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
