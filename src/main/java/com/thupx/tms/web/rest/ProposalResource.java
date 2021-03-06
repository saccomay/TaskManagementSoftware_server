package com.thupx.tms.web.rest;

import com.thupx.tms.service.ProposalService;
import com.thupx.tms.web.rest.errors.BadRequestAlertException;
import com.thupx.tms.service.dto.ProposalDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.thupx.tms.domain.Proposal}.
 */
@RestController
@RequestMapping("/api")
public class ProposalResource {

    private final Logger log = LoggerFactory.getLogger(ProposalResource.class);

    private static final String ENTITY_NAME = "proposal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProposalService proposalService;

    public ProposalResource(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    /**
     * {@code POST  /proposals} : Create a new proposal.
     *
     * @param proposalDTO the proposalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proposalDTO, or with status {@code 400 (Bad Request)} if the proposal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/proposals")
    public ResponseEntity<ProposalDTO> createProposal(@RequestBody ProposalDTO proposalDTO) throws URISyntaxException {
        log.debug("REST request to save Proposal : {}", proposalDTO);
        if (proposalDTO.getId() != null) {
            throw new BadRequestAlertException("A new proposal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProposalDTO result = proposalService.save(proposalDTO);
        return ResponseEntity.created(new URI("/api/proposals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /proposals} : Updates an existing proposal.
     *
     * @param proposalDTO the proposalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proposalDTO,
     * or with status {@code 400 (Bad Request)} if the proposalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proposalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/proposals")
    public ResponseEntity<ProposalDTO> updateProposal(@RequestBody ProposalDTO proposalDTO) throws URISyntaxException {
        log.debug("REST request to update Proposal : {}", proposalDTO);
        if (proposalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProposalDTO result = proposalService.save(proposalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proposalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /proposals} : get all the proposals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proposals in body.
     */
    @GetMapping("/proposals")
    public List<ProposalDTO> getAllProposals() {
        log.debug("REST request to get all Proposals");
        return proposalService.findAll();
    }

    /**
     * {@code GET  /proposals/:id} : get the "id" proposal.
     *
     * @param id the id of the proposalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proposalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/proposals/{id}")
    public ResponseEntity<ProposalDTO> getProposal(@PathVariable Long id) {
        log.debug("REST request to get Proposal : {}", id);
        Optional<ProposalDTO> proposalDTO = proposalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proposalDTO);
    }

    /**
     * {@code DELETE  /proposals/:id} : delete the "id" proposal.
     *
     * @param id the id of the proposalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/proposals/{id}")
    public ResponseEntity<Void> deleteProposal(@PathVariable Long id) {
        log.debug("REST request to delete Proposal : {}", id);

        proposalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/proposals?query=:query} : search for the proposal corresponding
     * to the query.
     *
     * @param query the query of the proposal search.
     * @return the result of the search.
     */
    @GetMapping("/_search/proposals")
    public List<ProposalDTO> searchProposals(@RequestParam String query) {
        log.debug("REST request to search Proposals for query {}", query);
        return proposalService.search(query);
    }
}
