package ch.smartlinksa.intern.interfaces.request;

import javax.persistence.Column;

public class PurchaseTransactionRequest extends TransactionRequest {

    private String address;

    private double totalPrice;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
