package cricketanalyser;

import java.util.Comparator;

public class SortedFieldComparatorForWicket implements Comparator<IPLDAO> {
    @Override
    public int compare(IPLDAO iplWicketCsv1, IPLDAO iplWicketCsv2) {
        return (((iplWicketCsv1.fourWkt * 4) + (iplWicketCsv1.fiveWicket * 5)) - ((iplWicketCsv2.fourWkt * 4) + (iplWicketCsv2.fiveWicket * 5)));
    }
}
