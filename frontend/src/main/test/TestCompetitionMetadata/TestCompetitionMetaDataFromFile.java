package TestCompetitionMetadata;

import data.URLS_25_SEP;
import models.CompetitionMetadata;
import models.HTMLToCompetitionMetaDataConverter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TestCompetitionMetaDataFromFile {

    private final String currentDataFile = URLS_25_SEP.WHOLE_PAGE;
    private final HTMLToCompetitionMetaDataConverter urlConverter = new HTMLToCompetitionMetaDataConverter(currentDataFile);

    @Test
    public void ShouldConvertStringOfHTMLCodeToArrayList() {
        urlConverter.convertRawDataToArrayList();
        assertEquals(58, urlConverter.getRawList().size());
    }

    @Test
    public void ShouldRemoveUnwantedRowsFromList() {
        urlConverter.convertRawDataToArrayList();
        urlConverter.removeUnwantedRowsFromList();
        assertEquals(20, urlConverter.getRawList().size());
    }

    @Test
    public void ShouldExtractCompetitionData() {
        urlConverter.convertRawDataToArrayList();
        urlConverter.removeUnwantedRowsFromList();
        urlConverter.extractCompetitionData();
        assertEquals(20, urlConverter.getRawList().size());
    }

    @Test
    public void ShouldConcatenateList() {
        urlConverter.convertRawDataToArrayList();
        urlConverter.removeUnwantedRowsFromList();
        urlConverter.extractCompetitionData();
        urlConverter.concatenateList();
        assertEquals(20, urlConverter.getRawList().size());
    }

    @Test
    public void ShouldCreateListofUrls() {
        urlConverter.convertRawDataToArrayList();
        urlConverter.removeUnwantedRowsFromList();
        urlConverter.extractCompetitionData();
        urlConverter.concatenateList();
        urlConverter.createListofCompetitionMetaData();
        assertEquals(10, urlConverter.getCompetitionMetadata().size());
    }

    @Test
    public void ShouldHaveSecondCompetitionInArrayListCorrect() {
        List<CompetitionMetadata> competitionMetadata = new ArrayList<>();
        urlConverter.convertRawDataToArrayList();
        urlConverter.removeUnwantedRowsFromList();
        urlConverter.extractCompetitionData();
        urlConverter.concatenateList();
        urlConverter.createListofCompetitionMetaData();
        CompetitionMetadata competitionMetadataItem = urlConverter.getCompetitionMetadata().get(1);
        assertEquals("Club Stableford", competitionMetadataItem.getCompetitionTitle());
        assertEquals("Sun 22 Sep 19", competitionMetadataItem.getDateOfCompetition());
        assertEquals(5336, competitionMetadataItem.getViewId());
    }
}