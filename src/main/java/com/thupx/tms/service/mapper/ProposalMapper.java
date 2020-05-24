package com.thupx.tms.service.mapper;


import com.thupx.tms.domain.*;
import com.thupx.tms.service.dto.ProposalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Proposal} and its DTO {@link ProposalDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProposalMapper extends EntityMapper<ProposalDTO, Proposal> {



    default Proposal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Proposal proposal = new Proposal();
        proposal.setId(id);
        return proposal;
    }
}
