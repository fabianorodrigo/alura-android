package br.com.aluras.agenda.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.aluras.agenda.R;
import br.com.aluras.agenda.dao.AlunoDAO;
import br.com.aluras.agenda.model.Aluno;

public class FormAlunoActivity extends AppCompatActivity {
    private AlunoDAO dao = new AlunoDAO();

    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_aluno);
        setTitle("Novo Aluno");
        inicializacaoCampos();
        configuraBotaoSalvar();
    }

    private void inicializacaoCampos() {
        campoNome = findViewById(R.id.activity_FormAluno_nome);
        campoEmail = findViewById(R.id.activity_FormAluno_email);
        campoTelefone = findViewById(R.id.activity_FormAluno_telefone);
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_FormAluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aluno aluno = criaAluno();
                salva(aluno);
            }
        });
    }

    @NonNull
    private Aluno criaAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();
        Aluno aluno = new Aluno(nome,telefone,email);
        return aluno;
    }

    private void salva(Aluno aluno){
        dao.salva(aluno);
        // a Intent indica onde estava e para qual activity vai
        //startActivity(new Intent(FormAlunoActivity.this,ListaAlunosActivity.class));
        finish();
    }

}