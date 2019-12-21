package cricketanalyser;

import java.util.Map;

public class IPLWicketAdapter extends CricketAdapter {
    @Override
    public Map<String, IPLDAO> loadIPLCensusData(String... csvfilePath) throws CricketAnalyserException {
        Map<String,IPLDAO> ipldaoMap = super.loadIPLCensusData(IPLWicketCsv.class, csvfilePath[0]);
        return ipldaoMap;
    }
}
