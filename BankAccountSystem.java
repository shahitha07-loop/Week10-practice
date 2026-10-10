import java.util.*;

abstract class Account {
    String id;
    double balance;
    
    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }
    
    public abstract void withdraw(double amount);
}

class SavingsAccount extends Account {
    public SavingsAccount(String id, double balance) {
        super(id, balance);
    }
    
    @Override
    public void withdraw(double amount) {
        if (balance - amount < 1000) {
            System.out.println(id + " rejected: minimum balance 1000");
        } else {
            balance -= amount;
            System.out.println(id + " balance " + (int)balance);
        }
    }
}

class CurrentAccount extends Account {
    public CurrentAccount(String id, double balance) {
        super(id, balance);
    }
    
    @Override
    public void withdraw(double amount) {
        if (balance - amount < -5000) {
            System.out.println(id + " rejected: overdraft limit 5000");
        } else {
            balance -= amount;
            System.out.println(id + " balance " + (int)balance);
        }
    }
}

public class BankAccountSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Account> accounts = new HashMap<>();
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            
            if (parts[0].equals("Savings")) {
                accounts.put(parts[1], new SavingsAccount(parts[1], Double.parseDouble(parts[2])));
            } else if (parts[0].equals("Current")) {
                accounts.put(parts[1], new CurrentAccount(parts[1], Double.parseDouble(parts[2])));
            } else if (parts[0].equals("WITHDRAW")) {
                String id = parts[1];
                double amount = Double.parseDouble(parts[2]);
                if (accounts.containsKey(id)) {
                    accounts.get(id).withdraw(amount);
                } else {
                    System.out.println("Account not found");
                }
            }
        }
        scanner.close();
    }
}