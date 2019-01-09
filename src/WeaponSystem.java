/*
public class WeaponSystem
{
  static public boolean register(User user,User userRegister)
  {
    //trzeba sprawdzic, czy user ma prawo dodac userRegister, jesli tak to dodac userRegister do systemu
    if(!user.hasPermission(Permission.RU)) return false;


    return true;
  }

  */
/*static public boolean changePermissions(User user, User userChange, Permission[] newPermissions)
  {
    //trzeba sprawdzic, czy user ma prawo zmienic uprawnienia userChange, jesli tak to zmienic uprawnienia userChange
    if(!user.hasPermission(Permission.ZU)) return false;


    return true;
  }*//*


  static public boolean removeUser(User user, User userRemove)
  {
    //trzeba sprawdzic, czy user ma prawo usunac userRemove
    if(!user.hasPermission(Permission.UU)) return false;


    return true;
  }

  static public boolean changePassword(User user, String newPassword)
  {
    //trzeba sprawdzic, czy user ma prawo sam zmienic swoje haslo
    if(user.getTyp().equals(UserType.Administrator) || user.getTyp().equals(UserType.OrganUprawniony)
    || user.getTyp().equals(UserType.PodmiotKoncesjonowany)) return false;

    return true;
  }

  static public boolean hasWeaponPermission(User user)
  {
    //trzeba sprawdzic, czy user ma pozwolenie w systemie
    return false;
  }

  static public User[] showUsers()
  {
    //zwraca wszystkie osoby w systemie
    return new User[1];
  }

}
*/
