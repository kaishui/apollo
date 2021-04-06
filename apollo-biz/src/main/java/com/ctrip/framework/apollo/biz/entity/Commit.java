package com.ctrip.framework.apollo.biz.entity;

import com.ctrip.framework.apollo.common.entity.BaseEntity;

import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "commit")
@SQLDelete(sql = "UPDATE commit SET deleted = TRUE WHERE id = ?")
@Where(clause = "NOT deleted")
@SequenceGenerator(name = "sequence", sequenceName = "commit_id_seq", allocationSize = 1)
public class Commit extends BaseEntity {

  @Lob
  @Column(name = "change_sets", nullable = false)
  private String changeSets;

  @Column(name = "app_id", nullable = false)
  private String appId;

  @Column(name = "cluster_name", nullable = false)
  private String clusterName;

  @Column(name = "namespace_name", nullable = false)
  private String namespaceName;

  @Column(name = "comment")
  private String comment;

  public String getChangeSets() {
    return changeSets;
  }

  public void setChangeSets(String changeSets) {
    this.changeSets = changeSets;
  }

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

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public String toString() {
    return toStringHelper().add("changeSets", changeSets).add("appId", appId).add("clusterName", clusterName)
        .add("namespaceName", namespaceName).add("comment", comment).toString();
  }
}
