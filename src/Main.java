import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Product pulpen = new Product("pulpen", 3000);
        Product pencil = new Product("pencil", 3500);
        ArrayList<Product> products = new ArrayList<>();
        products.add(pulpen);
        products.add(pencil);

        TransactionItem item1 = new TransactionItem(pulpen, 2);
        TransactionItem item2 = new TransactionItem(pencil, 1);
        ArrayList<TransactionItem> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        Transaction transaction1 = new Transaction("Aka", 10000, items);
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);

        Shop shop = new Shop(products, transactions);

        MainFrame mainFrame = new MainFrame(shop);
        mainFrame.setVisible(true);
    }
}