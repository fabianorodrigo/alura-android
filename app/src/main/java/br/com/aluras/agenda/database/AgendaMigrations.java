package br.com.aluras.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.aluras.agenda.model.TipoTelefone;

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
            database.execSQL("DROP TABLE aluno");
            //e renomear a nova tabela com o nome da tabela antiga
            database.execSQL("ALTER TABLE AlunoTmp RENAME TO Aluno");
        }
    };
    public static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN dataCriacao INTEGER");
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
            database.execSQL("DROP TABLE aluno");
            //e renomear a nova tabela com o nome da tabela antiga
            database.execSQL("ALTER TABLE AlunoTmp RENAME TO aluno");
        }
    };
    private static final Migration MIGRATION_5_6 = new Migration(5,6){

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `AlunoTmp` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`email` TEXT, " +
                    "`endereco` TEXT, " +
                    "`dataCriacao` INTEGER)");
            database.execSQL("CREATE TABLE IF NOT EXISTS `telefone` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`alunoId` INTEGER NOT NULL, " +
                    "`numero` TEXT, " +
                    "`tipo` TEXT, FOREIGN KEY(`alunoId`) REFERENCES `Aluno`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");

            // copiar os dados para as novas tabelas
            database.execSQL("INSERT INTO AlunoTmp(id,nome,email,endereco)" +
                    "SELECT id,nome,email,endereco FROM Aluno");
            database.execSQL("INSERT INTO telefone(numero,alunoId)" +
                    "SELECT telefoneFixo, id FROM aluno");
            // Atualizando todos os telefones para tipo FIXO
            database.execSQL("UPDATE telefone SET tipo = ?", new TipoTelefone[]{TipoTelefone.FIXO});

            // remover a tabela antiga
            database.execSQL("DROP TABLE aluno");
            //e renomear a nova tabela com o nome da tabela antiga
            database.execSQL("ALTER TABLE AlunoTmp RENAME TO aluno");
        }
    };
    static final Migration[] MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6};
}
