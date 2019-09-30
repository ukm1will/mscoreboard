package app.controllers;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import enums.DataResponseType;
import models.Competition;
import models.CompetitionMetadata;
import models.Golfer;
import models.HTMLToCompetitionMetaDataConverter;
import org.springframework.web.bind.annotation.*;
import service.StringHelper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static service.LoginService.autoLogin;


@RestController
public class BackendController {

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


    @RequestMapping(method = RequestMethod.GET, value = "/views/{viewId}", produces = "application/json")
    public List<Golfer> getView(@PathVariable("viewId") final int viewId) throws Exception {
        String url = "http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=" + viewId;
        String dataSource = getDataSource(url, DataResponseType.TEXT);
        dataSource = StringHelper.splitBeforeAndAfter(dataSource, "Handicap\n", "Number of Cards Processed");
        Competition competition = new Competition(dataSource);
        competition.addResultsToCompetition(dataSource);
        competition.addGolfersToCompetition();
        Collections.sort(competition.golfers);
        return competition.golfers;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/urls", produces = "application/json")
    public List<CompetitionMetadata> getMasterScoreboardHomePage() throws IOException {
        String msHomePage = "http://masterscoreboard.co.uk/ClubIndex.php?CWID=5142";
        String dataSource = getDataSource(msHomePage, DataResponseType.HTML);
        HTMLToCompetitionMetaDataConverter urlConverter = new HTMLToCompetitionMetaDataConverter(dataSource);
        urlConverter.convertRawDataToArrayList();
        urlConverter.removeUnwantedRowsFromList();
        urlConverter.extractCompetitionData();
        urlConverter.concatenateList();
        urlConverter.createListofCompetitionMetaData();
        return urlConverter.getCompetitionMetadata();
    }


    private String getDataSource(String url, DataResponseType dataResponseType) throws IOException {
        String password = "swanseabay";
        WebClient webClient = autoLogin(url, password);
        HtmlPage page = webClient.getPage(url);
        return dataResponseType == DataResponseType.HTML ? page.getWebResponse().getContentAsString() : page.asText();
    }
}
