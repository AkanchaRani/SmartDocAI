package com.akancha.smartdocai.service.impl;

import com.akancha.smartdocai.dto.DatabaseTableRequest;
import com.akancha.smartdocai.dto.DatabaseTableResponse;
import com.akancha.smartdocai.entity.DatabaseTable;
import com.akancha.smartdocai.exception.ResourceNotFoundException;
import com.akancha.smartdocai.repository.DatabaseTableRepository;
import com.akancha.smartdocai.service.DatabaseTableService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseTableServiceImpl implements DatabaseTableService {

    private final DatabaseTableRepository databaseTableRepository;

    public DatabaseTableServiceImpl(DatabaseTableRepository databaseTableRepository) {
        this.databaseTableRepository = databaseTableRepository;
    }

    // Save Database Table
    @Override
    public DatabaseTableResponse saveDatabaseTable(DatabaseTableRequest request) {

        DatabaseTable databaseTable = new DatabaseTable();

        databaseTable.setTableName(request.getTableName());
        databaseTable.setColumns(request.getColumns());
        databaseTable.setPrimaryKey(request.getPrimaryKey());
        databaseTable.setForeignKey(request.getForeignKey());
        databaseTable.setDatatype(request.getDatatype());
        databaseTable.setProjectId(request.getProjectId());

        DatabaseTable savedTable = databaseTableRepository.save(databaseTable);

        return mapToResponse(savedTable);
    }

    // Get All Database Tables
    @Override
    public List<DatabaseTableResponse> getAllDatabaseTables() {

        return databaseTableRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get Database Tables By Project Id
    @Override
    public List<DatabaseTableResponse> getDatabaseTablesByProjectId(Long projectId) {

        return databaseTableRepository.findByProjectId(projectId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get Database Table By Id
    @Override
    public DatabaseTableResponse getDatabaseTableById(Long id) {

        DatabaseTable databaseTable = databaseTableRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Database table not found with id : " + id));

        return mapToResponse(databaseTable);
    }

    // Update Database Table
    @Override
    public DatabaseTableResponse updateDatabaseTable(Long id, DatabaseTableRequest request) {

        DatabaseTable databaseTable = databaseTableRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Database table not found with id : " + id));

        databaseTable.setTableName(request.getTableName());
        databaseTable.setColumns(request.getColumns());
        databaseTable.setPrimaryKey(request.getPrimaryKey());
        databaseTable.setForeignKey(request.getForeignKey());
        databaseTable.setDatatype(request.getDatatype());
        databaseTable.setProjectId(request.getProjectId());

        DatabaseTable updatedTable = databaseTableRepository.save(databaseTable);

        return mapToResponse(updatedTable);
    }

    // Delete Database Table
    @Override
    public void deleteDatabaseTable(Long id) {

        DatabaseTable databaseTable = databaseTableRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Database table not found with id : " + id));

        databaseTableRepository.delete(databaseTable);
    }

    // Helper method to convert Entity to Response DTO
    private DatabaseTableResponse mapToResponse(DatabaseTable databaseTable) {
        return new DatabaseTableResponse(
                databaseTable.getId(),
                databaseTable.getTableName(),
                databaseTable.getColumns(),
                databaseTable.getPrimaryKey(),
                databaseTable.getForeignKey(),
                databaseTable.getDatatype(),
                databaseTable.getProjectId()
        );
    }
}
