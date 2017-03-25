package com.exercise1.restfulservice;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by daniel on 25.03.17.
 */
@RestController
public class JobOffersController {

    @RequestMapping("/")
    public String index() {
        return "Job offers index" + System.getProperty("line.separator");
    }

    @RequestMapping(method=GET, value="/getOffer")
    public JobOffer jobOffer(@RequestParam(value="id", defaultValue="-1") long id) {
        return new JobOffer(id, "TEST");
    }
}
