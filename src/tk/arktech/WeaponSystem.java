package tk.arktech;

public class WeaponSystem
{
  static public boolean register(User user,User userRegister)
  {

    if(!user.hasPermission(Permission.RU)) return false;


    return true;
  }

/*static public boolean changePermissions(User user, User userChange, Permission[] newPermissions)
  {
    //trzeba sprawdzic, czy user ma prawo zmienic uprawnienia userChange, jesli tak to zmienic uprawnienia userChange
    if(!user.hasPermission(Permission.ZU)) return false;


    return true;
  }*/


  static public boolean removeUser(User user, String pesel)
  {
    //trzeba sprawdzic, czy user ma prawo usunac userRemove
    if(!user.hasPermission(Permission.UU)) return false;

    var sql = SQL.getInstance();
    return sql.removeUser(pesel);


    //return true;
  }

  static public boolean changePassword(User user, String pesel, String newPassword)
  {
    //trzeba sprawdzic, czy user ma prawo sam zmienic swoje haslo
//    if(user.getTyp().equals(UserType.Administrator) || user.getTyp().equals(UserType.OrganUprawniony)
//    || user.getTyp().equals(UserType.PodmiotKoncesjonowany)) return false;
//
//    return true;

      if(!user.hasPermission(Permission.RH)) return false;

      var sql = SQL.getInstance();
      sql.changePwd(pesel, newPassword);
      return true;


  }

  static public boolean hasWeaponPermission(User user)
  {
    var sql = SQL.getInstance();
    return sql.hasWeaponPermission(user.getID());
  }

  /*static public User[] showUsers()
  {




    return new User[1];
  }*/

}
