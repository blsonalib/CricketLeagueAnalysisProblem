package cricketanalyser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockingTest {

    public static final String CRICKET_RUNS_CSV_FILE = "/home/admin1/IdeaProjects/CricketLeagueAnalyserProblem/src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Mock
    CricketAdapter cricketAdapter;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    Map<String, IPLDAO> ipldaoMap;

    @Before
    public void setUp() {
        this.ipldaoMap = new HashMap<>();
        IPLDAO ipldao = new IPLDAO();
        ipldaoMap.put("Hardik pandya", ipldao);
        ipldaoMap.put("viratKohali", ipldao);
        ipldaoMap.put("Rohit sharma", ipldao);
    }

    @Test
    public void givenIPLFactSheetData_ReturnsCorrectRecords() throws CricketAnalyserException {
        try {
            CricketAdapter cricketAdapter = mock(IPLRunAdapter.class);
            when(cricketAdapter.loadIPLCensusData(CricketAnalyser.Cricket.RUNS, CRICKET_RUNS_CSV_FILE)).thenReturn(ipldaoMap);
            CricketAnalyser cricketAnalyser = new CricketAnalyser();
            cricketAnalyser.setCricketAdapter(cricketAdapter);
            int i = cricketAnalyser.loadIPLCensusData(CricketAnalyser.Cricket.RUNS, CRICKET_RUNS_CSV_FILE);
            Assert.assertEquals(3, i);
        } catch (CricketAnalyserException e) {
            e.printStackTrace();
        }
    }
}


