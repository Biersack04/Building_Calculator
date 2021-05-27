package com.example.buildingcalculator.roomDataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Entity(tableName = "work", foreignKeys = @ForeignKey(
        entity = Project.class,
        parentColumns = "project_id",
        childColumns = "parent_project_id"))
public class Work {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "work_id")
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private long quantity;

    @NotNull
    private long pricePerOne;

    @NotNull
    private long totalPrice;


    @TypeConverters({PriorityConverter.class})
    @Nullable
    private Priority priority;

    @Nullable
    @ColumnInfo(name = "start_date")
    private long startDate;

    @TypeConverters({UnitsConverter.class})
    private Units units;

    @Nullable
    @ColumnInfo(name = "need_days_to_completed")
    private long needDaysToCompleted;

    @Nullable
    @TypeConverters({StatusConverter.class})
    private Status status;

    @Nullable
    @ColumnInfo(name = "parent_project_id", index = true)
    private long parentId;


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

    public long getPricePerOne() {
        return pricePerOne;
    }

    public void setPricePerOne(long pricePerOne) {
        this.pricePerOne = pricePerOne;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public long getNeedDaysToCompleted() {
        return needDaysToCompleted;
    }

    public void setNeedDaysToCompleted(long needDaysToCompleted) {
        this.needDaysToCompleted = needDaysToCompleted;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }



    @NonNull
    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(@NonNull long quantity) {
        this.quantity = quantity;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", pricePerOne=" + pricePerOne +
                ", totalPrice=" + totalPrice +
                ", priority=" + priority +
                ", startDate=" + startDate +
                ", units=" + units +
                ", needDaysToCompleted=" + needDaysToCompleted +
                ", status=" + status +
                ", parentId=" + parentId +
                '}';
    }
}
