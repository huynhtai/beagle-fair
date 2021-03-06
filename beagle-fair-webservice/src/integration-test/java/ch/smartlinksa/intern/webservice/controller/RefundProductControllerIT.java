package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.business.util.JsonUtil;
import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.request.RefundResquest;
import ch.smartlinksa.intern.webservice.config.IntegrationTestConfiguration;
import com.github.springtestdbunit.annotation.*;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTestConfiguration
@DatabaseSetups(value = {@DatabaseSetup(value = "/data/beagle_fair.xml")})
@DatabaseTearDowns(value = {@DatabaseTearDown(value = "/data/beagle_fair.xml", type = DatabaseOperation.DELETE_ALL)})
public class RefundProductControllerIT extends LoginBaseITController{

    @Test
    public void shouldNotAddRefundProductSuccessfullWhenInputEmptyProductCode() throws Exception{
            HttpHeaders headers = buildHttpHeaders();
            RefundResquest refundResquest = prepareRefundRequest();
            refundResquest.setProductCode(null);
            getMockMvc().perform(post("/refundProduct")
                    .session(getSession())
                    .content(JsonUtil.convertObjectToJson(refundResquest))
                    .headers(headers))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_FIELD_REQUIRED))
                    .andReturn();
    }

    @Test
    public void shouldNotAddRefundProductSuccessfullWhenInputErrorProductCodePattern() throws Exception{
        HttpHeaders headers = buildHttpHeaders();
        RefundResquest refundResquest = prepareRefundRequest();
        refundResquest.setProductCode("aQ1222");
        getMockMvc().perform(post("/refundProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(refundResquest))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_PATTERN_PRODUCT_CODE))
                .andReturn();
    }

    @Test
    public void shouldNotAddRefundProductSuccessfullyWhenInputEmptyAddress() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        RefundResquest refundResquest = prepareRefundRequest();
        refundResquest.setAddress(null);
        getMockMvc().perform(post("/refundProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(refundResquest))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_FIELD_REQUIRED))
                .andReturn();
    }

    @Test
    public void shouldNotAddRefundProductSuccessfullyWhenInputSizeAddressLessThanMinLenght() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        RefundResquest refundResquest = prepareRefundRequest();
        refundResquest.setAddress("Quan");
        getMockMvc().perform(post("/refundProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(refundResquest))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_SIZE))
                .andReturn();
    }

    @Test
    public void shouldNotAddRefundProductSuccessfullyWhenInputSizeAddressMoreThanMaxLength() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        RefundResquest refundResquest = prepareRefundRequest();
        refundResquest.setAddress("QuangNamAaQuangNamAaQuangNamAa" +
                                "QuangNamAaQuangNamAaQuangNamAa" +
                                "QuangNamAaQuangNamAaQuangNamAa" +
                                "QuangNamAaQuangNamAaQuangNamAa");
        getMockMvc().perform(post("/refundProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(refundResquest))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_SIZE))
                .andReturn();
    }

    @Test
    public void shouldNotAddRefundProductSuccessfullWhenInputEmptyReason() throws Exception{
        HttpHeaders headers = buildHttpHeaders();
        RefundResquest refundResquest = prepareRefundRequest();
        refundResquest.setReason(null);
        getMockMvc().perform(post("/refundProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(refundResquest))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_FIELD_REQUIRED))
                .andReturn();
    }

    @Test
    public void shouldNotAddRefundProductSuccessfullWhenInputSizeReasonLessThanMinLength() throws Exception{
        HttpHeaders headers = buildHttpHeaders();
        RefundResquest refundResquest = prepareRefundRequest();
        refundResquest.setReason("Huhh");
        getMockMvc().perform(post("/refundProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(refundResquest))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_SIZE))
                .andReturn();
    }

    @Test
    public void shouldNotAddRefundProductSuccessfullWhenInputSizeReasonMoreThanMaxLength() throws Exception{
        HttpHeaders headers = buildHttpHeaders();
        RefundResquest refundResquest = prepareRefundRequest();
        refundResquest.setReason("LoiKyThuatLoiKyThuatLoiKyThuat" +
                                "LoiKyThuatLoiKyThuatLoiKyThuat" +
                                "LoiKyThuatLoiKyThuatLoiKyThuat" +
                                "LoiKyThuatLoiKyThuatLoiKyThuat");
        getMockMvc().perform(post("/refundProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(refundResquest))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_SIZE))
                .andReturn();
    }

    @Test
    public void shouldNotAddRefundProductSuccessfullWhenInputQuantitysZero() throws Exception{
        HttpHeaders headers = buildHttpHeaders();
        RefundResquest refundResquest = prepareRefundRequest();
        refundResquest.setQuantity(0);
        getMockMvc().perform(post("/refundProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(refundResquest))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_VALUE_MUST_GREATER_THAN_ZERO))
                .andReturn();
    }

    @Test
    public void shouldNotAddRefundProductSuccessfullWhenInputUnitPriceIsZero() throws Exception{
        HttpHeaders headers = buildHttpHeaders();
        RefundResquest refundResquest = prepareRefundRequest();
        refundResquest.setUnitPrice(0);
        getMockMvc().perform(post("/refundProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(refundResquest))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_VALUE_MUST_GREATER_THAN_ZERO))
                .andReturn();
    }

    @Test
    public void shouldRefundSuccessfully() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        RefundResquest refundResquest = prepareRefundRequest();
        getMockMvc().perform(post("/refundProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(refundResquest))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.SUCCESS))
                .andReturn();
    }

    private RefundResquest prepareRefundRequest() {
        String json = "{\n" +
                "  \"productCode\": \"12AQ1234Aa78\",\n" +
                "  \"address\": \"Quang Nam\",\n" +
                "  \"reason\": \"Loi ky thuat\",\n" +
                "  \"quantity\": \"120\",\n" +
                "  \"unitPrice\": \"90\"\n" +
                "}";
        return JsonUtil.convertJsonToObject(json, RefundResquest.class);
    }
}
