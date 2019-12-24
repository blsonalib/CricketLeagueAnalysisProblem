package cricketanalyser;

import com.bridgelabz.censusanalyser.CSVBuilderException;
import com.bridgelabz.censusanalyser.CSVBuilderFactory;
import com.bridgelabz.censusanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IPLWicketAdapter extends CricketAdapter {
    @Override
    public Map<String, IPLDAO> loadIPLCensusData(CricketAnalyser.Cricket runs, String... csvPath) throws CricketAnalyserException {
        Map<String,IPLDAO> ipldaoMap = super.loadIPLCensusData(IPLWicketCsv.class, csvPath[0]);
        this.loadRunData(ipldaoMap, csvPath);
        return ipldaoMap;
    }


    private Map<String, IPLDAO> loadRunData(Map<String, IPLDAO> ipldaoMap, String...csvPath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvPath[1]))) {
            ICSVBuilder csvbuilder = CSVBuilderFactory.createCsvbuilder();
            Iterator<IPLRunsCsv> runCsvIterator = csvbuilder.getCSVFileIterator(reader, IPLRunsCsv.class);
            Iterable<IPLRunsCsv> csvIterable = () -> runCsvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false).
                    filter(csvRun ->ipldaoMap.get(csvRun.player) != null).
                    forEach(csvRun -> ipldaoMap.get(csvRun.player).bowlingAverage = csvRun.average);
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.DATA_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.FILE_ISSUE);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
        return ipldaoMap;
    }
}