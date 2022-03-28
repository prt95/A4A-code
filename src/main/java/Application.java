import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String [] args) {
        ResourceReader.initialiseProperties();
        System.out.println(ResourceReader.getProperty(Constants.HELP_PROPERTY));
        Runtime.getRuntime().addShutdownHook( new Thread(()-> System.out.println("Bye")));
        /*
          Allowed input formats: 1. console input 2. File input
         */
        Scanner scanner = new Scanner(System.in);
        int selectInput = Integer.valueOf(scanner.nextLine());
        List<String> texts = new ArrayList<>();

        while (true) {

            switch (selectInput) {
                case 1:
                    System.out.println(ResourceReader.getProperty(Constants.CONSOLE_INPUT_PROPERTY));
                    texts.add(scanner.nextLine());
                    List<Integer> result = process(texts);
                    System.out.println(ResourceReader.
                            getProperty(Constants.RESULT_PROPERTY, texts.get(0), String.valueOf(result.get(0))));
                    texts = new ArrayList<>();
                    break;
                case 2:
                     System.out.println(ResourceReader.getProperty(Constants.FILE_INPUT_PROPERTY));
                     texts = new FileReader().readFile(scanner.nextLine());
                     List<Integer> results = process(texts);
                     outputResultFile(texts, results);
                    break;
            }

            System.out.println(ResourceReader.getProperty(Constants.EXIT_MSG_PROPERTY));
            String shouldExit = scanner.nextLine();
            if(shouldExit.equalsIgnoreCase("Y")) {
                System.exit(0);
            }


        }
    }


    private static void outputResultFile(List<String> inputs, List<Integer> outputs) {
        File file = new File(Constants.OUTPUT_FILE_NAME);
        if(file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(Constants.OUTPUT_FILE_NAME);
            for(int idx=0; idx < inputs.size() ;idx++) {
                fileWriter.append(ResourceReader.
                        getProperty(Constants.RESULT_PROPERTY, inputs.get(idx), String.valueOf(outputs.get(idx)))+"\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            
        }

    }

    private static List<Integer> process(List<String> texts) {
        List<Integer> result = new ArrayList<>();
        texts.forEach(text -> result.add(new FrequencyProcessor(text).findMinimumRemovalForUniqueFrequency()));
        return result;
    }


}
