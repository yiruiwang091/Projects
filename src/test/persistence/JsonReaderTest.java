package persistence;

import model.Expense;
import model.ListOfExpenses;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfExpenses expenses = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyExpenses() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyExpenses.json");
        try {
            ListOfExpenses expenses = reader.read();
            assertEquals(0, expenses.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralExpenses() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralExpenses.json");
        try {
            ListOfExpenses expenses = reader.read();
            List<Expense> e = expenses.getExpenses();
            assertEquals(2, expenses.length());
            checkExpense("tnt", 3.9, "CNY", 20, "bmo", expenses.get(0));
            checkExpense("saveon", 2.3, "CAD", 15, "bmo", expenses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
