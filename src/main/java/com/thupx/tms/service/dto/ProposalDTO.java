package com.thupx.tms.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;

/**
 * A DTO for the {@link com.thupx.tms.domain.Proposal} entity.
 */
public class ProposalDTO implements Serializable {
    
    private Long id;

    private String contentProposal;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private Boolean status;


    private Long hospitalDepartmentId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentProposal() {
        return contentProposal;
    }

    public void setContentProposal(String contentProposal) {
        this.contentProposal = contentProposal;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getHospitalDepartmentId() {
        return hospitalDepartmentId;
    }

    public void setHospitalDepartmentId(Long hospitalDepartmentId) {
        this.hospitalDepartmentId = hospitalDepartmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProposalDTO)) {
            return false;
        }

        return id != null && id.equals(((ProposalDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProposalDTO{" +
            "id=" + getId() +
            ", contentProposal='" + getContentProposal() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", status='" + isStatus() + "'" +
            ", hospitalDepartmentId=" + getHospitalDepartmentId() +
            "}";
    }
}
