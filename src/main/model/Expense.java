package model;

// Represents an expense with description, date, currency, amount, account.
public class Expense {
    private String description;
    private double time;
    private int currency;
    private double amount;
    private String account;

    public Expense(String description, double time, int currency, double amount, String account) {
        this.description = description;
        this.time = time;
        this.currency = currency;
        this.amount = amount;
        this.account = account;
    }

    // EFFECTS: return the description
    public String getDescription() {
        return description;
    }

    // EFFECTS: return the date
    public double getTime() {
        return time;
    }

    // EFFECTS: return the currency
    public int getCurrency() {
        return currency;
    }

    // EFFECTS: return the amount
    public double getAmount() {
        return amount;
    }

    // EFFECTS: return the account
    public String getAccount() {
        return account;
    }

    // MODIFIES: this
    // EFFECTS: Modify the currency to the new currency.
    public void updateDescription(String description) {
        this.description = description;
    }

    // MODIFIES: this
    // EFFECTS: Set the date to the new date.
    public void updateDate(double time) {
        this.time = time;
    }

    // MODIFIES: this
    // EFFECTS: Modify the currency to the new currency.
    public void updateCurrency(int currency) {
        this.currency = currency;
    }

    // MODIFIES: this
    // EFFECTS: Modify the amount to the given amount.
    public void updateMoney(double amount) {
        this.amount = amount;
    }

    // MODIFIES: this
    // EFFECTS: Change the account to the new account.
    public void updateAccount(String account) {
        this.account = account;
    }

}