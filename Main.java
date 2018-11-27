import java.util.*;
import LZW.java;

public class Main {

    public static void main(String[] args) {

        List<Integer> compressed = compress("Je suis ton père",12);

        System.out.println(compressed + compressed.getSizeOfFile().toString);
        String decompressed = decompress(compressed);
        System.out.println(decompressed);
    }
}