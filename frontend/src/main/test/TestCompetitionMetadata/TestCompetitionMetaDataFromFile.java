package TestCompetitionMetadata;

import data.URLS_25_SEP;
import models.CompetitionMetadata;
import models.UrlConverter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TestCompetitionMetaDataFromFile {

    private final String currentDataFile = URLS_25_SEP.WHOLE_PAGE;
    private final UrlConverter urlConverter = new UrlConverter(currentDataFile);

    @Test
    public void ShouldShowListOfCompetitionMetadata() {
        List<CompetitionMetadata> competitionMetadataList;
        urlConverter.convertRawDataToArrayList();
        urlConverter.removeUnwantedRowsFromList();
        urlConverter.extractCompetitionData();
        urlConverter.concatenateList();
        urlConverter.createListofUrls();
        competitionMetadataList = urlConverter.getCompetitionURLS();
        assertEquals(10, competitionMetadataList.size());
        for (CompetitionMetadata cmd : competitionMetadataList) {
            System.out.println(cmd.toString());
        }
    }
}

