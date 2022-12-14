package br.com.aluras.agenda.ui.activity;

import static br.com.aluras.agenda.ui.activity.ConstantesActitivies.CHAVE_ALUNO;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import br.com.aluras.agenda.R;
import br.com.aluras.agenda.databinding.ActivityListaAlunosBinding;
import br.com.aluras.agenda.model.Aluno;
import br.com.aluras.agenda.ui.ListaAlunosView;
import br.com.aluras.agenda.ui.activity.adapters.AlunoListAdapter;

@SuppressWarnings("CommentedOutCode")
public class ListaAlunosActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityListaAlunosBinding binding;
    private ListaAlunosView listaAlunosView;

    @SuppressWarnings("CommentedOutCode")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listaAlunosView = new ListaAlunosView(this);

        binding = ActivityListaAlunosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        // configuração da barra de navegação
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.activityListaAlunosBotaoAdicionar.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        // por alguma razão esse título está sendo sobreposto pelo comportamento dos Fragments
        this.setTitle("Opa Galera");

        configuraBotaoAdicionaAluno();
        configuraLista();
        // exibir uma mensagem por um tempo
        //Toast.makeText(this, "Fabiano Nascimento",Toast.LENGTH_LONG).show();

        //Seta uma text na view
        /*TextView aluno = new TextView(this);
        aluno.setText("Fabiano Nascimento");
        setContentView(aluno);*/
    }

    /*
     * OnResume vai ser executado quando desempilhar uma Activity que estava em cima.
     * No nosso caso, quando o formulário de cadastro for fechado, queremos que busque
     * novamente os dados novos na DAO
     * */
    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizaAlunos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_listaalunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_listaalunos_menu_remover) {
            listaAlunosView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }




    private void configuraBotaoAdicionaAluno() {
        FloatingActionButton btAdd = findViewById(R.id.activity_ListaAlunos_botaoAdicionar);
        btAdd.setOnClickListener(view -> abreFormularioModoInsercao());
    }

    private void configuraLista() {
        // ATENÇÃO: o ListView é uma solução simples não mais tão usada como no surgimento do Android
        // Hoje existem soluções mais rebuscadas
        ListView lv = findViewById(R.id.fragment_first_lvAlunos);
        listaAlunosView.configuraAdapter(lv);
        configuraClickItemLista(lv);
        registerForContextMenu(lv);
    }

/*    private void configuraClickLongoItemLista(ListView lv) {
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long id) {
                //Log.i("clic","cLIQUE LONGO " + String.valueOf(i));
                Aluno aluno = (Aluno) adapterView.getItemAtPosition(i);
                // remove da base de dados
                dao.remove(aluno);
                // remove da interface
                adapter.remove(aluno);
                // Se retornar TRUE, não for passar para o evento Click normal
                // Se retornar FALSE, vai seguir para o próximo evento
                return false;
            }
        });
    }*/

    private void configuraClickItemLista(ListView lv) {
        lv.setOnItemClickListener((adapterView, view, i, id) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(i);
            //Log de INFO no Logcat
            //Log.i("Aluno escolhido >>>>",alunoEscolhido.getNome());
            //Log de WARN no Logcat
            //Log.w("WARNING Posição>>>>",String.valueOf(i) + " " + String.valueOf(l));
            abreFormularioModoEdicao(alunoEscolhido);
        });
    }

    private void abreFormularioModoInsercao() {
        startActivity(new Intent(this, FormAlunoActivity.class));
    }

    private void abreFormularioModoEdicao(Aluno aluno) {
        Intent intentFormAluno = new Intent(ListaAlunosActivity.this, FormAlunoActivity.class);
        // o extra é uma forma de mandar para uma próxima Activity com algum dado/objeto (serializable)
        intentFormAluno.putExtra(CHAVE_ALUNO, aluno);
        startActivity(intentFormAluno);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}