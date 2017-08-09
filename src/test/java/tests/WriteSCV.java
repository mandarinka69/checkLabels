package tests;

import org.apache.commons.csv.CSVUtils;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.util.Arrays;

public class WriteSCV {
    @Test()
    public void main() throws Exception {

        FileWriter writer = new FileWriter(System.getProperty("user.dir") + "/src/test/java/main/resources/test.csv");

        writer.append("ID");
        writer.append(',');
        writer.append("name");
        writer.append(',');
//...
        writer.append('\n');

        writer.flush();
        writer.close();

    }
}
