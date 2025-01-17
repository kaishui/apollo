package com.ctrip.framework.apollo.portal.entity.po;

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
@Table(name = "role_permission")
@SQLDelete(sql = "UPDATE role_permission SET deleted = TRUE WHERE id = ?")
@Where(clause = "NOT deleted")
@SequenceGenerator(name = "sequence", sequenceName = "role_permission_id_seq", allocationSize = 1)
public class RolePermission extends BaseEntity {
  @Column(name = "role_id", nullable = false)
  private long roleId;

  @Column(name = "permission_id", nullable = false)
  private long permissionId;

  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }

  public long getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(long permissionId) {
    this.permissionId = permissionId;
  }
}
