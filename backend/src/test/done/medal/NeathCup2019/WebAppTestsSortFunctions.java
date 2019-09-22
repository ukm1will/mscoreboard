package done.medal.NeathCup2019;

import app.data.NeathCup2019;
import models.Competition;
import models.Golfer;
import org.junit.Before;
import org.junit.Test;
import service.StringHelper;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class WebAppTestsSortFunctions {

    private final String currentDataFile = NeathCup2019.WHOLE_PAGE;

    private Competition competition;

    @Before
    public void doBefore() {
        competition = new Competition(currentDataFile);
    }

    @Test
    public void BeforeSortingCheckNames() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        assertEquals("Euan Edwards", posOne.getName());
        assertEquals("Mike Edwards", posTwo.getName());
    }

    @Test
    public void BeforeSortingCheckPositions() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        assertEquals(1, posOne.getPosition());
        assertEquals(2, posTwo.getPosition());
    }


    @Test
    public void AfterSortingCheckNames() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Collections.sort(competition.golfers);
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        assertEquals("R. A. Evans", posOne.getName());
        assertEquals("Jonathan R Bevan", posTwo.getName());
    }

    @Test
    public void AfterSettingNewRankingsCheckPositions() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Collections.sort(competition.golfers);
        competition.updateRankings();
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        assertEquals(1, posOne.getPosition());
        assertEquals(2, posTwo.getPosition());
    }
}

