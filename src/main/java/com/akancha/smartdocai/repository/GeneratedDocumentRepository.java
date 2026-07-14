package com.akancha.smartdocai.repository;

import com.akancha.smartdocai.entity.GeneratedDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeneratedDocumentRepository extends JpaRepository<GeneratedDocument, Long> {

    List<GeneratedDocument> findByProjectId(Long projectId);

    // Get the latest generated document for a project
    Optional<GeneratedDocument> findTopByProjectIdOrderByGeneratedDateDesc(Long projectId);
}
