package com.ctrip.framework.apollo.openapi.entity;

import com.ctrip.framework.apollo.common.entity.BaseEntity;

import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
@Entity
@Table(name = "consumer_token")
@SQLDelete(sql = "UPDATE ConsumerToken SET deleted = TRUE WHERE id = ?")
@Where(clause = "NOT deleted")
@SequenceGenerator(name = "sequence", sequenceName = "consumer_token_id_seq", allocationSize = 1)
public class ConsumerToken extends BaseEntity {
  @Column(name = "consumer_id", nullable = false)
  private long consumerId;
  @Column(name = "token", nullable = false)
  private String token;

  @Column(name = "expires", nullable = false)
  private Date expires;

  public long getConsumerId() {
    return consumerId;
  }

  public void setConsumerId(long consumerId) {
    this.consumerId = consumerId;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Date getExpires() {
    return expires;
  }

  public void setExpires(Date expires) {
    this.expires = expires;
  }

  @Override
  public String toString() {
    return toStringHelper().add("consumerId", consumerId).add("token", token)
        .add("expires", expires).toString();
  }
}
