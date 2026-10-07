public class CurrentAccount extends Account {
    private final double overdraftLimit;
    private static final double MONTHLY_FEE = 10.00;

    public CurrentAccount(String accountNumber, double initialBalance, double overdraftLimit) {
        super(accountNumber, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("[" + accountNumber + "] Withdrawal rejected: amount must be positive.");
        } else if (balance - amount < -overdraftLimit) {
            System.out.printf("[%s] Withdrawal of %.2f REJECTED: exceeds overdraft limit of %.2f.%n",
                    accountNumber, amount, overdraftLimit);
        } else {
            balance -= amount;
            String note = balance < 0 ? " (in overdraft)" : "";
            System.out.printf("[%s] Withdrew %.2f. New balance: %.2f%s%n", accountNumber, amount, balance, note);
        }
    }

    @Override
    public void endOfMonth() {
        balance -= MONTHLY_FEE;
        System.out.printf("[%s] Current month-end: maintenance fee of %.2f deducted. Balance: %.2f%n",
                accountNumber, MONTHLY_FEE, balance);
    }
}
