package br.com.aluras.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.aluras.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<Aluno>();

    public List<Aluno> todos(){
        // retorna cópia
        return new ArrayList<>(AlunoDAO.alunos);
    }

    public void salva(Aluno aluno) {
        AlunoDAO.alunos.add(aluno);
    }
}
