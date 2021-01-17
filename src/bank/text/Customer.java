package bank.text;

public class Customer {

    private final String firstName;
    private final String lastName;
    private final String ssn;
    private final Account account;

    Customer(String firstName, String lastName, String ssn, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.account = account;
    }
    
    @Override
    public String toString() {
        return "\nCustomer Information\n" +
                "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Social Security Number: " + ssn + "\n" +
                account;    // account object prints out overriden toString method of checking or savings
    }
    
    public String basicInfo() {
        return "Name: " + firstName + " " + lastName + " | " +
                "Account Number: " + account.getAccountNumber();
    }
    
    Account getAccount() {
        return account;     // account object prints out overriden toString method of checking or savings
    }
}
