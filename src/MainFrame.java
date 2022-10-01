import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    ProductFrame productFrame = new ProductFrame();
    TransactionFrame transactionFrame = new TransactionFrame();

    public MainFrame() {
        productFrame.setLocationRelativeTo(this);
        transactionFrame.setLocationRelativeTo(this);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("action");
        JMenuItem menuItemProduct = new JMenuItem("product");
        menuItemProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productFrame.setVisible(true);
            }
        });

        JMenuItem menuItemTransaction = new JMenuItem("transaction");
        menuItemTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transactionFrame.setVisible(true);
            }
        });

        menu.add(menuItemProduct);
        menu.add(menuItemTransaction);
        menuBar.add(menu);

        this.setTitle("POS");
        this.setSize(400, 400);
        this.setJMenuBar(menuBar);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
