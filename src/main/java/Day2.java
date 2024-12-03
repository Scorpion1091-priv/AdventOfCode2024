import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Day2 {

    private static final List<List<Integer>> reports = new ArrayList<>();
    private static final List<List<Integer>> unsafeReports = new ArrayList<>();
    private static final List<List<Integer>> safeReports = new ArrayList<>();
    private static final List<List<Integer>> dumpedReports = new ArrayList<>();


    public static void main(String[] args) {

        readReports();

        checkReports();

        System.out.println("Number of SafeReports: " + safeReports.size());
        System.out.println("Number of UnsafeReports: " + unsafeReports.size());

        brutForceWithDumper();

        System.out.println("Number of dumpedReports: " + dumpedReports.size());

        System.out.println("Number of SafeReports with dumper: " + (safeReports.size() + dumpedReports.size()));
        System.out.println("Number of UnsafeReports with dumper: " + (unsafeReports.size() - dumpedReports.size()));

    }

    private static void brutForceWithDumper() {

        for (List<Integer> report : unsafeReports) {
            for (int i = 0; i < report.size(); i++) {
                List<Integer> dumpedReport = new ArrayList<>(report.stream().toList());
                dumpedReport.remove(i);
                if (checkReport(dumpedReport, false)) {
                    dumpedReports.add(report);
                    break;
                }
            }
        }
    }

    private static void checkReports() {

        for (List<Integer> report : reports) {
            checkReport(report, true);
        }

    }

    private static boolean checkReport(List<Integer> report, boolean addReports) {
        if (checkDescending(report) || checkAscending(report)) {
            if (addReports) safeReports.add(report);
            return true;
        } else {
            if (addReports) unsafeReports.add(report);
            return false;
        }
    }

    private static boolean checkAscending(List<Integer> report) {
        for (int i = 0; i < report.size() - 1; i++) {
            if (report.get(i) <= report.get(i + 1) || Math.abs(report.get(i) - report.get(i + 1)) > 3) return false;
        }
        return true;
    }

    private static boolean checkDescending(List<Integer> report) {

        for (int i = 0; i < report.size() - 1; i++) {
            if (report.get(i) >= report.get(i + 1) || Math.abs(report.get(i) - report.get(i + 1)) > 3) return false;
        }
        return true;

    }

    private static void readReports() {

        ClassLoader classLoader = Day1.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("day2_input.txt");
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(streamReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                reports.add(Arrays.stream(values).map(Integer::valueOf).toList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}