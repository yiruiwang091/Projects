package model;

import java.util.ArrayList;
import java.util.LinkedList;

// Represents a list of expenses.
public class ListOfExpenses {
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
}
