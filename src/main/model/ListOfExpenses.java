package model;

import java.util.LinkedList;

public class ListOfExpenses {
    private LinkedList<Expense> expenses;

    public ListOfExpenses() {
        expenses = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: Remove the expense at the given index position.
    public Expense removeExpense(int index) {
        expenses.remove(index);
        return null;
    }

    // MODIFIES:
    // EFFECTS:
    public Expense addExpense(Expense expense) {
        expenses.add(expense);
        return null;
    }

    public int length() {
        return expenses.size();
    }

}
