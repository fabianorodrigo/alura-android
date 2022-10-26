package br.com.aluras.agenda.ui.activity;

import static br.com.aluras.agenda.ui.activity.ConstantesActitivies.CHAVE_ALUNO;

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
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Editar Aluno";
    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
    private AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;
    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_aluno);
        inicializacaoCampos();
        configuraBotaoSalvar();
        carregaAluno();
    }

    private void carregaAluno() {
        Intent intent = getIntent();
        if(intent.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) getIntent().getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        }else{
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoEmail.setText(aluno.getEmail());
        campoTelefone.setText(aluno.getTelefone());
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
                finalizaFormulario();
            }
        });
    }

    private void finalizaFormulario() {
        preencheAluno();
        if(aluno.temIdValido()) {
            dao.edita(aluno);
        }else{
            dao.salva(aluno);
        }
        finish();
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