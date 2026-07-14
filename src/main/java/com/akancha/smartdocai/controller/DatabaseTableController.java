package com.akancha.smartdocai.controller;

import com.akancha.smartdocai.dto.DatabaseTableRequest;
import com.akancha.smartdocai.dto.DatabaseTableResponse;
import com.akancha.smartdocai.response.ApiResponse;
import com.akancha.smartdocai.service.DatabaseTableService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/database-tables")
public class DatabaseTableController {

    private final DatabaseTableService databaseTableService;

    public DatabaseTableController(DatabaseTableService databaseTableService) {
        this.databaseTableService = databaseTableService;
    }

    // Create Database Table
    @PostMapping
    public ApiResponse<DatabaseTableResponse> saveDatabaseTable(@Valid @RequestBody DatabaseTableRequest request) {

        DatabaseTableResponse response = databaseTableService.saveDatabaseTable(request);

        return new ApiResponse<>(true, "Database table created successfully", response);
    }

    // Get All Database Tables
    @GetMapping
    public ApiResponse<List<DatabaseTableResponse>> getAllDatabaseTables() {

        List<DatabaseTableResponse> response = databaseTableService.getAllDatabaseTables();

        return new ApiResponse<>(true, "Database tables fetched successfully", response);
    }

    // Get Database Tables By Project Id
    @GetMapping("/project/{projectId}")
    public ApiResponse<List<DatabaseTableResponse>> getDatabaseTablesByProjectId(@PathVariable Long projectId) {

        List<DatabaseTableResponse> response = databaseTableService.getDatabaseTablesByProjectId(projectId);

        return new ApiResponse<>(true, "Database tables fetched successfully", response);
    }

    // Get Database Table By Id
    @GetMapping("/{id}")
    public ApiResponse<DatabaseTableResponse> getDatabaseTableById(@PathVariable Long id) {

        DatabaseTableResponse response = databaseTableService.getDatabaseTableById(id);

        return new ApiResponse<>(true, "Database table fetched successfully", response);
    }

    // Update Database Table
    @PutMapping("/{id}")
    public ApiResponse<DatabaseTableResponse> updateDatabaseTable(@PathVariable Long id,
                                                                    @Valid @RequestBody DatabaseTableRequest request) {

        DatabaseTableResponse response = databaseTableService.updateDatabaseTable(id, request);

        return new ApiResponse<>(true, "Database table updated successfully", response);
    }

    // Delete Database Table
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteDatabaseTable(@PathVariable Long id) {

        databaseTableService.deleteDatabaseTable(id);

        return new ApiResponse<>(true, "Database table deleted successfully", null);
    }
}
