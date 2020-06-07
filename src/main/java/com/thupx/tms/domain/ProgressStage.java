package com.thupx.tms.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class ProgressStage implements Serializable {
	private Long id;
	private ZonedDateTime time;
	private String performBy;
	private Progress progress;
	public ProgressStage(Long id, ZonedDateTime time, String performBy, Progress progress) {
		super();
		this.id = id;
		this.time = time;
		this.performBy = performBy;
		this.progress = progress;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ZonedDateTime getTime() {
		return time;
	}
	public void setTime(ZonedDateTime time) {
		this.time = time;
	}
	public String getPerformBy() {
		return performBy;
	}
	public void setPerformBy(String performBy) {
		this.performBy = performBy;
	}
	public Progress getProgress() {
		return progress;
	}
	public void setProgress(Progress progress) {
		this.progress = progress;
	}
	
	
}
