package br.com.aluras.agenda;

import android.app.Application;

import br.com.aluras.agenda.dao.AlunoDAO;
import br.com.aluras.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosTeste();
    }

    private void criaAlunosTeste() {
        AlunoDAO dao = new AlunoDAO();
        for (int i = 2; i < 6; i++) {
            dao.salva(new Aluno("José Nascimento " + i, "(" + i + "3) 9999-1111", "fabrodrigo@hotmail.com"));
            dao.salva(new Aluno("Maria Nascimento " + i, "(" + i + "1) 8888-2222", "kassiusfab@hotmail.com"));
            dao.salva(new Aluno("Nicolas Nascimento " + i, "(" + i + "1) 8888-2222", "kassiusfab@hotmail.com"));
        }
    }
}
