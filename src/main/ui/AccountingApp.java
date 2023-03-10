package ui;

import model.Expense;
import model.ListOfExpenses;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class AccountingApp {
    private static final String JSON_STORE = "./data/expenses.json";
    private ListOfExpenses expenses;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the Accounting application
    public AccountingApp() throws FileNotFoundException {
        expenses = new ListOfExpenses();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runAccounting();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runAccounting() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThanks for choosing this accounting software!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tk -> keep");
        System.out.println("\tm -> modify");
        System.out.println("\td -> delete");
        System.out.println("\ts -> save expenses to file");
        System.out.println("\tp -> print expenses from file");
        System.out.println("\tl -> load expenses from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("k")) {
            doKeep();
        } else if (command.equals("m")) {
            doModify();
        } else if (command.equals("d")) {
            doDelete();
        } else if (command.equals("s")) {
            saveExpenses();
        } else if (command.equals("p")) {
            printExpenses();
        } else if (command.equals("l")) {
            loadExpenses();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: saves the expenses to file
    private void saveExpenses() {
        try {
            jsonWriter.open();
            jsonWriter.write(expenses);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints all the expenses in the list to the console
    private void printExpenses() {
        List<Expense> list = expenses.getExpenses();

        for (Expense e : list) {
            System.out.println("Description: " + e.getDescription()
                    + " Time: " + e.getTime()
                    + " Currency: " + e.getCurrency()
                    + " Amount: " + e.getAmount()
                    + " Account: " + e.getAccount());
        }
    }

    // MODIFIES: this
    // EFFECTS: loads expenses from file
    private void loadExpenses() {
        try {
            expenses = jsonReader.read();
            System.out.println("Loaded " + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the list of expenses
    private void init() {
        expenses = new ListOfExpenses();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: conducts a keep of a new expense
    private void doKeep() {
        Expense expense = new Expense("Description", 1.1, "CAD", 0, "bmo");
        System.out.print("\nEnter the description: ");
        String str = input.next();
        expense.updateDescription(str);

        System.out.print("Enter the date: ");
        double date = input.nextDouble();
        expense.updateDate(date);

        System.out.print("\nChoose the currency:");
        String currencyNew = selectCurrency();
        expense.updateCurrency(currencyNew);

        System.out.print("Enter amount: $");
        doConvertMoney(expense);

        System.out.print("Select an account:");
        String account = selectAccount();
        expense.updateAccount(account);

        expenses.addExpense(expense);

        System.out.println("I had a new expense: " + str + " Time: " + date + " Currency: "
                + currencyNew + " Amount: " + expense.getCurrency() + " " + expense.getAmount()
                + " Account: " + expense.getAccount());
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: modify an expense
    private void doModify() {
        System.out.print("What expense would you like to modify? Type the description of that expense: ");
        Expense thisExpense;
        String str = input.next();
        for (int i = 0; i < expenses.length(); i++) {
            if (str.equals(expenses.get(i).getDescription())) {
                thisExpense = expenses.get(i);
                System.out.print("\tChoose the category you'd like to change: ");
                System.out.println("\tdes -> description");
                System.out.println("\tdate -> date");
                System.out.println("\tc -> currency");
                System.out.println("\tam -> amount");
                System.out.println("\tacc -> account");

                String selection = "";  // force entry into loop
                while (!(selection.equals("des") || selection.equals("date") || selection.equals("c")
                        || selection.equals("am") || selection.equals("acc"))) {
                    selection = input.next();
                    selection = selection.toLowerCase();
                }

                if (selection.equals("des")) {
                    newDes(thisExpense);
                } else if (selection.equals("date")) {
                    newDate(thisExpense);
                } else if (selection.equals("c")) {
                    newCurrency(thisExpense);
                } else if (selection.equals("am")) {
                    newAmount(thisExpense);
                } else {
                    newAccount(thisExpense);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: modify the description
    private void newDes(Expense thisExpense) {
        System.out.println("Enter the new description:");
        String str = input.next();
        thisExpense.updateDescription(str);
        System.out.println("New description: " + thisExpense.getDescription() + " has been updated.");
    }

    // MODIFIES: this
    // EFFECTS: modify the date
    private void newDate(Expense thisExpense) {
        System.out.println("Enter the new date:");
        Double time = input.nextDouble();
        thisExpense.updateDate(time);
        System.out.println("New date: " + thisExpense.getTime() + " has been updated.");
    }

    // MODIFIES: this
    // EFFECTS: modify the currency
    private void newCurrency(Expense thisExpense) {
        System.out.println("Enter the new currency:");

        System.out.println("\nSelect from:");
        System.out.println("\nCAD for Canadian Dollar");
        System.out.println("CNY for Chinese Yuan");
        System.out.println("EUR for European Euro");
        System.out.println("HKD for HongKong Dollar");
        System.out.println("INR for Indian Rupee");
        System.out.println("JPY for Japanese Yen");
        System.out.println("KRW for Korean Won");
        System.out.println("USD for US Dollar");

        String str = input.next();
        thisExpense.updateCurrency(str);
        System.out.println("New currency: " + thisExpense.getCurrency() + " has been updated.");
        System.out.println("Please enter the amount again: ");
        doConvertMoney(thisExpense);
        System.out.println("The new amount is " + thisExpense.getCurrency() + thisExpense.getAmount());
    }

    // MODIFIES: this
    // EFFECTS: modify the amount
    private void newAmount(Expense thisExpense) {
        System.out.println("Enter the new money amount:");
        Double am = input.nextDouble();
        thisExpense.updateMoney(am);
        System.out.println("New amount: " + thisExpense.getAmount() + " has been updated.");
    }

    // MODIFIES: this
    // EFFECTS: modify the account
    private void newAccount(Expense thisExpense) {
        System.out.println("Enter the new account for the expense:");
        String acc = input.next();
        thisExpense.updateAccount(acc);
        System.out.println("New account: " + thisExpense.getAccount() + " has been updated.");
    }

    // MODIFIES: this
    // EFFECTS: delete an expense
    private void doDelete() {
        for (int i = 0; i < expenses.length(); i++) {
            System.out.println(i + " " + expenses.get(i).getDescription()
                    + " " + expenses.get(i).getTime()
                    + " " + expenses.get(i).getCurrency()
                    + " " + expenses.get(i).getAmount()
                    + " " + expenses.get(i).getAccount());
        }
        System.out.println("Type the number of the expense that you'd like to remove: ");
        int number = input.nextInt();
        expenses.removeExpense(number);

        for (int i = 0; i < expenses.length(); i++) {
            System.out.println(i + " " + expenses.get(i).getDescription()
                    + " " + expenses.get(i).getTime()
                    + " " + expenses.get(i).getCurrency()
                    + " " + expenses.get(i).getAmount()
                    + " " + expenses.get(i).getAccount());
        }
    }

    @SuppressWarnings("methodlength")
    // EFFECTS: prompts user to select different currencies and returns it
    private String selectCurrency() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("CAD") || selection.equals("CNY") || selection.equals("EUR")
                || selection.equals("HKD") || selection.equals("INR") || selection.equals("JPY")
                || selection.equals("KRW") || selection.equals("USD"))) {
            System.out.println("\nCAD for Canadian Dollar");
            System.out.println("CNY for Chinese Yuan");
            System.out.println("EUR for European Euro");
            System.out.println("HKD for HongKong Dollar");
            System.out.println("INR for Indian Rupee");
            System.out.println("JPY for Japanese Yen");
            System.out.println("KRW for Korean Won");
            System.out.println("USD for US Dollar");
            selection = input.next();
            selection = selection.toUpperCase();
        }

        if (selection.equals("CAD")) {
            return "CAD";
        } else if (selection.equals("CNY")) {
            return "CNY";
        } else if (selection.equals("EUR")) {
            return "EUR";
        } else if (selection.equals("HKD")) {
            return "HKD";
        } else if (selection.equals("INR")) {
            return "INR";
        } else if (selection.equals("JPY")) {
            return "JPY";
        } else if (selection.equals("KRW")) {
            return "KRW";
        } else {
            return "USD";
        }
    }

    // EFFECTS: prompts user to select bmo or td bank account and returns it
    private String selectAccount() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("bmo") || selection.equals("td"))) {
            System.out.println("\nbmo");
            System.out.println("td");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("bmo")) {
            return "bmo";
        } else {
            return "td";
        }
    }

    @SuppressWarnings("methodlength")
    // EFFECTS: Converts the money to the specific currency and returns it
    private void doConvertMoney(Expense expense) {
        double money = input.nextDouble();
        String currency = expense.getCurrency();
        if (currency == "CNY") {
            double convertedMoney = money / 0.2;
            expense.updateMoney(convertedMoney);
            System.out.println("Yuan\t¥" + convertedMoney);
        } else if (currency == "EUR") {
            double convertedMoney = money / 1.45;
            expense.updateMoney(convertedMoney);
            System.out.println("Euro\t€" + convertedMoney);
        } else if (currency == "HKD") {
            double convertedMoney = money / 0.17;
            expense.updateMoney(convertedMoney);
            System.out.println("HKD\t$" + convertedMoney);
        } else if (currency == "INR") {
            double convertedMoney = money / 0.017;
            expense.updateMoney(convertedMoney);
            System.out.println("INR\t₹" + convertedMoney);
        } else if (currency == "JPY") {
            double convertedMoney = money / 0.01;
            expense.updateMoney(convertedMoney);
            System.out.println("Yen\t¥" + convertedMoney);
        } else if (currency == "KRW") {
            double convertedMoney = money / 0.001;
            expense.updateMoney(convertedMoney);
            System.out.println("Won\t₩" + convertedMoney);
        } else if (currency == "USD") {
            double convertedMoney = money / 1.36;
            expense.updateMoney(convertedMoney);
            System.out.println("USD\t$" + convertedMoney);
        } else {
            expense.updateMoney(money);
            System.out.println("CAD\t$" + money);
        }
    }

}

