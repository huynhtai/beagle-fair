package ch.smartlinksa.intern.interfaces.request;


import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.validate.constraint.GreaterThanZero;
import ch.smartlinksa.intern.interfaces.validate.constraint.IsInteger;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SellRequest {

    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    private String productCode;

    @NotNull(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @GreaterThanZero(message = MessageCodeConstant.ERROR_VALUE_MUST_GREATER_THAN_ZERO)
    @IsInteger(message = MessageCodeConstant.ERROR_MUST_BE_INTEGER)
    private Number quantity;

    @NotNull(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @GreaterThanZero(message = MessageCodeConstant.ERROR_VALUE_MUST_GREATER_THAN_ZERO)
    private double unitPrice;

    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Size(message = MessageCodeConstant.ERROR_SIZE, min = 3, max = 200)
    private String description;

    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Size(message = MessageCodeConstant.ERROR_SIZE, min = 3, max = 200)
    private String address;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return (Integer)quantity;
    }

    public void setQuantity(Number quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

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
