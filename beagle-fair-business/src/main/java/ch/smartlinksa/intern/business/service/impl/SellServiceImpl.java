package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.business.service.ISellService;
import ch.smartlinksa.intern.business.util.SessionUtil;
import ch.smartlinksa.intern.dao.entity.SellTransaction;
import ch.smartlinksa.intern.dao.repository.SellTransactionRepository;
import ch.smartlinksa.intern.interfaces.constant.SellTransactionConstant;
import ch.smartlinksa.intern.interfaces.request.SellRequest;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.SellRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellServiceImpl implements ISellService{

    @Autowired
    SellTransactionRepository sellRepository;

    public RestApiResponse<SellRespone> sellProduct(SellRequest sellRequest){
        SellTransaction sellTransaction = convertSellRequestToSellTransaction(sellRequest);
        sellTransaction = sellRepository.save(sellTransaction);
        return  sendResponseObjectWhenAddSellTransactionSuccessful(sellTransaction);
    }

    private RestApiResponse<SellRespone> sendResponseObjectWhenAddSellTransactionSuccessful(SellTransaction sellTransaction){
        RestApiResponse<SellRespone> sellRestApiResponse = new RestApiResponse<SellRespone>();
        SellRespone sellRespone = convertSellTransactionEntityToSellResponse(sellTransaction);
        sellRestApiResponse.setBody(sellRespone);
        return sellRestApiResponse;
    }

    private SellTransaction convertSellRequestToSellTransaction(SellRequest sellRequest){
        SellTransaction sellTransaction = new SellTransaction();

        sellTransaction.setUser(SessionUtil.getCurrentUser());
        sellTransaction.setProductCode(sellRequest.getProductCode());
        sellTransaction.setUnitPrice(sellRequest.getUnitPrice());
        sellTransaction.setQuantity(sellRequest.getQuantity());
        sellTransaction.setDescription(sellRequest.getDescription());
        sellTransaction.setAddress(sellRequest.getAddress());

        sellTransaction.setResultCode(SellTransactionConstant.SELL_TRANSACTION_RESULT_CODE);
        sellTransaction.setResultMessage(SellTransactionConstant.SELL_TRANSACTION_RESULT_MESSAGE);

        return sellTransaction;
    }

    private SellRespone convertSellTransactionEntityToSellResponse(SellTransaction sellTransaction){
        SellRespone sellRespone = new SellRespone();

        sellRespone.setId(sellTransaction.getId());
        sellRespone.setProductCode(sellTransaction.getProductCode());
        sellRespone.setQuantity(sellTransaction.getQuantity());
        sellRespone.setUnitPrice(sellTransaction.getUnitPrice());
        sellRespone.setDescription(sellTransaction.getDescription());
        sellRespone.setAddress(sellTransaction.getAddress());
        sellRespone.setResultCode(sellTransaction.getResultCode());
        sellRespone.setResultMessage(sellTransaction.getResultMessage());

        return sellRespone;
    }
}
