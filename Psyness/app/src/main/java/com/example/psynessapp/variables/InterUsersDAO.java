package com.example.psynessapp.variables;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.DeleteTable;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao

public interface InterUsersDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(InterUsers user);
    @Query("SELECT * FROM InterUsers")
    LiveData<List<InterUsers>> getAllUsers();

    @Query("SELECT * FROM InterUsers WHERE IUserActive = 1 LIMIT 1")
    InterUsers getUserConnected();

    @Query("UPDATE interusers SET IUserActive = 0")
    void setDesconectForAllUsers();

}
