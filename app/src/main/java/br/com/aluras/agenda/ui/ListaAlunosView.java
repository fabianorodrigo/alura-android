package br.com.aluras.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import java.util.List;

import br.com.aluras.agenda.dao.AlunoDAO;
import br.com.aluras.agenda.model.Aluno;
import br.com.aluras.agenda.ui.activity.adapters.AlunoListAdapter;

public class ListaAlunosView {

    private final AlunoDAO dao;
    private final AlunoListAdapter adapter;
    private final Context context;

    public ListaAlunosView(Context context){
        this.context = context;
        this.adapter = new AlunoListAdapter(context);
        dao = new AlunoDAO();
    }

    public void configuraAdapter(ListView lv) {
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        // a linha acima utilizava um layout/componente e o também o Adapter nativos do Android, na linha
        // abaixo passamos a utilizar um layout/componente criado neste projeto bem como a criação de um
        // Adapter especilizado para trabalhar com layout/componente do projeto

        lv.setAdapter(this.adapter);
    }

    public void confirmaRemocao(@NonNull MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo Aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno aluno = adapter.getItem((menuInfo.position));
                    remove(aluno);
                })
                .setNegativeButton("Não", null).show();
    }

    private void remove(Aluno aluno) {
        // remove da base de dados
        dao.remove(aluno);
        // remove da interface
        adapter.remove(aluno);
    }

    public void atualizaAlunos(){
        this.adapter.atualizaAlunos(dao.todos());
    }
}
