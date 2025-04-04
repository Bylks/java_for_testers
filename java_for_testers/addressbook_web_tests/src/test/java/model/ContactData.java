package model;

import java.time.LocalDate;

public record ContactData (String id, String lastName, String firstName, String address, String email1, String homePhone, LocalDate birthday, LocalDate anniversary,
                           String photo, String mobilePhone, String workPhone, String phone2Phone, String email2,
                           String email3) {
   public ContactData(){
        this ("", "","","","","",null,null, null, "", "", "", "", "");
    }
//    public ContactData withChangedGroup(String group)
//    {
//        return new ContactData(this.id(), this.lastName(),this.firstName(),this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.photo());
//    }
    public ContactData withChangedLastName(String lastName)
    {
        return new ContactData(this.id(), lastName,this.firstName(),this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.photo(), this.mobilePhone(), this.workPhone(), this.phone2Phone(), this.email2(), this.email3());
    }
    public ContactData withChangedFirstName(String firstName)
    {
        return new ContactData(this.id(), this.lastName(),firstName,this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.photo(), this.mobilePhone(), this.workPhone(), this.phone2Phone(), this.email2(), this.email3());
    }
    public ContactData withChangedAddress(String address)
    {
        return new ContactData(this.id(), this.lastName(),this.firstName(),address,this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.photo(), this.mobilePhone(), this.workPhone(), this.phone2Phone(), this.email2(), this.email3());
    }
    public ContactData withChangedId(String id)
    {
        return new ContactData(id, this.lastName(),this.firstName(),this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.photo(), this.mobilePhone(), this.workPhone(), this.phone2Phone(), this.email2(), this.email3());
    }
    public ContactData truncateAllBesidesNamesAndId()
    {
        return new ContactData(this.id(), this.lastName(),this.firstName(),null,null,null,null,null, null, null, null, null, null, null);
    }
    public ContactData truncateAllBesidesNamesIdAddress()
    {
        return new ContactData(this.id(), this.lastName(),this.firstName(),this.address(),this.email1(),this.homePhone(),null,null, null, this.mobilePhone(), this.workPhone(), this.phone2Phone(), this.email2(), this.email3());
    }
    public ContactData withPhoto(String photo)
    {
        return new ContactData(this.id(), this.lastName(), this.firstName(), this.address(), this.email1(), this.homePhone(), this.birthday(), this.anniversary(), photo, this.mobilePhone(), this.workPhone(), this.phone2Phone(), this.email2(), this.email3());
    }
    //String homePhone, LocalDate birthday, LocalDate anniversary,
    //                           String photo, String mobilePhone, String workPhone, String phone2Phone
    public ContactData withChangedHomePhone(String homePhone)
    {
        return new ContactData(this.id(), this.lastName(),this.firstName(),this.address(),this.email1(),homePhone,this.birthday(),this.anniversary(), this.photo(), this.mobilePhone(), this.workPhone(), this.phone2Phone(), this.email2(), this.email3());
    }
    public ContactData withChangedMobilePhone(String mobilePhone)
    {
        return new ContactData(this.id(), this.lastName(),this.firstName(),this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.photo(), mobilePhone, this.workPhone(), this.phone2Phone(), this.email2(), this.email3());
    }
    public ContactData withChangedWorkPhone(String workPhone)
    {
        return new ContactData(this.id(), this.lastName(),this.firstName(),this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.photo(), this.mobilePhone(), workPhone, this.phone2Phone(), this.email2(), this.email3());
    }
    public ContactData withChangedPhone2Phone(String phone2Phone)
    {
        return new ContactData(this.id(), this.lastName(),this.firstName(),this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.photo(), this.mobilePhone(), this.workPhone(), phone2Phone, this.email2(), this.email3());
    }
    public ContactData withChangedEmail1(String email1)
    {
        return new ContactData(this.id(), this.lastName(),this.firstName(),this.address(),email1,this.homePhone(),this.birthday(),this.anniversary(), this.photo(), this.mobilePhone(), this.workPhone(), this.phone2Phone(), this.email2(), this.email3());
    }
    public ContactData withChangedEmail2(String email2)
    {
        return new ContactData(this.id(), this.lastName(),this.firstName(),this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.photo(), this.mobilePhone(), this.workPhone(), this.phone2Phone(), email2, this.email3());
    }
    public ContactData withChangedEmail3(String email3)
    {
        return new ContactData(this.id(), this.lastName(),this.firstName(),this.address(),this.email1(),this.homePhone(),this.birthday(),this.anniversary(), this.photo(), this.mobilePhone(), this.workPhone(), this.phone2Phone(), this.email2(), email3);
    }
}
