package com.thupx.tms.repository.search;

import com.thupx.tms.domain.Proposal;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Proposal} entity.
 */
public interface ProposalSearchRepository extends ElasticsearchRepository<Proposal, Long> {
}
