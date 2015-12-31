package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.dao.constant.Gender;
import ch.smartlinksa.intern.dao.entity.RefundTransaction;
import ch.smartlinksa.intern.dao.repository.RefundRepository;
import ch.smartlinksa.intern.interfaces.request.RefundResquest;
import ch.smartlinksa.intern.interfaces.response.RefundResponse;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import ch.smartlinksa.intern.interfaces.response.UserResponse;
import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RefundController_addRefundProductTest {

    @Mock
    private RefundRepository refundRepository;

    @InjectMocks
    private RefundProductServiceImpl refundProductServiceImpl;

    @Test
    public void shouldGetRefundResponseLikeRefundRequest(){
        RefundResquest refundResquest = prepareRefundRequest();
        Mockito.when(refundRepository.save(Matchers.any(RefundTransaction.class))).thenReturn(prepareRefundTransactionEntity(refundResquest));
        RestApiResponse<RefundResponse> response = refundProductServiceImpl.addRefundProduct(refundResquest);
        RefundResponse refundResponse = response.getBody();
        Assertions.assertThat(refundResquest.getAddress()).isEqualToIgnoringCase(refundResponse.getAddress());
        Assertions.assertThat(refundResquest.getReason()).isEqualToIgnoringCase(refundResponse.getReason());
        Assertions.assertThat(refundResquest.getTotalPrice()).isEqualTo(refundResponse.getTotalPrice());
    }

    private RefundTransaction prepareRefundTransactionEntity(RefundResquest refundResquest) {
       RefundTransaction refundTransaction = new RefundTransaction();

        refundTransaction.setAddress(refundResquest.getAddress());
        refundTransaction.setReason(refundResquest.getReason());
        refundTransaction.setTotalPrice(refundResquest.getTotalPrice());
        refundTransaction.setProductCode(refundResquest.getProductCode());

        return refundTransaction;
    }

    private RefundResquest prepareRefundRequest(){
        RefundResquest refundResquest = new RefundResquest();

        refundResquest.setAddress("Quang Nam");
        refundResquest.setReason("Hang kem");
        refundResquest.setTotalPrice(1000);
        refundResquest.setProductCode("1233444");

        return refundResquest;
    }
}
