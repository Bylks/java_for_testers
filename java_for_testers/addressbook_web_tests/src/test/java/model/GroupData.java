package model;

public record GroupData(String name, String header, String footer) {
    public GroupData()
    {
        this("","","");
    }

    public GroupData withChangedName(String name) {
        return new GroupData (name, this.header(),this.footer());
    }
    public GroupData withChangedHeader(String header) {
        return new GroupData (this.name(), header,this.footer());
    }
    public GroupData withChangedFooter(String footer) {
        return new GroupData (this.name(), this.header(),footer);
    }
}
