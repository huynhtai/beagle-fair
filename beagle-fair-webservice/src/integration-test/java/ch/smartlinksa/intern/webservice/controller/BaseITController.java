package ch.smartlinksa.intern.webservice.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
public class BaseITController {

    @Autowired
    private WebApplicationContext webCtx;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    @Before
    public void initRules() {
        this.mockMvc = webAppContextSetup(getWebCtx())
                .addFilters(springSecurityFilterChain)
                .build();
    }

    public WebApplicationContext getWebCtx() {
        return webCtx;
    }

    public void setWebCtx(WebApplicationContext webCtx) {
        this.webCtx = webCtx;
    }

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    protected HttpHeaders buildHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json;charset=UTF-8");
        headers.add("Content-Type", "application/json;charset=UTF-8");
        return headers;
    }
}
