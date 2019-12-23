package cricketanalyser;
import com.opencsv.bean.CsvBindByName;

public class IPLRunsCsv {

    public IPLRunsCsv() {
    }
    @CsvBindByName(column = "POS")
    public int position;

    @CsvBindByName(column = "PLAYER", required = true)
    public  String player;

    @CsvBindByName(column = "Mat", required = true)
    public int matches;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "NO", required = true)
    public int notOut;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "HS", required = true)
    public String highScore;

    @CsvBindByName(column = "Avg", required = true)
    public double  average;

    @CsvBindByName(column = "BF", required = true)
    public int ballFaced;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "100", required = true)
    public int hundreds;

    @CsvBindByName(column = "50", required = true)
    public int fiftys;

    @CsvBindByName(column = "4s", required = true)
    public int fours;

    @CsvBindByName(column = "6s", required = true)
    public int sixes;

    public IPLRunsCsv(int position, String player, int matches, int runs, double strikeRate, int sixes, double average, int fours) {
      this.position=position;
      this.player=player;
      this.matches=matches;
      this.runs=runs;
      this.strikeRate=strikeRate;
      this.sixes=sixes;
      this.average=average;
      this.fours=fours;
    }


    @Override
    public String toString() {
        return "IPLCsv{" +
                "position='" + position + '\'' +
                ", player='" + player + '\'' +
                ", match=" + matches +
                ", innings=" + innings +
                ", notOut=" + notOut +
                ", runs=" + runs +
                ", highScore='" + highScore + '\'' +
                ", average='" + average + '\'' +
                ", ballFaced=" + ballFaced +
                ", strikeRate=" + strikeRate +
                ", hundreds=" + hundreds +
                ", fiftys=" + fiftys +
                ", fours=" + fours +
                ", sixes=" + sixes +
                '}';
    }
}



