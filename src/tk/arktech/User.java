package tk.arktech;

public class User
{
  private String ID;
//  private UserType typ;
  private String imie;
  private String nazwisko;
//  private String haslo;
//  private Permission[] uprawnienia;

  public String getImie()
  {
    return imie;
  }
//  public void setImie(String imie)
//  {
//    this.imie = imie;
//  }

  public String getNazwisko()
  {
    return nazwisko;
  }
//  public void setNazwisko(String nazwisko)
//  {
//    this.nazwisko = nazwisko;
//  }

  /*private String getHaslo()
  {
    return haslo;
  }
  private void setHaslo(String haslo)
  {
    this.haslo = haslo;
  }*/

  public String getID()
  {
    return ID;
  }

//  public UserType getTyp()
//  {
//    return typ;
//  }

  /*private void setPermissions(UserType typ)
  {
    switch(typ)
    {
      case Administrator:
      {
        uprawnienia = new Permission[6];
        uprawnienia[0] = Permission.RU;
        uprawnienia[1] = Permission.UU;
        uprawnienia[2] = Permission.RH;
        uprawnienia[3] = Permission.ZU;
        uprawnienia[4] = Permission.SP;
        uprawnienia[5] = Permission.PA;
      }
      case OrganUprawniony:
      {
        uprawnienia = new Permission[14];
        uprawnienia[0] = Permission.CBF;
        uprawnienia[1] = Permission.CBU;
        uprawnienia[2] = Permission.WP;
        uprawnienia[3] = Permission.WK;
        uprawnienia[4] = Permission.SP;
        uprawnienia[5] = Permission.RB;
        uprawnienia[6] = Permission.WWP;
        uprawnienia[7] = Permission.WSB;
        uprawnienia[8] = Permission.DEB;
        uprawnienia[9] = Permission.MHB;
        uprawnienia[10] = Permission.ZB;
        uprawnienia[11] = Permission.PA;
        uprawnienia[12] = Permission.SB;
        uprawnienia[13] = Permission.ED;
      }
      case PodmiotKoncesjonowany:
      {
        uprawnienia = new Permission[6];
        uprawnienia[0] = Permission.SP;
        uprawnienia[1] = Permission.DMB;
        uprawnienia[2] = Permission.DEB;
        uprawnienia[3] = Permission.NB;
        uprawnienia[4] = Permission.MHB;
        uprawnienia[5] = Permission.ED;
      }
      case WytworcaBroni:
      {
        uprawnienia = new Permission[3];
        uprawnienia[0] = Permission.SP;
        uprawnienia[1] = Permission.MHB;
        uprawnienia[2] = Permission.ED;
      }
      case ModyfikatorBroni:
      {
        uprawnienia = new Permission[2];
        uprawnienia[0] = Permission.SP;
        uprawnienia[1] = Permission.MHB;
      }
      case UzytkownikBroni:
      {
        uprawnienia = new Permission[1];
        uprawnienia[0] = Permission.SP;
      }
      default:
      {
        uprawnienia = null;
      }
    }
  }*/

  public User(/*UserType typ,*/ String pesel, String imie, String nazwisko/*, String haslo*/)
  {
    this.ID = pesel;
//    this.typ = typ;
    this.imie = imie;
    this.nazwisko = nazwisko;
//    this.haslo = haslo;

//    setPermissions();
  }

//  public User(UserType typ, long ID)
//  {
//    //TODO : robi Usera z bazy danych o takim ID, wszystko ustawia
//    //czy potrzebne jest UserType, czy wszyscy sa w jednej tabeli?
//  }

//  public boolean hasPermission(Permission permission)
//  {
//    for(Permission p : uprawnienia) if(p.equals(permission)) return true;
//    return false;
//  }

    public boolean hasPermission(Permission permission)
    {
        var s = SQL.getInstance();
        return s.hasPermission(this.ID, permission);
    }
}
