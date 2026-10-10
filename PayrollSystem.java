import java.util.*;

abstract class Staff {
    String name;
    String type;

    public Staff(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public abstract double calculatePay();

    public String getPayslip() {
        return "Payslip[name=" + name + ", type=" + type + ", pay=" + (int)calculatePay() + "]";
    }
}

class FullTimeStaff extends Staff {
    double salary;

    public FullTimeStaff(String name, double salary) {
        super(name, "FullTime");
        this.salary = salary;
    }

    @Override
    public double calculatePay() {
        return salary;
    }
}

class PartTimeStaff extends Staff {
    double hours;
    double rate;

    public PartTimeStaff(String name, double hours, double rate) {
        super(name, "PartTime");
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    public double calculatePay() {
        return hours * rate;
    }
}

class InternStaff extends Staff {
    double stipend;

    public InternStaff(String name, double stipend) {
        super(name, "Intern");
        this.stipend = stipend;
    }

    @Override
    public double calculatePay() {
        return stipend;
    }
}

public class PayrollSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Staff> staffList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            String type = parts[0];
            String name = parts[1];

            if (type.equals("FullTime")) {
                double salary = Double.parseDouble(parts[2]);
                staffList.add(new FullTimeStaff(name, salary));
            } else if (type.equals("PartTime")) {
                double hours = Double.parseDouble(parts[2]);
                double rate = Double.parseDouble(parts[3]);
                staffList.add(new PartTimeStaff(name, hours, rate));
            } else if (type.equals("Intern")) {
                double stipend = Double.parseDouble(parts[2]);
                staffList.add(new InternStaff(name, stipend));
            }
        }

        double totalPay = 0;
        double maxPay = -1;
        String topEarner = "";

        for (Staff s : staffList) {
            System.out.println(s.getPayslip());
            double pay = s.calculatePay();
            totalPay += pay;
            if (pay > maxPay) {
                maxPay = pay;
                topEarner = s.name;
            }
        }

        System.out.println("Total " + (int)totalPay);
        System.out.println("top earner " + topEarner);
        scanner.close();
    }
}