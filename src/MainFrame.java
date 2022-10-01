import javax.swing.*;

public class MainFrame extends JFrame {
    private Shop shop;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("action");
    private JMenuItem menuItemProduct = new JMenuItem("product");
    private JMenuItem menuItemTransaction = new JMenuItem("transaction");

    public MainFrame(Shop shop) {
        this.shop = shop;
        initLayout();
        initEventHandler();
    }

    public void initLayout() {
        menu.add(menuItemProduct);
        menu.add(menuItemTransaction);
        menuBar.add(menu);

        this.setTitle("POS");
        this.setSize(400, 400);
        this.setJMenuBar(menuBar);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initEventHandler() {
        menuItemProduct.addActionListener(e -> onMenuItemProductClick());
        menuItemTransaction.addActionListener(e -> onMenuItemTransactionClick());
    }

    public void onMenuItemProductClick() {
        ProductFrame productFrame = new ProductFrame(shop);
        productFrame.setLocationRelativeTo(this);
        productFrame.setVisible(true);
    }

    public void onMenuItemTransactionClick() {
        TransactionFrame transactionFrame = new TransactionFrame(shop);
        transactionFrame.setLocationRelativeTo(this);
        transactionFrame.setVisible(true);
    }
}
