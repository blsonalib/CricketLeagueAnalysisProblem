package cricketanalyser;
import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyserTest {

    public static final String CRICKET_CSV_FILE = "/home/admin1/IdeaProjects/CricketLeagueAnalyserProblem/src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() throws CricketAnalyserException {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        int battingAverage = cricketAnalyser.loadCricketIPLData(CRICKET_CSV_FILE);
        Assert.assertEquals(101, battingAverage);
    }
}
