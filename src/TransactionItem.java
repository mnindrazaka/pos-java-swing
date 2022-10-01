public class TransactionItem {
    public Product product;
    public int amount;

    public TransactionItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public int getSubTotal() {
        return product.price * amount;
    }
}
