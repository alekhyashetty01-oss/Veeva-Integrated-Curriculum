import java.util.*;
public class InventoryService {
    private Map<String,Company> companies=new HashMap<>();
    private Map<String,Product> products=new HashMap<>();
    private Map<String,Integer> stock=new HashMap<>();
    private List<Transaction> transactionLog=new ArrayList<>();
    private int transactionNumber=1;

    public void addCompany(Company c){
        if(companies.containsKey(c.getCompanyId())){System.out.println("Company ID already exists.");return;}
        companies.put(c.getCompanyId(),c); System.out.println("Company added.");
    }
    public void updateCompany(String id,String name,String contact,String location){
        Company c=companies.get(id);
        if(c==null){System.out.println("Company not found.");return;}
        c.updateDetails(name,contact,location); System.out.println("Company updated.");
    }
    public void showCompanies(){
        if(companies.isEmpty()) System.out.println("No companies.");
        for(Company c:companies.values()) System.out.println(c);
    }
    private Product findByName(String name){
        for(Product p:products.values()) if(p.getProductName().equalsIgnoreCase(name)) return p;
        return null;
    }
    public void addProduct(Product p,int quantity){
        if(products.containsKey(p.getProductId())){System.out.println("Product ID already exists.");return;}
        if(findByName(p.getProductName())!=null){System.out.println("Product name already exists.");return;}
        if(!companies.containsKey(p.getCompanyId())){System.out.println("Supplier company not found.");return;}
        if(p.getUnitPrice()<=0 || quantity<0){System.out.println("Price must be positive; stock cannot be negative.");return;}
        products.put(p.getProductId(),p); stock.put(p.getProductId(),quantity);
        System.out.println("Product added.");
    }
    public void updateProduct(String id,String name,double price,String companyId,String category){
        Product p=products.get(id);
        if(p==null){System.out.println("Product not found.");return;}
        Product duplicate=findByName(name);
        if(duplicate!=null && !duplicate.getProductId().equals(id)){System.out.println("Product name already used.");return;}
        if(price<=0 || !companies.containsKey(companyId)){System.out.println("Invalid price or supplier ID.");return;}
        p.updateDetails(name,price,companyId,category); System.out.println("Product updated.");
    }
    public void lookup(String key){
        Product p=products.get(key);
        if(p==null)p=findByName(key);
        if(p==null){System.out.println("Product not found.");return;}
        System.out.println(p);
        System.out.println("Supplier: "+companies.get(p.getCompanyId()));
        System.out.println("Available stock: "+stock.get(p.getProductId()));
    }
    public void showProducts(){
        if(products.isEmpty())System.out.println("No products.");
        for(Product p:products.values()){
            System.out.println(p+" | Available stock: "+stock.get(p.getProductId()));
        }
    }
    public void restock(String id,int qty){
        if(!products.containsKey(id)){System.out.println("Product not found.");return;}
        if(qty<=0){System.out.println("Restock quantity must be positive.");return;}
        stock.put(id,stock.get(id)+qty); System.out.println("New stock: "+stock.get(id));
    }
    // Validate the complete order before changing stock (all-or-nothing transaction).
    public void sell(Map<String,Integer> order){
        if(order.isEmpty()){System.out.println("Order is empty.");return;}
        List<SaleItem> items=new ArrayList<>();
        Map<String,Integer> validated=new LinkedHashMap<>();
        double total=0;
        for(Map.Entry<String,Integer> e:order.entrySet()){
            String id=e.getKey(); int qty=e.getValue();
            if(qty<=0){System.out.println("Transaction failed: quantity must be greater than zero.");return;}
            Product p=products.get(id);
            if(p==null){System.out.println("Transaction failed: product "+id+" not found.");return;}
            int available=stock.get(id);
            if(available==0){System.out.println("Alert: "+p.getProductName()+" is currently out of stock.");return;}
            if(qty>available){
                System.out.println("Transaction failed: Only "+available+" units of "+p.getProductName()+" are available.");return;
            }
            validated.put(id,qty);
            SaleItem item=new SaleItem(id,p.getProductName(),qty,p.getUnitPrice());
            items.add(item); total+=item.getLineTotal();
        }
        // Deduct only after every line passes validation.
        for(Map.Entry<String,Integer> e:validated.entrySet())
            stock.put(e.getKey(),stock.get(e.getKey())-e.getValue());
        Transaction t=new Transaction(String.format("TXN%04d",transactionNumber++),items,total);
        transactionLog.add(t);
        System.out.println("Transaction successful!\\n"+t);
        System.out.println("Updated stock:");
        for(String id:validated.keySet())
            System.out.println(products.get(id).getProductName()+": "+stock.get(id));
    }
    public void showTransactions(){
        if(transactionLog.isEmpty())System.out.println("No transactions recorded.");
        for(Transaction t:transactionLog)System.out.println(t+"\\n--------------------");
    }
}
