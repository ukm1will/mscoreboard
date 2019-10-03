package medal.ClubMedal_08Aug_5287;

import data.medal.ClubMedal_08Aug_5287;
import models.Competition;
import models.Golfer;
import org.junit.Test;
import service.StringHelper;

import java.util.Collections;

import static enums.ScoringSystem.MEDAL;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class TestScores08Aug {

    private final String currentDataFile = ClubMedal_08Aug_5287.WHOLE_PAGE;
    private Competition competition = new Competition(currentDataFile);

    @Test
    public void CompetitionShouldBeMedal() {
        assertEquals(MEDAL, competition.getScoringSystem());
    }

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
        assertEquals(52, competition.golfers.size());
    }

    @Test
    public void DerekEvansShouldHaveCorrectData() {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer derekEvans = competition.find("Derek Evans");
        assertEquals(32, derekEvans.getPosition());
        assertEquals(91, derekEvans.getGross());
        assertEquals(78, derekEvans.getNett());
        assertEquals(13, derekEvans.getHandicap());

    }

    @Test
    public void KeithLarkmanShouldHaveCorrectData() {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer keithLarkman = competition.find("Keith Larkman");
        assertEquals(22, keithLarkman.getPosition());
        assertEquals(86, keithLarkman.getGross());
        assertEquals(75, keithLarkman.getNett());
        assertEquals(11, keithLarkman.getHandicap());

    }

    @Test
    public void AlanGreenowShouldHaveCorrectData() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer alanGreenow = competition.find("Alan Greenow");
        assertEquals(9, alanGreenow.getPosition());
        assertEquals(90, alanGreenow.getGross());
        assertEquals(71, alanGreenow.getNett());
        assertEquals(19, alanGreenow.getHandicap());
    }

    @Test
    public void BeforeSortingCheckNames() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        assertEquals("Michael M Linnane", posOne.getFullName());
        assertEquals("Nigel P. Maimone", posTwo.getFullName());
    }

    @Test
    public void AfterSortingCheckNames() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Collections.sort(competition.golfers);
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        assertEquals("Michael M Linnane", posOne.getFullName());
        assertEquals("Gareth M Edwards", posTwo.getFullName());
    }
}
