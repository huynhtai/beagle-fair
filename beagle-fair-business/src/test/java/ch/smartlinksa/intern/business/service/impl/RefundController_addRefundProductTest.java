package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.business.util.SessionUtil;
import ch.smartlinksa.intern.dao.entity.RefundTransaction;
import ch.smartlinksa.intern.dao.repository.RefundRepository;
import ch.smartlinksa.intern.interfaces.request.RefundResquest;
import ch.smartlinksa.intern.interfaces.response.RefundResponse;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SessionUtil.class)
public class RefundController_addRefundProductTest {

    @Mock
    private RefundRepository refundRepository;

    @InjectMocks
    private RefundServiceImpl refundServiceImpl;

    @Before
    public void  initMocks(){
        PowerMockito.mockStatic(SessionUtil.class);
    }

    @Test
    public void shouldGetRefundResponseLikeRefundRequest(){
        RefundResquest refundResquest = prepareRefundRequest();
        Mockito.when(refundRepository.save(Matchers.any(RefundTransaction.class))).thenReturn(prepareRefundTransactionEntity(refundResquest));
        RestApiResponse<RefundResponse> response = refundServiceImpl.addRefundProduct(refundResquest);
        RefundResponse refundResponse = response.getBody();
        Assertions.assertThat(refundResquest.getAddress()).isEqualToIgnoringCase(refundResponse.getAddress());
        Assertions.assertThat(refundResquest.getReason()).isEqualToIgnoringCase(refundResponse.getReason());
    }

    private RefundTransaction prepareRefundTransactionEntity(RefundResquest refundResquest) {
       RefundTransaction refundTransaction = new RefundTransaction();

        refundTransaction.setAddress(refundResquest.getAddress());
        refundTransaction.setReason(refundResquest.getReason());
        refundTransaction.setProductCode(refundResquest.getProductCode());

        return refundTransaction;
    }

    private RefundResquest prepareRefundRequest(){
        RefundResquest refundResquest = new RefundResquest();

        refundResquest.setAddress("Quang Nam");
        refundResquest.setReason("Hang kem");

        return refundResquest;
    }
}
