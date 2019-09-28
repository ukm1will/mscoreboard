package models;

import service.StringHelper;

import java.util.List;

public class CompetitionMetadata {

    private String dateOfCompetition;
    private String competitionTitle;
    private String url;
    private int viewId;


    public void setDateOfCompetition(String dateOfCompetition) {
        this.dateOfCompetition = dateOfCompetition;
    }

    public void setCompetitionTitle(String competitionTitle) {
        this.competitionTitle = competitionTitle;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static String toString(List<CompetitionMetadata> urls) {
        StringBuilder sb = new StringBuilder();
        for (CompetitionMetadata compUrl: urls) {
            sb.append(compUrl.toString());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "CompetitionMetadata{" +
                "dateOfCompetition='" + dateOfCompetition + '\'' +
                ", competitionTitle='" + competitionTitle + '\'' +
                ", url='" + url + '\'' +
                "}";


    }
    CompetitionMetadata(String str) {
        this.dateOfCompetition = StringHelper.removeAfter(str, "<a");
        this.viewId = Integer.parseInt(StringHelper.splitBeforeAndAfter(str, "View=", "'>"));
        this.competitionTitle = StringHelper.splitBeforeAndAfter(str, "'>", "</a>");
        String urlPath = "http://masterscoreboard.co.uk/results/Result.php?CWID=5142&View=";
        this.url = urlPath + viewId;
    }

    public CompetitionMetadata() {
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

    public int getViewId() {
        return this.viewId;
    }
}

// TODO: tidy up this file, check if we need setDate etc, constructor