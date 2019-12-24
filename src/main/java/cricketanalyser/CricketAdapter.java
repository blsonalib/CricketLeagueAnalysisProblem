package cricketanalyser;

import com.bridgelabz.censusanalyser.CSVBuilderException;
import com.bridgelabz.censusanalyser.CSVBuilderFactory;
import com.bridgelabz.censusanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class CricketAdapter {

    public abstract Map<String, IPLDAO> loadIPLCensusData(CricketAnalyser.Cricket runs, String... csvfilePath) throws CricketAnalyserException;

    protected <E> Map<String, IPLDAO> loadIPLCensusData(Class<E> IPLCsvClass, String... csvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(String.valueOf(csvFilePath[0])));) {
            ICSVBuilder csvbuilder = CSVBuilderFactory.createCsvbuilder();
            Map<String, IPLDAO> ipldaoMap = new HashMap<>();
            Iterator<E> csvIterator = csvbuilder.getCSVFileIterator(reader, IPLCsvClass);
            Iterable<E> csvIterable = () -> csvIterator;
            if (IPLCsvClass.getName().equals("cricketanalyser.IPLRunsCsv")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLRunsCsv.class::cast)
                        .forEach(censusCsv -> ipldaoMap.put(censusCsv.player, new IPLDAO(censusCsv)));
            } else if (IPLCsvClass.getName().equals("cricketanalyser.IPLWicketCsv")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLWicketCsv.class::cast)
                        .forEach(censusCsv -> ipldaoMap.put(censusCsv.player, new IPLDAO(censusCsv)));
            }
            return ipldaoMap;
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.DATA_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.FILE_ISSUE);

        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
        return null;
    }

}

