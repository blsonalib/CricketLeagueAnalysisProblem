package cricketanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyser {

    private Cricket cricket;
    private CricketAdapter cricketAdapter;

    public void setCricketAdapter(CricketAdapter cricketAdapter) {
        this.cricketAdapter = cricketAdapter;
    }

    enum Cricket {RUNS, WICKET}

    CricketAnalyser(Cricket cricket) {
        this.cricket = cricket;
    }

    public CricketAnalyser() {
    }

    Map<String, IPLDAO> ipldaoMap = new HashMap<>();

    public int loadIPLCensusData(Cricket cricket, String... csvFilePath) throws CricketAnalyserException {
        ipldaoMap = cricketAdapter.loadIPLCensusData(cricket, csvFilePath);
        return ipldaoMap.size();
    }

    public String getSortIPLCricketRecord(SortedField.Field... field) throws CricketAnalyserException {
        if (ipldaoMap == null || ipldaoMap.size() == 0) {
            throw new CricketAnalyserException("No Census Data", CricketAnalyserException.ExceptionType.DATA_NOT_FOUND);
        }

        Comparator<IPLDAO> censusComparator = SortedField.getComparatorField(field[0]);
        ArrayList censusDAOS = ipldaoMap.values().stream().sorted(censusComparator).
                map(censusDAO -> censusDAO.getIPLDTO(cricket)).
                collect(Collectors.toCollection(ArrayList::new));
        String sortedIPLDataJson = new Gson().toJson(censusDAOS);
        return sortedIPLDataJson;
    }
}

