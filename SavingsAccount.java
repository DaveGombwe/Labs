public class SavingsAccount extends Account {
    private final double minimumBalance;
    private static final double INTEREST_RATE = 0.02; // 2% per month

    public SavingsAccount(String accountNumber, double initialBalance, double minimumBalance) {
        super(accountNumber, initialBalance);
        this.minimumBalance = minimumBalance;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("[" + accountNumber + "] Withdrawal rejected: amount must be positive.");
        } else if (balance - amount < minimumBalance) {
            System.out.printf("[%s] Withdrawal of %.2f REJECTED: balance would fall below the minimum of %.2f.%n",
                    accountNumber, amount, minimumBalance);
        } else {
            balance -= amount;
            System.out.printf("[%s] Withdrew %.2f. New balance: %.2f%n", accountNumber, amount, balance);
        }
    }

    @Override
    public void endOfMonth() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.printf("[%s] Savings month-end: interest of %.2f applied. Balance: %.2f%n",
                accountNumber, interest, balance);
    }
}
