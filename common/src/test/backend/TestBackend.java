package backend;

import enums.DataResponseType;
import models.Competition;
import models.Golfer;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.StringHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TestBackend {

//    @RequestMapping(method = RequestMethod.GET, value = "/singlecompetition", produces = "application/json")
//    public List<Golfer> getSingleCompetition() throws Exception {
//        String dataSource = getDataSource("http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=5315", DataResponseType.TEXT);
//        dataSource = StringHelper.splitBeforeAndAfter(dataSource, "Handicap\n", "Number of Cards Processed");
//        Competition competition = new Competition(dataSource);
//        competition.addResultsToCompetition(dataSource);
//        competition.addGolfersToCompetition();
//        return competition.golfers;
//    }

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

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        conn.disconnect();
    }
}
