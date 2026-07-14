package com.akancha.smartdocai.repository;

import com.akancha.smartdocai.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    // Used on dashboard to show recent projects
    List<Project> findTop5ByOrderByCreatedDateDesc();

    List<Project> findByUserId(Long userId);
}
