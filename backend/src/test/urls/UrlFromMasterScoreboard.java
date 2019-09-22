package urls;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.reflect.TypeToken;
import models.CompetitionURL;
import models.UrlConverter;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static service.LoginService.autoLogin;

public class UrlFromMasterScoreboard {

    private String currentDataSource;

    public UrlFromMasterScoreboard() throws IOException {
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

    @Test
    public void ShouldReturnListOfURLFromService() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject("http://localhost:9090/urls", String.class);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<CompetitionURL>>() {
        }.getType();
        List<CompetitionURL> urls = gson.fromJson(json, listType);
        System.out.println(CompetitionURL.toString(urls));
    }


}


