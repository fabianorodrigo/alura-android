package br.com.aluras.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.aluras.agenda.R;
import br.com.aluras.agenda.dao.AlunoDAO;
import br.com.aluras.agenda.databinding.ActivityListaAlunosBinding;
import br.com.aluras.agenda.databinding.ActivityListaAlunosBinding;
import br.com.aluras.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityListaAlunosBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListaAlunosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.activityListaAlunosBotaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // por alguma razão esse título está sendo sobreposto pelo comportamento dos Fragments
        this.setTitle("Opa Galera");

        FloatingActionButton btAdd = findViewById(R.id.activity_ListaAlunos_botaoAdicionar);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaAlunosActivity.this,FormAlunoActivity.class));
            }
        });
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
        AlunoDAO dao = new AlunoDAO();
        // ATENÇÃO: o ListView é uma solução simples não mais tão usada como no surgimento do Android
        // Hoje existem soluções mais rebuscadas
        //List<String> alunos = new ArrayList<>(Arrays.asList("Fabiano","Katisoca","Rubão","Jujanelli","Mary Paul"));
        ListView lv = findViewById(R.id.fragment_first_lvAlunos);
        lv.setAdapter(new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1,dao.todos()));
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