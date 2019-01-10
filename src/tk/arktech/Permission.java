package tk.arktech;

public enum Permission
{
  RU(524288, "RU"), UU(262144, "UU"), RH(131072, "RH"), CBF(65536, "CBF"), CBU(32768, "CBU"), ZU(16384, "ZU"), WP(8192, "WP"), WK(4096, "WK"), SP(2048, "SP"), RB(1024, "RB"), WWP(512, "WWP"), WSB(256, "WSB"), DMB(128, "DMB"), DEB(64, "DEB"),
  NB(32, "NB"), MHB(16, "MHB"), ZB(8, "ZB"), PA(4, "PA"), SB(2, "SB"), ED(1, "ED");

  private final int id;
  private final String name;
  Permission(int id, String name)
  {
    this.id = id;
    this.name = name;
  }
  public int getId()
  {
    return id;
  }
  public String getName() {return name;}
}
