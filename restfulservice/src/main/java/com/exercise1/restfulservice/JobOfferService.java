package com.exercise1.restfulservice;


import java.util.List;

/**
 * Created by daniel on 25.03.17.
 */
public interface JobOfferService {

    JobOffer findOne(long id);

    List<Long> getIds();
}
