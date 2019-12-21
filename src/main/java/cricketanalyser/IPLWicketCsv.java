package cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLWicketCsv {
    public IPLWicketCsv() {
    }

    @CsvBindByName(column = "POS")
    public int position;

    @CsvBindByName(column = "PLAYER")
    public String player;

    @CsvBindByName(column = "Mat")
    public int matches;

    @CsvBindByName(column = "Inns")
    public int inning;

    @CsvBindByName(column = "Ov")
    public double over;

    @CsvBindByName(column = "Runs")
    public int runs;

    @CsvBindByName(column = "Wkts")
    public int wickets;

    @CsvBindByName(column = "BBI")
    public int bestBallingInning;

    @CsvBindByName(column = "Avg")
    public double average;

    @CsvBindByName(column = "Econ")
    public int economy;

    @CsvBindByName(column = "SR")
    public double strikeRate;

    @CsvBindByName(column = "4w")
    public int fourWkt;

    @CsvBindByName(column = "5w")
    public int fiveWicket;

    public IPLWicketCsv(int position, String player, int matches, int runns) {
    }


    @Override
    public String toString() {
        return "IPLWicketCsv{" +
                "position=" + position +
                ", player='" + player + '\'' +
                ", matches=" + matches +
                ", inning=" + inning +
                ", over=" + over +
                ", runs=" + runs +
                ", wickets=" + wickets +
                ", bestBallingInning=" + bestBallingInning +
                ", average=" + average +
                ", economy=" + economy +
                ", strikeRate=" + strikeRate +
                ", fourWkt=" + fourWkt +
                ", fiveWicket=" + fiveWicket +
                '}';
    }

    public IPLWicketCsv(int position, String player, int matches, int inning, double over, int runs, int wickets, int bestBallingInning, double average, int economy, double strikeRate, int fourWkt, int fiveWicket) {
        this.position = position;
        this.player = player;
        this.matches = matches;
        this.inning = inning;
        this.over = over;
        this.runs = runs;
        this.wickets = wickets;
        this.bestBallingInning = bestBallingInning;
        this.average = average;
        this.economy = economy;
        this.strikeRate = strikeRate;
        this.fourWkt = fourWkt;
        this.fiveWicket = fiveWicket;
    }
}
