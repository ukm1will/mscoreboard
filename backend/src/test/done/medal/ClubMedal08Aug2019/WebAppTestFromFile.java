package done.medal.ClubMedal08Aug2019;


import enums.ScoringSystem;
import models.Competition;
import models.Golfer;
import org.junit.Before;
import org.junit.Test;
import service.StringHelper;

import static junit.framework.TestCase.assertEquals;

public class WebAppTestFromFile {

//    final String currentDataFile = ClubMedal08Aug2019.WHOLE_PAGE;
//    Competition competition;
//
//    @Before
//    public void doBefore() {
//        competition = new Competition();
//        competition.setScoringSystem(StringHelper.getScoringSystem(currentDataFile));
//        assertEquals(ScoringSystem.MEDAL, competition.getScoringSystem());
//    }
//
//    @Test
//    public void ShouldAddResultsToCompetition() {
//        String activeData = StringHelper.splitOnTopAndBottom(currentDataFile, "Handicap\n", "Number of Cards Processed");
//        competition.addResultsToCompetition(activeData);
//        assertEquals(68, competition.results.size());
//    }
//
//    @Test
//    public void ShouldAddGolfersToCompetition() throws Exception {
//        String activeData = StringHelper.splitOnTopAndBottom(currentDataFile, "Handicap\n", "Number of Cards Processed");
//        competition.addResultsToCompetition(activeData);
//        competition.addGolfersToCompetition();
//        assertEquals(52, competition.golfers.size());
//    }
//
//    @Test
//    public void DerekEvansShouldHaveAGrossOf91() throws Exception {
//        String activeData = StringHelper.splitOnTopAndBottom(currentDataFile, "Handicap\n", "Number of Cards Processed");
//        competition.addResultsToCompetition(activeData);
//        competition.addGolfersToCompetition();
//        Golfer derekEvans = competition.find("Derek Evans");
//        assertEquals(91, derekEvans.getGross());
//    }
//
//    @Test
//    public void KeithLarkmanShouldHaveAGrossOf86() throws Exception {
//        String activeData = StringHelper.splitOnTopAndBottom(currentDataFile, "Handicap\n", "Number of Cards Processed");
//        competition.addResultsToCompetition(activeData);
//        competition.addGolfersToCompetition();
//        Golfer chrisSmith = competition.find("Keith Larkman");
//        assertEquals(86, chrisSmith.getGross());
//    }
//
//
//    @Test
//    public void AlanGreenowScoresGross90Nett71() throws Exception {
//        String activeData = StringHelper.splitOnTopAndBottom(currentDataFile, "Handicap\n", "Number of Cards Processed");
//        competition.addResultsToCompetition(activeData);
//        competition.addGolfersToCompetition();
//        Golfer richieEvans = competition.find("Alan Greenow");
//        assertEquals(90, richieEvans.getGross());
//        assertEquals(71, richieEvans.getNett());
//    }
//
//    @Test
//    public void CompetitionShouldBeMedal() {
//        ScoringSystem scoringSystem = StringHelper.getScoringSystem(currentDataFile);
//        assertEquals(MEDAL, scoringSystem);
//    }
}


