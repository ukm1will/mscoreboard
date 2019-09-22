package CompetitionTests.Stableford03Aug;

import com.michaelangela.data.Stableford;
import com.michaelangela.models.Competition;
import com.michaelangela.models.Golfer;
import com.michaelangela.models.ScoringSystem;
import com.michaelangela.service.StringHelper;
import org.junit.Before;
import org.junit.Test;

import static com.michaelangela.models.ScoringSystem.*;
import static junit.framework.TestCase.assertEquals;

public class WebAppTestFromFile {

    final String currentDataFile = Stableford.WHOLE_PAGE;
    Competition competition;

    @Before
    public void doBefore() {
        competition = new Competition();
        competition.setScoringSystem(StringHelper.getScoringSystem(currentDataFile));
    }

    @Test
    public void ShouldAddResultsToCompetition() {
        String activeData = StringHelper.splitOnTopAndBottom(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        assertEquals(68, competition.results.size());
    }

    @Test
    public void ShouldAddGolfersToCompetition() throws Exception {
        String activeData = StringHelper.splitOnTopAndBottom(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        assertEquals(68, competition.golfers.size());
    }

    @Test
    public void MikeWilliamsShouldHaveAGrossOf76() throws Exception {
        String activeData = StringHelper.splitOnTopAndBottom(currentDataFile, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(activeData);
        competition.addGolfersToCompetition();
        Golfer mikeWilliams = competition.find("Mike Williams");
        assertEquals("Mike Williams", mikeWilliams.getName());
        assertEquals(76, mikeWilliams.getGross());
    }

    @Test
    public void CompetitionShouldBeMedal() {
        ScoringSystem scoringSystem = StringHelper.getScoringSystem(currentDataFile);
        assertEquals(STABLEFORD, scoringSystem);
    }

}
