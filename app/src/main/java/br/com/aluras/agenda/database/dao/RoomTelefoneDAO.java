package br.com.aluras.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.aluras.agenda.model.Telefone;

@Dao
public interface RoomTelefoneDAO {

    // é possível salvar de 1 a N telefones
    // Se já existir, a estratégia de conflito faz o UPDATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void salva(Telefone... telefones);


    @Delete
    void remove(Telefone t);

    @Query("SELECT * FROM telefone")
    List<Telefone> todos();

    @Query("SELECT t.* " +
            "FROM telefone t JOIN aluno a ON t.alunoId = a.id " +
            "WHERE t.alunoId = :alunoId LIMIT 1")
    Telefone buscaPrimeiroTelefone(int alunoId);

    @Query("SELECT * " +
            "FROM telefone " +
            "WHERE alunoId = :alunoId")
    List<Telefone> buscaTelefonesAluno(int alunoId);
}
