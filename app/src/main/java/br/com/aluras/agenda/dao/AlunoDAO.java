package br.com.aluras.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.aluras.agenda.model.Aluno;

public class AlunoDAO {
    private final static List<Aluno> alunos = new ArrayList<Aluno>();
    private static int contadorIds = 1;

    static {
        alunos.add(new Aluno("Fabiano Nascimento","(33) 9999-1111","fabrodrigo@hotmail.com"));
        alunos.add(new Aluno("Kassius Nascimento","(31) 8888-2222","kassiusfab@hotmail.com"));
    }

    public AlunoDAO(){
    }

    public List<Aluno> todos(){
        // retorna cópia
        return new ArrayList<>(AlunoDAO.alunos);
    }

    public void edita(Aluno aluno){
        Aluno alunoEncontrado = null;
        for(Aluno a : alunos){
            if(a.getId() == aluno.getId()){
                alunoEncontrado = a;
            }
        }
        if(alunoEncontrado != null){
          alunos.set(alunos.indexOf(alunoEncontrado),aluno);
        }
    }

    public void salva(Aluno aluno) {
        if(aluno.getId() == 0) {
            aluno.setId(contadorIds);
            contadorIds++;
        }
        AlunoDAO.alunos.add(aluno);
    }
}
