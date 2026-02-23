package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataProvider {

    @DataProvider(name="csvData")
    public static Object[][] getDataFromCSV() {
        List<Object[]> data = new ArrayList<>();
        String csvPath = System.getProperty("user.dir") + "/src/test/java/resources/logindata/login-data.csv";
        try(FileReader fileReader = new FileReader(csvPath);
            CSVReader csvReader = new CSVReader(fileReader);)
        {
            List<String[]> allRows = csvReader.readAll();
            for(int i = 1; i <allRows.size(); i++){
                String[] row = allRows.get(i);
                data.add(row);
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

        return data.toArray(new Object[0][]);
    }
}
