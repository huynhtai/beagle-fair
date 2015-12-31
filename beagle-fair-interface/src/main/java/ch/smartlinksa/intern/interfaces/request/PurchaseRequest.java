package ch.smartlinksa.intern.interfaces.request;

import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class PurchaseRequest extends TransactionRequest {

    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Size(min = 10, max = 200, message = MessageCodeConstant.ERROR_SIZE)
    private String address;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
