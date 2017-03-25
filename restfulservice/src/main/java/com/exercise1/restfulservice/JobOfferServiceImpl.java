package com.exercise1.restfulservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 25.03.17.
 */
@Service
@Transactional
public class JobOfferServiceImpl implements JobOfferService {

    final JobOfferRepository jobOfferRepository;

    @Autowired
    public JobOfferServiceImpl(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    @Override
    public JobOffer findOne(long id) {
        return jobOfferRepository.findOne(id);
    }

    @Override
    public List<Long> getIds() {
        List<Long> ids = new ArrayList<>();
        for (JobOffer offer : jobOfferRepository.findAll()) {
            ids.add(offer.getId());
        }
        return ids;
    }
}
