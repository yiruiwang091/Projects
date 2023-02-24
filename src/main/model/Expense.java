package model;

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

    public String getDescription() {
        return description;
    }

    public double getTime() {
        return time;
    }

    public int getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

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