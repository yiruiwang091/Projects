package persistence;

import model.ListOfExpenses;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;

// Represents a writer that writes JSON representation of list of expenses to file
public class JsonWriter {
    private PrintWriter writer;
    private String destination;
    private static final int TAB = 6;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of list of expenses to file
    public void write(ListOfExpenses loe) {
        JSONObject json = loe.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
