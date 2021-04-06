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
@Table(name = "release")
@SQLDelete(sql = "UPDATE release SET deleted = TRUE WHERE id = ?")
@Where(clause = "NOT deleted")
@SequenceGenerator(name = "sequence", sequenceName = "release_id_seq", allocationSize = 1)
public class Release extends BaseEntity {
  @Column(name = "release_key", nullable = false)
  private String releaseKey;

  @Column(name = "release_name", nullable = false)
  private String name;

  @Column(name = "app_id", nullable = false)
  private String appId;

  @Column(name = "cluster_name", nullable = false)
  private String clusterName;

  @Column(name = "namespace_name", nullable = false)
  private String namespaceName;

  @Column(name = "configurations", nullable = false)
  private String configurations;

  @Column(name = "comment", nullable = false)
  private String comment;

  @Column(name = "abandoned")
  private boolean abandoned;
  public String getReleaseKey() {
    return releaseKey;
  }

  public String getAppId() {
    return appId;
  }

  public String getClusterName() {
    return clusterName;
  }

  public String getComment() {
    return comment;
  }

  public String getConfigurations() {
    return configurations;
  }

  public String getNamespaceName() {
    return namespaceName;
  }

  public String getName() {
    return name;
  }

  public void setReleaseKey(String releaseKey) {
    this.releaseKey = releaseKey;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public void setClusterName(String clusterName) {
    this.clusterName = clusterName;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public void setConfigurations(String configurations) {
    this.configurations = configurations;
  }

  public void setNamespaceName(String namespaceName) {
    this.namespaceName = namespaceName;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isAbandoned() {
    return abandoned;
  }

  public void setAbandoned(boolean abandoned) {
    this.abandoned = abandoned;
  }

  public String toString() {
    return toStringHelper().add("name", name).add("appId", appId).add("clusterName", clusterName)
        .add("namespaceName", namespaceName).add("configurations", configurations)
        .add("comment", comment).add("isAbandoned", abandoned).toString();
  }
}
