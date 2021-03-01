package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataStorageInitializer {
    private static final List<File> dataFiles = new ArrayList<>(Arrays.asList(
            new File("data", "users.csv"),
            new File("data", "tests.csv"),
            new File("data", "questions.csv"),
            new File("data", "answers.csv"),
            new File("data", "statistics.csv")
    ));

    private DataStorageInitializer() {
    }

    public static void initializeDataStorage() {
        dataFiles.stream()
                .filter(DataStorageInitializer::checkIsAvailable)
                .forEach(DataStorageInitializer::createFile);
    }

    private static boolean checkIsAvailable(File file) {
        return !(file.getParentFile().exists() && file.exists());
    }

    private static void createFile(File file) {
        File directory = file.getParentFile();
        directory.mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Initialization error");
        }
    }

}
