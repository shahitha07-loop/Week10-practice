import java.util.*;

class Student {
    String rollNumber;
    String name;

    public Student(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(rollNumber, student.rollNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNumber);
    }
}

public class ClubRegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Student> members = new LinkedHashMap<>();
        List<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                lines.add(line);
            }
        }

        boolean countPrinted = false;
        for (String line : lines) {
            String[] parts = line.split("\\s+", 3);
            String cmd = parts[0];
            
            if (cmd.equals("ADD")) {
                String roll = parts[1];
                String name = parts[2];
                if (members.containsKey(roll)) {
                    System.out.println("duplicate rejected");
                } else {
                    members.put(roll, new Student(roll, name));
                    System.out.println("Added");
                }
            } else if (cmd.equals("CONTAINS")) {
                if (!countPrinted) {
                    System.out.println("member count " + members.size());
                    countPrinted = true;
                }
                String roll = parts[1];
                boolean found = members.containsKey(roll);
                System.out.println("contains: " + found);
            }
        }
        if (!countPrinted) {
            System.out.println("member count " + members.size());
        }
        scanner.close();
    }
}