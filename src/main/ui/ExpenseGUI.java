package ui;

import model.Expense;
import model.ListOfExpenses;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.text.html.HTMLEditorKit;

public class ExpenseGUI {
    @SuppressWarnings("methodlength")
    public static void main(String[] args) throws IOException {

        // EFFECTS: create JFrame and JTable
        JFrame frame = new JFrame();
        JTable table = new JTable();
        Expense expense = new Expense("Saveon", 3.3, "cad", 10, "bmo");
        ListOfExpenses expenses = new ListOfExpenses();

        // EFFECTS: create a table model and set a Column Identifiers to this model
        Object[] columns = {"Description","Date","Currency","Amount", "Account"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        // EFFECTS: set the model to the table
        table.setModel(model);

        // EFFECTS: Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);

        // EFFECTS: create JTextFields
        JTextField textDescription = new JTextField();
        JTextField textDate = new JTextField();
        JTextField textCurrency = new JTextField();
        JTextField textAmount = new JTextField();
        JTextField textAccount = new JTextField();

        // EFFECTS: create JButtons
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");

        textDescription.setBounds(20, 220, 100, 25);
        textDate.setBounds(20, 250, 100, 25);
        textCurrency.setBounds(20, 280, 100, 25);
        textAmount.setBounds(20, 310, 100, 25);
        textAccount.setBounds(20, 340, 100, 25);

        btnAdd.setBounds(150, 220, 100, 25);
        btnUpdate.setBounds(150, 250, 100, 25);
        btnDelete.setBounds(150, 280, 100, 25);

        // EFFECTS: create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 500, 200);

        frame.setLayout(null);

        frame.add(pane);

        // EFFECTS: add JTextFields to the jframe
        frame.add(textDescription);
        frame.add(textDate);
        frame.add(textCurrency);
        frame.add(textAmount);
        frame.add(textAccount);

        // EFFECTS: add JButtons to the jframe
        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnUpdate);

        // EFFECTS: create an array of objects to set the row data
        Object[] row = new Object[5];

        // EFFECTS: button add row
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                row[0] = textDescription.getText();
                row[1] = textDate.getText();
                row[2] = textCurrency.getText();
                row[3] = textAmount.getText();
                row[4] = textAccount.getText();

                // add row to the model
                model.addRow(row);
                expense.updateDescription(textDescription.getText());
                expense.updateDescription(textDate.getText());
                expense.updateDescription(textCurrency.getText());
                expense.updateDescription(textAmount.getText());
                expense.updateDescription(textAccount.getText());

                expenses.addExpense(expense);
            }
        });

        // EFFECTS: button remove row
        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();
                if (i >= 0) {
                    // remove a row from jtable
                    model.removeRow(i);
                    expenses.removeExpense(i);
                } else {
                    System.out.println("Delete Error");
                }
            }
        });

        // EFFECTS: Get selected row data From table to textfields
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                textDescription.setText(model.getValueAt(i, 0).toString());
                textDate.setText(model.getValueAt(i, 1).toString());
                textCurrency.setText(model.getValueAt(i, 2).toString());
                textAmount.setText(model.getValueAt(i, 3).toString());
                textAccount.setText((model.getValueAt(i,4).toString()));
            }
        });

        // EFFECTS: button update row
        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
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

        frame.setSize(900,1440);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final String filename = "ExpensesGUI.java";
        Container content = frame.getContentPane();

        final JEditorPane editorPane = new JEditorPane();
        JScrollPane scrollPane = new JScrollPane(editorPane);
        content.add(scrollPane, BorderLayout.CENTER);

        editorPane.setEditorKit(new HTMLEditorKit());

        JPanel panel = new JPanel();

        // EFFECTS: Load data
        Action loadAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doLoadCommand(editorPane, filename);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        };

        loadAction.putValue(Action.NAME, "Load");
        JButton btnLoad = new JButton(loadAction);
        //panel.add(btnLoad);
        btnLoad.setBounds(150,340,100,25);
        frame.add(btnLoad);

        // EFFECTS: Save data
        Action saveAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    doSaveCommand(editorPane, filename);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        };

        saveAction.putValue(Action.NAME, "Save");
        JButton btnSave = new JButton(saveAction);
        //panel.add(btnSave);
        btnSave.setBounds(150,310,100,25);
        frame.add(btnSave);

         //EFFECTS: Display an image
        BufferedImage img = ImageIO.read(new URL(
                "https://encrypted-tbn0.gstatic.com/images?q="
                        +
                        "tbn:ANd9GcSZUmIp0fj4x88KXfc1KJ-H4Xx_tKYun_HEQA&usqp=CAU"));
        ImageIcon icon = new ImageIcon(img);
        frame.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        JLabel lbl = new JLabel(icon);
        lbl.setLocation(100,50);
        frame.add(lbl);
        lbl.setIcon(icon);
        lbl.setLocation(100,50);
        content.add(lbl);

        content.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void doSaveCommand(JTextComponent textComponent, String filename) throws Exception {
        FileWriter writer = null;
        writer = new FileWriter(filename);
        textComponent.write(writer);
        writer.close();
    }

    public static void doLoadCommand(JTextComponent textComponent, String filename) throws Exception {
        FileReader reader = null;
        reader = new FileReader(filename);
        textComponent.read(reader, filename);
        reader.close();
    }

}