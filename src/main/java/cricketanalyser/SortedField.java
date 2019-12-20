package cricketanalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortedField {

    static Map<Field, Comparator> sortFieldComparator = new HashMap<>();

    enum Field {
        AVERAGE, STRIKE_RATE, CENTURY, FOURS, HALF_CENTURY, HIGH_SCORE, SIX, RUN, PLAYERS,POSITION,SIX_AND_FOURS;
    }

    SortedField() {

    }

    public static Comparator getComparatorField(SortedField.Field field) {

        Comparator<IPLCsv> iplPositionComparator = Comparator.comparing(census -> census.position);
        Comparator<IPLCsv> iplPlayerComparator = Comparator.comparing(census -> census.player);
        Comparator<IPLCsv> iplAverageComparator = Comparator.comparing(census->census.average);
        Comparator<IPLCsv> iplCenturyComparator = Comparator.comparing(census -> census.hundreds);
        Comparator<IPLCsv> iplFoursComparator = Comparator.comparing(census -> census.fours);
        Comparator<IPLCsv> iplHalfCenturyComparator = Comparator.comparing(census -> census.fiftys);
        Comparator<IPLCsv> iplHighScoreComparator = Comparator.comparing(census -> census.highScore);
        Comparator<IPLCsv> iplSixComparator = Comparator.comparing(census -> census.sixes);
        Comparator<IPLCsv> iplRunComparator = Comparator.comparing(census -> census.runs);
        Comparator<IPLCsv> iplStrikeRateComparator = Comparator.comparing(census -> census.strikeRate);

        sortFieldComparator.put(Field.POSITION, iplPositionComparator);
        sortFieldComparator.put(Field.PLAYERS, iplPlayerComparator);
        sortFieldComparator.put(Field.AVERAGE, iplAverageComparator);
        sortFieldComparator.put(Field.STRIKE_RATE, iplStrikeRateComparator);
        sortFieldComparator.put(Field.CENTURY, iplCenturyComparator);
        sortFieldComparator.put(Field.HALF_CENTURY, iplHalfCenturyComparator);
        sortFieldComparator.put(Field.FOURS, iplFoursComparator);
        sortFieldComparator.put(Field.HIGH_SCORE, iplHighScoreComparator);
        sortFieldComparator.put(Field.RUN, iplRunComparator);
        sortFieldComparator.put(Field.HIGH_SCORE, iplHighScoreComparator);
        sortFieldComparator.put(Field.SIX, iplSixComparator);
        sortFieldComparator.put(Field.SIX_AND_FOURS, new SortedFieldComparator());

        Comparator<IPLCsv> daoComparator = sortFieldComparator.get(field);
        return daoComparator;

    }

}