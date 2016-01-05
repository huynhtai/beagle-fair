package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.business.util.JsonUtil;
import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.request.PurchaseRequest;
import ch.smartlinksa.intern.webservice.config.IntegrationTestConfiguration;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTestConfiguration
@DatabaseSetups({@DatabaseSetup(value = "/data/beagle_fair.xml")})
public class PurchaseProductControllerIT extends LoginBaseITController{

    @Test
    public void shouldNotPurchaseProductSuccessfullyWhenInputEmptyProductCode() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        PurchaseRequest purchaseRequest = preparePurchaseRequest();
        purchaseRequest.setProductCode(null);
        getMockMvc().perform(post("/purchaseProduct")
                    .session(getSession())
                    .content(JsonUtil.convertObjectToJson(purchaseRequest))
                    .headers(headers))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_FIELD_REQUIRED))
                    .andReturn();
    }

    @Test
    public void shouldNotPurchaseProductSuccessfullyWhenInputInvalidProductCode() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        PurchaseRequest purchaseRequest = preparePurchaseRequest();
        purchaseRequest.setProductCode("ASD,45");
        getMockMvc().perform(post("/purchaseProduct")
                    .session(getSession())
                    .content(JsonUtil.convertObjectToJson(purchaseRequest))
                    .headers(headers))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_PRODUCT_CODE_PATTERN))
                    .andReturn();
    }

    @Test
    public void shouldNotPurchaseProductSuccessfullyWhenInputEmptyAddress() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        PurchaseRequest purchaseRequest = preparePurchaseRequest();
        purchaseRequest.setAddress(null);
        getMockMvc().perform(post("/purchaseProduct")
                    .session(getSession())
                    .content(JsonUtil.convertObjectToJson(purchaseRequest))
                    .headers(headers))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_FIELD_REQUIRED))
                    .andReturn();
    }

    @Test
    public void shouldNotPurchaseProductSuccessfullyWhenInputAddressIsMoreThanMaxLength() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        PurchaseRequest purchaseRequest = preparePurchaseRequest();
        purchaseRequest.setAddress("12adfdf asdfsfsdfsfsdfsdfsfsfsfsdfsdfsf"+
                                    "12adfdf asdfsfsdfsfsdfsdfsfsfsfsdfsdfsf"+
                                    "12adfdf asdfsfsdfsfsdfsdfsfsfsfsdfsdfsf"+
                                    "12adfdf asdfsfsdfsfsdfsdfsfsfsfsdfsdfsf"+
                                    "12adfdf asdfsfsdfsfsdfsdfsfsfsfsdfsdfsf"+
                                    "12adfdf asdfsfsdfsfsdfsdfsfsfsfsdfsdfsf");
        getMockMvc().perform(post("/purchaseProduct")
                    .session(getSession())
                    .content(JsonUtil.convertObjectToJson(purchaseRequest))
                    .headers(headers))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_SIZE))
                    .andReturn();
    }

    @Test
    public void shouldNotPurchaseSuccessfullyWhenInputQuantityIsZero() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        PurchaseRequest purchaseRequest = preparePurchaseRequest();
        purchaseRequest.setQuantity(0);
        getMockMvc().perform(post("/purchaseProduct")
                    .session(getSession())
                    .content(JsonUtil.convertObjectToJson(purchaseRequest))
                    .headers(headers))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_VALUE_MUST_GREATER_THAN_ZERO))
                    .andReturn();
    }

    @Test
    public void shouldNotPurchaseSuccessfullyWhenNotEnoughBalance() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        PurchaseRequest purchaseRequest = preparePurchaseRequest();
        purchaseRequest.setUnitPrice(1000000);
        getMockMvc().perform(post("/purchaseProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(purchaseRequest))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_NOT_ENOUGH_TO_PURCHASE))
                .andReturn();
    }

    @Test
    public void shouldPurchaseSuccessfully() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        PurchaseRequest purchaseRequest = preparePurchaseRequest();
        getMockMvc().perform(post("/purchaseProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(purchaseRequest))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.SUCCESS))
                .andReturn();
    }

    private PurchaseRequest preparePurchaseRequest(){
        String json = "{ \n"+
                        "\"productCode\": \"123AADFGDDSG\",\n" +
                        "    \"address\": \"23 Nguyen \",\n" +
                        "    \"quantity\":\"2\",\n" +
                        "    \"unitPrice\": \"3\"\n"+
                        "}";
        return JsonUtil.convertJsonToObject(json, PurchaseRequest.class);
    }
}
