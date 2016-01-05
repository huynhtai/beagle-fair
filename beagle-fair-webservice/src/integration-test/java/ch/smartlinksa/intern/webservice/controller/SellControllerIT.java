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
    public void shouldNotSellProductSuccessfullyWhenInputValid() throws Exception {
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
    public void shouldNotSellProductSuccessfullyWhenInputInvalidProduceCode() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        SellRequest request = prepareSellRequest();
        request.setProductCode("12345");
        getMockMvc().perform(post("/sellProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_PRODUCT_CODE_PATTERN))
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
