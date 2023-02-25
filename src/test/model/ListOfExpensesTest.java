package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfExpensesTest {
    private ListOfExpenses expenses;
    private Expense expenseOne;
    private Expense expenseTwo;
    private Expense expenseThree;

    @BeforeEach
    void runBefore() {
        expenses = new ListOfExpenses();
        expenseOne = new Expense("H-Mart", 2.1, 1, 50, "bmo");
        expenseTwo = new Expense("Tuition", 3.3, 1, 5000, "td");
        expenseThree = new Expense("Residence Fee", 1.1, 1, 1000, "bmo");
    }

    @Test
    void testAddExpense() {
        expenses.addExpense(expenseOne);
        expenses.addExpense(expenseTwo);
        expenses.addExpense(expenseThree);

        assertEquals(3,expenses.length());
    }

    @Test
    void testLength() {
        expenses.addExpense(expenseOne);
        expenses.addExpense(expenseTwo);

        assertEquals(2,expenses.length());
    }

    @Test
    void testRemove() {
        expenses.addExpense(expenseOne);
        expenses.addExpense(expenseTwo);
        expenses.addExpense(expenseThree);

        expenses.removeExpense(2);
        assertEquals(2,expenses.length());

        expenses.removeExpense(0);
        expenses.removeExpense(0);
        assertEquals(0,expenses.length());
    }
}
