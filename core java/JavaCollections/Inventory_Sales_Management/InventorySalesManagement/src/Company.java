public class Company {
    private String companyId, companyName, contactDetails, location;
    public Company(String id, String name, String contact, String location) {
        this.companyId=id; this.companyName=name; this.contactDetails=contact; this.location=location;
    }
    public String getCompanyId(){ return companyId; }
    public String getCompanyName(){ return companyName; }
    public String getContactDetails(){ return contactDetails; }
    public String getLocation(){ return location; }
    public void updateDetails(String name,String contact,String location){
        this.companyName=name; this.contactDetails=contact; this.location=location;
    }
    public String toString(){ return companyId+" | "+companyName+" | "+contactDetails+" | "+location; }
}
