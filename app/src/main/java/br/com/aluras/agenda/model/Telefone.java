package br.com.aluras.agenda.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Aluno.class, parentColumns = "id", childColumns = "alunoId", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)})
public class Telefone {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "alunoId")
    private int alunoId;

    private String numero;
    private TipoTelefone tipo;

    public Telefone(String numero, TipoTelefone tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }
}
