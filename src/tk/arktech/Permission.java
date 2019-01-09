package tk.arktech;

public enum Permission
{
  RU(524288), UU(262144), RH(131072), CBF(65536), CBU(32768), ZU(16384), WP(8192), WK(4096), SP(2048), RB(1024), WWP(512), WSB(256), DMB(128), DEB(64),
  NB(32), MHB(16), ZB(8), PA(4), SB(2), ED(1);

  private final int id;
  Permission(int id)
  {
    this.id = id;
  }
  public int getId()
  {
    return id;
  }
}
