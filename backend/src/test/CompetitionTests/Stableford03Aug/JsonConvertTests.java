package CompetitionTests.Stableford03Aug;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.michaelangela.models.Competition;
import com.michaelangela.models.CompetitionURL;
import com.michaelangela.models.Person;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonConvertTests {


    @Test
    public void ShouldReturnListOfURLFromJsonString() throws IOException {

        String json = "[" +
                "    {" +
                "        \"dateOfCompetition\": \"Sun 8 Sep 19\",\n" +
                "        \"competitionTitle\": \"Millennium Cup\",\n" +
                "        \"url\": \"http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=5316\"" +
                "    }," +
                "    {" +
                "        \"dateOfCompetition\": \"Sun 8 Sep 19\"," +
                "        \"competitionTitle\": \"Junior 9 Hole Comp 1-5 7,12,13,14\"," +
                "        \"url\": \"http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=5317\"" +
                "    }" +
                "]";

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<CompetitionURL>>() {}.getType();
        List<CompetitionURL> urls = gson.fromJson(json, listType);
        System.out.println(urls.toString());
    }

    @Test
    public void ShouldReturnListOfURLFromService() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject("http://localhost:9090/urls", String.class);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<CompetitionURL>>() {
        }.getType();
        List<CompetitionURL> urls = gson.fromJson(json, listType);
        System.out.println(urls.toString());
    }
}