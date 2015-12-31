package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.business.constant.TransactionConstant;
import ch.smartlinksa.intern.business.util.SessionUtil;
import ch.smartlinksa.intern.dao.entity.SellTransaction;
import ch.smartlinksa.intern.dao.entity.User;
import ch.smartlinksa.intern.dao.repository.SellTransactionRepository;
import ch.smartlinksa.intern.interfaces.request.SellRequest;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.SellRespone;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SessionUtil.class)
public class SellService_sellProductTest {

    @Mock
    private SellTransactionRepository sellRepository;

    @InjectMocks
    private SellServiceImpl sellService;

    @Before
    public void initMocks() {
        PowerMockito.mockStatic(SessionUtil.class);
    }

    @Test
    public void shouldGetSellResponseLikeSellRequest(){
        SellRequest sellRequest = prepareSellRequest();
        Mockito.when(sellRepository.save(Matchers.any(SellTransaction.class))).thenReturn(prepareSellTransactionEntity(sellRequest));
        PowerMockito.when(SessionUtil.getCurrentUser()).thenReturn(new User());
        RestApiResponse<SellRespone> response = sellService.sellProduct(sellRequest);
        SellRespone sellRespone = response.getBody();
        Assertions.assertThat(sellRespone.getId()).isNotNull();
        Assertions.assertThat(sellRequest.getAddress()).isEqualToIgnoringCase(sellRespone.getAddress());
        Assertions.assertThat(sellRequest.getQuantity()).isEqualTo(sellRespone.getQuantity());
        Assertions.assertThat(sellRequest.getDescription()).isEqualToIgnoringCase(sellRespone.getDescription());
        Assertions.assertThat(sellRequest.getProductCode()).isEqualToIgnoringCase(sellRespone.getProductCode());
        Assertions.assertThat(sellRequest.getUnitPrice()).isEqualTo(sellRespone.getUnitPrice());
        Assertions.assertThat(TransactionConstant.RESULT_CODE).isEqualTo(sellRespone.getResultCode());
        Assertions.assertThat(TransactionConstant.RESULT_MESSAGE).isEqualTo(sellRespone.getResultMessage());
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

        sellTransaction.setProductCode(sellRequest.getProductCode());
        sellTransaction.setUnitPrice(sellRequest.getUnitPrice());
        sellTransaction.setQuantity(sellRequest.getQuantity());
        sellTransaction.setDescription(sellRequest.getDescription());
        sellTransaction.setAddress(sellRequest.getAddress());
        sellTransaction.setResultCode(TransactionConstant.RESULT_CODE);
        sellTransaction.setResultMessage(TransactionConstant.RESULT_MESSAGE);

        return sellTransaction;
    }

}
