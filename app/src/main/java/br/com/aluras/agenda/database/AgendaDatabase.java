package br.com.aluras.agenda.database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.aluras.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 3, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String AGENDA_DB = "Agenda.db";
    private static AgendaDatabase instance = null;

    public abstract RoomAlunoDAO getAlunoDAO();

    public static AgendaDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AgendaDatabase.class, AGENDA_DB)
                    .allowMainThreadQueries().addMigrations(new Migration(1,2) {
                        @Override
                        public void migrate(@NonNull SupportSQLiteDatabase database) {
                            database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
                        }
                    },new Migration(2,3) {
                        @Override
                        public void migrate(@NonNull SupportSQLiteDatabase database) {
                            // Como SQLLite não aceita remoção de colunas, caso se queira apagar uma coluna,
                            // será necessário:
                            // criar uma nova tabela
                            database.execSQL("CREATE TABLE IF NOT EXISTS `AlunoTmp` (" +
                                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                    "`nome` TEXT, `telefone` TEXT, `email` TEXT, `endereco` TEXT)");
                            // copiar os dados para a nova tabela
                            database.execSQL("INSERT INTO AlunoTmp(id,nome,telefone,email,endereco)" +
                                    "SELECT id,nome,telefone,email,endereco FROM Aluno");
                            // remover a tabela antiga
                            database.execSQL("DROP TABLE Aluno");
                            //e renomear a nova tabela com o nome da tabela antiga
                            database.execSQL("ALTER TABLE AlunoTmp RENAME TO Aluno");
                        }
                    })
                    .build();
        }
        return instance;
    }
}