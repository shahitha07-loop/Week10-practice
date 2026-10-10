import java.util.*;

public class MarksGridAnalysis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> names = new ArrayList<>();
        List<int[]> marksList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            
            int bracketIdx = line.indexOf('[');
            String name = line.substring(0, bracketIdx).trim();
            String marksStr = line.substring(bracketIdx + 1, line.indexOf(']'));
            String[] markParts = marksStr.split(",");
            int[] marks = new int[markParts.length];
            for (int i = 0; i < markParts.length; i++) {
                marks[i] = Integer.parseInt(markParts[i].trim());
            }
            names.add(name);
            marksList.add(marks);
        }

        StringBuilder totalsStr = new StringBuilder("Totals ");
        int maxTotal = -1;
        String topper = "";
        int numStudents = names.size();
        int numSubjects = 3;
        double[] subSums = new double[numSubjects];

        for (int i = 0; i < numStudents; i++) {
            String name = names.get(i);
            int[] marks = marksList.get(i);
            int total = 0;
            for (int j = 0; j < marks.length; j++) {
                total += marks[j];
                subSums[j] += marks[j];
            }
            totalsStr.append(name).append(" ").append(total);
            if (i < numStudents - 1) {
                totalsStr.append(", ");
            }
            if (total > maxTotal) {
                maxTotal = total;
                topper = name;
            }
        }
        System.out.println(totalsStr.toString());

        StringBuilder avgStr = new StringBuilder("averages ");
        for (int j = 0; j < numSubjects; j++) {
            double avg = subSums[j] / numStudents;
            avgStr.append(String.format(Locale.US, "%.2f", avg));
            if (j < numSubjects - 1) {
                avgStr.append(", ");
            }
        }
        System.out.println(avgStr.toString());

        System.out.println("topper " + topper + " (" + maxTotal + ")");
        scanner.close();
    }
}