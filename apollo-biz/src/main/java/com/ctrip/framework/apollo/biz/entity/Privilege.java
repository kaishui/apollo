package com.ctrip.framework.apollo.biz.entity;

import com.ctrip.framework.apollo.common.entity.BaseEntity;

import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "privilege")
@SQLDelete(sql = "UPDATE privilege SET deleted = TRUE WHERE id = ?")
@Where(clause = "NOT deleted")
@SequenceGenerator(name = "sequence", sequenceName = "privilege_id_seq", allocationSize = 1)
public class Privilege extends BaseEntity {

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "privilege_type", nullable = false)
  private String privilegeType;

  @Column(name = "namespace_id")
  private long namespaceId;

  public String getName() {
    return name;
  }

  public long getNamespaceId() {
    return namespaceId;
  }

  public String getPrivilegeType() {
    return privilegeType;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setNamespaceId(long namespaceId) {
    this.namespaceId = namespaceId;
  }

  public void setPrivilegeType(String privilegeType) {
    this.privilegeType = privilegeType;
  }

  public String toString() {
    return toStringHelper().add("namespaceId", namespaceId).add("privilType", privilegeType)
        .add("name", name).toString();
  }
}
