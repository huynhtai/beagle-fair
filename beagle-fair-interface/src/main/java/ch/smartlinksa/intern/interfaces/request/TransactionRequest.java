package ch.smartlinksa.intern.interfaces.request;

import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.constant.PatternConstant;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;

public class TransactionRequest {

    @NotBlank(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Pattern(message = MessageCodeConstant.ERROR_PRODUCT_CODE_PATTERN, regexp = PatternConstant.PRODUCT_CODE)
    private String productCode;

    @NotNull(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    @Min(value = 1, message = MessageCodeConstant.ERROR_MINIMUM_QUANTITY)
    @Max(value = 200, message = MessageCodeConstant.ERROR_MAXIMUM_QUANTITY)
    private int quantity;

    @NotNull(message = MessageCodeConstant.ERROR_FIELD_REQUIRED)
    private double unitPrice;

    private String userId;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
