package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.business.util.SessionUtil;
import ch.smartlinksa.intern.dao.repository.PurchaseTransactionRepository;
import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.request.PurchaseRequest;
import ch.smartlinksa.intern.interfaces.response.PurchaseResponse;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SessionUtil.class)
public class PurchaseService_purchaseProduct {

    @Mock
    private PurchaseTransactionRepository purchaseTransactionRepository;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @Before
    public void initMocks() {
        PowerMockito.mockStatic(SessionUtil.class);
    }

    @Test
    public void shouldGetTotalPriceEqual40WhenUnitPriceIs5AndQuantityIs8() {

        PurchaseRequest purchaseRequest = preparePurchaseRequest();
        RestApiResponse<PurchaseResponse> response = purchaseService.addNewPurchase(purchaseRequest);
        PurchaseResponse purchaseResponse = response.getBody();
        Assertions.assertThat(purchaseResponse.getTotalPrice()).isEqualTo(40);
    }

    @Test
    public void shouldGetResponseWithIdTransactionWhenPurchaseProduct() {

        PurchaseRequest purchaseRequest = preparePurchaseRequest();
        RestApiResponse<PurchaseResponse> response = purchaseService.addNewPurchase(purchaseRequest);
        PurchaseResponse purchaseResponse = response.getBody();
        Assertions.assertThat(purchaseResponse.getProductCode()).isEqualTo(purchaseRequest.getProductCode());
        Assertions.assertThat(purchaseResponse.getUnitPrice()).isEqualTo(purchaseRequest.getUnitPrice());
        Assertions.assertThat(purchaseResponse.getQuantity()).isEqualTo(purchaseRequest.getQuantity());
        Assertions.assertThat(purchaseResponse.getAddress()).isEqualTo(purchaseRequest.getAddress());
        Assertions.assertThat(purchaseResponse.getResultCode()).isEqualTo(MessageCodeConstant.SUCCESS);
        Assertions.assertThat(purchaseResponse.getId()).isNotEmpty();
    }

    private PurchaseRequest preparePurchaseRequest() {
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        purchaseRequest.setProductCode("1234DDGGDSAL");
        purchaseRequest.setQuantity(8);
        purchaseRequest.setUnitPrice(5);
        purchaseRequest.setAddress("123");
        return purchaseRequest;
    }

}
