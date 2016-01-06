package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.business.util.JsonUtil;
import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.request.SellRequest;
import ch.smartlinksa.intern.webservice.config.IntegrationTestConfiguration;
import com.github.springtestdbunit.annotation.*;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTestConfiguration
@DatabaseSetups(value = {@DatabaseSetup(value = "/data/beagle_fair.xml")})
public class SellControllerIT extends LoginBaseITController{

    @Test
    public void shouldNotSellProductSuccessfully() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        SellRequest request = prepareSellRequest();
        getMockMvc().perform(post("/sellProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.SUCCESS))
                .andReturn();
    }

    @Test
    public void shouldNotSellProductSuccessfullyWhenInputEmptyProduceCode() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        SellRequest request = prepareSellRequest();
        request.setProductCode(null);
        getMockMvc().perform(post("/sellProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_FIELD_REQUIRED))
                .andReturn();
    }

    @Test
    public void shouldNotSellProductSuccessfullyWhenInputInvalidProduceCode() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        SellRequest request = prepareSellRequest();
        request.setProductCode("12345");
        getMockMvc().perform(post("/sellProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_PATTERN_PRODUCT_CODE))
                .andReturn();
    }

    @Test
    public void shouldNotSellProductSuccessfullyWhenQuantityNotGreaterThanZero() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        SellRequest request = prepareSellRequest();
        request.setQuantity(-3);
        getMockMvc().perform(post("/sellProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_VALUE_MUST_GREATER_THAN_ZERO))
                .andReturn();
    }

    @Test
    public void shouldNotSellProductSuccessfullyWhenUnitPriceNotGreaterThanZero() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        SellRequest request = prepareSellRequest();
        request.setUnitPrice(-3);
        getMockMvc().perform(post("/sellProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_VALUE_MUST_GREATER_THAN_ZERO))
                .andReturn();
    }

    @Test
    public void shouldNotSellProductSuccessfullyWhenInputEmptyDescription() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        SellRequest request = prepareSellRequest();
        request.setDescription(null);
        getMockMvc().perform(post("/sellProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_FIELD_REQUIRED))
                .andReturn();
    }

    @Test
    public void shouldNotSellProductSuccessfullyWhenDescriptionIsMoreThanMaxLength() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        SellRequest request = prepareSellRequest();
        request.setDescription("123456789asgdkasher"+"123456789asgdkasher"
                        +"123456789asgdkasher"
                        +"123456789asgdkasher"
                        +"123456789asgdkasher"
                        +"123456789asgdkasher"
                        +"123456789asgdkasher"
                        +"123456789asgdkasher"
                        +"123456789asgdkasher"
                        +"123456789asgdkasher"
                        +"123456789asgdkasherkjhk"
        );
        getMockMvc().perform(post("/sellProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_SIZE))
                .andReturn();
    }

    @Test
    public void shouldNotSellProductSuccessfullyWhenDescriptionIsBlank() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        SellRequest request = prepareSellRequest();
        request.setDescription("        ");
        getMockMvc().perform(post("/sellProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_FIELD_REQUIRED))
                .andReturn();
    }

    @Test
    public void shouldNotSellProductSuccessfullyWhenDescriptionIsLessThanMinLength() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        SellRequest request = prepareSellRequest();
        request.setDescription("1");
        getMockMvc().perform(post("/sellProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_SIZE))
                .andReturn();
    }

    @Test
    public void shouldNotSellProductSuccessfullyWhenInputEmptyAddress() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        SellRequest request = prepareSellRequest();
        request.setAddress(null);
        getMockMvc().perform(post("/sellProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_FIELD_REQUIRED))
                .andReturn();
    }

    @Test
    public void shouldNotPurchaseProductSuccessfullyWhenAddressIsMoreThanMaxLength() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        SellRequest purchaseRequest = prepareSellRequest();
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
    public void shouldNotSellProductSuccessfullyWhenAddressIsBlank() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        SellRequest request = prepareSellRequest();
        request.setAddress("        ");
        getMockMvc().perform(post("/sellProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_FIELD_REQUIRED))
                .andReturn();
    }

    @Test
    public void shouldNotSellProductSuccessfullyWhenAddressIsLessThanMinLength() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        SellRequest request = prepareSellRequest();
        request.setAddress("1");
        getMockMvc().perform(post("/sellProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_SIZE))
                .andReturn();
    }

    private SellRequest prepareSellRequest(){
        SellRequest sellRequest = new SellRequest();

        sellRequest.setProductCode("KA2345KA2345");
        sellRequest.setQuantity(2);
        sellRequest.setUnitPrice(4.7);
        sellRequest.setDescription("New 100%");
        sellRequest.setAddress("Da Nang");

        return sellRequest;
    }
}
