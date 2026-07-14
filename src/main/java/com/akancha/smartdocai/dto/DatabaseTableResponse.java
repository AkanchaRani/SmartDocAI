package com.akancha.smartdocai.dto;

public class DatabaseTableResponse {

    private Long id;
    private String tableName;
    private String columns;
    private String primaryKey;
    private String foreignKey;
    private String datatype;
    private Long projectId;

    public DatabaseTableResponse() {
    }

    public DatabaseTableResponse(Long id, String tableName, String columns, String primaryKey,
                                  String foreignKey, String datatype, Long projectId) {
        this.id = id;
        this.tableName = tableName;
        this.columns = columns;
        this.primaryKey = primaryKey;
        this.foreignKey = foreignKey;
        this.datatype = datatype;
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
