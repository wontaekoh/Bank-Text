package bank.text;

public class Savings extends Account{
    
    private static String accountType = "Savings";
    
    Savings(double initialDeposit) {
        this.setBalance(initialDeposit);
        this.checkInterest();
    }
    
    @Override
    public String toString() {
        return "Account Type: " + accountType + " Account\n" +
                "Account Number: " + this.getAccountNumber() + "\n" +
                "Balance: " + this.getBalance() + "\n" +
                "Interest Rate: " + this.getInterest() + "%";
    }
}
