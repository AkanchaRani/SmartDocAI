package com.akancha.smartdocai.service;

import com.akancha.smartdocai.dto.DatabaseTableRequest;
import com.akancha.smartdocai.dto.DatabaseTableResponse;

import java.util.List;

public interface DatabaseTableService {

    DatabaseTableResponse saveDatabaseTable(DatabaseTableRequest request);

    List<DatabaseTableResponse> getAllDatabaseTables();

    List<DatabaseTableResponse> getDatabaseTablesByProjectId(Long projectId);

    DatabaseTableResponse getDatabaseTableById(Long id);

    DatabaseTableResponse updateDatabaseTable(Long id, DatabaseTableRequest request);

    void deleteDatabaseTable(Long id);
}
