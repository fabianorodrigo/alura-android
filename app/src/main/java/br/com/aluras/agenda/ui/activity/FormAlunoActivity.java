package br.com.aluras.agenda.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.aluras.agenda.R;
import br.com.aluras.agenda.dao.AlunoDAO;
import br.com.aluras.agenda.model.Aluno;

public class FormAlunoActivity extends AppCompatActivity {
    private AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;
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
        Intent intent = getIntent();
        if(intent.hasExtra("aluno")) {
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            campoNome.setText(aluno.getNome());
            campoEmail.setText(aluno.getEmail());
            campoTelefone.setText(aluno.getTelefone());
        }else{
            aluno = new Aluno();
        }
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
                    preencheAluno();
                    if(aluno.temIdValido()) {
                        dao.edita(aluno);
                    }else{
                        dao.salva(aluno);
                    }
                finish();
            }
        });
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();
        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }

    private void salva(Aluno aluno){
        dao.salva(aluno);
        // a Intent indica onde estava e para qual activity vai
        //startActivity(new Intent(FormAlunoActivity.this,ListaAlunosActivity.class));
    }

}