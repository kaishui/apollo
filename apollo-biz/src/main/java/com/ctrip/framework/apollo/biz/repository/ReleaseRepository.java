package com.ctrip.framework.apollo.biz.repository;

import com.ctrip.framework.apollo.biz.entity.Release;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
public interface ReleaseRepository extends PagingAndSortingRepository<Release, Long> {

  Release findFirstByAppIdAndClusterNameAndNamespaceNameAndAbandonedFalseOrderByIdDesc(@Param("appId") String appId, @Param("clusterName") String clusterName,
                                                                                         @Param("namespaceName") String namespaceName);

  Release findByIdAndAbandonedFalse(long id);

  List<Release> findByAppIdAndClusterNameAndNamespaceNameOrderByIdDesc(String appId, String clusterName, String namespaceName, Pageable page);

  List<Release> findByAppIdAndClusterNameAndNamespaceNameAndAbandonedFalseOrderByIdDesc(String appId, String clusterName, String namespaceName, Pageable page);

  List<Release> findByAppIdAndClusterNameAndNamespaceNameAndAbandonedFalseAndIdBetweenOrderByIdDesc(String appId, String clusterName, String namespaceName, long fromId, long toId);

  List<Release> findByReleaseKeyIn(Set<String> releaseKey);

  List<Release> findByIdIn(Set<Long> releaseIds);

  @Modifying
  @Query("UPDATE Release SET isDeleted = TRUE, dataChangeLastModifiedBy = ?4 WHERE appId =?1 AND clusterName = ?2 AND namespaceName = ?3")
  int batchDelete(String appId, String clusterName, String namespaceName, String operator);

  // For release history conversion program, need to delete after conversion it done
  List<Release> findByAppIdAndClusterNameAndNamespaceNameOrderByIdAsc(String appId, String clusterName, String namespaceName);
}
