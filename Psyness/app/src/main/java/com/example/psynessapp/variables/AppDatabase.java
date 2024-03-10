package com.example.psynessapp.variables;
import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {InterUsers.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract InterUsersDAO interUserDao();
}
