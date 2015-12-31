package ch.smartlinksa.intern.business.exception;

import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;

public class UserNotEnoughMoneyToPurchaseException extends RuntimeException{

    public String getMessage(){
        return MessageCodeConstant.ERROR_NOT_ENOUGH_TO_PURCHASE;
    }
}
