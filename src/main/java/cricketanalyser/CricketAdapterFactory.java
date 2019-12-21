package cricketanalyser;

public class CricketAdapterFactory {
    public static CricketAdapter getCricketData(CricketAnalyser.Cricket cricket) throws CricketAnalyserException {
        if(cricket.equals(CricketAnalyser.Cricket.RUNS))
            return new IPLRunAdapter();
        else if(cricket.equals(CricketAnalyser.Cricket.WICKET))
            return new IPLWicketAdapter();
        else
            throw new CricketAnalyserException("Incorrect File",CricketAnalyserException.ExceptionType.INCORRECT_FILE);
    }

}


