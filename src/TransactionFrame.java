import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class TransactionFrameProps {
    Shop shop;
    public TransactionFrameProps(Shop shop) {
        this.shop = shop;
    }
}

public class TransactionFrame extends JFrame {
    private TransactionFrameProps props;
    private JButton buttonCreate = new JButton("Create new transaction");

    private DefaultTableModel tableModel = new DefaultTableModel();
    private JTable table = new JTable(tableModel);

    public TransactionFrame(TransactionFrameProps props) {
        this.props = props;
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
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipady = 200;
        panel.add(table, constraints);

        this.setTitle("Transaction Management");
        this.setSize(400, 400);
        this.add(panel);
    }

    private void initEventListener() {
        buttonCreate.addActionListener(e -> onButtonCreateClick());
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Transaction transaction : props.shop.transactions)
            tableModel.addRow(new Object[]{transaction.customer, transaction.getTotal(), transaction.money, transaction.getChange()});
    }

    private void onButtonCreateClick() {
        CreateTransactionFrame createTransactionFrame = new CreateTransactionFrame(new CreateTransactionFrameProps(props.shop, () -> updateTable()));
        createTransactionFrame.setLocationRelativeTo(this);
        createTransactionFrame.setVisible(true);
    }
 }
