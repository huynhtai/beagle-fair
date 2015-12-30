package ch.smartlinksa.intern.webservice.controller;

import ch.smartlinksa.intern.interfaces.constant.MessageCodeConstant;
import ch.smartlinksa.intern.webservice.config.IntegrationTestConfiguration;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTestConfiguration
@DatabaseSetups(value = {@DatabaseSetup("/beagle_fair.xml")})
public class ListUserControllerIT extends LoginBaseITController {

    @Test
    public void shouldListUserSuccessfully() throws Exception {
        HttpHeaders headers = buildHttpHeaders();
        MvcResult result = getMockMvc().perform(get("/listUser")
                .session(getSession())
                .headers(headers))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headers.resultCode").value(MessageCodeConstant.SUCCESS))
                .andReturn();
    }

}
