package persistence;

import model.Expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExpense(String des, double time, String c, double am, String acc, Expense e) {
        assertEquals(des, e.getDescription());
        assertEquals(time, e.getTime());
        assertEquals(c, e.getCurrency());
        assertEquals(am, e.getAmount());
        assertEquals(acc, e.getAccount());
    }
}
