package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainGUI {
    public static void main(String[] args) {
        try {
            new ExpenseGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}