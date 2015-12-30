package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.dao.entity.Purchase;
import ch.smartlinksa.intern.interfaces.request.PurchaseRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    @RequestMapping(value = "/purchase")
    public Purchase purchaseProduct(@RequestBody PurchaseRequest purchaseRequest){

        return null;
    }

}
