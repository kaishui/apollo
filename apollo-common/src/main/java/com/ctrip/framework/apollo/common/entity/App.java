package com.ctrip.framework.apollo.common.entity;

import com.ctrip.framework.apollo.common.utils.InputValidator;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "app")
@SQLDelete(sql = "UPDATE app SET deleted = TRUE WHERE id = ?")
@Where(clause = "NOT deleted")
@SequenceGenerator(name = "sequence", sequenceName = "app_id_seq", allocationSize = 1)
public class App extends BaseEntity {

  @Column(name = "app_name", nullable = false)
  private String name;

  @Column(name = "app_id", nullable = false)
  private String appId;

  @Column(name = "org_id", nullable = false)
  private String orgId;

  @Column(name = "org_name", nullable = false)
  private String orgName;

  @Column(name = "owner_name", nullable = false)
  private String ownerName;

  @Column(name = "owner_email", nullable = false)
  private String ownerEmail;

  public String getAppId() {
    return appId;
  }

  public String getName() {
    return name;
  }

  public String getOrgId() {
    return orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public String getOwnerEmail() {
    return ownerEmail;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public void setOwnerEmail(String ownerEmail) {
    this.ownerEmail = ownerEmail;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public String toString() {
    return toStringHelper().add("name", name).add("appId", appId)
        .add("orgId", orgId)
        .add("orgName", orgName)
        .add("ownerName", ownerName)
        .add("ownerEmail", ownerEmail).toString();
  }

  public static class Builder {

    public Builder() {
    }

    private App app = new App();

    public Builder name(String name) {
      app.setName(name);
      return this;
    }

    public Builder appId(String appId) {
      app.setAppId(appId);
      return this;
    }

    public Builder orgId(String orgId) {
      app.setOrgId(orgId);
      return this;
    }

    public Builder orgName(String orgName) {
      app.setOrgName(orgName);
      return this;
    }

    public Builder ownerName(String ownerName) {
      app.setOwnerName(ownerName);
      return this;
    }

    public Builder ownerEmail(String ownerEmail) {
      app.setOwnerEmail(ownerEmail);
      return this;
    }

    public App build() {
      return app;
    }

  }

  public static Builder builder() {
    return new Builder();
  }


}
