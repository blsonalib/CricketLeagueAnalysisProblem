package cricketanalyser;

import java.util.Comparator;

public class SortedFieldComparator implements Comparator<IPLDAO> {

    @Override
    public int compare(IPLDAO iplRunsCsv1, IPLDAO iplRunsCsv2 ) {
        return (((iplRunsCsv1.sixes * 6) + (iplRunsCsv1.fours * 4)) - ((iplRunsCsv2.sixes * 6) + (iplRunsCsv2.fours * 4)));
    }
}

