package br.com.aluras.agenda;

import android.app.Application;

import androidx.room.Room;

import br.com.aluras.agenda.dao.AlunoDAO;
import br.com.aluras.agenda.database.AgendaDatabase;
import br.com.aluras.agenda.database.RoomAlunoDAO;
import br.com.aluras.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
