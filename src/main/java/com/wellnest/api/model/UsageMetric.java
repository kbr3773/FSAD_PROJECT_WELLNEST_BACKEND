package com.wellnest.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class UsageMetric {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate metricDate;
  private int activeUsers;
  private int sessions;
  private int resourceViews;
  private int programEnrollments;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getMetricDate() {
    return metricDate;
  }

  public void setMetricDate(LocalDate metricDate) {
    this.metricDate = metricDate;
  }

  public int getActiveUsers() {
    return activeUsers;
  }

  public void setActiveUsers(int activeUsers) {
    this.activeUsers = activeUsers;
  }

  public int getSessions() {
    return sessions;
  }

  public void setSessions(int sessions) {
    this.sessions = sessions;
  }

  public int getResourceViews() {
    return resourceViews;
  }

  public void setResourceViews(int resourceViews) {
    this.resourceViews = resourceViews;
  }

  public int getProgramEnrollments() {
    return programEnrollments;
  }

  public void setProgramEnrollments(int programEnrollments) {
    this.programEnrollments = programEnrollments;
  }
}
