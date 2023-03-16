package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of expenses.
public class ListOfExpenses implements Writable {
    private ArrayList<Expense> expenses;

    public ListOfExpenses() {
        expenses = new ArrayList<>();
    }

    // REQUIRES: input is less than the list length - 1
    // MODIFIES: this
    // EFFECTS: Remove the expense at the given index position.
    public boolean removeExpense(int index) {
        if (length() != 0) {
            expenses.remove(index);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: Add expense to the list
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    // EFFECTS: returns the size of the list
    public int length() {
        return expenses.size();
    }

    // EFFECTS: get the expense at ith position
    public Expense get(int i) {
        return expenses.get(i);
    }

//    // EFFECTS: returns an unmodifiable list of expenses in the list
//    public List<Expense> getExpenses() {
//        return Collections.unmodifiableList(expenses);
//    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listofexpenses", expensesToJson());
        return json;
    }

    // EFFECTS: returns things in this list as a JSON array
    private JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense e : expenses) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }

}
