import java.util.ArrayList;

public class Shop {
    public ArrayList<Product> products = new ArrayList<>();
    public ArrayList<Transaction> transactions = new ArrayList<>();

    public static Shop instance = new Shop();
}
