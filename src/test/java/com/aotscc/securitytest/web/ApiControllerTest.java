package com.aotscc.securitytest.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private TestRestTemplate template;

    // Access /helloWorld with good username/password
    @Test
    public void testingGoodAuthentication() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("utils", "utils")
                .getForEntity("/helloWorld", String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    // Access /helloWorld with bad username/password (should fail)
    @Test
    public void testingBadAuthentication() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("xxxx", "xxxx")
                .getForEntity("/helloWorld", String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    // Access /helloWorld with no username/password (should fail if not then there is no authentication for the path)
    @Test
    public void testingNoAuthentication() throws Exception {
        ResponseEntity<String> result = template
                .getForEntity("/helloWorld", String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

}