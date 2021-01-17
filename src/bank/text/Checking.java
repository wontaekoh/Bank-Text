package bank.text;

public class Checking extends Account{
    
    private static String accountType = "Checking";
    
    Checking(double initialDeposit) {
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
