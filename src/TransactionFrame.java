import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TransactionFrame extends JFrame {
    private Shop shop;
    private JButton buttonCreate = new JButton("Create new transaction");
    private JButton buttonRefresh = new JButton("Refresh");

    private DefaultTableModel tableModel = new DefaultTableModel();
    private JTable table = new JTable(tableModel);

    public TransactionFrame(Shop shop) {
        this.shop = shop;
        initLayout();
        initEventListener();
        updateTable();
    }

    private void initLayout() {
        tableModel.addColumn("Customer");
        tableModel.addColumn("Total");
        tableModel.addColumn("Money");
        tableModel.addColumn("Change");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(buttonCreate, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(buttonRefresh, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.ipady = 200;
        panel.add(table, constraints);

        this.setTitle("Transaction Management");
        this.setSize(400, 400);
        this.add(panel);
    }

    private void initEventListener() {
        buttonCreate.addActionListener(e -> onButtonCreateClick());
        buttonRefresh.addActionListener(e -> updateTable());
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Transaction transaction : shop.transactions)
            tableModel.addRow(new Object[]{transaction.customer, transaction.getTotal(), transaction.money, transaction.getChange()});
    }

    private void onButtonCreateClick() {
        CreateTransactionFrame createTransactionFrame = new CreateTransactionFrame(shop);
        createTransactionFrame.setLocationRelativeTo(this);
        createTransactionFrame.setVisible(true);
    }
 }
