package ru.stqua.mantis.model;

public record UserData(String username, String email, Long accesslevel, String password, String realname) {
    public UserData ()
    {
        this("", "", 25L,"","");
    }
    public UserData withUserName(String username)
    {
        return new UserData (username, this.email, this.accesslevel, this.password, this.realname);
    }
    public UserData withEmail(String email)
    {
        return new UserData (this.username, email, this.accesslevel, this.password, this.realname);
    }
    public UserData withAccessLevel(Long accesslevel)
    {
        return new UserData (this.username, this.email, accesslevel, this.password, this.realname);
    }
    public UserData withPassword(String password)
    {
        return new UserData (this.username, this.email, this.accesslevel, password, this.realname);
    }
    public UserData withRealName(String realname)
    {
        return new UserData (this.username, this.email, this.accesslevel, this.password, realname);
    }
}
