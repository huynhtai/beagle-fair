package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.interfaces.request.PurchaseTransactionRequest;
import ch.smartlinksa.intern.interfaces.request.TransactionRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    @RequestMapping(value = "/purchase")
    public TransactionRequest purchaseProduct(@RequestBody PurchaseTransactionRequest purchaseTransactionRequest){
        System.out.println("adf");
        return purchaseTransactionRequest;
    }
}
