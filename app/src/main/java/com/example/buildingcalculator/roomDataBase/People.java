package com.example.buildingcalculator.roomDataBase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.jetbrains.annotations.NotNull;

@Entity
public class People  {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String patronymic;

    @NonNull
    @TypeConverters({LegalStatusConverter.class})
    private LegalStatus legalStatus;

    @Nullable
    private String mailbox;

    @ColumnInfo(name = "name_of_firm")
    @Nullable
    private String nameOfFirm;

    @ColumnInfo(name = "address_of_firm")
    @Nullable
    private String addressOfFirm;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getSurname() {
        return surname;
    }

    public void setSurname(@NonNull String surname) {
        this.surname = surname;
    }

    @NonNull
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(@NonNull String patronymic) {
        this.patronymic = patronymic;
    }

    @NonNull
    public LegalStatus getLegalStatus() {
        return legalStatus;
    }

    public void setLegalStatus(@NonNull LegalStatus legalStatus) {
        this.legalStatus = legalStatus;
    }

    @Nullable
    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(@Nullable String mailbox) {
        this.mailbox = mailbox;
    }
    @Nullable
    public String getNameOfFirm() {
        return nameOfFirm;
    }

    public void setNameOfFirm(@Nullable String nameOfFirm) {
        this.nameOfFirm = nameOfFirm;
    }

    @Override
    public @NotNull String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", status=" + legalStatus +
                ", mailbox='" + mailbox + '\'' +
                ", nameOfFirm='" + nameOfFirm + '\'' +
                ", addressOfFirm='" + addressOfFirm + '\'' +
                '}';
    }

    @Nullable
    public String getAddressOfFirm() {
        return addressOfFirm;
    }

    public void setAddressOfFirm(@Nullable String addressOfFirm) {
        this.addressOfFirm = addressOfFirm;
    }
}
