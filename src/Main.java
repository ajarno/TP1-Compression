import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {

    public static void main(String[] args) {

        try {
            String uncompressed = new String(Files.readAllBytes(Paths.get("src/Bonjour.txt")));
            System.out.println(uncompressed);
            String compressed = LZW.compress(uncompressed,12);
            System.out.println(compressed);
            Files.write(Paths.get("src/Compress.txt"), compressed.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}