package cricketanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CricketAnalyserTest {

    public static final String CRICKET_RUNS_CSV_FILE = "/home/admin1/IdeaProjects/CricketLeagueAnalyserProblem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    public static final String CRICKET_CSV_FILE_FOR_WRONG_DELIMETER = "/home/admin1/IdeaProjects/CricketLeagueAnalyserProblem/src/test/resources/IPL2019FactsheetMostRunsForDelimeter.csv";
    public static final String CRICKET_CSV_FILE_FOR_WITHOUT_HEADER = "/home/admin1/IdeaProjects/CricketLeagueAnalyserProblem/src/test/resources/IPL2019FactsheetMostRunsForWithoutHeader.csv";
    public static final String WRONG_IPL_CSV_FILE = "/home/admin1/IdeaProjects/CricketLeagueAnalyserProblem/src/test/resources/IPL2019FactsheetMostWktsForWrongFile.csv";
    public static final String CRICKET_WICKETS_CSV_FILE = "/home/admin1/IdeaProjects/CricketLeagueAnalyserProblem/src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLFactSheetData_ReturnsCorrectRecords() throws CricketAnalyserException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.RUNS);
        int records = cricketAnalyser.loadIPLCensusData(CricketAnalyser.Cricket.RUNS,CRICKET_RUNS_CSV_FILE);
        Assert.assertEquals(100, records);
    }

    @Test
    public void givenIPLFactSheetData_WithWrongDelimeter_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.RUNS);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketAnalyserException.class);
            cricketAnalyser.loadIPLCensusData(CricketAnalyser.Cricket.RUNS,CRICKET_CSV_FILE_FOR_WRONG_DELIMETER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_ISSUE, e.type);
        }
    }

    @Test
    public void givenIPLFactSheetData_WithWithoutHeader_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.RUNS);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketAnalyserException.class);
            cricketAnalyser.loadIPLCensusData(CricketAnalyser.Cricket.RUNS,CRICKET_CSV_FILE_FOR_WITHOUT_HEADER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_ISSUE, e.type);
        }
    }

    @Test
    public void givenIPLFactSheetData_WithWrongFile_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.RUNS);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketAnalyserException.class);
            cricketAnalyser.loadIPLCensusData(CricketAnalyser.Cricket.RUNS,WRONG_IPL_CSV_FILE);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_ISSUE, e.type);
        }
    }

    @Test
    public void givenIPLFactSheetData_WhenSortedOnTopBattingAverages_ShouldReturnSortedResult() throws CricketAnalyserException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.RUNS);
        cricketAnalyser.loadIPLCensusData(CricketAnalyser.Cricket.RUNS,CRICKET_RUNS_CSV_FILE);
        String iplPlayersRecords = cricketAnalyser.getSortIPLCricketRecord(SortedField.Field.AVERAGE);
        IPLRunsCsv[] mostAverageRuns = new Gson().fromJson(iplPlayersRecords, IPLRunsCsv[].class);
        Assert.assertEquals("MS Dhoni", mostAverageRuns[mostAverageRuns.length-1].player);
    }

    @Test
    public void givenIPLFactSheetData_WhenSortedOnStrikingRates_ShouldReturnSortedResult() throws CricketAnalyserException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.RUNS);
        cricketAnalyser.loadIPLCensusData(CricketAnalyser.Cricket.RUNS,CRICKET_RUNS_CSV_FILE);
        String iplPlayersRecords = cricketAnalyser.getSortIPLCricketRecord( SortedField.Field.STRIKE_RATE);
        IPLRunsCsv[] mostAverageRuns = new Gson().fromJson(iplPlayersRecords, IPLRunsCsv[].class);
        Assert.assertEquals("Ishant Sharma", mostAverageRuns[mostAverageRuns.length-1].player);
    }

    @Test
    public void givenIPLFactSheetData_WhenSortedOn6sAnd4s_ShouldReturnMaximumHitsOf6sAnd4s() throws CricketAnalyserException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.RUNS);
        cricketAnalyser.loadIPLCensusData(CricketAnalyser.Cricket.RUNS,CRICKET_RUNS_CSV_FILE);
        String iplPlayersRecords = cricketAnalyser.getSortIPLCricketRecord(SortedField.Field.SIX_AND_FOURS);
        IPLRunsCsv[] mostAverageRuns = new Gson().fromJson(iplPlayersRecords, IPLRunsCsv[].class);
        Assert.assertEquals("Andre Russell", mostAverageRuns[mostAverageRuns.length-1].player);
    }

    @Test
    public void givenIPLFactSheetData_WhenSortedOnGreatAveragesAndBestStrikingRate_ShouldReturnBestStrikinRateWithGreatAverages() throws CricketAnalyserException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.RUNS);
        cricketAnalyser.loadIPLCensusData(CricketAnalyser.Cricket.RUNS,CRICKET_RUNS_CSV_FILE);
        String iplPlayersRecords = cricketAnalyser.getSortIPLCricketRecord(SortedField.Field.AVERAGE_WITH_BEST_STRIKE_RATE);
        IPLRunsCsv[] mostAverageRuns = new Gson().fromJson(iplPlayersRecords, IPLRunsCsv[].class);
        Assert.assertEquals("MS Dhoni", mostAverageRuns[mostAverageRuns.length-1].player);
    }
    @Test
    public void givenIPLFactSheetData_WhenSortedMaximumRunsAndBestAverages_ShouldReturnMaximumRunsWithGreatAverages() throws CricketAnalyserException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser(CricketAnalyser.Cricket.RUNS);
        cricketAnalyser.loadIPLCensusData(CricketAnalyser.Cricket.RUNS,CRICKET_RUNS_CSV_FILE);
        String iplPlayersRecords = cricketAnalyser.getSortIPLCricketRecord(SortedField.Field.RUN);
        IPLRunsCsv[] mostAverageRuns = new Gson().fromJson(iplPlayersRecords, IPLRunsCsv[].class);
        Assert.assertEquals("David Warner", mostAverageRuns[mostAverageRuns.length-1].player);
    }
}


