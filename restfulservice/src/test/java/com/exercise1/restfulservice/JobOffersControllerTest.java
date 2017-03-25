package com.exercise1.restfulservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JobOffersControllerTest {

    @Autowired
    private MockMvc mvc;


    JobOffer offer1 = new JobOffer(1, "Test");
    JobOffer offer2 = new JobOffer(2, "123");

    @Before
    public void setup() throws Exception {
        postOffer(mvc, offer1);
        postOffer(mvc, offer2);
    }

    private static void postOffer(MockMvc mvc, JobOffer offer) throws Exception {
        mvc.perform(
                post("/jobs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(offer))
        )
                .andExpect(status().isCreated())
                .andExpect(
                        header().string(
                                "location",
                                containsString("/jobs/" + offer.getId())
                        )
                );
    }

    @Test
    public void getOfferWithoutId() throws Exception {
        mvc.perform(
                get("/getOffer")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getOfferIds() throws Exception {
        mvc.perform(
                get("/getOfferIds")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string("[1,2]"));
    }

    @Test
    public void getOfferWithId() throws Exception {
        mvc.perform(
                get("/getOffer?id={id}", offer1.getId())
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(Long.valueOf(offer1.getId())))
                .andExpect(jsonPath("$.content").value(offer1.getContent()));
    }

    @Test
    public void getOfferUsingPagingApi() throws Exception {
        mvc.perform(
                get("/jobs/{id}", offer1.getId())
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(offer1.getContent()));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}