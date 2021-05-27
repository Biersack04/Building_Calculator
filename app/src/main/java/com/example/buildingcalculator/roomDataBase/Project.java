package com.example.buildingcalculator.roomDataBase;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@Entity(tableName = "project",
        foreignKeys = {
        @ForeignKey(
        entity = Executor.class,
        parentColumns = "id",
        childColumns = "executor_id",
        onDelete = ForeignKey.CASCADE),
        @ForeignKey(
                entity = Customer.class,
                parentColumns = "id",
                childColumns = "customer_id",
                onDelete = ForeignKey.SET_NULL)}
)
public class Project {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "project_id")
    private Long id;

    @NonNull
    private String name;

    @NotNull
    @ColumnInfo(name = "created_date")
    private long createdDate;

    @Nullable
    @ColumnInfo(name = "end_date")
    private long endDate;

    @Nullable
    @ColumnInfo(name = "project_address")
    private String projectAddress;

    @NotNull
    @ColumnInfo(index = true)
    public Long executor_id;

    @Nullable
    @ColumnInfo(index = true)
    public Long customer_id;

    @Nullable
    private long numberOfWorks;

    @Nullable
    private long numberOfMaterials;

    @Nullable
    private long totalPrice;

    @NotNull
    @TypeConverters({StatusConverter.class})
    private Status status;


    public Project() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long
    getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }


    public long getExecutor_id() {
        return executor_id;
    }

    public void setExecutor_id(long executor_id) {
        this.executor_id = executor_id;
    }

        public long getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(long customer_id) {
            this.customer_id = customer_id;
        }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public long getNumberOfWorks() {
        return numberOfWorks;
    }

    public void setNumberOfWorks(long numberOfWorks) {
        this.numberOfWorks = numberOfWorks;
    }

    public long getNumberOfMaterials() {
        return numberOfMaterials;
    }

    public void setNumberOfMaterials(long numberOfMaterials) {
        this.numberOfMaterials = numberOfMaterials;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", endDate=" + endDate +
                ", projectAddress='" + projectAddress + '\'' +
                ", executor_id=" + executor_id +
                ", customer_id=" + customer_id +
                ", numberOfWorks=" + numberOfWorks +
                ", numberOfMaterials=" + numberOfMaterials +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                '}';
    }
}
