package model;

public record ContactData (String lastName,String firstName, String address, String email1, String homePhone, String group) {
   public ContactData(){
        this("","","","","","");
    }
    public ContactData contactDataChangedGroup(String group)
    {
        return new ContactData(this.lastName(),this.firstName(),this.address(),this.email1(),this.homePhone(), group);
    }
}
