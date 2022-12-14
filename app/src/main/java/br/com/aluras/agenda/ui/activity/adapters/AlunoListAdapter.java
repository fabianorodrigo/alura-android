package br.com.aluras.agenda.ui.activity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.aluras.agenda.R;
import br.com.aluras.agenda.database.AgendaDatabase;
import br.com.aluras.agenda.database.dao.RoomTelefoneDAO;
import br.com.aluras.agenda.model.Aluno;
import br.com.aluras.agenda.model.Telefone;
import br.com.aluras.agenda.model.TipoTelefone;

public class AlunoListAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    private final RoomTelefoneDAO dao;
    public AlunoListAdapter(Context context){
        this.context = context;
        this.dao = AgendaDatabase.getInstance(this.context).getTelefoneDAO();
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
        ViewHolderAluno viewHolder;
        if (view == null) {
            // o attachToRoot = false indica que nosso adapter não é responsável por adicionar a viewCriada dentro da viewGroup
            // ou seja, nós apenas criamos e retornamos. Com isso, a inclusão no viewGroup é delegada para o Android
            view = LayoutInflater.from(context).inflate(R.layout.item_aluno, viewGroup, false);
            viewHolder = new ViewHolderAluno(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderAluno) view.getTag();
        }

        viewHolder.getNomeAluno().setText(this.alunos.get(i).getNome());
        for(Telefone t : dao.buscaTelefonesAluno(this.alunos.get(i).getId())){
            if(t.getTipo() == TipoTelefone.CELULAR){
                viewHolder.getTelefoneCelularAluno().setText(t.getNumero());
            }
        }
        return view;
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
