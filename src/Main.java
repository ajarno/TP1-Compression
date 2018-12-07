import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        String filepath = JOptionPane.showInputDialog(null, "Quel est le fichier à compresser ? (Indiquez le path)","src/LoremIpsum.txt");
        String nbByte = JOptionPane.showInputDialog(null, "Sur combien de bits voulez-vous coder chaque symbole ?", "12");

        try {
            if ((filepath != null) && (filepath.length() > 0) && (nbByte != null) && (nbByte.length() > 0)) {

                String uncompressed = new String(Files.readAllBytes(Paths.get(filepath)));
                System.out.println(uncompressed);
                String compressed = LZW.compress(uncompressed,Integer.parseInt(nbByte));
                System.out.println(compressed);
                Files.write(Paths.get("src/Compress.txt"), compressed.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}