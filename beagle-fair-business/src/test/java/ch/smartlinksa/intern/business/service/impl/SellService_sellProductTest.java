package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.business.util.SessionUtil;
import ch.smartlinksa.intern.dao.entity.SellTransaction;
import ch.smartlinksa.intern.dao.repository.SellTransactionRepository;
import ch.smartlinksa.intern.interfaces.constant.SellTransactionConstant;
import ch.smartlinksa.intern.interfaces.request.SellRequest;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.SellRespone;
import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SellService_sellProductTest {

    @Mock
    private SellTransactionRepository sellRepository;

    @InjectMocks
    private SellServiceImpl sellService;

    @Test
    public void shouldGetSellResponseLikeSellRequest(){
//        SellRequest sellRequest = prepareSellRequest();
//        Mockito.when(sellRepository.save(Matchers.any(SellTransaction.class))).thenReturn(prepareSellTransactionEntity(sellRequest));
//        Mockito.when(SessionUtil.getCurrentUserId()).thenReturn("aa008292-4efb-4ba0-b408-d14078412dbd");
//        RestApiResponse<SellRespone> response = sellService.sellProduct(sellRequest);
//        SellRespone sellRespone = response.getBody();
//        Assertions.assertThat(sellRespone.getId()).isNotNull();
//        Assertions.assertThat(sellRequest.getAddress()).isEqualToIgnoringCase(sellRespone.getAddress());
//        Assertions.assertThat(sellRequest.getQuantity()).isEqualTo(sellRespone.getQuantity());
//        Assertions.assertThat(sellRequest.getDescription()).isEqualToIgnoringCase(sellRespone.getDescription());
//        Assertions.assertThat(sellRequest.getProductCode()).isEqualToIgnoringCase(sellRespone.getProductCode());
//        Assertions.assertThat(sellRequest.getUnitPrice()).isEqualTo(sellRespone.getUnitPrice());
//        Assertions.assertThat(SellTransactionConstant.SELL_TRANSACTION_RESULT_CODE).isEqualTo(sellRespone.getResultCode());
//        Assertions.assertThat(SellTransactionConstant.SELL_TRANSACTION_RESULT_MESSAGE).isEqualTo(sellRespone.getResultMessage());
    }

    private SellRequest prepareSellRequest(){
        SellRequest sellRequest = new SellRequest();

        sellRequest.setUnitPrice(40000);
        sellRequest.setAddress("Da Nang");
        sellRequest.setProductCode("Ah798");
        sellRequest.setDescription("100% zin");
        sellRequest.setQuantity(7);

        return sellRequest;
    }

    private SellTransaction prepareSellTransactionEntity(SellRequest sellRequest){
        SellTransaction sellTransaction = new SellTransaction();

//        Mockito.when(SessionUtil.getCurrentUserId()).thenReturn("aa008292-4efb-4ba0-b408-d14078412dbd");
        sellTransaction.setUserId(SessionUtil.getCurrentUserId());
        sellTransaction.setProductCode(sellRequest.getProductCode());
        sellTransaction.setUnitPrice(sellRequest.getUnitPrice());
        sellTransaction.setQuantity(sellRequest.getQuantity());
        sellTransaction.setDescription(sellRequest.getDescription());
        sellTransaction.setAddress(sellRequest.getAddress());
        sellTransaction.setResultCode(SellTransactionConstant.SELL_TRANSACTION_RESULT_CODE);
        sellTransaction.setResultMessage(SellTransactionConstant.SELL_TRANSACTION_RESULT_MESSAGE);

        return sellTransaction;
    }

}
