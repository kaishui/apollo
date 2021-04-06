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
@Table(name = "role")
@SQLDelete(sql = "UPDATE role SET deleted = TRUE WHERE id = ?")
@Where(clause = "NOT deleted")
@SequenceGenerator(name = "sequence", sequenceName = "role_id_seq", allocationSize = 1)
public class Role extends BaseEntity {
  @Column(name = "role_name", nullable = false)
  private String roleName;

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
}
