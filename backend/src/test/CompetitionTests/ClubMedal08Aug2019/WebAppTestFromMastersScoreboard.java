package CompetitionTests.ClubMedal08Aug2019;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.michaelangela.enums.OutputFile;
import com.michaelangela.models.Competition;
import com.michaelangela.service.DebugPrint;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.michaelangela.service.LoginService.autoLogin;

public class WebAppTestFromMastersScoreboard {

    Competition competition;

    public WebAppTestFromMastersScoreboard() throws IOException {
    }

    @Before
    public void doBefore() {
        competition = new Competition();
    }

    @Test
    public void ShouldAddResultsToCompetition() throws IOException {
        final String url = "http://masterscoreboard.co.uk/ClubIndex.php?CWID=5142";
        final String password = "swanseabay";
        String raw;
        WebClient client;
        HtmlPage page;
        client = autoLogin(url, password);
        page = client.getPage(url);
        raw = page.asText();
        DebugPrint.toFile(raw, OutputFile.RAW);
    }


    @Test
    public void doNothing() throws IOException {
        final String url = "http://masterscoreboard.co.uk/ClubIndex.php?CWID=5142";
        final String password = "swanseabay";
        String raw;
        WebClient webClient;
        webClient = autoLogin(url, password);
        HtmlPage page = webClient.getPage(url);
        WebResponse response = page.getWebResponse();
        String sourceFile = response.getContentAsString();
        DebugPrint.toFile(sourceFile, OutputFile.SOURCE);
    }
}



























