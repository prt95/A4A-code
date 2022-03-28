import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    public List<String> readFile(String path) {
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(path))) {

            //1. filter line 3
            //2. convert all content to upper case
            //3. convert it into a List
            list = stream.collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
