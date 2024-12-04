import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Day4 {

    private static List<List<String>> puzzle = new ArrayList<>();
    private static int numberOfFounds = 0;
    private static int numberOfMasXes = 0;


    public static void main(String[] args) {
        readPuzzle();
        countXMAS();
        System.out.println("Found " + numberOfFounds + " XMAS");
        countMasXes();
        System.out.println("Found " + numberOfMasXes + " MAS-Xes");
    }

    private static void countMasXes() {

        for (int i = 1; i < puzzle.size() - 1; i++) {
            for (int j = 1; j < puzzle.get(i).size() - 1; j++) {
                if (puzzle.get(i).get(j).equalsIgnoreCase("A")) {
                    searchMAndS(i,j);
                }
            }
        }

    }

    private static void searchMAndS(int i, int j) {

        boolean firstDiagonal = false;
        boolean secondDiagonal = false;


        if((puzzle.get(i-1).get(j-1).equalsIgnoreCase("M")
            && puzzle.get(i+1).get(j+1).equalsIgnoreCase("S")) ||
            (puzzle.get(i-1).get(j-1).equalsIgnoreCase("S")
             && puzzle.get(i+1).get(j+1).equalsIgnoreCase("M"))) {
            firstDiagonal = true;
        }

        if((puzzle.get(i+1).get(j-1).equalsIgnoreCase("M")
                && puzzle.get(i-1).get(j+1).equalsIgnoreCase("S")) ||
                (puzzle.get(i+1).get(j-1).equalsIgnoreCase("S")
                        && puzzle.get(i-1).get(j+1).equalsIgnoreCase("M"))) {
            secondDiagonal = true;
        }

        if (firstDiagonal && secondDiagonal) {
            numberOfMasXes++;
        }

    }

    private static void countXMAS() {

        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = 0; j < puzzle.get(i).size(); j++) {
                if (puzzle.get(i).get(j).equalsIgnoreCase("X")) {
                    searchDirections(i,j);
                }
            }
        }


    }

    private static void searchDirections(int i, int j) {

        searchNorth(i,j);
        searchNorthEast(i,j);
        searchEast(i,j);
        searchSouthEast(i,j);
        searchSouth(i,j);
        searchSouthWest(i,j);
        searchWest(i,j);
        searchNorthWest(i,j);


    }

    private static void searchNorthWest(int i, int j) {
        if (i < 3 || j < 3) return;

        if (puzzle.get(i-1).get(j-1).equalsIgnoreCase("M")
                && puzzle.get(i-2).get(j-2).equalsIgnoreCase("A")
                && puzzle.get(i-3).get(j-3).equalsIgnoreCase("S")
        ) {
            numberOfFounds++;
        }
    }

    private static void searchWest(int i, int j) {
        if (j < 3) return;

        if (puzzle.get(i).get(j-1).equalsIgnoreCase("M")
                && puzzle.get(i).get(j-2).equalsIgnoreCase("A")
                && puzzle.get(i).get(j-3).equalsIgnoreCase("S")
        ) {
            numberOfFounds++;
        }
    }

    private static void searchSouthWest(int i, int j) {
        if (i > puzzle.size() - 4 || j < 3) return;

        if (puzzle.get(i+1).get(j-1).equalsIgnoreCase("M")
                && puzzle.get(i+2).get(j-2).equalsIgnoreCase("A")
                && puzzle.get(i+3).get(j-3).equalsIgnoreCase("S")
        ) {
            numberOfFounds++;
        }
    }

    private static void searchSouth(int i, int j) {
        if (i > puzzle.size() - 4) return;

        if (puzzle.get(i+1).get(j).equalsIgnoreCase("M")
                && puzzle.get(i+2).get(j).equalsIgnoreCase("A")
                && puzzle.get(i+3).get(j).equalsIgnoreCase("S")
        ) {
            numberOfFounds++;
        }
    }

    private static void searchSouthEast(int i, int j) {
        if (i > puzzle.size() - 4 || j > puzzle.get(i).size() - 4) return;

        if (puzzle.get(i+1).get(j+1).equalsIgnoreCase("M")
                && puzzle.get(i+2).get(j+2).equalsIgnoreCase("A")
                && puzzle.get(i+3).get(j+3).equalsIgnoreCase("S")
        ) {
            numberOfFounds++;
        }
    }

    private static void searchEast(int i, int j) {
        if (j > puzzle.get(i).size() - 4) return;

        if (puzzle.get(i).get(j+1).equalsIgnoreCase("M")
            && puzzle.get(i).get(j+2).equalsIgnoreCase("A")
            && puzzle.get(i).get(j+3).equalsIgnoreCase("S")
        ) {
            numberOfFounds++;
        }
    }

    private static void searchNorthEast(int i, int j) {
        if (i < 3 || j > puzzle.get(i).size() - 4) return;

        if (puzzle.get(i-1).get(j+1).equalsIgnoreCase("M")
            && puzzle.get(i-2).get(j+2).equalsIgnoreCase("A")
            && puzzle.get(i-3).get(j+3).equalsIgnoreCase("S")
        ) {
            numberOfFounds++;
        }

    }

    private static void searchNorth(int i, int j) {

        if (i < 3) return;
        if (puzzle.get(i-1).get(j).equalsIgnoreCase("M")
                && puzzle.get(i-2).get(j).equalsIgnoreCase("A")
                && puzzle.get(i-3).get(j).equalsIgnoreCase("S")
        ) {
            numberOfFounds++;
        }
    }

    private static void readPuzzle() {

        ClassLoader classLoader = Day1.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("day4_input.txt");
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(streamReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                puzzle.add(List.of(line.split("")));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
