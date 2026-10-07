import java.util.ArrayList;
import java.util.List;

public class BankDemo {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("SAV-001", 1000.00, 200.00));
        accounts.add(new CurrentAccount("CUR-001", 300.00, 500.00));
        accounts.add(new SavingsAccount("SAV-002", 500.00, 100.00));
        accounts.add(new CurrentAccount("CUR-002", 800.00, 250.00));

        System.out.println("=== Initial balances ===");
        for (Account a : accounts) {
            System.out.printf("%s: %.2f%n", a.getAccountNumber(), a.getBalance());
        }

        System.out.println("\n=== Deposit validation ===");
        accounts.get(0).deposit(-50);
        accounts.get(0).deposit(150);

        System.out.println("\n=== Polymorphic withdraw (300 from each) ===");
        for (Account a : accounts) {
            a.withdraw(300.00);
        }

        System.out.println("\n=== Edge case 1: Savings withdrawal rejected ===");
        // SAV-001 now 850; withdrawing 700 would leave 150 < min 200
        accounts.get(0).withdraw(700.00);

        System.out.println("\n=== Edge case 2: Current account overdraft within limit ===");
        // CUR-001 now 0; withdrawing 400 -> -400, within the 500 limit
        accounts.get(1).withdraw(400.00);
        // and one beyond the limit for contrast
        accounts.get(1).withdraw(200.00);

        System.out.println("\n=== Polymorphic endOfMonth ===");
        for (Account a : accounts) {
            a.endOfMonth();
        }

        System.out.println("\n=== Final balances ===");
        for (Account a : accounts) {
            System.out.printf("%s: %.2f%n", a.getAccountNumber(), a.getBalance());
        }
    }
}
