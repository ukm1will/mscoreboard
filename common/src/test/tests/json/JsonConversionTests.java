package tests.json;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.reflect.TypeToken;
import models.CompetitionURL;
import models.StablefordGolfer;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        assertEquals(2, urls.size());
    }


    @Test
    public void ShouldReturnListOfStablefordGolfersFromJsonString() throws IOException {

        String json = "[" +
                " {" +
                "        \"position\": 2," +
                "        \"firstName\": \"Gareth J\"," +
                "        \"lastName\": \"Davies\"," +
                "        \"gross\": 78," +
                "        \"nett\": 68," +
                "        \"handicap\": 10," +
                "        \"pts\": 40" +
                "    }," +
                "    {" +
                "        \"position\": 3," +
                "        \"firstName\": \"Chris\"," +
                "        \"lastName\": \"Holwill\"," +
                "        \"gross\": 79," +
                "        \"nett\": 69," +
                "        \"handicap\": 10," +
                "        \"pts\": 39" +
                "    }," +
                "    {" +
                "        \"position\": 4," +
                "        \"firstName\": \"Ceri\"," +
                "        \"lastName\": \"Dawes\"," +
                "        \"gross\": 84," +
                "        \"nett\": 70," +
                "        \"handicap\": 14," +
                "        \"pts\": 38" +
                "    }" +
                "]";

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<StablefordGolfer>>() {}.getType();
        List<StablefordGolfer> stablefordGolfers = gson.fromJson(json, listType);
        assertEquals(3, stablefordGolfers.size());
    }
}
