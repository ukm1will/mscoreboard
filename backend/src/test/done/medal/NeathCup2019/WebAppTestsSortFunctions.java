package done.medal.NeathCup2019;

import models.Competition;
import models.Golfer;
import org.junit.Before;
import org.junit.Test;
import service.StringHelper;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class WebAppTestsSortFunctions {

//    final String newLineChar = System.getProperty("line.separator");
//    final String currentDataFile = NeathCup2019.WHOLE_PAGE;
//
//    Competition competition;
//
//    @Before
//    public void doBefore() {
//        competition = new Competition();
//        competition.setScoringSystem(StringHelper.getScoringSystem(currentDataFile));
//    }
//
//    @Test
//    public void BeforeSortingCheckNames() throws Exception {
//        String activeData = StringHelper.splitOnTopAndBottom(currentDataFile, "Handicap\n", "Number of Cards Processed");
//        competition.addResultsToCompetition(activeData);
//        competition.addGolfersToCompetition();
//        Golfer posOne = competition.golfers.get(0);
//        Golfer posTwo = competition.golfers.get(1);
//        assertEquals("Euan Edwards", posOne.getName());
//        assertEquals("Mike Edwards", posTwo.getName());
//    }
//
//    @Test
//    public void BeforeSortingCheckPositions() throws Exception {
//        String activeData = StringHelper.splitOnTopAndBottom(currentDataFile, "Handicap\n", "Number of Cards Processed");
//        competition.addResultsToCompetition(activeData);
//        competition.addGolfersToCompetition();
//        Golfer posOne = competition.golfers.get(0);
//        Golfer posTwo = competition.golfers.get(1);
//        assertEquals(1, posOne.getPosition());
//        assertEquals(2, posTwo.getPosition());
//    }
//
//
//    @Test
//    public void AfterSortingCheckNames() throws Exception {
//        String activeData = StringHelper.splitOnTopAndBottom(currentDataFile, "Handicap\n", "Number of Cards Processed");
//        competition.addResultsToCompetition(activeData);
//        competition.addGolfersToCompetition();
//        Collections.sort(competition.golfers);
//        Golfer posOne = competition.golfers.get(0);
//        Golfer posTwo = competition.golfers.get(1);
//        assertEquals("R. A. Evans", posOne.getName());
//        assertEquals("Jonathan R Bevan", posTwo.getName());
//    }
//
//    @Test
//    public void AfterSettingNewRankingsCheckPositions() throws Exception {
//        String activeData = StringHelper.splitOnTopAndBottom(currentDataFile, "Handicap\n", "Number of Cards Processed");
//        competition.addResultsToCompetition(activeData);
//        competition.addGolfersToCompetition();
//        Collections.sort(competition.golfers);
//        Golfer.setNewRankings(competition.golfers);
//        Golfer posOne = competition.golfers.get(0);
//        Golfer posTwo = competition.golfers.get(1);
//        assertEquals(1, posOne.getPosition());
//        assertEquals(2, posTwo.getPosition());
//    }
}

