package com.ctrip.framework.apollo.biz.entity;

import com.ctrip.framework.apollo.common.entity.BaseEntity;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "access_key")
@SQLDelete(sql = "Update access_key set deleted = TRUE where id = ?")
@Where(clause = "NOT deleted")
@SequenceGenerator(name = "sequence", sequenceName = "access_key_id_seq", allocationSize = 1)
public class AccessKey extends BaseEntity {

  @Column(name = "app_id", nullable = false)
  private String appId;

  @Column(name = "secret", nullable = false)
  private String secret;

  @Column(name = "enabled", columnDefinition = "false")
  private boolean enabled;

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @Override
  public String toString() {
    return toStringHelper().add("appId", appId).add("secret", secret)
        .add("enabled", enabled).toString();
  }
}
