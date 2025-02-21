package com.demo.poc.entrypoint.shoppingcart.addition.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.demo.poc.entrypoint.shoppingcart.addition.dto.ShoppingCartAdditionRequestDto;
import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailEntity;
import com.demo.poc.utils.JsonFileReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShoppingCartAdditionMapperTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Given request data, when mapping it, then returns ShoppingCartDetailEntity")
    public void givenRequestData_WhenMappingIt_ThenReturnsShoppingCartDetailEntity() throws JsonProcessingException {
        //Arrange
        ShoppingCartAdditionRequestDto additionRequest = JsonFileReader.readObjectFromFile("shoppingcart/addition/ShoppingCartAdditionRequestDto.json", new TypeReference<>() {});

        Long shoppingCartId = 1L;

        ShoppingCartDetailEntity expectedDetailEntity = JsonFileReader.readObjectFromFile("shoppingcart/finder/ShoppingCartDetailEntity.json", new TypeReference<>() {});
        String expected = objectMapper.writeValueAsString(expectedDetailEntity);

        //Act
        ShoppingCartDetailEntity actualDetailEntity = ShoppingCartAdditionMapper.toEntity(additionRequest, shoppingCartId);
        String actual = objectMapper.writeValueAsString(actualDetailEntity);

        //Assert
        assertEquals(expected, actual);
    }
}
