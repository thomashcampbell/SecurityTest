package com.aotscc.securitytest.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ActuatorControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private TestRestTemplate template;


    // Access /actuator/health without a password
    @Test
    public void testingNoAuthenticationHealthActuator() throws Exception {
        ResponseEntity<String> result = template.getForEntity("/actuator/health", String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    // Access /actuator/info without a password
    @Test
    public void testingNoAuthenticationInfoActuator() throws Exception {
        ResponseEntity<String> result = template.getForEntity("/actuator/info", String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    // Access /actuator/env with a password
    @Test
    public void testingGoodAuthenticationEnvActuator() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("utils", "utils")
                .getForEntity("/actuator/env", String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    // Access /actuator/env without a password (which fails)
    @Test
    public void testingNoAuthenticationEnvActuator() throws Exception {
        ResponseEntity<String> result = template.getForEntity("/actuator/env", String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    // Access /actuator/env with a bad password (which fails)
    @Test
    public void testingBadAuthenticationEnvActuator() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("xxxx", "xxxx")
                .getForEntity("/actuator/env", String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

}