package app.controllers;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import enums.DataResponseType;
import models.Competition;
import models.CompetitionMetadata;
import models.Golfer;
import models.UrlConverter;
import org.springframework.web.bind.annotation.*;
import service.StringHelper;

import java.io.IOException;
import java.util.List;

import static service.LoginService.autoLogin;


@RestController
public class BackendController {

    private final String password = "swanseabay";
    private final String msHomePage = "http://masterscoreboard.co.uk/ClubIndex.php?CWID=5142";
    private final String backend = "http://localhost:9090";

    @RequestMapping(method = RequestMethod.GET, value = "/health")
    public String health() {
        return "Your backend is available";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/competition", produces = "application/json")
    public List<Golfer> getCompetition(CompetitionMetadata competitionURL) throws Exception {
        String url = competitionURL.getUrl();
        String dataSource = getDataSource(url, DataResponseType.TEXT);
        dataSource = StringHelper.splitBeforeAndAfter(dataSource, "Handicap\n", "Number of Cards Processed");
        Competition competition = new Competition(dataSource);
        competition.addResultsToCompetition(dataSource);
        competition.addGolfersToCompetition();
        return competition.golfers;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/singlecompetition", produces = "application/json")
    public List<Golfer> getSingleCompetition() throws Exception {
        String dataSource = getDataSource("http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=5315", DataResponseType.TEXT);
        dataSource = StringHelper.splitBeforeAndAfter(dataSource, "Handicap\n", "Number of Cards Processed");
        Competition competition = new Competition(dataSource);
        competition.addResultsToCompetition(dataSource);
        competition.addGolfersToCompetition();
        return competition.golfers;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/view/{viewId}", produces = "application/json")
    public List<Golfer> getCompetitionFromView(@RequestParam("viewId") int viewId) throws Exception {
        String url = backend + viewId;
        String dataSource = getDataSource(url, DataResponseType.TEXT);
        Competition competition = new Competition(dataSource);
        competition.addResultsToCompetition(dataSource);
        competition.addGolfersToCompetition();
        return competition.golfers;
    }



    @RequestMapping(method = RequestMethod.GET, value = "/urls", produces = "application/json")
    public List<CompetitionMetadata> getMasterScoreboardHomePage() throws IOException {
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
