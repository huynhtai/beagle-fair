package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.business.service.ISellService;
import ch.smartlinksa.intern.interfaces.request.SellRequest;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.SellRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SellController {

    @Autowired
    private ISellService sellService;

    @Secured("ROLE_USER")
    @RequestMapping(value = "/sellProduct", method = RequestMethod.POST)
    public RestApiResponse<SellRespone> sellProduct(@RequestBody @Valid SellRequest sellRequest){
        return sellService.sellProduct(sellRequest);
    }

}
