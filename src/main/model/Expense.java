package model;

public class Expense {
    private String description;
    private double time;
    private int currency;
    private double money;
    private String account;

    public Expense(String description, double time, int currency, double money, String account) {
        this.description = description;
        this.time = time;
        this.currency = currency;
        this.money = money;
        this.account = account;
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
    public void updateMoney(double money) {
        this.money = money;
    }

    // MODIFIES: this
    // EFFECTS: Change the account to the new account.
    public void updateAccount(String account) {
        this.account = account;
    }

}