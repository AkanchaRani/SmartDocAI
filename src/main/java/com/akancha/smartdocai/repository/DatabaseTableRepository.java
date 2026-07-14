package com.akancha.smartdocai.repository;

import com.akancha.smartdocai.entity.DatabaseTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseTableRepository extends JpaRepository<DatabaseTable, Long> {

    List<DatabaseTable> findByProjectId(Long projectId);
}
