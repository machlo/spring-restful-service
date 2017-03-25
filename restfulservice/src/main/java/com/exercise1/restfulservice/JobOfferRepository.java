package com.exercise1.restfulservice;

/**
 * Created by daniel on 25.03.17.
 */

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "jobs", path = "jobs")
public interface JobOfferRepository extends PagingAndSortingRepository<JobOffer, Long> {

    List<JobOffer> findByContent(@Param("content") String content);
}
