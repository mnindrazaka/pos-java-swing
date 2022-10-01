import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTransactionFrame extends JFrame {
    private Shop shop;

    private Transaction transaction = new Transaction();

    private JLabel labelProduct = new JLabel("Product");
    private JComboBox<Product> comboBoxProduct = new JComboBox<>();

    private JLabel labelAmount = new JLabel("Amount");
    private JTextField textFieldAmount = new JTextField(20);

    private JButton buttonAddItem = new JButton("Add Item");

    private JLabel labelCustomer = new JLabel("Customer");
    private JTextField textFieldCustomer = new JTextField(30);

    private JLabel labelTotal = new JLabel("Total");
    private JTextField textFieldTotal = new JTextField(30);

    private JLabel labelMoney = new JLabel("Money");
    private JTextField textFieldMoney = new JTextField(30);

    private JLabel labelChange = new JLabel("Change");
    private JTextField textFieldChange = new JTextField(30);

    private DefaultTableModel tableModel = new DefaultTableModel();
    private JTable tableItems = new JTable(tableModel);

    private JButton buttonSubmit = new JButton("Submit");

    public CreateTransactionFrame(Shop shop) {
        this.shop = shop;
        initLayout();
        initEventListener();
        updateTable();
    }

    public void initLayout() {
        for (Product product : shop.products) {
            comboBoxProduct.addItem(product);
        }

        textFieldTotal.setEditable(false);
        textFieldChange.setEditable(false);

        tableModel.addColumn("Product Name");
        tableModel.addColumn("Price");
        tableModel.addColumn("Amount");
        tableModel.addColumn("Subtotal");


        Panel panel = new Panel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.weightx = 1;
        constraints.ipady = 15;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(labelProduct, constraints);

        constraints.weightx = 1;
        constraints.ipady = 15;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(labelAmount, constraints);

        constraints.weightx = 1;
        constraints.ipady = 15;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comboBoxProduct, constraints);

        constraints.weightx = 1;
        constraints.ipady = 15;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(textFieldAmount, constraints);

        constraints.weightx = 1;
        constraints.ipady = 15;
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonAddItem, constraints);

        constraints.weightx = 1;
        constraints.ipady = 30;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(labelCustomer, constraints);

        constraints.weightx = 1;
        constraints.ipady = 30;
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(labelTotal, constraints);

        constraints.weightx = 1;
        constraints.ipady = 30;
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(labelMoney, constraints);

        constraints.weightx = 1;
        constraints.ipady = 30;
        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(labelChange, constraints);

        constraints.weightx = 1;
        constraints.ipady = 15;
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(textFieldCustomer, constraints);

        constraints.weightx = 1;
        constraints.ipady = 15;
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(textFieldTotal, constraints);

        constraints.weightx = 1;
        constraints.ipady = 15;
        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(textFieldMoney, constraints);

        constraints.weightx = 1;
        constraints.ipady = 15;
        constraints.gridx = 3;
        constraints.gridy = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(textFieldChange, constraints);

        constraints.weightx = 1;
        constraints.gridwidth = 4;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipady = 200;
        panel.add(tableItems, constraints);

        constraints.weightx = 1;
        constraints.gridwidth = 4;
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipady = 15;
        panel.add(buttonSubmit, constraints);

        this.setTitle("Create new transaction");
        this.setSize(600, 600);
        this.add(panel);
    }

    public void initEventListener() {
        buttonAddItem.addActionListener(e ->  onButtonAddItemClick());
        textFieldMoney.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onTextFieldMoneyChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onTextFieldMoneyChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onTextFieldMoneyChange();
            }
        });
        textFieldCustomer.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onTextFieldCustomerChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onTextFieldCustomerChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onTextFieldCustomerChange();
            }
        });
        buttonSubmit.addActionListener(e -> onButtonSubmitClick());
    }

    public void onButtonAddItemClick() {
        Product product = (Product) comboBoxProduct.getSelectedItem();
        int amount = Integer.parseInt(textFieldAmount.getText());

        TransactionItem transactionItem = new TransactionItem(product, amount);
        transaction.items.add(transactionItem);
        textFieldTotal.setText(Integer.toString(transaction.getTotal()));
        updateTable();
        resetItemForm();
    }

    public void onButtonSubmitClick() {
        shop.transactions.add(transaction);
        transaction = new Transaction();
        resetItemForm();
        updateTable();
        resetDetailForm();
    }

    public void onTextFieldMoneyChange() {
       int money = Integer.parseInt(textFieldMoney.getText());
       transaction.money = money;
       textFieldChange.setText(Integer.toString(transaction.getChange()));
    }

    public void onTextFieldCustomerChange() {
        String customer = textFieldCustomer.getText();
        transaction.customer = customer;
    }

    public void updateTable() {
        tableModel.setRowCount(0);
        for (TransactionItem item : transaction.items)
            tableModel.addRow(new Object[]{item.product.name, item.product.price, item.amount, item.getSubTotal()});
    }

    public void resetItemForm() {
        comboBoxProduct.setSelectedItem(shop.products.get(0));
        textFieldAmount.setText("");
    }

    public void resetDetailForm() {
        textFieldCustomer.setText("");
        textFieldTotal.setText("");
        textFieldMoney.setText("");
        textFieldChange.setText("");
    }
}
