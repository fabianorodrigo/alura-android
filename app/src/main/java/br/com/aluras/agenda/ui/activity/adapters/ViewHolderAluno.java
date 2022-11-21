package br.com.aluras.agenda.ui.activity.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import br.com.aluras.agenda.R;

// Implementação do View Holder Pattern https://www.spreys.com/view-holder-design-pattern-for-android/
public class ViewHolderAluno {

    private final TextView nomeAluno;
    private final TextView telefoneCelularAluno;

    public ViewHolderAluno(@NonNull View view) {
        this.nomeAluno = view.findViewById(R.id.item_aluno_nome);
        this.telefoneCelularAluno = view.findViewById(R.id.item_aluno_telefone_celular);

    }

    public TextView getNomeAluno() {
        return nomeAluno;
    }

    public TextView getTelefoneCelularAluno(){ return telefoneCelularAluno;}
}
