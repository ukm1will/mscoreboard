package Scores.FromWeb;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import app.models.UrlConverter;
import org.junit.Test;

import java.io.IOException;

import static app.service.LoginService.autoLogin;
import static junit.framework.TestCase.assertEquals;


public class TestUrlFromMasterScoreboard {

    private String currentDataSource;

    public TestUrlFromMasterScoreboard() throws IOException {
        final String url = "http://masterscoreboard.co.uk/ClubIndex.php?CWID=5142";
        final String password = "swanseabay";
        WebClient webClient;
        webClient = autoLogin(url, password);
        HtmlPage page = webClient.getPage(url);
        WebResponse response = page.getWebResponse();
        currentDataSource = response.getContentAsString();
    }

    @Test
    public void ShouldReturnRowsAfterRemovingHeadTailAndNewLines() {
        UrlConverter urlConverter = new UrlConverter(currentDataSource);
        urlConverter.convertRawDataToArrayList();
        assertEquals(58, urlConverter.getRawList().size());
    }

    @Test
    public void ShouldReturnRowsAfterRemovingUnwantedRows() {
        UrlConverter urlConverter = new UrlConverter(currentDataSource);
        urlConverter.convertRawDataToArrayList();
        urlConverter.removeUnwantedRowsFromList();
        assertEquals(20, urlConverter.getRawList().size());
    }

    @Test
    public void ShouldConcatenateListAndResizeByHalf() {
        UrlConverter urlConverter = new UrlConverter(currentDataSource);
        urlConverter.convertRawDataToArrayList();
        urlConverter.removeUnwantedRowsFromList();
        urlConverter.extractCompetitionData();
        urlConverter.concatenateList();
        assertEquals(10, urlConverter.getConcatenatedList().size());
    }

    @Test
    public void ShouldCreateURLs() {
        UrlConverter urlConverter = new UrlConverter(currentDataSource);
        urlConverter.convertRawDataToArrayList();
        urlConverter.removeUnwantedRowsFromList();
        urlConverter.extractCompetitionData();
        urlConverter.concatenateList();
        urlConverter.createListofUrls();
        System.out.println(urlConverter.toStringCompetitionURLList());
        assertEquals(10, urlConverter.getCompetitionURLS().size());
    }
}


