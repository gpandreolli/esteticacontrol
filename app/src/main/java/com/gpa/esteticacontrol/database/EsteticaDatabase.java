package com.gpa.esteticacontrol.database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.gpa.esteticacontrol.converters.Converters;
import com.gpa.esteticacontrol.dao.ClienteDao;
import com.gpa.esteticacontrol.model.Cliente;

@Database(entities = {Cliente.class},version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class EsteticaDatabase extends RoomDatabase {

    public abstract ClienteDao clienteDao();

    private static EsteticaDatabase instance;

    public static EsteticaDatabase getDatabase(final Context context){
        if(instance ==null){
            synchronized (EsteticaDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context,
                            EsteticaDatabase.class,
                            "estetica.db").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }


    @Override
    public void clearAllTables() {

    }
}
