package ui;

import model.Expense;

import java.util.Scanner;

public class AccountingApp {
    private Expense bmo;
    private Expense td;

    // MODIFIES: this
    // EFFECTS: initializes 2 accounts
    private void init() {
        bmo = new Expense("Lunch", 1.23, 1, 15, "bmo");
        td = new Expense("T&T", 2.5, 1, 40, "td");

    }
}

