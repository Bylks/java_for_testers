package model;

import java.time.LocalDate;

public record ContactData (String lastName, String firstName, String address, String email1, String homePhone, LocalDate birthday, LocalDate anniversary, String group) {
   public ContactData(){
        this("","","","","",null,null,"");
    }
    public ContactData withChangedGroup(String group)
    {
        return new ContactData(this.lastName(),this.firstName(),this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), group);
    }
    public ContactData withChangedLastName(String lastName)
    {
        return new ContactData(lastName,this.firstName(),this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.group());
    }
    public ContactData withChangedFirstName(String firstName)
    {
        return new ContactData(this.lastName(),firstName,this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.group());
    }
    public ContactData withChangedAddress(String address)
    {
        return new ContactData(this.lastName(),this.firstName(),address,this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.group());
    }
}
