package models;

import service.StringHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UrlConverter {

    private final List<String> itemsToRemove = new ArrayList<>(Arrays.asList("<tr>", "</tr>", "</th>", "img src", "colspan"));
    private String rawData;
    private List<String> rawList;
    private List<String> concatenatedList;

    public List<CompetitionMetadata> getCompetitionURLS() {
        return competitionURLS;
    }

    private List<CompetitionMetadata> competitionURLS;

    public List<String> getConcatenatedList() {
        return concatenatedList;
    }

    public UrlConverter(String rawData) {
        this.rawData = rawData;
    }

    public String toStringCompetitionURLList() {
        StringBuilder sb = new StringBuilder();
        for (CompetitionMetadata item : competitionURLS) {
           sb.append(String.format("%s %s %s\n", item.getDateOfCompetition(), item.getUrl(), item.getCompetitionTitle()));
        }
        return sb.toString();
    }

    public List<String> getRawList() {
        return rawList;
    }

    public void convertRawDataToArrayList() {
        rawData = StringHelper.splitBeforeAndAfter(rawData, "Recent Competitions'>\n", "</table>");
        rawList = StringHelper.splitToListByNewLine(rawData);
    }

    public void removeUnwantedRowsFromList() {
        for (String str : itemsToRemove) {
            rawList.removeIf(item -> item.contains(str));
        }
    }

    public void extractCompetitionData() {
        for (int i = 0; i < rawList.size(); i++) {
            String newListItem;
            if (rawList.get(i).contains("center")) {
                newListItem = StringHelper.splitBeforeAndAfter(rawList.get(i), "center>", "</td>");
                rawList.set(i, newListItem);
            } else if (rawList.get(i).contains("href")) {
                newListItem = StringHelper.splitBeforeAndAfter(rawList.get(i), "top>", "<br");
                rawList.set(i, newListItem);
            } else {
                throw new UnsupportedOperationException("The string does not contain either center or href");
            }
        }
    }

    public void concatenateList() {
        concatenatedList = new ArrayList<>();
        for (int i = 0; i < rawList.size(); i+=2) {
            concatenatedList.add(rawList.get(i) + rawList.get(i + 1));
        }
    }

    public void createListofUrls() {
        competitionURLS = new ArrayList<>();
        for (String item: this.concatenatedList) {
            CompetitionMetadata competitionURL = new CompetitionMetadata(item);
            competitionURLS.add(competitionURL);
        }
    }
}

