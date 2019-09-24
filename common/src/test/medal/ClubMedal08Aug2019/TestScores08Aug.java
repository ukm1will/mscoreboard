package medal.ClubMedal08Aug2019;

import data.medal.ClubMedal08Aug2019;
import data.stableford.Stableford_03_AUG;
import models.Competition;
import models.Golfer;
import org.junit.Before;
import org.junit.Test;
import service.StringHelper;

import java.util.Collections;

import static enums.ScoringSystem.MEDAL;
import static junit.framework.TestCase.assertEquals;

public class TestScores08Aug {

    private final String currentDataFile = ClubMedal08Aug2019.WHOLE_PAGE;
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
    public void DerekEvansShouldHaveAGrossOf91() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer derekEvans = competition.find("Derek Evans");
        assertEquals(91, derekEvans.getGross());
    }

    @Test
    public void KeithLarkmanShouldHaveAGrossOf86() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer chrisSmith = competition.find("Keith Larkman");
        assertEquals(86, chrisSmith.getGross());
    }


    @Test
    public void AlanGreenowScoresGross90Nett71() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer richieEvans = competition.find("Alan Greenow");
        assertEquals(90, richieEvans.getGross());
        assertEquals(71, richieEvans.getNett());
    }

    @Test
    public void BeforeSortingCheckNames() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        assertEquals("Michael M Linnane", posOne.getName());
        assertEquals("Nigel P. Maimone", posTwo.getName());
    }


    @Test
    public void AfterSortingCheckNames() throws Exception {
        String activeData = StringHelper.splitBeforeAndAfter(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Collections.sort(competition.golfers);
        Golfer posOne = competition.golfers.get(0);
        Golfer posTwo = competition.golfers.get(1);
        assertEquals("Michael M Linnane", posOne.getName());
        assertEquals("Gareth M Edwards", posTwo.getName());
    }


}