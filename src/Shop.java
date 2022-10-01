import java.util.ArrayList;

public class Shop {
    public ArrayList<Product> products;
    public ArrayList<Transaction> transactions;

    public Shop(ArrayList<Product> products, ArrayList<Transaction> transactions) {
        this.products = products;
        this.transactions = transactions;
    }
}
