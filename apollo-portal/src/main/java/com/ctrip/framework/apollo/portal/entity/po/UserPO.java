package com.ctrip.framework.apollo.portal.entity.po;

import com.ctrip.framework.apollo.portal.entity.bo.UserInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author lepdou 2017-04-08
 */
@Entity
@Table(name = "users")
@SequenceGenerator(name = "sequence", sequenceName = "users_id_seq", allocationSize = 1)
public class UserPO {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
  @Column(name = "id")
  private long id;
  @Column(name = "username", nullable = false)
  private String username;
  @Column(name = "password", nullable = false)
  private String password;
  @Column(name = "email", nullable = false)
  private String email;
  @Column(name = "enabled", nullable = false)
  private boolean enabled = true;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public UserInfo toUserInfo() {
    UserInfo userInfo = new UserInfo();
    userInfo.setName(this.getUsername());
    userInfo.setUserId(this.getUsername());
    userInfo.setEmail(this.getEmail());
    return userInfo;
  }
}
