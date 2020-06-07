package com.thupx.tms.domain;

import java.io.Serializable;

public class ProposalData2 implements Serializable {
	private Proposal proposal;
	private Long progressDetailId;
	private String currentProgressName;
	public ProposalData2(Proposal proposal, Long progressDetailId, String currentProgressName) {
		super();
		this.proposal = proposal;
		this.progressDetailId = progressDetailId;
		this.currentProgressName = currentProgressName;
	}
	public Proposal getProposal() {
		return proposal;
	}
	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}
	public Long getProgressDetailId() {
		return progressDetailId;
	}
	public void setProgressDetailId(Long progressDetailId) {
		this.progressDetailId = progressDetailId;
	}
	public String getCurrentProgressName() {
		return currentProgressName;
	}
	public void setCurrentProgressName(String currentProgressName) {
		this.currentProgressName = currentProgressName;
	}
	
	
}
