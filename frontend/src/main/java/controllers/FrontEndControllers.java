package controllers;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import enums.DataResponseType;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.reflect.TypeToken;
import models.Competition;
import models.CompetitionURL;
import models.Golfer;
import models.UrlConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import service.StringHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FrontEndControllers {
//    @RequestMapping("/urls")
//    public String getURLs() {
//        return "message.jsp";
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/urls")
    public ModelAndView getURIs(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject("http://localhost:9090/urls", String.class);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<CompetitionURL>>() {}.getType();
        List<CompetitionURL> urls = gson.fromJson(json, listType);

        for (CompetitionURL url: urls) {
            System.out.println(url.toString());
        }

        mv.setViewName("url.jsp");
        mv.addObject("urls", urls);
        return mv;
    }
}


//    @RequestMapping(method = RequestMethod.GET, value = "/health")
//    public String health() {
//        return "Your backend is available";
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/competition", produces = "application/json")
//    public List<Golfer> getCompetition(CompetitionURL competitionURL) throws Exception {
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
//    public List<CompetitionURL> getMasterScoreboardHomePage() throws IOException {
//        String dataSource = getDataSource(msHomePage, DataResponseType.HTML);
//        UrlConverter urlConverter = new UrlConverter(dataSource);
//        urlConverter.convertRawDataToArrayList();
//        urlConverter.removeUnwantedRowsFromList();
//        urlConverter.extractCompetitionData();
//        urlConverter.concatenateList();
//        urlConverter.createListofUrls();
//        return urlConverter.getCompetitionURLS();
//    }
//
//    private String getDataSource(String url, DataResponseType dataResponseType) throws IOException {
//        WebClient webClient = autoLogin(url, password);
//        HtmlPage page = webClient.getPage(url);
//        return dataResponseType == DataResponseType.HTML ? page.getWebResponse().getContentAsString() : page.asText();
//    }