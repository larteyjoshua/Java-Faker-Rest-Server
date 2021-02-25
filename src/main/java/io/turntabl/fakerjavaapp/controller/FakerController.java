package io.turntabl.fakerjavaapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FakerController {


    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/products")
    public JsonNode getRandomPersons() {

        Faker faker = new Faker();
        ArrayNode products = objectMapper.createArrayNode();

        for (int i = 1; i < 301; i++) {
            products.add(objectMapper.createObjectNode()
                    .put("id", i)
                    .put("name", faker.commerce().productName())
                    .put("account", faker.business().creditCardNumber())
                    .put("date", String.valueOf(faker.date().birthday(12,90)))
                    .put("price", faker.commerce().price())
                    .put("amount", faker.number().randomNumber(2,true))
                    .put("type", faker.business().creditCardType())
                    .put("branch", faker.address().cityName()));
        }

        return products;
    }
}
