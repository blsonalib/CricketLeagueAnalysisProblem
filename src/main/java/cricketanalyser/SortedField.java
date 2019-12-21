package cricketanalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortedField {

    static Map<Field, Comparator> sortFieldComparator = new HashMap<>();

    enum Field {
        AVERAGE, STRIKE_RATE, FOURS,SIX,RUN,PLAYERS,SIX_AND_FOURS,
        SIX_AND_FOURS_WITH_STRIKE_RATE,AVERAGE_WITH_BEST_STRIKE_RATE,
        MAXIMUM_RUNS_WITH_BEST_AVERAGES;
    }

    SortedField() {

    }

    public static Comparator getComparatorField(SortedField.Field field) {

        Comparator<IPLDAO> iplPlayerComparator = Comparator.comparing(census -> census.player);
        Comparator<IPLDAO> iplAverageComparator = Comparator.comparing(census->census.average);
        Comparator<IPLDAO> iplFoursComparator = Comparator.comparing(census -> census.fours);
        Comparator<IPLDAO> iplSixComparator = Comparator.comparing(census -> census.sixes);
        Comparator<IPLDAO> iplRunComparator = Comparator.comparing(census -> census.runs);
        Comparator<IPLDAO> iplStrikeRateComparator = Comparator.comparing(census -> census.strikeRate);

        sortFieldComparator.put(Field.PLAYERS, iplPlayerComparator);
        sortFieldComparator.put(Field.AVERAGE, iplAverageComparator);
        sortFieldComparator.put(Field.STRIKE_RATE, iplStrikeRateComparator);
        sortFieldComparator.put(Field.FOURS, iplFoursComparator);
        sortFieldComparator.put(Field.RUN, iplRunComparator);
        sortFieldComparator.put(Field.SIX, iplSixComparator);
        sortFieldComparator.put(Field.SIX_AND_FOURS, new SortedFieldComparator());
        sortFieldComparator.put(Field.SIX_AND_FOURS_WITH_STRIKE_RATE, new SortedFieldComparator().thenComparing(iplStrikeRateComparator));
        sortFieldComparator.put(Field.AVERAGE_WITH_BEST_STRIKE_RATE,(iplAverageComparator).thenComparing(iplStrikeRateComparator));
        sortFieldComparator.put(Field.MAXIMUM_RUNS_WITH_BEST_AVERAGES,(iplRunComparator).thenComparing(iplAverageComparator));

        Comparator<IPLRunsCsv> daoComparator = sortFieldComparator.get(field);
        return daoComparator;

    }
}