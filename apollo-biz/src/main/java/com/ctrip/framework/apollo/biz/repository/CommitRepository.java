package com.ctrip.framework.apollo.biz.repository;

import com.ctrip.framework.apollo.biz.entity.Commit;

import java.util.Date;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommitRepository extends PagingAndSortingRepository<Commit, Long> {

  List<Commit> findByAppIdAndClusterNameAndNamespaceNameOrderByIdDesc(String appId, String clusterName,
                                                                      String namespaceName, Pageable pageable);

  List<Commit> findByAppIdAndClusterNameAndNamespaceNameAndDataChangeLastModifiedTimeGreaterThanEqualOrderByIdDesc(
      String appId, String clusterName, String namespaceName, Date dataChangeLastModifiedTime, Pageable pageable);

  @Modifying
  @Query("UPDATE Commit SET isDeleted = TRUE, dataChangeLastModifiedBy = ?4 WHERE appId = ?1 AND clusterName = ?2 AND namespaceName = ?3")
  int batchDelete(String appId, String clusterName, String namespaceName, String operator);

}
