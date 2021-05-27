package com.example.buildingcalculator.roomDataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.jetbrains.annotations.Nullable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "material", foreignKeys = @ForeignKey(
        entity = Work.class,
        parentColumns = "work_id",
        childColumns = "parent_work_id",
        onDelete = CASCADE,
        onUpdate = CASCADE))
public class Material {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "material_id")
    private long id;

    private String name;

    private long quantity;

    @TypeConverters({UnitsConverter.class})
    private Units units;

    @ColumnInfo(name = "price_for_one")
    private long priceForOne;

    @ColumnInfo(name = "total_price")
    private long totalPrice;

    @ColumnInfo(name = "parent_work_id", index = true)
    @Nullable
    private long parentId;

    public Material() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPriceForOne() {
        return priceForOne;
    }

    public void setPriceForOne(long priceForOne) {
        this.priceForOne = priceForOne;
    }


    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Materials{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", units=" + units +
                ", priceForOne=" + priceForOne +
                ", totalPrice=" + totalPrice +
                ", parentId=" + parentId +
                '}';
    }
}
