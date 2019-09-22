package stableford.Stableford03Aug;

import data.stableford.Stableford_03_AUG;
import models.Competition;
import models.StablefordGolfer;
import org.junit.Test;
import service.StringHelper;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class TestScores03Aug {

    private final String currentDataFile = Stableford_03_AUG.WHOLE_PAGE;
    private Competition competition = new Competition(currentDataFile);

    @Test
    public void ShouldAddResultsToCompetition() {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        assertEquals(68, competition.results.size());
    }

    @Test
    public void ShouldAddGolfersToCompetition() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        assertEquals(68, competition.golfers.size());
    }

    @Test
    public void MikeShouldHaveCorrectData() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        StablefordGolfer mikeWilliams = (StablefordGolfer) competition.find("Mike Williams");
        assertEquals("Mike Williams", mikeWilliams.getName());
        assertEquals(76, mikeWilliams.getGross());
        assertEquals(71, mikeWilliams.getNett());
        assertEquals(5, mikeWilliams.getHandicap());
        assertEquals(37, mikeWilliams.getPts());
    }

    @Test
    public void ShouldShowDeanBeforeSort() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        StablefordGolfer deanMorris = (StablefordGolfer) competition.find("Dean Morris");
        assertEquals("Dean Morris", deanMorris.getName());
        assertEquals(2, deanMorris.getPosition());
        assertEquals(65, deanMorris.getGross());
        assertEquals(66, deanMorris.getNett());
        assertEquals(-1, deanMorris.getHandicap());
        assertEquals(42, deanMorris.getPts());
    }

    @Test
    public void ShouldShowDeansPositionAfterSort() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Collections.sort(competition.golfers);
        competition.updateRankings();
        StablefordGolfer deanMorris = (StablefordGolfer) competition.find("Dean Morris");
        assertEquals("Dean Morris", deanMorris.getName());
        assertEquals(1, deanMorris.getPosition());
        assertEquals(65, deanMorris.getGross());
        assertEquals(66, deanMorris.getNett());
        assertEquals(-1, deanMorris.getHandicap());
        assertEquals(42, deanMorris.getPts());
    }
}
