package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents an expense with description, date, currency, amount, account.
public class Expense implements Writable {
    private String description;
    private double time;
    private String currency;
    private double amount;
    private String account;

    public Expense(String description, double time, String currency, double amount, String account) {
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
    public String getCurrency() {
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
        EventLog.getInstance().logEvent(
                new Event("The description has been updated to: " + this.description));
    }


    // MODIFIES: this
    // EFFECTS: Set the date to the new date.
    public void updateDate(double time) {
        this.time = time;
        EventLog.getInstance().logEvent(
                new Event("The date has been updated to: " + this.time));
    }

    // MODIFIES: this
    // EFFECTS: Modify the currency to the new currency.
    public void updateCurrency(String currency) {
        this.currency = currency;
        EventLog.getInstance().logEvent(
                new Event("The currency has been updated to: " + this.currency));

    }

    // MODIFIES: this
    // EFFECTS: Modify the amount to the given amount.
    public void updateMoney(double amount) {
        this.amount = amount;
        EventLog.getInstance().logEvent(
                new Event("The amount has been updated to: " + this.amount));
    }

    // MODIFIES: this
    // EFFECTS: Change the account to the new account.
    public void updateAccount(String account) {
        this.account = account;
        EventLog.getInstance().logEvent(
                new Event("The bank account has been updated to: " + this.account));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("description", description);
        json.put("time", time);
        json.put("currency", currency);
        json.put("amount", amount);
        json.put("account", account);
        return json;
    }
}