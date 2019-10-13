package com.exclusivelounge.rental.domains.assets.controllers;

import com.exclusivelounge.rental.domains.assets.model.entities.brands.Brand;
import com.exclusivelounge.rental.domains.assets.model.enums.AssetType;
import com.exclusivelounge.rental.domains.assets.services.BrandServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BrandControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandServiceImpl brandService;

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    @Nested
    @DisplayName("Given database has many records")
    class ManyDBRecordsTests {

        @BeforeEach
        void setup() {
            Brand audi = new Brand(1L, "audi", AssetType.CAR);
            Brand bmw = new Brand(2L, "bmw", AssetType.CAR);
            Brand boeing = new Brand(3L, "boeing", AssetType.PLANE);
            when(brandService.findBrandsByType(AssetType.CAR)).thenReturn(Arrays.asList(audi, bmw));
            when(brandService.findBrandsByType(AssetType.PLANE)).thenReturn(Collections.singletonList(boeing));
        }

        @Nested
        @DisplayName("Result should be 2xx")
        class RequestsWith2xxResponses {
            @Test
            @DisplayName("When http GET /brands/CAR then returns brands for CARs")
            void whenHttpGETByCar_ThenReturnOnlyCarAssetType() throws Exception {
                mockMvc.perform(get("/brands/CAR"))
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(status().is2xxSuccessful())
                        .andExpect(jsonPath("$", hasSize(2)))
                        .andExpect(jsonPath("$[0].id", is(1)))
                        .andExpect(jsonPath("$[0].name", is("audi")))
                        .andExpect(jsonPath("$[0].assetType", is(AssetType.CAR.name())))
                        .andExpect(jsonPath("$[1].id", is(2)))
                        .andExpect(jsonPath("$[1].name", is("bmw")))
                        .andExpect(jsonPath("$[1].assetType", is(AssetType.CAR.name())));
            }

            @Test
            @DisplayName("When http GET /brands/PLANE then returns brands for PLANEs")
            void whenHttpGetByPlane_ThenReturnOnlyPlaneAssetType() throws Exception {
                mockMvc.perform(get("/brands/PLANE"))
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(status().is2xxSuccessful())
                        .andExpect(jsonPath("$", hasSize(1)))
                        .andExpect(jsonPath("$[0].id", is(3)))
                        .andExpect(jsonPath("$[0].name", is("boeing")))
                        .andExpect(jsonPath("$[0].assetType", is(AssetType.PLANE.name())));
            }

            @Test
            @DisplayName("When http GET /brands/YACHT then returns brands for YACHTs")
            void whenHttpGetByYacht_ThenReturnOnlyYachtAssetType() throws Exception {
                mockMvc.perform(get("/brands/YACHT"))
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(status().is2xxSuccessful())
                        .andExpect(jsonPath("$", hasSize(0)));
            }

            @Test
            @DisplayName("When http GET /brands/car with case insensitive link then returns brands for CARs")
            void whenHttpGetByAssetTypeCaseInsensitive_ThenReturnOnlyThisAssetType() throws Exception {
                mockMvc.perform(get("/brands/caR"))
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(status().is2xxSuccessful())
                        .andExpect(jsonPath("$", hasSize(2)));
                mockMvc.perform(get("/brands/car"))
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(status().is2xxSuccessful())
                        .andExpect(jsonPath("$", hasSize(2)));
                mockMvc.perform(get("/brands/CaR"))
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(status().is2xxSuccessful())
                        .andExpect(jsonPath("$", hasSize(2)));
            }

            @Test
            @DisplayName("when POST new Brand then should return 2xx with newly created brands")
            void whenHttpPostNewBrand_thenShouldReturnSuccessfulCreateWithObject() throws Exception {


                Brand actualBrand = new Brand( "mercedes", AssetType.CAR);
                Mockito.when(brandService.save(any(Brand.class))).thenAnswer(
                        invocationOnMock ->
                        {
                            Brand brandToSave = invocationOnMock.getArgument(0);
                            brandToSave.setId(100L);
                            return brandToSave;
                        });

                mockMvc.perform(post("/brands")
                        .content(jsonMapper.writeValueAsString(actualBrand))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(jsonPath("$.id", is(100)))
                        .andExpect(jsonPath("$.name", is("mercedes")))
                        .andExpect(jsonPath("assetType", is("CAR")));
            }
        }

        @Nested
        @DisplayName("Result should be 4xx")
        class RequestsWith4xxResponses {
            @Test
            @DisplayName("When GET brands for incorrect type /brands/cars then should return bad request 400")
            void whenGetIncorrectAssetType_thenShouldReturnBadRequest() throws Exception{
                mockMvc.perform(get("/brands/cars"))
                        .andExpect(status().isBadRequest());
            }

            @Test
            @DisplayName("When GET brands for incorrect type /brands/cars then should return not found 404")
            void whenGetIncorrectUrl_thenShouldReturnNotFound() throws Exception{
                mockMvc.perform(get("/brandy/car"))
                        .andExpect(status().isNotFound());
            }
        }



    }

}