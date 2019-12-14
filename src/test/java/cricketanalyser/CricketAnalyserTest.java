package cricketanalyser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CricketAnalyserTest {

    public static final String CRICKET_CSV_FILE = "/home/admin1/IdeaProjects/CricketLeagueAnalyserProblem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    public static final String CRICKET_CSV_FILE_FOR_WRONG_DELIMETER="/home/admin1/IdeaProjects/CricketLeagueAnalyserProblem/src/test/resources/IPL2019FactsheetMostRunsForDelimeter.csv";
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() throws CricketAnalyserException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        int battingAverage = cricketAnalyser.loadCricketIPLData(CRICKET_CSV_FILE);
        Assert.assertEquals(101, battingAverage);
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CricketAnalyser cricketAnalyser=new CricketAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CricketAnalyserException.class);
            cricketAnalyser.loadCricketIPLData(CRICKET_CSV_FILE_FOR_WRONG_DELIMETER);
        } catch (CricketAnalyserException e) {
            Assert.assertEquals(CricketAnalyserException.ExceptionType.FILE_ISSUE,e.type);
        }

    }
}
