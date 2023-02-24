package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountingAppTest {
    private Expense testExpense;

    @BeforeEach
    void runBefore() {
        testExpense = new Expense("H-Mart", 2.20, 1, 20, "bmo");
    }

    @Test
    void testConstructor() {
        assertEquals("H-Mart", testExpense.getDescription());
        assertEquals(2.20, testExpense.getTime());
        assertEquals(1, testExpense.getCurrency());
        assertEquals(20, testExpense.getAmount());
        assertEquals("bmo", testExpense.getAccount());
    }


}