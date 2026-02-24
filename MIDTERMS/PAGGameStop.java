package MIDTERMS;

import java.io.*;
import java.util.*;

public class PAGGameStop {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> gameList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("gameList.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                gameList.add(line);
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded " + (gameList.size() - 1) + " games.");
    }
}
