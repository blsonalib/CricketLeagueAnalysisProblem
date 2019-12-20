package cricketanalyser;

import com.bridgelabz.censusanalyser.CSVBuilderException;
import com.bridgelabz.censusanalyser.CSVBuilderFactory;
import com.bridgelabz.censusanalyser.ICSVBuilder;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CricketAnalyser {

    List<IPLCsv> iplCsvsList = new ArrayList<>();

    public int loadCricketIPLData(String csvFilePath) throws CricketAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvbuilder = CSVBuilderFactory.createCsvbuilder();
            iplCsvsList = csvbuilder.getCSVFileList(reader, IPLCsv.class);
            return iplCsvsList.size();

        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.DATA_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.FILE_ISSUE);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getSortIPLCricketRecords(SortedField.Field field) throws CricketAnalyserException {
        if (iplCsvsList == null || iplCsvsList.size() == 0) {
            throw new CricketAnalyserException("No Census Data", CricketAnalyserException.ExceptionType.DATA_NOT_FOUND);
        }
        Comparator<IPLCsv> censusComparator = SortedField.getComparatorField(field);
        iplCsvsList.sort(censusComparator);
        iplCsvsList.forEach(System.out::println);
        return new Gson().toJson(iplCsvsList);
    }
}