package TestCompetitionMetadata;

import data.URLS_25_SEP;
import models.UrlConverter;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestCompetitionMetaDataFromFile {

    private final String currentDataFile = URLS_25_SEP.WHOLE_PAGE;
    private final UrlConverter urlConverter = new UrlConverter(currentDataFile);

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
        urlConverter.createListofUrls();
        assertEquals(10, urlConverter.getCompetitionMetadata().size());
    }
}

