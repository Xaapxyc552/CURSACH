import java.io.File;
import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws IOException {
        File datafile=  new File("data","data.csv");
        File directory = datafile.getParentFile();
        directory.mkdirs();
        datafile.createNewFile();


    }
}
