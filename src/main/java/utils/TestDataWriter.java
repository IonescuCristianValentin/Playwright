package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestDataWriter {

    private static final String FILE_PATH = "src/main/resources/testdata.txt";

    public static void saveUserData(UserData user) throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH);
        writer.write(user.getEmail() + "\n" + user.getPassword());
        writer.close();
    }

    public static UserData readUserData() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
        return new UserData(lines.get(0), lines.get(1));
    }
}

