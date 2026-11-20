import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class Transaction {
    private String transactionId;
    private List<SaleItem> items;
    private double totalAmount;
    private LocalDateTime timestamp;
    public Transaction(String id,List<SaleItem> items,double total){
        this.transactionId=id; this.items=new ArrayList<>(items);
        this.totalAmount=total; this.timestamp=LocalDateTime.now();
    }
    public String toString(){
        StringBuilder s=new StringBuilder("\\nTransaction: "+transactionId+
            "\\nTime: "+timestamp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))+"\\n");
        for(SaleItem item:items) s.append(item).append("\\n");
        return s+"Total bill: $"+String.format("%.2f",totalAmount);
    }
}
