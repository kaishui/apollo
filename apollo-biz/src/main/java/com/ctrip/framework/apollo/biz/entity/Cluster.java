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
@Table(name = "cluster")
@SQLDelete(sql = "update cluster set deleted = TRUE where id = ?")
@Where(clause = "NOT deleted")
@SequenceGenerator(name = "sequence", sequenceName = "cluster_id_seq", allocationSize = 1)
public class Cluster extends BaseEntity implements Comparable<Cluster> {

  @Column(name = "cluster_name", nullable = false)
  private String name;

  @Column(name = "app_id", nullable = false)
  private String appId;

  @Column(name = "parent_cluster_id", nullable = false)
  private long parentClusterId;

  public String getAppId() {
    return appId;
  }

  public String getName() {
    return name;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getParentClusterId() {
    return parentClusterId;
  }

  public void setParentClusterId(long parentClusterId) {
    this.parentClusterId = parentClusterId;
  }

  public String toString() {
    return toStringHelper().add("name", name).add("appId", appId)
        .add("parentClusterId", parentClusterId).toString();
  }

  @Override
  public int compareTo(Cluster o) {
    if (o == null || getId() > o.getId()) {
      return 1;
    }

    if (getId() == o.getId()) {
      return 0;
    }

    return -1;
  }
}
