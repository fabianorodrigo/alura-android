package br.com.aluras.agenda.ui.activity;

import static br.com.aluras.agenda.ui.activity.ConstantesActitivies.CHAVE_ALUNO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.aluras.agenda.R;
import br.com.aluras.agenda.database.AgendaDatabase;
import br.com.aluras.agenda.database.dao.RoomAlunoDAO;
import br.com.aluras.agenda.database.dao.RoomTelefoneDAO;
import br.com.aluras.agenda.model.Aluno;
import br.com.aluras.agenda.model.Telefone;
import br.com.aluras.agenda.model.TipoTelefone;

public class FormAlunoActivity extends AppCompatActivity {
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Editar Aluno";
    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
    private Aluno aluno;
    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoTelefoneFixo;
    private EditText campoTelefoneCelular;
    private EditText campoEndereco;
    private RoomAlunoDAO alunoDAO;
    private RoomTelefoneDAO telefoneDAO;
    private List<Telefone> telefones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_aluno);
        configuraBotaoSalvar();
        alunoDAO = AgendaDatabase.getInstance(this).getAlunoDAO();
        telefoneDAO = AgendaDatabase.getInstance(this).getTelefoneDAO();
        inicializacaoCampos();
        carregaAluno();
    }


    private void carregaAluno() {
        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) getIntent().getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formaluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.w("onOptionsItemSelected", "clicou");
        int itemId = item.getItemId();
        Log.w("onOptionsItemSelected", String.valueOf(itemId));
        Log.w("onOptionsItemSelected", String.valueOf(R.id.activity_formaluno_menu_salvar));
        if (itemId == R.id.activity_formaluno_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoEmail.setText(aluno.getEmail());
        campoEndereco.setText(aluno.getEndereco());
        preencheCamposDeTelefone();
    }

    private void preencheCamposDeTelefone() {
        telefones = telefoneDAO.buscaTelefonesAluno(aluno.getId());
        Log.i("tELEFONES", String.valueOf(telefones.size()));
        for (Telefone telefone : telefones) {
            if (telefone.getTipo() == TipoTelefone.FIXO){
                campoTelefoneFixo.setText(telefone.getNumero());
            } else {
                campoTelefoneCelular.setText(telefone.getNumero());
            }
        }
    }

    private void inicializacaoCampos() {
        campoNome = findViewById(R.id.activity_FormAluno_nome);
        campoEmail = findViewById(R.id.activity_FormAluno_email);
        campoTelefoneFixo = findViewById(R.id.activity_FormAluno_telefone_fixo);
        campoTelefoneCelular = findViewById(R.id.activity_FormAluno_telefone_celular);
        campoEndereco = findViewById(R.id.activity_FormAluno_endereco);
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_FormAluno_botao_salvar);
        botaoSalvar.setOnClickListener(view -> finalizaFormulario());
    }

    private void finalizaFormulario() {
        preencheAluno();
        String numeroFixo = campoTelefoneFixo.getText().toString();
        Telefone telefoneFixo = new Telefone(numeroFixo, TipoTelefone.FIXO);
        String numeroCelular = campoTelefoneCelular.getText().toString();
        Telefone telefoneCelular = new Telefone(numeroCelular, TipoTelefone.CELULAR);
        if (aluno.temIdValido()) {
            alunoDAO.edita(aluno);
            vinculaAlunoTelefone(aluno.getId(),telefoneFixo,telefoneCelular);
            for (Telefone t : telefones) {
                if (t.getTipo() == TipoTelefone.FIXO) {
                    telefoneFixo.setId(t.getId());
                } else {
                    telefoneCelular.setId(t.getId());
                }
            }
            telefoneDAO.salva(telefoneFixo, telefoneCelular);
        } else {
            int alunoId = alunoDAO.salva(aluno).intValue();
            vinculaAlunoTelefone(alunoId,telefoneFixo,telefoneCelular);
            telefoneDAO.salva(telefoneFixo, telefoneCelular);
        }
        finish();
    }

    private void vinculaAlunoTelefone( int alunoId, Telefone... telefones){
        for(Telefone t : telefones){
            t.setAlunoId(alunoId);
        }
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String endereco = campoEndereco.getText().toString();
        String telefoneFixo = campoTelefoneFixo.getText().toString();
        String telefoneCelular = campoTelefoneCelular.getText().toString();
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setEndereco(endereco);
    }

    private void salva(Aluno aluno) {
        alunoDAO.salva(aluno);
        // a Intent indica onde estava e para qual activity vai
        //startActivity(new Intent(FormAlunoActivity.this,ListaAlunosActivity.class));
    }

}