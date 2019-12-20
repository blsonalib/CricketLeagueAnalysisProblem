package cricketanalyser;

import java.util.Comparator;

public class SortedFieldComparator implements Comparator<IPLCsv> {

    @Override
    public int compare(IPLCsv iplCsv1, IPLCsv iplCsv2) {
        return (((iplCsv1.sixes * 6) + (iplCsv1.fours * 4)) - ((iplCsv2.sixes * 6) + (iplCsv2.fours * 4)));
    }
}
