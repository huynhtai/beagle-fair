package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.business.service.IPurchaseService;
import ch.smartlinksa.intern.business.service.impl.PurchaseServiceImpl;
import ch.smartlinksa.intern.interfaces.request.PurchaseRequest;
import ch.smartlinksa.intern.interfaces.request.TransactionRequest;
import ch.smartlinksa.intern.interfaces.response.PurchaseResponse;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PurchaseController {

    @Autowired
    IPurchaseService purchaseService;

    @RequestMapping(value = "/purchaseProduct", method = RequestMethod.POST)
    public RestApiResponse<PurchaseResponse> purchaseProduct(@RequestBody @Valid PurchaseRequest purchaseRequest){
        return purchaseService.addNewPurchase(purchaseRequest);
    }
}
