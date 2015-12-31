package ch.smartlinksa.intern.interfaces.response;

public class SellRespone extends  TransactionResponse{

    private String description;
    private String address;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
