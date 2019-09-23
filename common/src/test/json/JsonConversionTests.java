package json;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.reflect.TypeToken;
import models.CompetitionURL;
import models.Golfer;
import models.StablefordGolfer;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonConversionTests {

    @Test
    public void ShouldReturnListOfURLFromJsonString() throws IOException {

        String json = "[" +
                "    {" +
                "        \"dateOfCompetition\": \"Sun 8 Sep 19\"," +
                "        \"competitionTitle\": \"Millennium Cup\"," +
                "        \"url\": \"http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=5316\"" +
                "    }," +
                "    {" +
                "        \"dateOfCompetition\": \"Sun 8 Sep 19\"," +
                "        \"competitionTitle\": \"Junior 9 Hole Comp\"," +
                "        \"url\": \"http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=5317\"" +
                "    }" +
                "]";

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<CompetitionURL>>() {}.getType();
        List<CompetitionURL> urls = gson.fromJson(json, listType);
        System.out.println(CompetitionURL.toString(urls));
    }


    @Test
    public void ShouldReturnListOfStablefordGolfersFromJsonString() throws IOException {

        String json = "[" +
                " {\n" +
                "        \"position\": 2,\n" +
                "        \"firstName\": \"Gareth J\",\n" +
                "        \"lastName\": \"Davies\",\n" +
                "        \"gross\": 78,\n" +
                "        \"nett\": 68,\n" +
                "        \"handicap\": 10,\n" +
                "        \"pts\": 40\n" +
                "    },\n" +
                "    {\n" +
                "        \"position\": 3,\n" +
                "        \"firstName\": \"Chris\",\n" +
                "        \"lastName\": \"Holwill\",\n" +
                "        \"gross\": 79,\n" +
                "        \"nett\": 69,\n" +
                "        \"handicap\": 10,\n" +
                "        \"pts\": 39\n" +
                "    },\n" +
                "    {\n" +
                "        \"position\": 4,\n" +
                "        \"firstName\": \"Ceri\",\n" +
                "        \"lastName\": \"Dawes\",\n" +
                "        \"gross\": 84,\n" +
                "        \"nett\": 70,\n" +
                "        \"handicap\": 14,\n" +
                "        \"pts\": 38\n" +
                "    }" +
                "]";

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<StablefordGolfer>>() {}.getType();
        List<StablefordGolfer> stablefordGolfers = gson.fromJson(json, listType);
        System.out.println(StablefordGolfer.toString(stablefordGolfers));
    }
}
