package app.controllers;

import app.enums.DataResponseType;
import app.service.StringHelper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import app.models.Competition;
import app.models.CompetitionURL;
import app.models.Golfer;
import app.models.UrlConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static app.service.LoginService.autoLogin;

@RestController
public class BackendController {

    private final String password = "swanseabay";
    private final String msHomePage = "http://masterscoreboard.co.uk/ClubIndex.php?CWID=5142";

    @RequestMapping(method = RequestMethod.GET, value = "/health")
    public String health() {
        return "Your backend is available";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/competition", produces = "application/json")
    public List<Golfer> getCompetition(CompetitionURL competitionURL) throws Exception {
        String url = competitionURL.getUrl();
        String dataSource = getDataSource(url, DataResponseType.TEXT);
        dataSource = StringHelper.splitBeforeAndAfter(dataSource, "Handicap\n", "Number of Cards Processed");
        Competition competition = new Competition(dataSource);
        competition.addResultsToCompetition(dataSource);
        competition.addGolfersToCompetition();
        return competition.golfers;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/urls", produces = "application/json")
    public List<CompetitionURL> getMasterScoreboardHomePage() throws IOException {
        String dataSource = getDataSource(msHomePage, DataResponseType.HTML);
        UrlConverter urlConverter = new UrlConverter(dataSource);
        urlConverter.convertRawDataToArrayList();
        urlConverter.removeUnwantedRowsFromList();
        urlConverter.extractCompetitionData();
        urlConverter.concatenateList();
        urlConverter.createListofUrls();
        return urlConverter.getCompetitionURLS();
    }

    private String getDataSource(String url, DataResponseType dataResponseType) throws IOException {
        WebClient webClient = autoLogin(url, password);
        HtmlPage page = webClient.getPage(url);
        return dataResponseType == DataResponseType.HTML ? page.getWebResponse().getContentAsString() : page.asText();
    }
}

