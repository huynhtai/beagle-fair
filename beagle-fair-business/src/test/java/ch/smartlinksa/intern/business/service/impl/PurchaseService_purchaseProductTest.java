package ch.smartlinksa.intern.business.service.impl;

import ch.smartlinksa.intern.business.exception.UserNotEnoughMoneyToPurchaseException;
import ch.smartlinksa.intern.business.util.SessionUtil;
import ch.smartlinksa.intern.dao.entity.PurchaseTransaction;
import ch.smartlinksa.intern.dao.entity.User;
import ch.smartlinksa.intern.dao.repository.PurchaseTransactionRepository;
import ch.smartlinksa.intern.dao.repository.UserRepository;
import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.request.PurchaseRequest;
import ch.smartlinksa.intern.interfaces.response.PurchaseResponse;
import ch.smartlinksa.intern.interfaces.response.RestApiResponse;
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
public class PurchaseService_purchaseProductTest {

    @Mock
    private PurchaseTransactionRepository purchaseTransactionRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @Before
    public void initMocks() {
        PowerMockito.mockStatic(SessionUtil.class);
    }

    @Test(expected = UserNotEnoughMoneyToPurchaseException.class)
    public void shouldNotPurchaseSuccessfullyWhenBalanceIsNotEnough() {

        PurchaseRequest purchaseRequest = preparePurchaseRequest();
        purchaseRequest.setQuantity(100);
        purchaseRequest.setUnitPrice(1000);
        PowerMockito.when(SessionUtil.getCurrentUser()).thenReturn(prepareUser());
        RestApiResponse<PurchaseResponse> response = purchaseService.addNewPurchase(purchaseRequest);
    }

    @Test
    public void shouldGetTotalPriceIs40WhenQuantityIs8AndUnitPriceIs5() {
        PurchaseRequest purchaseRequest = preparePurchaseRequest();
        purchaseRequest.setQuantity(8);
        purchaseRequest.setUnitPrice(5);
        PowerMockito.when(SessionUtil.getCurrentUser()).thenReturn(prepareUser());
        Mockito.when(purchaseTransactionRepository.save(Matchers.any(PurchaseTransaction.class)))
                .thenReturn(preparePurchaseTransactionEntity(purchaseRequest));
        RestApiResponse<PurchaseResponse> response = purchaseService.addNewPurchase(purchaseRequest);
        PurchaseResponse purchaseResponse = response.getBody();
        Assertions.assertThat(purchaseResponse.getTotalPrice()).isEqualTo(40);
    }

    @Test
    public void shouldGetResponseWithIdTransactionWhenPurchaseProduct() {

        PurchaseRequest purchaseRequest = preparePurchaseRequest();
        PowerMockito.when(SessionUtil.getCurrentUser()).thenReturn(prepareUser());
        Mockito.when(purchaseTransactionRepository.save(Matchers.any(PurchaseTransaction.class)))
                .thenReturn(preparePurchaseTransactionEntity(purchaseRequest));
        Mockito.when(userRepository.save(Matchers.any(User.class))).thenReturn(new User());
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
    private PurchaseTransaction preparePurchaseTransactionEntity(PurchaseRequest purchaseRequest) {
        PurchaseTransaction purchaseTransaction = new PurchaseTransaction();
        purchaseTransaction.setProductCode(purchaseRequest.getProductCode());
        purchaseTransaction.setQuantity(purchaseRequest.getQuantity());
        purchaseTransaction.setUnitPrice(purchaseRequest.getUnitPrice());
        purchaseTransaction.setTotalPrice(40);
        purchaseTransaction.setAddress(purchaseRequest.getAddress());
        return purchaseTransaction;
    }

    private User prepareUser(){
        User user = new User();
        user.setUserName("abc");
        user.setBalance(100);
        return user;
    }
}
