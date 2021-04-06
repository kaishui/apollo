package com.ctrip.framework.apollo.common.entity;


import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@Table(name = "app_namespace")
@SQLDelete(sql = "UPDATE app_namespace SET deleted = TRUE WHERE id = ?")
@Where(clause = "NOT deleted")
@SequenceGenerator(name = "sequence", sequenceName = "app_namespace_id_seq", allocationSize = 1)
public class AppNamespace extends BaseEntity {

  @Column(name = "namespace_name", nullable = false)
  private String name;

  @Column(name = "app_id", nullable = false)
  private String appId;

  @Column(name = "format", nullable = false)
  private String format;

  @Column(name = "shared")
  private boolean shared = false;

  @Column(name = "comment")
  private String comment;

  public String getAppId() {
    return appId;
  }

  public String getComment() {
    return comment;
  }

  public String getName() {
    return name;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isShared() {
    return shared;
  }

  public void setShared(boolean publicc) {
    this.shared = publicc;
  }

  public ConfigFileFormat formatAsEnum() {
    return ConfigFileFormat.fromString(this.format);
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String toString() {
    return toStringHelper().add("name", name).add("appId", appId).add("comment", comment)
        .add("format", format).add("isPublic", shared).toString();
  }
}
