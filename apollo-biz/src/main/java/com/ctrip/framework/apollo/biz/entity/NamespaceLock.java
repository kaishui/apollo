package com.ctrip.framework.apollo.biz.entity;

import com.ctrip.framework.apollo.common.entity.BaseEntity;

import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "namespace_lock")
@Where(clause = "NOT deleted")
@SequenceGenerator(name = "sequence", sequenceName = "namespace_lock_id_seq", allocationSize = 1)
public class NamespaceLock extends BaseEntity{

  @Column(name = "namespace_id")
  private long namespaceId;

  public long getNamespaceId() {
    return namespaceId;
  }

  public void setNamespaceId(long namespaceId) {
    this.namespaceId = namespaceId;
  }
}
