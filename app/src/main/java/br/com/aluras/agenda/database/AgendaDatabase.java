package br.com.aluras.agenda.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.aluras.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 2, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String AGENDA_DB = "Agenda.db";
    private static AgendaDatabase instance = null;

    public abstract RoomAlunoDAO getAlunoDAO();

    public static AgendaDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context, AgendaDatabase.class, AGENDA_DB).allowMainThreadQueries().build();
        }
        return instance;
    }
}