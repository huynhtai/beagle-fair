package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.business.util.JsonUtil;
import ch.smartlinksa.intern.business.util.NameGenerator;
import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.interfaces.request.UserRequest;
import ch.smartlinksa.intern.webservice.config.IntegrationTestConfiguration;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTestConfiguration
@DatabaseSetups(value = {@DatabaseSetup(value = "/data/beagle_fair.xml")})
public class AddUserControllerIT extends BaseITController {

    @Test
    public void shouldNotAddUserSuccessfullyWhenInputEmptyUserName() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        UserRequest request = prepareUserRequest();
        request.setUserName(null);
        getMockMvc().perform(post("/addUser")
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_FIELD_REQUIRED))
                .andReturn();
    }

    @Test
    public void shouldNotAddUserSuccessfullyWhenInputInvalidPassword() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        UserRequest request = prepareUserRequest();
        request.setUserName(NameGenerator.generateName());
        request.setPassword("123232343243242");
        getMockMvc().perform(post("/addUser")
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_PATTERN_PASSWORD))
                .andReturn();
    }

    @Test
    public void shouldNotAddUserSuccessfullyWhenInputExistedUserName() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        UserRequest request = prepareUserRequest();
        getMockMvc().perform(post("/addUser")
                .content(JsonUtil.convertObjectToJson(request))
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.ERROR_EXIST_USERNAME))
                .andReturn();
    }

    private UserRequest prepareUserRequest() {
        String json = "{\n" +
                "  \"userName\": \"tranductrinh\",\n" +
                "  \"password\": \"123456aA\",\n" +
                "  \"firstName\": \"Trinh\",\n" +
                "  \"lastName\": \"Tran Duc\",\n" +
                "  \"birthday\": \"09/09/1991\",\n" +
                "  \"gender\": \"0\",\n" +
                "  \"phoneNumber\": \"+849359103097\",\n" +
                "  \"address\": \"Da Nang\"\n" +
                "}";
        return JsonUtil.convertJsonToObject(json, UserRequest.class);
    }

}
