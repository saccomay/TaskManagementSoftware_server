package com.thupx.tms.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class ProgressStage implements Serializable {
	private Long id;
	private ZonedDateTime timeStart;
	private ZonedDateTime timeEnd;
	private String performBy;
	private Progress progress;
	public ProgressStage(Long id, ZonedDateTime timeStart, ZonedDateTime timeEnd, String performBy, Progress progress) {
		super();
		this.id = id;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.performBy = performBy;
		this.progress = progress;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public ZonedDateTime getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(ZonedDateTime timeStart) {
		this.timeStart = timeStart;
	}
	public ZonedDateTime getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(ZonedDateTime timeEnd) {
		this.timeEnd = timeEnd;
	}
	
	
	
	
}
