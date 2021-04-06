package com.ctrip.framework.apollo.biz.entity;

import com.ctrip.framework.apollo.common.entity.BaseEntity;

import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
@Entity
@Table(name = "release_history")
@SQLDelete(sql = "UPDATE release_history SET deleted = TRUE WHERE id = ?")
@Where(clause = "NOT deleted")
@SequenceGenerator(name = "sequence", sequenceName = "release_history_id_seq", allocationSize = 1)
public class ReleaseHistory extends BaseEntity {
  @Column(name = "app_id", nullable = false)
  private String appId;

  @Column(name = "cluster_name", nullable = false)
  private String clusterName;

  @Column(name = "namespace_name", nullable = false)
  private String namespaceName;

  @Column(name = "branch_name", nullable = false)
  private String branchName;

  @Column(name = "release_id")
  private long releaseId;

  @Column(name = "previous_release_id")
  private long previousReleaseId;

  @Column(name = "operation")
  private int operation;

  @Column(name = "operation_context", nullable = false)
  private String operationContext;

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getClusterName() {
    return clusterName;
  }

  public void setClusterName(String clusterName) {
    this.clusterName = clusterName;
  }

  public String getNamespaceName() {
    return namespaceName;
  }

  public void setNamespaceName(String namespaceName) {
    this.namespaceName = namespaceName;
  }

  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  public long getReleaseId() {
    return releaseId;
  }

  public void setReleaseId(long releaseId) {
    this.releaseId = releaseId;
  }

  public long getPreviousReleaseId() {
    return previousReleaseId;
  }

  public void setPreviousReleaseId(long previousReleaseId) {
    this.previousReleaseId = previousReleaseId;
  }

  public int getOperation() {
    return operation;
  }

  public void setOperation(int operation) {
    this.operation = operation;
  }

  public String getOperationContext() {
    return operationContext;
  }

  public void setOperationContext(String operationContext) {
    this.operationContext = operationContext;
  }

  public String toString() {
    return toStringHelper().add("appId", appId).add("clusterName", clusterName)
        .add("namespaceName", namespaceName).add("branchName", branchName)
        .add("releaseId", releaseId).add("previousReleaseId", previousReleaseId)
        .add("operation", operation).toString();
  }
}
