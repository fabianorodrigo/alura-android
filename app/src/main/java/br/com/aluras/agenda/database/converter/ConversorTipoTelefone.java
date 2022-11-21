package br.com.aluras.agenda.database.converter;

import androidx.room.TypeConverter;

import br.com.aluras.agenda.model.TipoTelefone;

public class ConversorTipoTelefone {

    @TypeConverter
    public String toString(TipoTelefone tipo){
        return tipo.name();
    }

    @TypeConverter
    public TipoTelefone toTipoTelefone(String valor){
        if(valor != null){
            TipoTelefone.valueOf(valor);
        }
        return TipoTelefone.FIXO;
    }
}
