package model;

import java.util.LinkedList;

public class ListOfExpenses {
    private LinkedList<Expense> expenses;

    public ListOfExpenses() {
        expenses = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: Remove the expense at the given index position.
    public void removeExpense(int index) {
        expenses.remove(index);
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

}
