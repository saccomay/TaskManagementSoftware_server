package com.thupx.tms.service.mapper;


import com.thupx.tms.domain.*;
import com.thupx.tms.service.dto.ProposalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Proposal} and its DTO {@link ProposalDTO}.
 */
@Mapper(componentModel = "spring", uses = {HospitalDepartmentMapper.class})
public interface ProposalMapper extends EntityMapper<ProposalDTO, Proposal> {

    @Mapping(source = "hospitalDepartment.id", target = "hospitalDepartmentId")
    ProposalDTO toDto(Proposal proposal);

    @Mapping(source = "hospitalDepartmentId", target = "hospitalDepartment")
    Proposal toEntity(ProposalDTO proposalDTO);

    default Proposal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Proposal proposal = new Proposal();
        proposal.setId(id);
        return proposal;
    }
}
