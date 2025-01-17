package com.ctrip.framework.apollo.biz.repository;

import com.ctrip.framework.apollo.common.entity.AppNamespace;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;


public interface AppNamespaceRepository extends PagingAndSortingRepository<AppNamespace, Long>{

  AppNamespace findByAppIdAndName(String appId, String namespaceName);

  List<AppNamespace> findByAppIdAndNameIn(String appId, Set<String> namespaceNames);

  AppNamespace findByNameAndSharedTrue(String namespaceName);

  List<AppNamespace> findByNameInAndSharedTrue(Set<String> namespaceNames);

  List<AppNamespace> findByAppIdAndShared(String appId, boolean shared);

  List<AppNamespace> findByAppId(String appId);

  List<AppNamespace> findFirst500ByIdGreaterThanOrderByIdAsc(long id);

  @Modifying
  @Query("UPDATE AppNamespace SET isDeleted= true ,dataChangeLastModifiedBy = ?2 WHERE appId=?1")
  int batchDeleteByAppId(String appId, String operator);

  @Modifying
  @Query("UPDATE AppNamespace SET isDeleted= true ,dataChangeLastModifiedBy = ?3 WHERE appId=?1 and name = ?2")
  int delete(String appId, String namespaceName, String operator);
}
