package ch.smartlinksa.intern.interfaces.request;

import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RefundResquest extends  TransactionRequest {

    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Size(message = MessageCodeConstant.ERROR_SIZE, min = 1, max = 100)
    private String address;

    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Size(message = MessageCodeConstant.ERROR_SIZE, min = 1, max = 100)
    private String reason;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
