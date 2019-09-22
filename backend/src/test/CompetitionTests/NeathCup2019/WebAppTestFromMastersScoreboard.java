package CompetitionTests.NeathCup2019;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.michaelangela.models.Competition;
import com.michaelangela.models.ScoringSystem;
import com.michaelangela.service.StringHelper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.michaelangela.service.LoginService.autoLogin;
import static org.junit.Assert.assertEquals;

public class WebAppTestFromMastersScoreboard {

    final String url = "https://www.masterscoreboard.co.uk/ClubIndex.php?CWID=5142";
    final String password = "swanseabay";
    WebClient client;
    HtmlPage page;
    Competition competition;

    public WebAppTestFromMastersScoreboard() throws IOException {
        client = autoLogin(url, password);
        page = client.getPage("http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=5282");
    }

    @Before
    public void doBefore() {
        competition = new Competition();
    }

    @Test
    public void ShouldAddResultsToCompetition() {
        String dataFromWebPage;
        String processedDataFromWebPage;
        dataFromWebPage = page.asText();
        processedDataFromWebPage = StringHelper.splitOnTopAndBottom(dataFromWebPage, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(processedDataFromWebPage);
//        assertEquals(26, competition.results.size());
    }

    @Test
    public void ShouldAddGolfersToCompetition() throws Exception {
        String dataFromWebPage;
        String processedDataFromWebPage;
        dataFromWebPage = page.asText();
        processedDataFromWebPage = StringHelper.splitOnTopAndBottom(dataFromWebPage, "Handicap\n", "Number of Cards Processed");
        competition.addResultsToCompetition(processedDataFromWebPage);
        competition.addGolfersToCompetition();
//        assertEquals(20, competition.golfers.size());
    }
}


