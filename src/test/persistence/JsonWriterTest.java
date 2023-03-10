package persistence;

import model.Expense;
import model.ListOfExpenses;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfExpenses e = new ListOfExpenses();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyExpenses() {
        try {
            ListOfExpenses e = new ListOfExpenses();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExpenses.json");
            writer.open();
            writer.write(e);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExpenses.json");
            e = reader.read();
            assertEquals(0, e.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralExpenses() {
        try {
            ListOfExpenses e = new ListOfExpenses();
            e.addExpense(new Expense("tnt", 3.9, "CNY", 20, "bmo"));
            e.addExpense(new Expense("saveon", 2.3, "CAD", 15, "bmo"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExpenses.json");
            writer.open();
            writer.write(e);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralExpenses.json");
            e = reader.read();
            List<Expense> expenses = e.getExpenses();
            assertEquals(2,expenses.size());
            checkExpense("tnt", 3.9, "CNY", 20, "bmo", expenses.get(0));
            checkExpense("saveon", 2.3, "CAD", 15, "bmo", expenses.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
