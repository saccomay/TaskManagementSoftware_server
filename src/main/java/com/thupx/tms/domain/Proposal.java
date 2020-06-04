package com.thupx.tms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A Proposal.
 */
@Entity
@Table(name = "proposal")
public class Proposal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "content_proposal")
    private String contentProposal;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JsonIgnoreProperties(value = "proposals", allowSetters = true)
    private HospitalDepartment hospitalDepartment;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentProposal() {
        return contentProposal;
    }

    public Proposal contentProposal(String contentProposal) {
        this.contentProposal = contentProposal;
        return this;
    }

    public void setContentProposal(String contentProposal) {
        this.contentProposal = contentProposal;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public Proposal startDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public Proposal endDate(ZonedDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Boolean isStatus() {
        return status;
    }

    public Proposal status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public HospitalDepartment getHospitalDepartment() {
        return hospitalDepartment;
    }

    public Proposal hospitalDepartment(HospitalDepartment hospitalDepartment) {
        this.hospitalDepartment = hospitalDepartment;
        return this;
    }

    public void setHospitalDepartment(HospitalDepartment hospitalDepartment) {
        this.hospitalDepartment = hospitalDepartment;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Proposal)) {
            return false;
        }
        return id != null && id.equals(((Proposal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Proposal{" +
            "id=" + getId() +
            ", contentProposal='" + getContentProposal() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", status='" + isStatus() + "'" +
            "}";
    }
}