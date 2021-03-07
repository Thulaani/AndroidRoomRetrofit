package com.example.myapplication.room;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.model.Hero;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void OnCreateHero(List<Hero> hero);

    @Query("select * from hero")
    List<Hero> OnGetHeroes();

    @Query("DELETE FROM hero")
    void OnDelete();

}
