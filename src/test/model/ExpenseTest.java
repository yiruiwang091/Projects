package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {
    private Expense testExpense;

    @BeforeEach
    void runBefore() {
        testExpense = new Expense("H-Mart", 2.20, "CAD", 20, "bmo");
    }

    @Test
    void testConstructor() {
        assertEquals("H-Mart", testExpense.getDescription());
        assertEquals(2.20, testExpense.getTime());
        assertEquals("CAD", testExpense.getCurrency());
        assertEquals(20, testExpense.getAmount());
        assertEquals("bmo", testExpense.getAccount());
    }


    @Test
    void testUpdateDescription() {
        testExpense = new Expense("H-Mart", 2.1, "CNY", 20, "bmo");
        testExpense.updateDescription("Shopping");
        assertEquals("Shopping", testExpense.getDescription());
    }

    @Test
    void testUpdateDate() {
        testExpense = new Expense("H-Mart", 2.1, "CAD", 10, "bmo");
        testExpense.updateDate(3.5);
        assertEquals(3.5, testExpense.getTime());
    }

    @Test
    void testUpdateCurrency() {
        testExpense = new Expense("H-Mart", 2.1, "CAD", 20, "bmo");
        testExpense.updateCurrency("USD");
        assertEquals("USD", testExpense.getCurrency());
    }

    @Test
    void testUpdateMoney() {
        testExpense = new Expense("H-Mart", 2.1, "HKD", 50, "bmo");
        testExpense.updateMoney(10);
        assertEquals(10, testExpense.getAmount());
    }

    @Test
    void testUpdateAccount() {
        testExpense = new Expense("H-Mart", 2.1, "CAD", 10, "bmo");
        testExpense.updateAccount("td");
        assertEquals("td", testExpense.getAccount());
    }


}