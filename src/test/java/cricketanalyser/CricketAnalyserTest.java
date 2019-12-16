package cricketanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CricketAnalyserTest {

    public static final String CRICKET_CSV_FILE = "/home/admin1/IdeaProjects/CricketLeagueAnalyserProblem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    public static final String CRICKET_CSV_FILE_FOR_WRONG_DELIMETER = "/home/admin1/IdeaProjects/CricketLeagueAnalyserProblem/src/test/resources/IPL2019FactsheetMostRunsForDelimeter.csv";
    public static final String CRICKET_CSV_FILE_FOR_WITHOUT_HEADER = "/home/admin1/IdeaProjects/CricketLeagueAnalyserProblem/src/test/resources/IPL2019FactsheetMostRunsForWithoutHeader.csv";
    public static final String WRONG_IPL_CSV_FILE = "/home/admin1/IdeaProjects/CricketLeagueAnalyserProblem/src/test/resources/IPL2019FactsheetMostWktsForWrongFile.csv";

    @Test
    public void givenIPLFactSheetData_ReturnsCorrectRecords() throws CricketAnalyserException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        int records = cricketAnalyser.loadCricketIPLData(CRICKET_CSV_FILE);
        Assert.assertEquals(101, records);
    }

    @Test
    public void givenIPLFactSheetData_WithWrongDelimeter_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketAnalyserException.class);
            cricketAnalyser.loadCricketIPLData(CRICKET_CSV_FILE_FOR_WRONG_DELIMETER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_ISSUE, e.type);
        }
    }

    @Test
    public void givenIPLFactSheetData_WithWithoutHeader_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketAnalyserException.class);
            cricketAnalyser.loadCricketIPLData(CRICKET_CSV_FILE_FOR_WITHOUT_HEADER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_ISSUE, e.type);
        }
    }

    @Test
    public void givenIPLFactSheetData_WithWrongFile_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketAnalyserException.class);
            cricketAnalyser.loadCricketIPLData(WRONG_IPL_CSV_FILE);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_ISSUE, e.type);
        }
    }

    @Test
    public void givenIPLFactSheetData_WhenSortedOnTopBattingAverages_ShouldReturnSortedResult() throws CricketAnalyserException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.loadCricketIPLData(CRICKET_CSV_FILE);
        String iplPlayersRecords = cricketAnalyser.getSortIPLCricketRecords(SortedField.Field.AVERAGE);
        IPLCsv[] mostAverageRuns = new Gson().fromJson(iplPlayersRecords, IPLCsv[].class);
        Assert.assertEquals("MS Dhoni", mostAverageRuns[0].player);
    }

    @Test
    public void givenIPLFactSheetData_WhenSortedOnStrikingRates_ShouldReturnSortedResult() throws CricketAnalyserException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.loadCricketIPLData(CRICKET_CSV_FILE);
        String iplPlayersRecords = cricketAnalyser.getSortIPLCricketRecords(SortedField.Field.STRIKE_RATE);
        IPLCsv[] mostAverageRuns = new Gson().fromJson(iplPlayersRecords, IPLCsv[].class);
        Assert.assertEquals("Ishant Sharma", mostAverageRuns[0].player);
    }
}