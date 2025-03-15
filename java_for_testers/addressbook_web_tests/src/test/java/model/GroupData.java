package model;

public record GroupData(String id, String name, String header, String footer) {
    public GroupData()
    {
        this("", "","","");
    }

    public GroupData withChangedName(String name) {
        return new GroupData (this.id(), name, this.header(),this.footer());
    }
    public GroupData withChangedHeader(String header) {
        return new GroupData (this.id(), this.name(), header,this.footer());
    }
    public GroupData withChangedFooter(String footer) {
        return new GroupData (this.id(), this.name(), this.header(),footer);
    }
    public GroupData withChangedId(String id) {
        return new GroupData (id, this.name(), this.header(),this.footer());
    }
}
