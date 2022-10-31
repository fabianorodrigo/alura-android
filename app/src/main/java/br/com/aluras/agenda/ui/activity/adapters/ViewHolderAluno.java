package br.com.aluras.agenda.ui.activity.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import br.com.aluras.agenda.R;

// Implementação do View Holder Pattern https://www.spreys.com/view-holder-design-pattern-for-android/
public class ViewHolderAluno {

    private final TextView nomeAluno;
    private final TextView telefoneAluno;

    public ViewHolderAluno(@NonNull View view) {
        this.nomeAluno = view.findViewById(R.id.item_aluno_nome);
        //telefoneAluno.setText(this.alunos.get(i).getTelefone());

        this.telefoneAluno = view.findViewById(R.id.item_aluno_telefone);
        //nomeAluno.setText(this.alunos.get(i).getNome());

    }

    public TextView getNomeAluno() {
        return nomeAluno;
    }

    public TextView getTelefoneAluno() {
        return telefoneAluno;
    }
}
