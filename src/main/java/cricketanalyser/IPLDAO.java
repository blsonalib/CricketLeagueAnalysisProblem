package cricketanalyser;

public class IPLDAO {
  public int position;
  public String player;
  public int matches;
  public int runns;

  public IPLDAO(IPLRunsCsv iplRunsCsv){
      position = iplRunsCsv.position;
      player = iplRunsCsv.player;
      matches = iplRunsCsv.match;
      runns = iplRunsCsv.runs;
  }
    public IPLDAO(IPLWicketCsv iplWicketCsv){
      position = iplWicketCsv.position;
      player = iplWicketCsv.player;
      matches = iplWicketCsv.matches;
      runns = iplWicketCsv.runs;
    }


    public Object getIPLDTO(CricketAnalyser.Cricket cricket) {
      if(cricket.equals(CricketAnalyser.Cricket.RUNS))
          return new IPLRunsCsv(position,player,matches,runns);
      return new IPLWicketCsv(position,player,matches,runns);

    }
}
