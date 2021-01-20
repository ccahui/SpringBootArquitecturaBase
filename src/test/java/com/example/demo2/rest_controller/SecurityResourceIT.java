package com.example.demo2.rest_controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demo2.ApiTestConfig;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@ApiTestConfig
class SecurityResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    //@Test
    void testAllUserOK() {
        this.webTestClient
                .mutate().filter(basicAuthentication("user", "123456")).build()
                .get().uri(SecurityResource.SECURITY + SecurityResource.ALL)
                .exchange()
                .expectStatus().isOk();
    }

    //@Test
    void testUserOK() {
        this.webTestClient
                .mutate().filter(basicAuthentication("user", "123456")).build()
                .get().uri(SecurityResource.SECURITY + SecurityResource.USER)
                .exchange()
                .expectStatus().isOk();
    }

    //@Test
    void testUserOtherUserOK() {
        this.webTestClient
                .mutate().filter(basicAuthentication("manager", "123456")).build()
                .get().uri(SecurityResource.SECURITY + SecurityResource.USER)
                .exchange()
                .expectStatus().isOk();
    }

    //@Test
    void testManagerOK() {
        this.webTestClient
                .mutate().filter(basicAuthentication("manager", "123456")).build()
                .get().uri(SecurityResource.SECURITY + SecurityResource.MANAGER)
                .exchange()
                .expectStatus().isOk();
    }

    //@Test
    void testAdminOK() {
        webTestClient
                .mutate().filter(basicAuthentication("admin", "123456")).build()
                .get().uri(SecurityResource.SECURITY + SecurityResource.ADMIN)
                .exchange()
                .expectStatus().isOk();
    }

    //@Test
    void testAdminUnauthorizedError() {
       this.webTestClient
                .get().uri(SecurityResource.SECURITY + SecurityResource.ADMIN)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    //@Test
    void testAdminUnauthorizedPasswordError() {
        this.webTestClient
                .mutate().filter(basicAuthentication("admin", "kk")).build()
                .get().uri(SecurityResource.SECURITY + SecurityResource.ADMIN)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    //@Test
    void testAdminUnauthorizedUserError() {
        this.webTestClient
                .mutate().filter(basicAuthentication("kk", "kk")).build()
                .get().uri(SecurityResource.SECURITY + SecurityResource.ADMIN)
                .exchange()
                .expectStatus().isUnauthorized();
    }

    //@Test
    void testAdminUnauthorizedForUser() {
        webTestClient
                .mutate().filter(basicAuthentication("user", "123456")).build()
                .get().uri(SecurityResource.SECURITY + SecurityResource.ADMIN)
                .exchange()
                .expectStatus().isUnauthorized();
    }

}
