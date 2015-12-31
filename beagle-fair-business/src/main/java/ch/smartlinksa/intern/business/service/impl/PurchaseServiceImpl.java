package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.business.exception.UserNotEnoughMoneyToPurchaseException;
import ch.smartlinksa.intern.business.service.IPurchaseService;
import ch.smartlinksa.intern.business.util.SessionUtil;
import ch.smartlinksa.intern.dao.entity.PurchaseTransaction;
import ch.smartlinksa.intern.dao.entity.User;
import ch.smartlinksa.intern.dao.repository.PurchaseTransactionRepository;
import ch.smartlinksa.intern.dao.repository.UserRepository;
import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.request.PurchaseRequest;
import ch.smartlinksa.intern.interfaces.response.PurchaseResponse;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements IPurchaseService {

    @Autowired
    PurchaseTransactionRepository purchaseTransactionRepository;

    @Autowired
    UserRepository userRepository;

    public RestApiResponse<PurchaseResponse> addNewPurchase(PurchaseRequest purchaseRequest) {
        PurchaseTransaction purchaseTransaction = convertPurchaseRequestToPurchaseEntity(purchaseRequest);
        User user = SessionUtil.getCurrentUser();
        if(isNotEnoughMoneyToPurchase(purchaseTransaction, user)){
            throw new UserNotEnoughMoneyToPurchaseException();
        }
        purchaseTransactionRepository.save(purchaseTransaction);
        updateBalanceOfUser(purchaseTransaction, user);
        return makePurchaseTransactionResponse(convertPurchaseEntityToPurchaseTransactionResponse(purchaseTransaction));
    }

    private PurchaseTransaction convertPurchaseRequestToPurchaseEntity(PurchaseRequest purchaseRequest) {
        PurchaseTransaction purchaseTransaction = new PurchaseTransaction();
        purchaseTransaction.setAddress(purchaseRequest.getAddress());
        purchaseTransaction.setProductCode(purchaseRequest.getProductCode());
        purchaseTransaction.setQuantity(purchaseRequest.getQuantity());
        purchaseTransaction.setUnitPrice(purchaseRequest.getUnitPrice());
        purchaseTransaction.setTotalPrice(calculateTotalPrice(purchaseRequest));
        purchaseTransaction.setUser(SessionUtil.getCurrentUser());
        return purchaseTransaction;
    }

    private PurchaseResponse convertPurchaseEntityToPurchaseTransactionResponse(PurchaseTransaction purchaseTransaction) {
        PurchaseResponse purchaseResponse = new PurchaseResponse();
        purchaseResponse.setId(purchaseTransaction.getId());
        purchaseResponse.setAddress(purchaseTransaction.getAddress());
        purchaseResponse.setTotalPrice(purchaseTransaction.getTotalPrice());
        purchaseResponse.setQuantity(purchaseTransaction.getQuantity());
        purchaseResponse.setUnitPrice(purchaseTransaction.getUnitPrice());
        purchaseResponse.setProductCode(purchaseTransaction.getProductCode());
        purchaseResponse.setResultCode(MessageCodeConstant.SUCCESS);
        purchaseResponse.setResultMessage("Success");
        return purchaseResponse;

    }

    private RestApiResponse<PurchaseResponse> makePurchaseTransactionResponse(PurchaseResponse purchaseResponse) {
        RestApiResponse<PurchaseResponse> response = new RestApiResponse<PurchaseResponse>();
        response.setBody(purchaseResponse);
        return response;
    }

    private double calculateTotalPrice(PurchaseRequest purchaseRequest) {
        return purchaseRequest.getQuantity() * purchaseRequest.getUnitPrice();
    }

    private boolean isNotEnoughMoneyToPurchase(PurchaseTransaction purchaseTransaction, User user){
        boolean isNotEnoughMoney = false;
        if(purchaseTransaction.getTotalPrice() > user.getBalance()){
            isNotEnoughMoney = true;
        }
        return isNotEnoughMoney;
    }

    private void updateBalanceOfUser(PurchaseTransaction purchaseTransaction, User user){
        double balanceAfterPurchase = user.getBalance() - purchaseTransaction.getTotalPrice();
        user.setBalance(balanceAfterPurchase);
        userRepository.save(user);
    }

}
