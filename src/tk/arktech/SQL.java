package tk.arktech;


import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import tk.arktech.Permission;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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
                            "\tVALUES('Administratorzy', 1048575);"
            );
            statement.addBatch(
                    "INSERT INTO Grupy(Nazwa, Uprawnienia)\n" +
                            "\tVALUES('Default', 0);"
            );
            statement.executeBatch();


            adduser("00000000000", "Janusz", "Administrator", "janusz@admin.pl", "admin", "admin", "+00000000000", 0, true, getGroupIdByName("Administratorzy"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getGroupIdByName(String name)
    {
        try {
            var s = conn.createStatement();
            var result = s.executeQuery(
                    "SELECT ID FROM Grupy WHERE Nazwa = '" + name + "';"
            );

            if(result.next())
            {
//                result.beforeFirst();
            }
            else
            {
                return 0;
            }

            return result.getInt("ID");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
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
                            "VALUES('" + pesel + "','" + imie + "','" + nazwisko + "','" + mail + "','" + login + "','" + pass + "','" + saltEnc + "','" + telefon + "'," + pozwolenia + "," + boolval + "," + idGrupy + ");"
            );

            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean removeUser(String userid)
    {
        try {
            var s = conn.createStatement();

            s.addBatch(
                    "DELETE FROM Users" +
                            " WHERE (PESEL = '" + userid + "');"
            );
            s.executeBatch();

            var result = s.executeQuery(
                    "SELECT PESEL FROM Users" +
                            " WHERE (PESEL = '" + userid + "');"
            );

            return !result.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public void changePwd(String pesel, String newPassword)
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
                            "    PESEL = '"+ pesel +"';"
            );
            s.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public boolean hasWeaponPermission(String pesel)
    {
        try {
            var s = conn.createStatement();
            var result = s.executeQuery(
                    "SELECT CzyUzytkownikBroni\n" +
                            "FROM Users\n" +
                            "WHERE\n" +
                            "\tPESEL = '"+ pesel +"';"
            );

            return result.getBoolean("CzyUzytkownikBroni");



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean hasPermission(String pesel, Permission permission)
    {
        try
        {
            var s = conn.createStatement();
            var result = s.executeQuery(
                    "SELECT Pozwolenia \n" +
                            "FROM Users\n" +
                            "WHERE\n" +
                            "PESEL = '" + pesel +"';"
            );
            var perm = result.getInt("Pozwolenia");

            result = s.executeQuery(
                    "SELECT Uprawnienia FROM Grupy\n" +
                            "WHERE\n" +
                            "ID = (\n" +
                            "  SELECT Grupa FROM Users WHERE PESEL = '"+ pesel +"');"
            );
            var gperm = result.getInt("Uprawnienia");

            perm = perm|gperm;

            return (perm & permission.getId()) > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public User login(String login, String password)
    {

        try
        {
            /*var s = conn.createStatement();
            var result = s.executeQuery(
                    "SELECT PESEL \n" +
                            "FROM Users\n" +
                            "WHERE\n" +
                            "" + "';"
            );
            var perm = result.getInt("Pozwolenia");



            return (perm & permission.getId()) > 0;*/

            var s = conn.createStatement();
            var result = s.executeQuery(
                    "SELECT Sol, Haslo, PESEL, Imie, Nazwisko\n" +
                            "FROM Users\n" +
                            "WHERE\n" +
                            "Login = '" + login + "';"
            );

//            System.out.println(result.isClosed());

            if (!result.next())
            {
                return null;
            }
            else
            {
//                result.beforeFirst();
            }

            var saltEnc = result.getString("Sol");

            var dec = Base64.getDecoder();
            var salt = dec.decode(saltEnc);

            /*result = s.executeQuery(
                    "SELECT Haslo\n" +
                            "FROM Users\n" +
                            "WHERE\n" +
                            "Login = '" + login + "';"
            );*/

            var truepass = result.getString("Haslo");

            var sbytes = password.getBytes();

            var bytes = new byte[salt.length + sbytes.length];
            System.arraycopy(sbytes, 0, bytes, 0, sbytes.length);
            System.arraycopy(salt, 0, bytes, sbytes.length, salt.length);

            String pass = DigestUtils.sha1Hex(bytes);

            if(pass.equals(truepass))
            {
//                return result.getString("PESEL");
                var pesel = result.getString("PESEL");
                var imie = result.getString("Imie");
                var nazwisko = result.getString("Nazwisko");
                return new User(pesel, imie, nazwisko);
            }
            else
            {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }


    public void close()
    {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> getAllUsers()
    {
        var array = new ArrayList<String[]>();

        try {
            var s = conn.createStatement();
            var result = s.executeQuery(
                    "SELECT Imie, Nazwisko, Mail, Login, Telefon, Pozwolenia, CzyUzytkownikBroni, Nazwa\n" +
                            "FROM (\n" +
                            "Users JOIN Grupy ON Users.Grupa = Grupy.ID);"
            );
            while(result.next())
            {
                String bron = result.getBoolean("CzyUzytkownikBroni") ? "tak" : "nie";
                StringBuilder permisje = new StringBuilder();
                for(Permission p : Permission.values())
                {
                    if((p.getId()|result.getInt("Pozwolenia"))>0)
                    {
                        permisje.append(p.getName()).append(",");
                    }

                }


                var str = new String[] {
                        result.getString("Imie"),
                        result.getString("Nazwisko"),
                        result.getString("Mail"),
                        result.getString("Login"),
                        result.getString("Telefon"),
                        permisje.toString(),
                        bron,
                        result.getString("Nazwa")
                };
                array.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return array;

    }


}
