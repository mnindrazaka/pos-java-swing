import javax.swing.*;

class MainFrameProps {
    Shop shop;
    public MainFrameProps(Shop shop) {
        this.shop = shop;
    }
}

public class MainFrame extends JFrame {
    private MainFrameProps props;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("action");
    private JMenuItem menuItemProduct = new JMenuItem("product");
    private JMenuItem menuItemTransaction = new JMenuItem("transaction");

    public MainFrame(MainFrameProps props) {
        this.props = props;
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
        ProductFrame productFrame = new ProductFrame(new ProductFrameProps(props.shop));
        productFrame.setLocationRelativeTo(this);
        productFrame.setVisible(true);
    }

    public void onMenuItemTransactionClick() {
        TransactionFrame transactionFrame = new TransactionFrame(new TransactionFrameProps(props.shop));
        transactionFrame.setLocationRelativeTo(this);
        transactionFrame.setVisible(true);
    }
}
