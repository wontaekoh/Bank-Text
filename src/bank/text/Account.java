package bank.text;

public class Account {

    private double balance = 0.0;
    private double interest = 0.02;
    private int accountNumber;
    
    private static int numberOfAccounts = 1000000;
    
    Account() {
        accountNumber = numberOfAccounts++;
    }
    
    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the interest
     */
    public double getInterest() {
        return interest * 100;
    }

    /**
     * @param interest the interest to set
     */
    public void setInterest(double interest) {
        this.interest = interest;
    }

    /**
     * @return the accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    public void withdraw(double amount) {
        if(amount + 1 > balance) {  // withdrawal fee = $1
            System.out.println("\nYou have insufficient funds.");
        }
        
        balance -= amount + 1;
        checkInterest();
        
        System.out.println("\nYou have withdrawn $" + amount + " and incurred a fee of $1.");
        System.out.println("Your current balance: $" + balance);
    }
    
    public void deposit(double amount) {
        if(amount <= 0) {
            System.out.println("\nYou cannot deposit " + amount + "dollars.");
        }
        
        balance += amount;
        checkInterest();
        
        System.out.println("\nYou have deposited $" + amount + " and the interest rate is " + (interest*100) + "%.");
        System.out.println("Your current balance: $" + balance);
    }

    public void checkInterest() {
        if(balance >= 100000) {
            interest = 0.04;
        } else if(balance >= 50000) {
            interest = 0.03;
        } else {
            interest = 0.02;
        }
    }
}
