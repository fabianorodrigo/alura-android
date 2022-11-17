package br.com.aluras.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class AgendaMigrations {

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
        }
    };
    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
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
    };
    public static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN dataCriacao INTEGER");
        }
    };
    private static final Migration MIGRATION_4_5 = new Migration(4,5){

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `AlunoTmp` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`telefoneFixo` TEXT, " +
                    "`telefoneCelular` TEXT, " +
                    "`email` TEXT, " +
                    "`endereco` TEXT, " +
                    "`dataCriacao` INTEGER)");
            // copiar os dados para a nova tabela
            database.execSQL("INSERT INTO AlunoTmp(id,nome,telefoneFixo,email,endereco)" +
                    "SELECT id,nome,telefone,email,endereco FROM Aluno");
            // remover a tabela antiga
            database.execSQL("DROP TABLE Aluno");
            //e renomear a nova tabela com o nome da tabela antiga
            database.execSQL("ALTER TABLE AlunoTmp RENAME TO Aluno");
        }
    };
    static final Migration[] MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5};
}
