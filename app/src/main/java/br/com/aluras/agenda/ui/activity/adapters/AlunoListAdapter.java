package br.com.aluras.agenda.ui.activity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.aluras.agenda.R;
import br.com.aluras.agenda.model.Aluno;

public class AlunoListAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public AlunoListAdapter(Context context){
        this.context = context;
    }



    @Override
    public int getCount() {
        return this.alunos.size();
    }

    @Override
    public Aluno getItem(int i) {
        return this.alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.alunos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // o attachToRoot = false indica que nosso adapter não é responsável por adicionar a viewCriada dentro da viewGroup
        // ou seja, nós apenas criamos e retornamos. Com isso, a inclusão no viewGroup é delegada para o Android
        View viewCriada;
        viewCriada = LayoutInflater.from(context).inflate(R.layout.item_aluno, viewGroup, false);
        TextView nomeAluno = viewCriada.findViewById(R.id.item_aluno_nome);
        nomeAluno.setText(this.alunos.get(i).getNome());
        TextView telefoneAluno = viewCriada.findViewById(R.id.item_aluno_telefone);
        telefoneAluno.setText(this.alunos.get(i).getTelefone());
        return viewCriada;
    }

    public void remove(Aluno aluno) {
        this.alunos.remove(aluno);
        // é necessário notificar o adapter que o dataset mudou para
        // que, quando ocorrer a mudança no dataset, isso se reflita na UI
        notifyDataSetChanged();
    }

    public void atualizaAlunos(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        // é necessário notificar o adapter que o dataset mudou para
        // que, quando ocorrer a mudança no dataset, isso se reflita na UI
        notifyDataSetChanged();
    }
}
