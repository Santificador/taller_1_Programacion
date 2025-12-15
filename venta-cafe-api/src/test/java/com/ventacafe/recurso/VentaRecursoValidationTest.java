package com.ventacafe.recurso;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class VentaRecursoValidationTest {

    @Test
    public void crearVenta_sinCamposObligatorios_debeRetornar400() {
        RestAssured.given()
                .contentType("application/json")
                .body("{}")
                .when().post("/api/ventas")
                .then()
                .statusCode(400)
                .body(containsString("items"));
    }
}
