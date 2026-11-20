public class SaleItem {
    private String productId, productName;
    private int quantity;
    private double unitPrice;
    public SaleItem(String id,String name,int quantity,double price){
        this.productId=id; this.productName=name; this.quantity=quantity; this.unitPrice=price;
    }
    public String getProductId(){return productId;}
    public int getQuantity(){return quantity;}
    public double getLineTotal(){return quantity*unitPrice;}
    public String toString(){
        return productName+" x "+quantity+" @ $"+String.format("%.2f",unitPrice)
            +" = $"+String.format("%.2f",getLineTotal());
    }
}
