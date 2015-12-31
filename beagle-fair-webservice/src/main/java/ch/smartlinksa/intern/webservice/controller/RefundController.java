package ch.smartlinksa.intern.webservice.controller;


import ch.smartlinksa.intern.business.service.IRefundProductService;
import ch.smartlinksa.intern.dao.entity.RefundTransaction;
import ch.smartlinksa.intern.dao.entity.User;
import ch.smartlinksa.intern.dao.repository.UserRepository;
import ch.smartlinksa.intern.interfaces.request.RefundResquest;
import ch.smartlinksa.intern.interfaces.response.RefundResponse;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RefundController {

    @Autowired
    IRefundProductService iRefundProductService;

    @RequestMapping(value = "/refundProduct", method = RequestMethod.POST)
    public RestApiResponse<RefundResponse> refundProduct(@RequestBody @Valid RefundResquest refundResquest){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
//        List<User> users = userRepository.findByUserName(userName);
//        refundResquest.setId(users.get(0).getId());
        return iRefundProductService.addRefundProduct(refundResquest);
    }

}
