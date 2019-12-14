package cricketanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CricketAnalyser {

    public int loadCricketIPLData(String csvFilePath) throws CricketAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IPLCsv> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IPLCsv.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IPLCsv> csvToBean = csvToBeanBuilder.build();
            Iterator<IPLCsv> IPLCsvIterator = csvToBean.iterator();
            Iterable<IPLCsv> csvIterable = () -> IPLCsvIterator;
            int playerCounts = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return playerCounts;
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.DATA_NOT_FOUND);
        }
    }
}
