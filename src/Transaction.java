import java.util.ArrayList;

public class Transaction {
    public String customer;
    public int money;

    ArrayList<TransactionItem> items = new ArrayList<>();

    public Transaction(String customer, int money, ArrayList<TransactionItem> items) {
        this.customer = customer;
        this.money = money;
        this.items = items;
    }

    public Transaction() {}

    public int getTotal() {
        int total = 0;
        for (TransactionItem item : items)
            total += item.getSubTotal();
        return total;
    }

    public int getChange() {
        return money - getTotal();
    }
}
