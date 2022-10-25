package br.com.aluras.agenda.ui.activity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_aluno);

        EditText campoNome = findViewById(R.id.activity_FormAluno_nome);
        EditText campoEmail = findViewById(R.id.activity_FormAluno_email);
        EditText campoTelefone = findViewById(R.id.activity_FormAluno_telefone);
        
        Button botaoSalvar = findViewById(R.id.activity_FormAluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = campoNome.getText().toString();
                String telefone = campoTelefone.getText().toString();
                String email = campoEmail.getText().toString();
                Aluno aluno = new Aluno(nome,telefone,email);
                AlunoDAO dao = new AlunoDAO();
                dao.salva(aluno);
                // a Intent indica onde estava e para qual activity vai
                startActivity(new Intent(FormAlunoActivity.this,ListaAlunosActivity.class));
            }
        });


    }
    
    
}