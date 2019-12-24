package cricketanalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortedField {

    static Map<Field, Comparator> sortFieldComparator = new HashMap<>();

    enum Field {
        AVERAGE_OF_BATSMAN, AVERAGE_OF_BOWLING, STRIKE_RATE, FOURS, SIX, RUN, PLAYERS, SIX_AND_FOURS,
        SIX_AND_FOURS_WITH_STRIKE_RATE, AVERAGE_WITH_BEST_STRIKE_RATE,
        MAXIMUM_RUNS_WITH_BEST_AVERAGES, ECONOMY_RATE, FOUR_WICKET, FIVE_WICKET,
        BEST_STRIKING_RATE_WITH_4AND5WICKET, BEST_BOWLINGRATE_WITH_STRIKERATE,
        MAXIMUM_WICKET_WITH_BESTBOWLINGRATE, BEST_AVERAGES_OF_BOTH, ALL_ROUNDER;
    }

    SortedField() {

    }

    public static Comparator getComparatorField(SortedField.Field field) {

        Comparator<IPLDAO> iplPlayerComparator = Comparator.comparing(census -> census.player);
        Comparator<IPLDAO> iplAverageBowlingComparator = Comparator.comparing(census -> census.bowlingAverage);
        Comparator<IPLDAO> iplAverageBattingComparator = Comparator.comparing(census -> census.batsManAverage);
        Comparator<IPLDAO> iplFoursComparator = Comparator.comparing(census -> census.fours);
        Comparator<IPLDAO> iplSixComparator = Comparator.comparing(census -> census.sixes);
        Comparator<IPLDAO> iplRunComparator = Comparator.comparing(census -> census.runs);
        Comparator<IPLDAO> iplStrikeRateComparator = Comparator.comparing(census -> census.strikeRate);
        Comparator<IPLDAO> iplEconomyRateComparator = Comparator.comparing(census -> census.economy);
        Comparator<IPLDAO> ipl4WicketComparator = Comparator.comparing(census -> census.fourWkt);
        Comparator<IPLDAO> ipl5WicketComparator = Comparator.comparing(census -> census.fiveWicket);

        sortFieldComparator.put(Field.PLAYERS, iplPlayerComparator);
        sortFieldComparator.put(Field.AVERAGE_OF_BATSMAN, iplAverageBattingComparator);
        sortFieldComparator.put(Field.AVERAGE_OF_BOWLING, iplAverageBowlingComparator);
        sortFieldComparator.put(Field.STRIKE_RATE, iplStrikeRateComparator);
        sortFieldComparator.put(Field.FOURS, iplFoursComparator);
        sortFieldComparator.put(Field.RUN, iplRunComparator);
        sortFieldComparator.put(Field.SIX, iplSixComparator);
        sortFieldComparator.put(Field.FOUR_WICKET, ipl4WicketComparator);
        sortFieldComparator.put(Field.FIVE_WICKET, ipl5WicketComparator);
        sortFieldComparator.put(Field.ECONOMY_RATE, iplEconomyRateComparator);
        sortFieldComparator.put(Field.SIX_AND_FOURS, new SortedFieldComparator());
        sortFieldComparator.put(Field.BEST_STRIKING_RATE_WITH_4AND5WICKET, new SortedFieldComparatorForWicket());
        sortFieldComparator.put(Field.SIX_AND_FOURS_WITH_STRIKE_RATE, new SortedFieldComparator().thenComparing(iplStrikeRateComparator));
        sortFieldComparator.put(Field.AVERAGE_WITH_BEST_STRIKE_RATE, (iplAverageBattingComparator).thenComparing(iplStrikeRateComparator));
        sortFieldComparator.put(Field.MAXIMUM_RUNS_WITH_BEST_AVERAGES, (iplRunComparator).thenComparing(iplAverageBattingComparator));
        sortFieldComparator.put(Field.BEST_BOWLINGRATE_WITH_STRIKERATE, (iplAverageBowlingComparator).thenComparing(iplStrikeRateComparator));
        sortFieldComparator.put(Field.MAXIMUM_WICKET_WITH_BESTBOWLINGRATE, new SortedFieldComparator().thenComparing(iplAverageBowlingComparator));
        sortFieldComparator.put(Field.BEST_AVERAGES_OF_BOTH, (iplAverageBattingComparator).thenComparing(iplAverageBattingComparator));
        sortFieldComparator.put(Field.ALL_ROUNDER, new SortedFieldComparator().thenComparing(iplRunComparator));

        Comparator<IPLDAO> daoComparator = sortFieldComparator.get(field);
        return daoComparator;

    }
}