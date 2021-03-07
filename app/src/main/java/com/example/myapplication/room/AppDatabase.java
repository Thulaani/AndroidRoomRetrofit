package com.example.myapplication.room;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.model.Hero;

@Database(entities = { Hero.class}, exportSchema = false, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Dao getDao();

    private static volatile AppDatabase MsaINSTANCE;
    public static AppDatabase getInstance(Context context) {

        // Android concept - Double checked locking
        // Safe threading
        // Only created once it is needed
        // Once the instance is created, the instance won't be changed and the next thread will use the same instance rather to create a new instance.

        if (MsaINSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (MsaINSTANCE == null) {
                    MsaINSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return MsaINSTANCE;
    }

    private static AppDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            Log.d("TAG", "created");
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            Log.d("TAG", "opened");
            super.onOpen(db);
        }

        @Override
        public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
            Log.d("TAG", "dropped");
            super.onDestructiveMigration(db);
        }
    };
}

