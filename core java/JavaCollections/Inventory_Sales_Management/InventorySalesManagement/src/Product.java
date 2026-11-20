public class Product {
    private String productId, productName, companyId, category;
    private double unitPrice;
    public Product(String id,String name,double price,String companyId,String category){
        this.productId=id; this.productName=name; this.unitPrice=price;
        this.companyId=companyId; this.category=category;
    }
    public String getProductId(){return productId;}
    public String getProductName(){return productName;}
    public double getUnitPrice(){return unitPrice;}
    public String getCompanyId(){return companyId;}
    public String getCategory(){return category;}
    public void updateDetails(String name,double price,String companyId,String category){
        this.productName=name; this.unitPrice=price; this.companyId=companyId; this.category=category;
    }
    public String toString(){
        return productId+" | "+productName+" | $"+String.format("%.2f",unitPrice)
            +" | Company: "+companyId+" | "+category;
    }
}
