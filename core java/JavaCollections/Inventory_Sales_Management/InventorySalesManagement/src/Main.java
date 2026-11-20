import java.util.*;
public class Main {
    private static final Scanner sc=new Scanner(System.in);
    private static final InventoryService service=new InventoryService();
    public static void main(String[] args){
        loadSampleData(); int choice;
        do{
            System.out.println("\\n===== INVENTORY & SALES MANAGEMENT =====");
            System.out.println("1 Add Company  2 Update Company  3 View Companies");
            System.out.println("4 Add Product  5 Update Product  6 Price Lookup");
            System.out.println("7 View Products/Stock  8 Restock  9 Sell Products");
            System.out.println("10 Transaction Log  0 Exit");
            choice=readInt("Choice: ");
            switch(choice){
                case 1: addCompany(); break;
                case 2: updateCompany(); break;
                case 3: service.showCompanies(); break;
                case 4: addProduct(); break;
                case 5: updateProduct(); break;
                case 6: service.lookup(readText("Enter product ID or name: ")); break;
                case 7: service.showProducts(); break;
                case 8: service.restock(readText("Product ID: "),readInt("Quantity to add: ")); break;
                case 9: sellProducts(); break;
                case 10: service.showTransactions(); break;
                case 0: System.out.println("Thank you!"); break;
                default: System.out.println("Invalid choice.");
            }
        }while(choice!=0);
    }
    private static void loadSampleData(){
        service.addCompany(new Company("C101","Tech Suppliers","9876543210","Hyderabad"));
        service.addCompany(new Company("C102","Keyboard World","9123456780","Bengaluru"));
        service.addProduct(new Product("P101","Wireless Mouse",25.0,"C101","Accessories"),10);
        service.addProduct(new Product("P102","Mechanical Keyboard",60.0,"C102","Accessories"),0);
    }
    private static void addCompany(){
        service.addCompany(new Company(readText("Company ID: "),readText("Company name: "),
            readText("Contact: "),readText("Location: ")));
    }
    private static void updateCompany(){
        service.updateCompany(readText("Company ID: "),readText("New name: "),
            readText("New contact: "),readText("New location: "));
    }
    private static void addProduct(){
        String id=readText("Product ID: "), name=readText("Product name: ");
        double price=readDouble("Unit price: ");
        String company=readText("Company ID: "), category=readText("Category: ");
        int qty=readInt("Initial stock: ");
        service.addProduct(new Product(id,name,price,company,category),qty);
    }
    private static void updateProduct(){
        String id=readText("Product ID: "), name=readText("New name: ");
        double price=readDouble("New price: ");
        String company=readText("Company ID: "), category=readText("Category: ");
        service.updateProduct(id,name,price,company,category);
    }
    private static void sellProducts(){
        int count=readInt("Number of different products in order: ");
        if(count<=0){System.out.println("Enter at least one item.");return;}
        Map<String,Integer> order=new LinkedHashMap<>();
        for(int i=1;i<=count;i++){
            String id=readText("Product "+i+" ID: ");
            int qty=readInt("Quantity: ");
            order.put(id,order.getOrDefault(id,0)+qty);
        }
        service.sell(order);
    }
    private static String readText(String prompt){System.out.print(prompt);return sc.nextLine().trim();}
    private static int readInt(String prompt){
        while(true){System.out.print(prompt);try{return Integer.parseInt(sc.nextLine().trim());}
            catch(NumberFormatException e){System.out.println("Enter a valid whole number.");}}
    }
    private static double readDouble(String prompt){
        while(true){System.out.print(prompt);try{return Double.parseDouble(sc.nextLine().trim());}
            catch(NumberFormatException e){System.out.println("Enter a valid number.");}}
    }
}
