package model;

import java.time.LocalDate;

public record ContactData (String lastName, String firstName, String address, String email1, String homePhone, LocalDate birthday, LocalDate anniversary, String group) {
   public ContactData(){
        this("","","","","",null,null,"");
    }
    public ContactData contactDataChangedGroup(String group)
    {
        return new ContactData(this.lastName(),this.firstName(),this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), group);
    }
}
