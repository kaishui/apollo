package com.ctrip.framework.apollo.biz.entity;

import com.google.common.base.MoreObjects;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
@Entity
@Table(name = "instance_config")
@SequenceGenerator(name = "sequence", sequenceName = "instance_config_id_seq", allocationSize = 1)
public class InstanceConfig {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
  @Column(name = "id")
  private long id;

  @Column(name = "instance_id")
  private long instanceId;

  @Column(name = "config_app_id", nullable = false)
  private String configAppId;

  @Column(name = "config_cluster_name", nullable = false)
  private String configClusterName;

  @Column(name = "config_namespace_name", nullable = false)
  private String configNamespaceName;

  @Column(name = "release_key", nullable = false)
  private String releaseKey;

  @Column(name = "release_delivery_time", nullable = false)
  private Date releaseDeliveryTime;

  @Column(name = "created_time", nullable = false)
  private Date dataChangeCreatedTime;

  @Column(name = "last_modified_time")
  private Date dataChangeLastModifiedTime;

  @PrePersist
  protected void prePersist() {
    if (this.dataChangeCreatedTime == null) {
      dataChangeCreatedTime = new Date();
    }
    if (this.dataChangeLastModifiedTime == null) {
      dataChangeLastModifiedTime = dataChangeCreatedTime;
    }
  }

  @PreUpdate
  protected void preUpdate() {
    this.dataChangeLastModifiedTime = new Date();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getInstanceId() {
    return instanceId;
  }

  public void setInstanceId(long instanceId) {
    this.instanceId = instanceId;
  }

  public String getConfigAppId() {
    return configAppId;
  }

  public void setConfigAppId(String configAppId) {
    this.configAppId = configAppId;
  }

  public String getConfigNamespaceName() {
    return configNamespaceName;
  }

  public void setConfigNamespaceName(String configNamespaceName) {
    this.configNamespaceName = configNamespaceName;
  }

  public String getReleaseKey() {
    return releaseKey;
  }

  public void setReleaseKey(String releaseKey) {
    this.releaseKey = releaseKey;
  }

  public Date getDataChangeCreatedTime() {
    return dataChangeCreatedTime;
  }

  public void setDataChangeCreatedTime(Date dataChangeCreatedTime) {
    this.dataChangeCreatedTime = dataChangeCreatedTime;
  }

  public Date getDataChangeLastModifiedTime() {
    return dataChangeLastModifiedTime;
  }

  public void setDataChangeLastModifiedTime(Date dataChangeLastModifiedTime) {
    this.dataChangeLastModifiedTime = dataChangeLastModifiedTime;
  }

  public String getConfigClusterName() {
    return configClusterName;
  }

  public void setConfigClusterName(String configClusterName) {
    this.configClusterName = configClusterName;
  }

  public Date getReleaseDeliveryTime() {
    return releaseDeliveryTime;
  }

  public void setReleaseDeliveryTime(Date releaseDeliveryTime) {
    this.releaseDeliveryTime = releaseDeliveryTime;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .omitNullValues()
        .add("id", id)
        .add("configAppId", configAppId)
        .add("configClusterName", configClusterName)
        .add("configNamespaceName", configNamespaceName)
        .add("releaseKey", releaseKey)
        .add("dataChangeCreatedTime", dataChangeCreatedTime)
        .add("dataChangeLastModifiedTime", dataChangeLastModifiedTime)
        .toString();
  }
}
