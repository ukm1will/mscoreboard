package json;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.reflect.TypeToken;
import models.CompetitionMetadata;
import models.Golfer;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonConversionTests {

    @Test
    public void ShouldConvertJSONToListOfCompetitionMetaData() {
        String json = "[" +
                "    {" +
                "        \"dateOfCompetition\": \"Thu 3 Oct 19\"," +
                "        \"competitionTitle\": \"Club Stableford\"," +
                "        \"viewId\": 5343" +
                "    }," +
                "    {" +
                "        \"dateOfCompetition\": \"Wed 2 Oct 19\"," +
                "        \"competitionTitle\": \"Over 55s Stableford\"," +
                "        \"viewId\": 5342" +
                "    }," +
                "    {" +
                "        \"dateOfCompetition\": \"Sun 29 Sep 19\"," +
                "        \"competitionTitle\": \"Summer Pairs\"," +
                "        \"viewId\": 5341" +
                "    }," +
                "    {" +
                "        \"dateOfCompetition\": \"Sat 28 Sep 19\"," +
                "        \"competitionTitle\": \"Club Stableford\"," +
                "        \"viewId\": 5339" +
                "    }" +
                "]";

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<CompetitionMetadata>>() {}.getType();
        List<CompetitionMetadata> urls = gson.fromJson(json, listType);
        CompetitionMetadata competitionMetadata = urls.get(0);
        assertEquals(4, urls.size());
        assertEquals("Thu 3 Oct 19", competitionMetadata.getDateOfCompetition());
        assertEquals("Club Stableford", competitionMetadata.getCompetitionTitle());
        assertEquals(5343, competitionMetadata.getViewId());
    }

    @Test
    public void ShouldConvertJSONToListOfStablefordGolfers() {
        String json = "[" +
                "    {" +
                "        \"gross\": 85," +
                "        \"nett\": 72," +
                "        \"handicap\": 13," +
                "        \"position\": 1," +
                "        \"pts\": 36," +
                "        \"fullName\": \"B. M. Dobson\"" +
                "    }," +
                "    {" +
                "        \"gross\": 87," +
                "        \"nett\": 74," +
                "        \"handicap\": 13," +
                "        \"position\": 2," +
                "        \"pts\": 34," +
                "        \"fullName\": \"Neil L. Lahif\"" +
                "    }," +
                "    {" +
                "        \"gross\": 94," +
                "        \"nett\": 75," +
                "        \"handicap\": 19," +
                "        \"position\": 3," +
                "        \"pts\": 33," +
                "        \"fullName\": \"Steve Grainger\"" +
                "    }]";

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Golfer>>() {}.getType();
        List<Golfer> golfers = gson.fromJson(json, listType);
        Golfer golfer = golfers.get(0);

        assertEquals(3, golfers.size());
        assertEquals(1, golfer.getPosition());
        assertEquals("B. M. Dobson", golfer.getFullName());
        assertEquals(85, golfer.getGross());
        assertEquals(72, golfer.getNett());
        assertEquals(36, golfer.getPts());
        assertEquals(13, golfer.getHandicap());
    }

    @Test
    public void ShouldConvertJSONToListOfMedalGolfers() {
        String json = "[" +
                "    {" +
                "        \"gross\": 78," +
                "        \"nett\": 74," +
                "        \"handicap\": 4," +
                "        \"position\": 1," +
                "        \"pts\": -1," +
                "        \"fullName\": \"Martin Ford\"" +
                "    }," +
                "    {" +
                "        \"gross\": 79," +
                "        \"nett\": 74," +
                "        \"handicap\": 5," +
                "        \"position\": 2," +
                "        \"pts\": -1," +
                "        \"fullName\": \"Tom Trippett\"" +
                "    }," +
                "    {" +
                "        \"gross\": 82," +
                "        \"nett\": 75," +
                "        \"handicap\": 7," +
                "        \"position\": 3," +
                "        \"pts\": -1," +
                "        \"fullName\": \"Mathew Mittell\"" +
                "    }]";

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Golfer>>() {}.getType();
        List<Golfer> golfers = gson.fromJson(json, listType);
        Golfer golfer = golfers.get(0);

        assertEquals(3, golfers.size());
        assertEquals(1, golfer.getPosition());
        assertEquals("Martin Ford", golfer.getFullName());
        assertEquals(78, golfer.getGross());
        assertEquals(74, golfer.getNett());
        assertEquals(-1, golfer.getPts());
        assertEquals(4, golfer.getHandicap());
    }


}
