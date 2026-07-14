package com.akancha.smartdocai.repository;

import com.akancha.smartdocai.entity.TechnicalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicalInfoRepository extends JpaRepository<TechnicalInfo, Long> {

    List<TechnicalInfo> findByProjectId(Long projectId);
}
