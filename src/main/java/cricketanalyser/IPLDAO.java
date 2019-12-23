package cricketanalyser;

public class IPLDAO {
    public int position;
    public String player;
    public int matches;
    public int runs;
    public int sixes;
    public double average;
    public double strikeRate;
    public int fours;
    public double economy;
    public int fourWkt;
    public int fiveWicket;


    public IPLDAO(IPLRunsCsv iplRunsCsv) {
        position = iplRunsCsv.position;
        player = iplRunsCsv.player;
        matches = iplRunsCsv.match;
        runs = iplRunsCsv.runs;
        strikeRate = iplRunsCsv.strikeRate;
        sixes = iplRunsCsv.sixes;
        average = iplRunsCsv.average;
        fours = iplRunsCsv.fours;

    }

    public IPLDAO(IPLWicketCsv iplWicketCsv) {
        position = iplWicketCsv.position;
        player = iplWicketCsv.player;
        matches = iplWicketCsv.matches;
        runs = iplWicketCsv.runs;
        strikeRate = iplWicketCsv.strikeRate;
        average = iplWicketCsv.average;
        economy = iplWicketCsv.economy;
        fiveWicket = iplWicketCsv.fiveWicket;
        fiveWicket = iplWicketCsv.fiveWicket;
    }

    public Object getIPLDTO(CricketAnalyser.Cricket cricket) {
        if (cricket.equals(CricketAnalyser.Cricket.RUNS))
            return new IPLRunsCsv(position, player, matches, runs, strikeRate, sixes, average, fours);
        return new IPLWicketCsv(position, player, runs, average, matches, strikeRate, economy,fourWkt,fiveWicket);

    }
}
