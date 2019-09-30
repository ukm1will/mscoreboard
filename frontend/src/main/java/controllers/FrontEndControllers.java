package controllers;

import enums.DataResponseType;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.reflect.TypeToken;
import models.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import service.StringHelper;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FrontEndControllers {

    @RequestMapping(method = RequestMethod.GET, value = "/urls")
    public ModelAndView getURIs() {
        ModelAndView mv = new ModelAndView();
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject("http://localhost:9090/urls", String.class);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<CompetitionMetadata>>() {
        }.getType();
        List<CompetitionMetadata> urls = gson.fromJson(json, listType);

        for (CompetitionMetadata url : urls) {
            System.out.println(url.toString());
        }

        mv.setViewName("url.jsp");
        mv.addObject("urls", urls);
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/foobar")
    public ModelAndView doNothing() {
        ModelAndView mv = new ModelAndView();
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject("http://localhost:9090/views/5315", String.class);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<StablefordGolfer>>() {}.getType();
        List<StablefordGolfer> stablefordGolfers = gson.fromJson(json, listType);
        mv.setViewName("stableford.jsp");
        mv.addObject("stablefordGolfers", stablefordGolfers);
        return mv;
    }
}
//
//    @RequestMapping(method = RequestMethod.GET, value = "/views/{viewId}", produces = "application/json")
//    public List<Golfer> getView(@PathVariable("viewId") final int viewId) throws Exception {
//        String url = "http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=" + viewId;
//        String dataSource = getDataSource(url, DataResponseType.TEXT);
//        dataSource = StringHelper.splitBeforeAndAfter(dataSource, "Handicap\n", "Number of Cards Processed");
//        Competition competition = new Competition(dataSource);
//        competition.addResultsToCompetition(dataSource);
//        competition.addGolfersToCompetition();
//        return competition.golfers;
//    }
//






//    @RequestMapping(method = RequestMethod.GET, value = "/health")
//    public String health() {
//        return "Your backend is available";
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/competition", produces = "application/json")
//    public List<Golfer> getCompetition(CompetitionMetadata competitionURL) throws Exception {
//        String url = competitionURL.getUrl();
//        String dataSource = getDataSource(url, DataResponseType.TEXT);
//        dataSource = StringHelper.splitBeforeAndAfter(dataSource, "Handicap\n", "Number of Cards Processed");
//        Competition competition = new Competition(dataSource);
//        competition.addResultsToCompetition(dataSource);
//        competition.addGolfersToCompetition();
//        return competition.golfers;
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/urls", produces = "application/json")
//    public List<CompetitionMetadata> getMasterScoreboardHomePage() throws IOException {
//        String dataSource = getDataSource(msHomePage, DataResponseType.HTML);
//        HTMLToCompetitionMetaDataConverter urlConverter = new HTMLToCompetitionMetaDataConverter(dataSource);
//        urlConverter.convertRawDataToArrayList();
//        urlConverter.removeUnwantedRowsFromList();
//        urlConverter.extractCompetitionData();
//        urlConverter.concatenateList();
//        urlConverter.createListofCompetitionMetaData();
//        return urlConverter.getCompetitionMetadata();
//    }
//
//    private String getDataSource(String url, DataResponseType dataResponseType) throws IOException {
//        WebClient webClient = autoLogin(url, password);
//        HtmlPage page = webClient.getPage(url);
//        return dataResponseType == DataResponseType.HTML ? page.getWebResponse().getContentAsString() : page.asText();
//    }