package cricketanalyser;

import java.util.Map;

public class IPLRunAdapter extends CricketAdapter {
    public Map<String, IPLDAO> loadIPLCensusData(CricketAnalyser.Cricket runs, String... csvFilePath) throws CricketAnalyserException {
        Map<String, IPLDAO> ipldaoMap = super.loadIPLCensusData(IPLRunsCsv.class, csvFilePath);
        return ipldaoMap;
    }
}
