package controllers;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.reflect.TypeToken;
import models.CompetitionURL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
