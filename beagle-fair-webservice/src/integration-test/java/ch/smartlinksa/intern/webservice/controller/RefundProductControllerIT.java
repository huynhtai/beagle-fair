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
    public void shouldNotAddRefundProductSuccessfullyWhenInputEmptyAddress() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        RefundResquest refundResquest = prepareUserRequest();
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
    public void shouldNotAddRefundProductSuccessfullyWhenInputSizeAddressLessThan5() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        RefundResquest refundResquest = prepareUserRequest();
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
    public void shouldNotAddRefundProductSuccessfullWhenInputEmptyReason() throws Exception{
        HttpHeaders headers = buildHttpHeaders();
        RefundResquest refundResquest = prepareUserRequest();
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
    public void shouldNotAddRefundProductSuccessfullWhenInputSizeReasonLessThan5() throws Exception{
        HttpHeaders headers = buildHttpHeaders();
        RefundResquest refundResquest = prepareUserRequest();
        refundResquest.setReason("Huhh");
        getMockMvc().perform(post("/refundProduct")
                .session(getSession())
                .content(JsonUtil.convertObjectToJson(refundResquest))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_SIZE))
                .andReturn();
    }

    private RefundResquest prepareUserRequest() {
        String json = "{\n" +
                "  \"productCode\": \"3222Aa3234r3\",\n" +
                "  \"quantity\": \"120\",\n" +
                "  \"address\": \"Quang Nam\",\n" +
                "  \"reason\": \"Hu hong\",\n" +
                "  \"unitPrice\": \"90\"\n" +
                "}";
        return JsonUtil.convertJsonToObject(json, RefundResquest.class);
    }
}
