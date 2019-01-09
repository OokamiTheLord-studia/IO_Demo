package tk.arktech;


import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;

public class SQL {
    private static SQL ourInstance = new SQL();

    public static SQL getInstance() {
        return ourInstance;
    }

    private static Connection conn;

    private SQL() {

        conn = null;

        var file = new File("./database.db");

        boolean needsInit = !file.exists();

        String url = "jdbc:sqlite:./database.db";

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(needsInit) init();

    }

    private void init()
    {
        try {
            var statement = conn.createStatement();
            statement.addBatch(
                    "CREATE TABLE Users (\n" +
                            "\tPESEL TEXT PRIMARY KEY NOT NULL,\n" +
                            "\tImie TEXT NOT NULL,\n" +
                            "\tNazwisko TEXT NOT NULL,\n" +
                            "\tMail TEXT NOT NULL,\n" +
                            "\tLogin TEXT NOT NULL,\n" +
                            "\tHaslo TEXT NOT NULL,\n" +
                            "\tSol TEXT NOT NULL,\n" +
                            "\tTelefon TEXT,\n" +
                            "\tPozwolenia INTEGER DEFAULT 0,\n" +
                            "\tCzyUzytkownikBroni INTEGER DEFAULT 0 NOT NULL,\n" +
                            "\tGrupa INTEGER NOT NULL,\n" +
                            "\tCONSTRAINT NewTable_UN UNIQUE (Login),\n" +
                            "\tCONSTRAINT NewTable_UN1 UNIQUE (Mail)\n" +
                            ");\n"
            );
            statement.addBatch(
                    "CREATE TABLE Grupy (\n" +
                            "\tID INTEGER PRIMARY KEY NOT NULL,\n" +
                            "\tNazwa TEXT NOT NULL,\n" +
                            "\tUprawnienia INTEGER DEFAULT 0 NOT NULL,\n" +
                            "\tCONSTRAINT name_unique UNIQUE (Nazwa)\n" +
                            ");"
            );
            statement.addBatch(
                    "INSERT INTO Grupy(Nazwa, Uprawnienia)\n" +
                            "\tVALUES('Administratorzy', 935940);"
            );
            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adduser(String pesel, String imie, String nazwisko, String mail, String login, String haslo, String telefon, int pozwolenia, boolean pozwolenieNaBron, int idGrupy)
    {
        try {

            var r = new SecureRandom();
            var salt = new byte[32];
            r.nextBytes(salt);

            var sbytes = haslo.getBytes();

            var bytes = new byte[salt.length + sbytes.length];
            System.arraycopy(sbytes, 0, bytes, 0, sbytes.length);
            System.arraycopy(salt, 0, bytes, sbytes.length, salt.length);

            var enc = Base64.getEncoder();
            String saltEnc = enc.encodeToString(salt);


            String pass = DigestUtils.sha1Hex(bytes);

            var boolval = pozwolenieNaBron ? 1 : 0;

            var statement = conn.createStatement();

            statement.addBatch(
                    "INSERT INTO Users(PESEL, Imie, Nazwisko, Mail, Login, Haslo, Sol, Telefon, Pozwolenia, CzyUzytkownikBroni, Grupa)\n" +
                            "VALUES('" + pesel + "','" + imie + "','" + nazwisko + "','" + mail + "','" + login + "','" + saltEnc + "','" + telefon + "'," + pozwolenia + "," + boolval + "," + idGrupy + ");"
            );

            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(int userid)
    {
        try {
            var s = conn.createStatement();

            s.addBatch(
                    "DELETE FROM Users" +
                            "WHERE (PESEL = '" + userid + "');"
            );
            s.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void changePwd(String newPassword)
    {
        var r = new SecureRandom();
        var salt = new byte[32];
        r.nextBytes(salt);

        var sbytes = newPassword.getBytes();

        var bytes = new byte[salt.length + sbytes.length];
        System.arraycopy(sbytes, 0, bytes, 0, sbytes.length);
        System.arraycopy(salt, 0, bytes, sbytes.length, salt.length);

        var enc = Base64.getEncoder();
        String saltEnc = enc.encodeToString(salt);


        String pass = DigestUtils.sha1Hex(bytes);


        try {
            var s = conn.createStatement();
            s.addBatch(
                    "UPDATE Users\n" +
                            "SET Haslo = " + pass + ",\n" +
                            "    Sol = '"+ saltEnc + "'\n" +
                            "WHERE\n" +
                            "    PESEL = '123456';"
            );
            s.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }



    public void close()
    {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
