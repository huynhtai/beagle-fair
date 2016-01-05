package ch.smartlinksa.intern.interfaces.request;

import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.constant.PatternConstant;
import ch.smartlinksa.intern.interfaces.validate.constraint.GreaterThanZero;
import ch.smartlinksa.intern.interfaces.validate.constraint.IsInteger;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;

public class TransactionRequest {

    @Pattern(message = MessageCodeConstant.ERROR_PRODUCT_CODE_PATTERN, regexp = PatternConstant.PRODUCT_CODE)
    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    private String productCode;

    @NotNull(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @GreaterThanZero(message = MessageCodeConstant.ERROR_VALUE_MUST_GREATER_THAN_ZERO)
    @IsInteger(message = MessageCodeConstant.ERROR_MUST_BE_INTEGER)
    private Number quantity;

    @NotNull(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @GreaterThanZero(message = MessageCodeConstant.ERROR_VALUE_MUST_GREATER_THAN_ZERO)
    private double unitPrice;

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


}
