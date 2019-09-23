package json;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.reflect.TypeToken;
import models.CompetitionURL;
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
                "        \"dateOfCompetition\": \"Sun 8 Sep 19\",\n" +
                "        \"competitionTitle\": \"Millennium Cup\",\n" +
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

}