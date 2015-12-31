package ch.smartlinksa.intern.webservice.controller;


import ch.smartlinksa.intern.business.service.IRefundService;
import ch.smartlinksa.intern.interfaces.request.RefundResquest;
import ch.smartlinksa.intern.interfaces.response.RefundResponse;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RefundController {

    @Autowired
    IRefundService iRefundService;

    @RequestMapping(value = "/refundProduct", method = RequestMethod.POST)
    public RestApiResponse<RefundResponse> refundProduct(@RequestBody @Valid RefundResquest refundResquest){
        return iRefundService.addRefundProduct(refundResquest);
    }

}
