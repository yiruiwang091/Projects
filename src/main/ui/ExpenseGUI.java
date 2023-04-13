package ui;

import model.*;
import model.Event;
import model.Expense;
import model.ListOfExpenses;
import persistence.JsonReader;
import persistence.JsonWriter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import static java.lang.Double.parseDouble;

public class ExpenseGUI {
    JFrame frame = new JFrame();
    JTable table = new JTable();
    JTextField textDescription = new JTextField();
    JTextField textDate = new JTextField();
    JTextField textCurrency = new JTextField();
    JTextField textAmount = new JTextField();
    JTextField textAccount = new JTextField();
    DefaultTableModel model = new DefaultTableModel();
    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    private Expense expense;
    private ListOfExpenses expenses;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/expenses.json";

    // EFFECTS: runs the GUI
    public ExpenseGUI() throws IOException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        expense = new Expense("tnt",1.2,"cad", 20, "bmo");
        expenses = new ListOfExpenses();
        Object[] columns = {"Description","Date(Double only)","Currency","Amount(Double only)", "Account"};
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        setField();
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);
        frame.setLayout(null);
        frame.add(pane);
        runExpensePanel(frame, table);
        menuBar.add(file);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        windowAction();
    }

    private void windowAction() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event: EventLog.getInstance()) {
                    System.out.println(event);
                }
                System.out.println("window closing");
                System.exit(0);
            }
        });
    }

    private void setField() {
        textDescription.setBounds(20, 220, 100, 25);
        textDate.setBounds(20, 250, 100, 25);
        textCurrency.setBounds(20, 280, 100, 25);
        textAmount.setBounds(20, 310, 100, 25);
        textAccount.setBounds(20, 340, 100, 25);
    }

    // MODIFIES: this
    // EFFECTS: Setup frame and buttons
    private void runExpensePanel(JFrame frame, JTable table) throws IOException {
        frame.add(textDescription);
        frame.add(textDate);
        frame.add(textCurrency);
        frame.add(textAmount);
        frame.add(textAccount);

        doBtnAdd(frame);
        doBtnUpdate(frame, table);
        doBtnDelete(frame, table);
        Container content = frame.getContentPane();
        final JEditorPane editorPane = new JEditorPane();
        JScrollPane scrollPane = new JScrollPane(editorPane);
        content.add(scrollPane, BorderLayout.CENTER);

        doBtnSave();
        doBtnLoad();

        frame.setSize(900,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        getRowData(table);
    }

    // EFFECTS: get selected row data From table to textfields
    private void getRowData(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                textDescription.setText(model.getValueAt(i, 0).toString());
                textDate.setText(model.getValueAt(i, 1).toString());
                textCurrency.setText(model.getValueAt(i, 2).toString());
                textAmount.setText(model.getValueAt(i, 3).toString());
                textAccount.setText((model.getValueAt(i,4).toString()));
            }
        });
    }

    // EFFECTS: a button action that adds inputs to a table row
    private void doBtnAdd(JFrame frame) {
        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(150, 220, 100, 25);
        frame.add(btnAdd);

        Object[] row = new Object[5];
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                row[0] = textDescription.getText();
                row[1] = textDate.getText();
                checkDouble("Date");
                row[2] = textCurrency.getText();
                row[3] = textAmount.getText();
                checkDouble("Amount");
                row[4] = textAccount.getText();

                model.addRow(row);
                updateExpense();
                expenses.addExpense(expense);
            }
        });
    }

    private void checkDouble(String str) {
        try {
            Double x = Double.parseDouble(textDate.getText());
        } catch (NumberFormatException exception) {
            System.out.println(str + " input is not a double value");
        }
    }

    private void updateExpense() {
        expense.updateDescription(textDescription.getText());
        expense.updateDate(parseDouble(textDate.getText()));
        expense.updateCurrency(textCurrency.getText());
        expense.updateMoney(parseDouble(textAmount.getText()));
        expense.updateAccount(textAccount.getText());
    }

    // EFFECTS: a button action that updates inputs to a table row
    private void doBtnUpdate(JFrame frame, JTable table) {
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(150, 250, 100, 25);
        frame.add(btnUpdate);

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();
                if (i >= 0) {
                    model.setValueAt(textDescription.getText(), i, 0);
                    model.setValueAt(textDate.getText(), i, 1);
                    model.setValueAt(textCurrency.getText(), i, 2);
                    model.setValueAt(textAmount.getText(), i, 3);
                    model.setValueAt(textAccount.getText(),i,4);

                    expense.updateDescription(textDescription.getText());
                    expense.updateDescription(textDate.getText());
                    expense.updateDescription(textCurrency.getText());
                    expense.updateDescription(textAmount.getText());
                    expense.updateDescription(textAccount.getText());

                } else {
                    System.out.println("Update Error");
                }
            }
        });
    }

    // EFFECTS: a button action that deletes a table row
    private void doBtnDelete(JFrame frame, JTable table) {
        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(150, 280, 100, 25);
        frame.add(btnDelete);

        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int i = table.getSelectedRow();
                if (i >= 0) {
                    model.removeRow(i);
                    expenses.removeExpense(i);
                } else {
                    System.out.println("Delete Error");
                }
            }
        });
    }

    // EFFECTS: a button action that saves table
    private void doBtnSave() {
        Action saveAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(expenses);
                    jsonWriter.close();
                    System.out.println("Saved " + " to " + JSON_STORE);
                } catch (FileNotFoundException exception) {
                    System.out.println("Unable to write to file: " + JSON_STORE);
                }
            }
        };

        ImageIcon icon = new ImageIcon("./data/save.jpg");

        JMenuItem saveMenu = new JMenuItem("Save");
        saveMenu.setIcon(icon);
        saveMenu.addActionListener(saveAction);
        file.add(saveMenu);
    }

    // EFFECTS: a button action that loads table
    private void doBtnLoad() {
        Action loadAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    expenses = jsonReader.read();
                    System.out.println("Loaded " + " from " + JSON_STORE);
                } catch (IOException exception) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }
            }
        };

        ImageIcon icon = new ImageIcon("./data/upload.png");
        JMenuItem loadMenu = new JMenuItem("Load");
        loadMenu.setIcon(icon);
        loadMenu.addActionListener(loadAction);
        file.add(loadMenu);
    }
}
