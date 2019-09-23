package urls;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import models.Competition;
import org.junit.Test;
import service.StringHelper;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static service.LoginService.autoLogin;

public class WebAppTestFromMastersScoreboard_todo {

    final String url = "https://www.masterscoreboard.co.uk/ClubIndex.php?CWID=5142";
    final String password = "swanseabay";
    WebClient client;
    HtmlPage page;
    Competition competition;

    @Test
    public void ShouldAddResultsToCompetition() throws IOException {
        String dataFromWebPage;
        String processedDataFromWebPage;

        client = autoLogin(url, password);
        page = client.getPage("http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=5282");
        dataFromWebPage = page.asText();
        processedDataFromWebPage = StringHelper.splitBeforeAndAfter(dataFromWebPage, "Handicap\n", "Number of Cards Processed");

        competition = new Competition(page.toString());
        competition.addResultsToCompetition(processedDataFromWebPage);
        assertEquals(26, competition.results.size());
    }

//    @Test
//    public void ShouldAddGolfersToCompetition() throws Exception {
//        String dataFromWebPage;
//        String processedDataFromWebPage;
//        dataFromWebPage = page.asText();
//        processedDataFromWebPage = StringHelper.splitBeforeAndAfter(dataFromWebPage, "Handicap\n", "Number of Cards Processed");
//        competition.addResultsToCompetition(processedDataFromWebPage);
//        competition.addGolfersToCompetition();
//        assertEquals(20, competition.golfers.size());
//    }
}


