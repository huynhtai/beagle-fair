package ch.smartlinksa.intern.business.service;

import ch.smartlinksa.intern.interfaces.request.PurchaseRequest;
import ch.smartlinksa.intern.interfaces.response.PurchaseResponse;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.TransactionResponse;

public interface IPurchaseService {

    RestApiResponse<PurchaseResponse> addNewPurchase(PurchaseRequest purchaseRequest);
}
