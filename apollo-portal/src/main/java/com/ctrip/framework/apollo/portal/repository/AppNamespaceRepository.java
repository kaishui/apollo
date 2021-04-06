package com.ctrip.framework.apollo.portal.repository;

import com.ctrip.framework.apollo.common.entity.AppNamespace;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppNamespaceRepository extends PagingAndSortingRepository<AppNamespace, Long> {

  AppNamespace findByAppIdAndName(String appId, String namespaceName);

  AppNamespace findByName(String namespaceName);

  List<AppNamespace> findByNameAndShared(String namespaceName, boolean shared);

  List<AppNamespace> findBySharedTrue();

  List<AppNamespace> findByAppId(String appId);

  @Modifying
  @Query("UPDATE AppNamespace SET IsDeleted=TRUE ,dataChangeLastModifiedBy=?2 WHERE appId=?1")
  int batchDeleteByAppId(String appId, String operator);

  @Modifying
  @Query("UPDATE AppNamespace SET IsDeleted=TRUE ,dataChangeLastModifiedBy = ?3 WHERE appId=?1 and name = ?2")
  int delete(String appId, String namespaceName, String operator);
}
