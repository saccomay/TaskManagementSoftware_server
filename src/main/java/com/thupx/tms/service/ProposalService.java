package com.thupx.tms.service;

import com.thupx.tms.domain.Proposal;
import com.thupx.tms.repository.ProposalRepository;
import com.thupx.tms.repository.search.ProposalSearchRepository;
import com.thupx.tms.service.dto.ProposalDTO;
import com.thupx.tms.service.mapper.ProposalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Proposal}.
 */
@Service
@Transactional
public class ProposalService {

    private final Logger log = LoggerFactory.getLogger(ProposalService.class);

    private final ProposalRepository proposalRepository;

    private final ProposalMapper proposalMapper;

    private final ProposalSearchRepository proposalSearchRepository;

    public ProposalService(ProposalRepository proposalRepository, ProposalMapper proposalMapper, ProposalSearchRepository proposalSearchRepository) {
        this.proposalRepository = proposalRepository;
        this.proposalMapper = proposalMapper;
        this.proposalSearchRepository = proposalSearchRepository;
    }

    /**
     * Save a proposal.
     *
     * @param proposalDTO the entity to save.
     * @return the persisted entity.
     */
    public ProposalDTO save(ProposalDTO proposalDTO) {
        log.debug("Request to save Proposal : {}", proposalDTO);
        Proposal proposal = proposalMapper.toEntity(proposalDTO);
        proposal = proposalRepository.save(proposal);
        ProposalDTO result = proposalMapper.toDto(proposal);
        proposalSearchRepository.save(proposal);
        return result;
    }

    /**
     * Get all the proposals.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProposalDTO> findAll() {
        log.debug("Request to get all Proposals");
        return proposalRepository.findAll().stream()
            .map(proposalMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one proposal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProposalDTO> findOne(Long id) {
        log.debug("Request to get Proposal : {}", id);
        return proposalRepository.findById(id)
            .map(proposalMapper::toDto);
    }

    /**
     * Delete the proposal by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Proposal : {}", id);

        proposalRepository.deleteById(id);
        proposalSearchRepository.deleteById(id);
    }

    /**
     * Search for the proposal corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProposalDTO> search(String query) {
        log.debug("Request to search Proposals for query {}", query);
        return StreamSupport
            .stream(proposalSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(proposalMapper::toDto)
        .collect(Collectors.toList());
    }
}
