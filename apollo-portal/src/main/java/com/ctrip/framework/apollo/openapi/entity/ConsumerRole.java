package com.ctrip.framework.apollo.openapi.entity;

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
@Table(name = "consumer_role")
@SQLDelete(sql = "UPDATE consumer_role SET deleted = TRUE WHERE id = ?")
@Where(clause = "NOT deleted")
@SequenceGenerator(name = "sequence", sequenceName = "consumer_role_id_seq", allocationSize = 1)
public class ConsumerRole extends BaseEntity {
  @Column(name = "consumer_id", nullable = false)
  private long consumerId;

  @Column(name = "role_id", nullable = false)
  private long roleId;

  public long getConsumerId() {
    return consumerId;
  }

  public void setConsumerId(long consumerId) {
    this.consumerId = consumerId;
  }

  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }

  @Override
  public String toString() {
    return toStringHelper().add("consumerId", consumerId).add("roleId", roleId).toString();
  }
}
