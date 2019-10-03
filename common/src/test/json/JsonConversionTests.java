package json;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.reflect.TypeToken;
import models.CompetitionMetadata;
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
                "        \"dateOfCompetition\": \"Wed 25 Sep 19\"," +
                "        \"competitionTitle\": \"Over 55s Stableford\"," +
                "        \"url\": \"http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=5337\"," +
                "        \"viewId\": 5337" +
                "    }," +
                "    {" +
                "        \"dateOfCompetition\": \"Sun 22 Sep 19\"," +
                "        \"competitionTitle\": \"Club Stableford\"," +
                "        \"url\": \"http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=5336\"," +
                "        \"viewId\": 5336" +
                "    }," +
                "    {" +
                "        \"dateOfCompetition\": \"Thu 19 Sep 19\"," +
                "        \"competitionTitle\": \"Club Stableford\"," +
                "        \"url\": \"http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=5331\"," +
                "        \"viewId\": 5331" +
                "    }" +
                "]";

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<CompetitionMetadata>>() {}.getType();
        List<CompetitionMetadata> urls = gson.fromJson(json, listType);
        CompetitionMetadata competitionMetadata = urls.get(0);
        assertEquals(3, urls.size());
        assertEquals("Wed 25 Sep 19", competitionMetadata.getDateOfCompetition());
        assertEquals("Over 55s Stableford", competitionMetadata.getCompetitionTitle());
        assertEquals("http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=5337", competitionMetadata.getUrl());
        assertEquals(5337, competitionMetadata.getViewId());
    }

//    @Test
//    public void ShouldConvertJSONToListOfStablefordGolfers() {
//        String json = "[" +
//                "    {" +
//                "        \"gross\": 80," +
//                "        \"nett\": 68," +
//                "        \"handicap\": 12," +
//                "        \"position\": 1," +
//                "        \"pts\": 40," +
//                "        \"fullName\": \"Lance L. Heycock\"" +
//                "    }," +
//                "    {" +
//                "        \"gross\": 78," +
//                "        \"nett\": 68," +
//                "        \"handicap\": 10," +
//                "        \"position\": 2," +
//                "        \"pts\": 40," +
//                "        \"fullName\": \"Gareth J Davies\"" +
//                "    }," +
//                "    {" +
//                "        \"gross\": 79," +
//                "        \"nett\": 69," +
//                "        \"handicap\": 10," +
//                "        \"position\": 3," +
//                "        \"pts\": 39," +
//                "        \"fullName\": \"Chris Holwill\"" +
//                "    }]";
//
//        Gson gson = new Gson();
//        Type listType = new TypeToken<ArrayList<StablefordGolfer>>() {}.getType();
//        List<StablefordGolfer> golfers = gson.fromJson(json, listType);
//        StablefordGolfer golfer = golfers.get(0);
//
//        assertEquals(3, golfers.size());
//        assertEquals(1, golfer.getPosition());
//        assertEquals("Lance L. Heycock", golfer.getFullName());
//        assertEquals(80, golfer.getGross());
//        assertEquals(68, golfer.getNett());
//        assertEquals(40, golfer.getPts());
//        assertEquals(12, golfer.getHandicap());
//    }
}
