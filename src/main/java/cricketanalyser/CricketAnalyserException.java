package cricketanalyser;

public class CricketAnalyserException extends Exception{
    enum ExceptionType
    {
        DATA_NOT_FOUND,FILE_ISSUE,INCORRECT_FILE;
    }
    ExceptionType type;

    public CricketAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
