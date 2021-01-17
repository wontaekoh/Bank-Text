package bank.text;

// make welcome message | header1 and 2 | 5) list customer information 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    // Instance variables
    Scanner input = new Scanner(System.in);
    Bank bank = new Bank();
    boolean exit;   // false by default
    
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.runMenu();
    }
    
    public void runMenu() {
        printHeader();
        while(!exit) {
            printMenu();
            int choice = getMenuChoice();
            performAction(choice);
        }
    }

    private void printHeader() {
        System.out.println("+------------------------+");
        System.out.println("|   Welcome to WO Bank   |");
        System.out.println("+------------------------+");
    }

    private void printMenu() {
        displayHeader("Please make a selection: ");
        System.out.println("1) Create a new Account");
        System.out.println("2) Make a deposit");
        System.out.println("3) Make a withdrawal");
        System.out.println("4) List account balance");
        System.out.println("0) Exit");
    }
        
    private int getMenuChoice() {
        int choice = -1;
        
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only please.");
            }
            
            if (choice < 0 || choice > 4) {
                System.out.println("Choice outside of range. Please choose again.");
            }
        } while(choice < 0 || choice > 4);
        return choice;
    }

    private void performAction(int choice) {
        switch(choice) {
            case 0:
                System.out.println("Thank you for using our service.");
                System.exit(0);     // 0: standard exit
                break;
            case 1:
            {
                try {
                    createAccount();
                } catch (InvalidAccountTypeException e) {
                    System.out.println("Account was not created successfully.");
                }
            }
                break;

            case 2:
                makeDeposit();
                break;
            case 3:
                makeWithdrawal();
                break;
            case 4:
                listBalances();
                break;
            default:
                System.out.println("Unknown error has occured.");
        }
    }
    
    private String askQuestion(String question, List<String> answers) {
        String response = "";
        Scanner scan = new Scanner(System.in);
        boolean choices = ((answers != null) && !answers.isEmpty());
        boolean firstRun = true;
        
        do {
            if(!firstRun) {
                System.out.println("Invalid selection, please try again.");
            }
            System.out.print(question);
            
            response = scan.nextLine();
            firstRun = false;
            if(!choices) {
                break;
            }
            
        } while (!answers.contains(response));
        
        return response;
    }
    
    private double getDeposit(String accountType) {
        double initialDeposit = 0.0;
        boolean valid = false;
        
        while(!valid) {
            System.out.print("Please enter an initial deposit: ");
            try {
                initialDeposit = Double.parseDouble(input.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Deposit must be a number.");
            }
            
            if(accountType.equalsIgnoreCase("c")) {
                if(initialDeposit < 100) {
                    System.out.println("Checking accounts require a minimum of $50.00 to open.");
                } else {
                    valid = true;
                }
            } else if (accountType.equalsIgnoreCase("s")) {
                if(initialDeposit < 50) {
                    System.out.println("Savings accounts require a minimum of $50.00 to open.");
                } else {
                    valid = true;
                }
            }
        }
        return initialDeposit;
    }
    
    private void createAccount() throws InvalidAccountTypeException {
        displayHeader("Create an Account");
        
        // Get account information
        String accountType = askQuestion("Please enter an account type: \n(C)hecking\n(S)avings\n>> ", Arrays.asList("c", "s"));        
        String firstName = askQuestion("Please enter your first name: ", null);
        String lastName = askQuestion("Please enter your last name: ", null);
        String ssn = askQuestion("Please enter your social security number: ", null);
        double initialDeposit = getDeposit(accountType);
        
        // Create Account
        Account account;
        if(accountType.equalsIgnoreCase("c")) {
            account = new Checking(initialDeposit);
        } else if(accountType.equalsIgnoreCase("s")){
            account = new Savings(initialDeposit);
        } else {
            throw new InvalidAccountTypeException();
        }
        
        Customer customer = new Customer(firstName, lastName, ssn, account);
        bank.addCustomer(customer);
    }

    private double getDollarAmount(String question) {
        System.out.print(question);
        
        double amount;
        try {
           amount = Double.parseDouble(input.nextLine());
        } catch(NumberFormatException e) {
           amount = 0;
        }
        return amount;
    }
    
    private void makeDeposit() {
        displayHeader("Make a Deposit");
        int account = selectAccount();
        
        if(account >= 0) {
            double amount = getDollarAmount("How much would you like to deposit? ");
            bank.getCustomer(account).getAccount().deposit(amount);
        }
    }

    private void makeWithdrawal() {
        displayHeader("Make a Withdrawal");
        int account = selectAccount();
        
        if(account >= 0) {
            double amount = getDollarAmount("How much would you like to withdraw? ");
            bank.getCustomer(account).getAccount().withdraw(amount);
        }
    }

    private void listBalances() {
        displayHeader("List Account Details");
        int account = selectAccount();
        
        if(account >= 0) {
            displayHeader("Account Details");
            System.out.println(bank.getCustomer(account).getAccount());
        }
    }
    
    private void displayHeader(String message) {
        System.out.println();
        int width = message.length() + 6;
        StringBuilder sb = new StringBuilder();
        
        sb.append("+");
        for(int i = 0; i < width; i++) {
            sb.append("-");
        }
        sb.append("+");
        
        System.out.println(sb.toString());
        System.out.println("|   " + message + "   |");
        System.out.println(sb.toString());
    }

    private int selectAccount() {
        ArrayList<Customer> customers = bank.getCustomers();
        
        if(customers.size() <= 0) {
            System.out.println("\nNo customers at WO bank.");
            return -1;
        }
        
        System.out.println("Select an account below");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ") " + customers.get(i).basicInfo());
        }
        
        int account;
        System.out.print("\nPlease enter your selection: ");
        try {
            account = Integer.parseInt(input.nextLine()) - 1;
        } catch(NumberFormatException e) {
            account = -1;
        }
        
        if(account < 0 || account >= customers.size()) {
            System.out.println("\nInvalid account selected.");
            account = -1;
        }
        
        return account;
    }
}
