package com.gpa.esteticacontrol.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gpa.esteticacontrol.model.Cliente;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ClienteDao {

    @Insert
    long insert(Cliente cliente);

    @Delete
    void delete(Cliente cliente);

    @Update
    void update(Cliente cliente);

    @Query("SELECT * FROM CLIENTE WHERE ID = :id")
    Cliente queryForId(long id);

    @Query("SELECT * FROM CLIENTE ORDER BY NOME")
    List<Cliente> queryAll();

}
