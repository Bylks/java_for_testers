package model;

import java.time.LocalDate;

public record ContactData (String id, String lastName, String firstName, String address, String email1, String homePhone, LocalDate birthday, LocalDate anniversary,
                           String photo) {
   public ContactData(){
        this("", "","","","","",null,null, "");
    }
//    public ContactData withChangedGroup(String group)
//    {
//        return new ContactData(this.id(), this.lastName(),this.firstName(),this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.photo());
//    }
    public ContactData withChangedLastName(String lastName)
    {
        return new ContactData(this.id(), lastName,this.firstName(),this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.photo());
    }
    public ContactData withChangedFirstName(String firstName)
    {
        return new ContactData(this.id(), this.lastName(),firstName,this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.photo());
    }
    public ContactData withChangedAddress(String address)
    {
        return new ContactData(this.id(), this.lastName(),this.firstName(),address,this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.photo());
    }
    public ContactData withChangedId(String id)
    {
        return new ContactData(id, this.lastName(),this.firstName(),this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.photo());
    }
    public ContactData truncateAllBesidesNamesAndId()
    {
        return new ContactData(this.id(), this.lastName(),this.firstName(),"","","",null,null, "");
    }
    public ContactData truncateAllBesidesNamesIdAddress()
    {
        return new ContactData(this.id(), this.lastName(),this.firstName(),this.address(),"","",null,null, "");
    }
    public ContactData withPhoto(String photo)
    {
        return new ContactData(this.id(), this.lastName(), this.firstName(), this.address(), this.email1(), this.homePhone(), this.birthday(), this.anniversary(), photo);
    }
}
