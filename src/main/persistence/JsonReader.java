package persistence;

import model.ListOfExpenses;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads list of expenses from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read form source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads list of expenses from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfExpenses read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject((jsonData));
        return parseExpenses(jsonObject);
    }

    // EFFECTS: parses workroom form JSON object and returns it
    private ListOfExpenses parseExpenses(JSONObject jsonObject) {
        ListOfExpenses expenses = new ListOfExpenses();
        addExpenses(expenses,jsonObject);
        return expenses;
    }

    // MODIFIES: this
    // EFFECTS: parses expenses from JSON object and adds them to the list
    private void addExpenses(ListOfExpenses expenses, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject)  json;
            addExpenses(expenses, nextExpense);
        }
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }
}
