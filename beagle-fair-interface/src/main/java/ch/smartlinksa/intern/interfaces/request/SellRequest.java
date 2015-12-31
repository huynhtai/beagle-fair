package ch.smartlinksa.intern.interfaces.request;

import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SellRequest extends TransactionRequest{

    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Size(message = MessageCodeConstant.ERROR_SIZE, min = 3, max = 200)
    private String description;

    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Size(message = MessageCodeConstant.ERROR_SIZE, min = 3, max = 200)
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
