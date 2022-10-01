import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProductFrame extends JFrame {
    private JLabel labelName = new JLabel("Name");
    private JTextField textFieldName = new JTextField(20);
    private JLabel labelPrice = new JLabel("Price");
    private JTextField textFieldPrice = new JTextField(20);
    private JButton buttonSubmit = new JButton("Submit");
    private JButton buttonDelete = new JButton("Delete");
    private JButton buttonCancel = new JButton("Cancel");
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JTable tableProducts = new JTable(tableModel);

    public ProductFrame() {
        initLayout();
        initEventListener();
        updateTable();
    }

    private void initLayout() {
        buttonCancel.setEnabled(false);
        buttonDelete.setEnabled(false);
        tableModel.addColumn("Name");
        tableModel.addColumn("Price");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panel.add(labelName, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        panel.add(textFieldName, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(labelPrice, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        panel.add(textFieldPrice, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        panel.add(buttonCancel, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        panel.add(buttonDelete, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        panel.add(buttonSubmit, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 4;
        constraints.ipady = 200;
        panel.add(tableProducts, constraints);

        this.setTitle("Product Management");
        this.setSize(400, 400);
        this.setLayout(new GridBagLayout());
        this.add(panel);
    }

    private void initEventListener() {
        buttonSubmit.addActionListener(e -> onButtonSubmitClick());
        buttonDelete.addActionListener(e -> onButtonDeleteClick());
        buttonCancel.addActionListener(e -> onButtonCancelClick());
        tableProducts.getSelectionModel().addListSelectionListener(e -> {
            onTableProductsRowClick();
        });
    }

    private void updateTable() {
        tableModel.setNumRows(0);
        for (Product product : Shop.instance.products)
            tableModel.addRow(new Object[]{product.name, product.price});
    }

    private void onButtonSubmitClick() {
        int row = tableProducts.getSelectedRow();

        String name = textFieldName.getText();
        int price = Integer.parseInt(textFieldPrice.getText());

        if (row >= 0) {
            Shop.instance.products.set(row, new Product(name,  price));
        } else {
            Shop.instance.products.add(new Product(name, price));
        }

        updateTable();
        resetForm();
    }

    private void onButtonDeleteClick() {
        int row = tableProducts.getSelectedRow();
        Shop.instance.products.remove(row);
        updateTable();
        resetForm();
    }

    private void onButtonCancelClick() {
        resetForm();
    }

    private void onTableProductsRowClick() {
        buttonDelete.setEnabled(true);
        buttonCancel.setEnabled(true);
        int row = tableProducts.getSelectedRow();
        if (row >= 0) {
            String name = tableProducts.getValueAt(row, 0).toString();
            String price = tableProducts.getValueAt(row, 1).toString();
            textFieldName.setText(name);
            textFieldPrice.setText(price);
        }
    }

    private void resetForm() {
        textFieldName.setText("");
        textFieldPrice.setText("");
        tableProducts.getSelectionModel().clearSelection();
        buttonDelete.setEnabled(false);
        buttonCancel.setEnabled(false);
    }
}
