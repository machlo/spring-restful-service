package com.exercise1.restfulservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by daniel on 25.03.17.
 */
@RestController
public class JobOffersController {

    JobOfferService jobOfferService;

    @Autowired
    JobOffersController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }


    @RequestMapping(method=GET, value="/getOffer")
    public JobOffer jobOffer(@RequestParam(value="id") long id) {
        return jobOfferService.findOne(id);
    }

    @RequestMapping(method=GET, value="/getOfferIds")
    public List<Long> jobOfferIds() {
        return jobOfferService.getIds();
    }
}
