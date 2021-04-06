package com.ctrip.framework.apollo.biz.repository;

import com.ctrip.framework.apollo.biz.entity.InstanceConfig;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface InstanceConfigRepository extends PagingAndSortingRepository<InstanceConfig, Long> {

  InstanceConfig findByInstanceIdAndConfigAppIdAndConfigNamespaceName(long instanceId, String
      configAppId, String configNamespaceName);

  Page<InstanceConfig> findByReleaseKeyAndDataChangeLastModifiedTimeAfter(String releaseKey, Date
      validDate, Pageable pageable);

  Page<InstanceConfig> findByConfigAppIdAndConfigClusterNameAndConfigNamespaceNameAndDataChangeLastModifiedTimeAfter(
      String appId, String clusterName, String namespaceName, Date validDate, Pageable pageable);

  List<InstanceConfig> findByConfigAppIdAndConfigClusterNameAndConfigNamespaceNameAndDataChangeLastModifiedTimeAfterAndReleaseKeyNotIn(
      String appId, String clusterName, String namespaceName, Date validDate, Set<String> releaseKey);

  @Modifying
  @Query("DELETE FROM InstanceConfig WHERE configAppId = ?1 AND configClusterName = ?2 AND configNamespaceName = ?3")
  int batchDelete(String appId, String clusterName, String namespaceName);

  @Query(
      value = "SELECT b.id FROM instance_config a INNER JOIN instance b ON b.id = " +
          "a.instance_id WHERE a.config_app_id = :configAppId AND a.config_cluster_name = " +
          ":clusterName AND a.config_namespace_name = :namespaceName AND a.last_modified_time " +
          "> :validDate AND b.app_id = :instanceAppId AND ?#{#pageable.pageSize} > 0",
      countQuery = "SELECT count(1) FROM instance_config a INNER JOIN instance b ON b.id = " +
          " a.instance_id WHERE a.config_app_id = :configAppId AND a.config_cluster_name = " +
          ":clusterName AND a.config_namespace_name = :namespaceName AND a.last_modified_time " +
          "> :validDate AND b.app_id = :instanceAppId",
      nativeQuery = true)
  Page<Object> findInstanceIdsByNamespaceAndInstanceAppId(
      @Param("instanceAppId") String instanceAppId, @Param("configAppId") String configAppId,
      @Param("clusterName") String clusterName, @Param("namespaceName") String namespaceName,
      @Param("validDate") Date validDate, Pageable pageable);
}
