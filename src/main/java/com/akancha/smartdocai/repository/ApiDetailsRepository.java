package com.akancha.smartdocai.repository;

import com.akancha.smartdocai.entity.ApiDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiDetailsRepository extends JpaRepository<ApiDetails, Long> {

    List<ApiDetails> findByProjectId(Long projectId);
}
