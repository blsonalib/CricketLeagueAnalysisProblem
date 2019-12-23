package cricketanalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortedField {

    static Map<Field, Comparator> sortFieldComparator = new HashMap<>();

    enum Field {
        AVERAGE, STRIKE_RATE, FOURS,SIX,RUN,PLAYERS,SIX_AND_FOURS,
        SIX_AND_FOURS_WITH_STRIKE_RATE,AVERAGE_WITH_BEST_STRIKE_RATE,
        MAXIMUM_RUNS_WITH_BEST_AVERAGES,ECONOMY_RATE,FOUR_WICKET,FIVE_WICKET,
        BEST_STRIKING_RATE_WITH_4AND5WICKET,BEST_BALLINGRATE_WITH_STRIKERATE,
        MAXIMUM_WICKET_WITH_BESTBALLINGRATE,BEST_AVERAGES_OF_BOTH;
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
        Comparator<IPLDAO> iplEconomyRateComparator = Comparator.comparing(census -> census.economy);
        Comparator<IPLDAO> ipl4WicketComparator = Comparator.comparing(census -> census.fourWkt);
        Comparator<IPLDAO> ipl5WicketComparator = Comparator.comparing(census -> census.fiveWicket);

        sortFieldComparator.put(Field.PLAYERS, iplPlayerComparator);
        sortFieldComparator.put(Field.AVERAGE, iplAverageComparator);
        sortFieldComparator.put(Field.STRIKE_RATE, iplStrikeRateComparator);
        sortFieldComparator.put(Field.FOURS, iplFoursComparator);
        sortFieldComparator.put(Field.RUN, iplRunComparator);
        sortFieldComparator.put(Field.SIX, iplSixComparator);
        sortFieldComparator.put(Field.FOUR_WICKET,ipl4WicketComparator);
        sortFieldComparator.put(Field.FIVE_WICKET, ipl5WicketComparator);
        sortFieldComparator.put(Field.ECONOMY_RATE,iplEconomyRateComparator);
        sortFieldComparator.put(Field.SIX_AND_FOURS, new SortedFieldComparator());
        sortFieldComparator.put(Field.BEST_STRIKING_RATE_WITH_4AND5WICKET, new SortedFieldComparatorForWicket());
        sortFieldComparator.put(Field.SIX_AND_FOURS_WITH_STRIKE_RATE, new SortedFieldComparator().thenComparing(iplStrikeRateComparator));
        sortFieldComparator.put(Field.AVERAGE_WITH_BEST_STRIKE_RATE,(iplAverageComparator).thenComparing(iplStrikeRateComparator));
        sortFieldComparator.put(Field.MAXIMUM_RUNS_WITH_BEST_AVERAGES,(iplRunComparator).thenComparing(iplAverageComparator));
        sortFieldComparator.put(Field.BEST_BALLINGRATE_WITH_STRIKERATE,(iplAverageComparator).thenComparing(iplStrikeRateComparator));
        sortFieldComparator.put(Field.MAXIMUM_WICKET_WITH_BESTBALLINGRATE,new SortedFieldComparator().thenComparing(iplAverageComparator));
        sortFieldComparator.put(Field.BEST_AVERAGES_OF_BOTH,(iplAverageComparator).thenComparing(iplAverageComparator));

        Comparator<IPLDAO> daoComparator = sortFieldComparator.get(field);
        return daoComparator;

    }
}