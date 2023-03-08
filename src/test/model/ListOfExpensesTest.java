package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class ListOfExpensesTest {
    private ListOfExpenses expenses;
    private Expense expenseOne;
    private Expense expenseTwo;
    private Expense expenseThree;

    @BeforeEach
    void runBefore() {
        expenses = new ListOfExpenses();
        expenseOne = new Expense("H-Mart", 2.1, "CAD", 50, "bmo");
        expenseTwo = new Expense("Tuition", 3.3, "CNY", 5000, "td");
        expenseThree = new Expense("Residence Fee", 1.1, "CAD", 1000, "bmo");
    }

    @Test
    void testAddExpense() {
        assertEquals(0,expenses.length());

        expenses.addExpense(expenseOne);
        assertEquals(1,expenses.length());

        expenses.addExpense(expenseTwo);
        expenses.addExpense(expenseTwo);
        expenses.addExpense(expenseThree);

        assertEquals(4,expenses.length());
    }

    @Test
    void testLength() {
        assertEquals(0,expenses.length());

        expenses.addExpense(expenseOne);
        expenses.addExpense(expenseTwo);

        assertEquals(2,expenses.length());
    }

    @Test
    void testRemove() {
        assertFalse(expenses.removeExpense(0));
    }

    @Test
    void testRemoveMulti() {
        expenses.addExpense(expenseOne);
        expenses.addExpense(expenseTwo);
        expenses.addExpense(expenseThree);

        assertTrue(expenses.length() != 0);
        assertTrue(expenses.removeExpense(2));
        assertEquals(2,expenses.length());

        expenses.removeExpense(0);
        expenses.removeExpense(0);
        assertEquals(0,expenses.length());

        assertFalse(expenses.removeExpense(0));
    }
}
