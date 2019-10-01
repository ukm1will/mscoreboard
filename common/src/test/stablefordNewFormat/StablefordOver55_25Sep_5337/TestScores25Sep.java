package stablefordNewFormat.StablefordOver55_25Sep_5337;

import data.stablefordNewFormat.Stableford_25_Sep_5337;
import data.stablefordOldFormat.Stableford_03_AUG_5281;
import junit.framework.TestCase;
import models.Competition;
import models.Golfer;
import models.StablefordGolfer;
import org.junit.Test;
import service.StringHelper;

import java.util.Collections;

import static enums.ScoringSystem.STABLEFORD;
import static org.junit.Assert.assertEquals;

public class TestScores25Sep {

    private final String currentDataFile = Stableford_25_Sep_5337.WHOLE_PAGE;
    private Competition competition = new Competition(currentDataFile);

    @Test
    public void CompetitionShouldBeStableford() {
        assertEquals(STABLEFORD, competition.getScoringSystem());
    }

    @Test
    public void ShouldAddResultsToCompetition() {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Countback\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        assertEquals(22, competition.results.size());
    }

    @Test
    public void ShouldAddGolfersToCompetition() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Countback\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        assertEquals(22, competition.stablefordGolfers.size());
    }

    @Test
    public void MikeShouldHaveCorrectData() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Countback\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        StablefordGolfer guydonCerasuolo = (StablefordGolfer) competition.find("Guydon Cerasuolo");
        assertEquals("Guydon Cerasuolo", guydonCerasuolo.getFullName());
        assertEquals(101, guydonCerasuolo.getGross());
        assertEquals(80, guydonCerasuolo.getNett());
        assertEquals(21, guydonCerasuolo.getHandicap());
        assertEquals(28, guydonCerasuolo.getPts());
    }

    @Test
    public void ShouldShowEdnyfedBeforeSort() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Countback\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        StablefordGolfer edMorgan = (StablefordGolfer) competition.find("Ednyfed O. Morgan");
        assertEquals("Ednyfed O. Morgan", edMorgan.getFullName());
        assertEquals(5, edMorgan.getPosition());
        assertEquals(86, edMorgan.getGross());
        assertEquals(76, edMorgan.getNett());
        assertEquals(10, edMorgan.getHandicap());
        assertEquals(32, edMorgan.getPts());

        for (StablefordGolfer stablefordGolfer: competition.stablefordGolfers) {
            System.out.println(stablefordGolfer.toString());
        }


    }




//
//    @Test
//    public void ShouldShowDeansPositionAfterSort() throws Exception {
//        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
//        competition.addResultsToCompetition(activeData);
//        competition.addGolfersToCompetition();
//        Collections.sort(competition.golfers);
//        competition.updateRankings();
//        StablefordGolfer edMorgan = (StablefordGolfer) competition.find("Dean Morris");
//        assertEquals("Dean Morris", edMorgan.getFullName());
//        assertEquals(1, edMorgan.getPosition());
//        assertEquals(65, edMorgan.getGross());
//        assertEquals(66, edMorgan.getNett());
//        assertEquals(-1, edMorgan.getHandicap());
//        assertEquals(42, edMorgan.getPts());
//    }
//
//    @Test
//    public void BeforeSortingCheckNames() throws Exception {
//        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
//        competition.addResultsToCompetition(activeData);
//        competition.addGolfersToCompetition();
//        Golfer posOne = competition.golfers.get(0);
//        Golfer posTwo = competition.golfers.get(1);
//        TestCase.assertEquals("Brett Phippen", posOne.getFullName());
//        TestCase.assertEquals("Dean Morris", posTwo.getFullName());
//    }
//
//    @Test
//    public void AfterSortingCheckNames() throws Exception {
//        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
//        competition.addResultsToCompetition(activeData);
//        competition.addGolfersToCompetition();
//        Collections.sort(competition.golfers);
//        Golfer posOne = competition.golfers.get(0);
//        Golfer posTwo = competition.golfers.get(1);
//        TestCase.assertEquals("Dean Morris", posOne.getFullName());
//        TestCase.assertEquals("Brett Phippen", posTwo.getFullName());
//    }
//

}
