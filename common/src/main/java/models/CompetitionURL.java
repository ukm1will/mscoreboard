package models;

import service.StringHelper;

public class CompetitionURL {

    private String dateOfCompetition;
    private String competitionTitle;
    private String url;


    public void setDateOfCompetition(String dateOfCompetition) {
        this.dateOfCompetition = dateOfCompetition;
    }

    public void setCompetitionTitle(String competitionTitle) {
        this.competitionTitle = competitionTitle;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    CompetitionURL(String str) {
        this.dateOfCompetition = StringHelper.removeAfter(str, "<a");
        String viewID = StringHelper.splitBeforeAndAfter(str, "View=", "'>");
        this.competitionTitle = StringHelper.splitBeforeAndAfter(str, "'>", "</a>");
        String urlPath = "http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=";
        this.url = urlPath + viewID;
    }

    public CompetitionURL() {
    }

    public String getDateOfCompetition() {
        return dateOfCompetition;
    }

    public String getCompetitionTitle() {
        return competitionTitle;
    }

    public String getUrl() {
        return url;
    }
}
