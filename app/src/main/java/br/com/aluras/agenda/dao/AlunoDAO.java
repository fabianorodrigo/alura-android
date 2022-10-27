package br.com.aluras.agenda.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.aluras.agenda.model.Aluno;

public class AlunoDAO {
    private final static List<Aluno> alunos = new ArrayList<Aluno>();
    private static int contadorIds = 1;

    public AlunoDAO(){
    }

    public List<Aluno> todos(){
        // retorna cópia
        return new ArrayList<>(AlunoDAO.alunos);
    }

    public void edita(Aluno aluno){
        Aluno alunoEncontrado = buscaAlunoPorId(aluno);
        if(alunoEncontrado != null){
          alunos.set(alunos.indexOf(alunoEncontrado),aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPorId(Aluno aluno) {
        for(Aluno a : alunos){
            if(a.getId() == aluno.getId()){
                return a;
            }
        }
        return null;
    }

    public void salva(Aluno aluno) {
        if(aluno.getId() == 0) {
            aluno.setId(contadorIds);
            atualizaIds();
        }
        AlunoDAO.alunos.add(aluno);
    }

    private void atualizaIds() {
        contadorIds++;
    }

    public void remove(Aluno aluno) {
        Aluno a = buscaAlunoPorId(aluno);
        if(a != null) {
            alunos.remove(a);
        }
    }
}
