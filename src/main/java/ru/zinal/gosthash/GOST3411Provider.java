package ru.zinal.gosthash;

import java.security.Provider;

public class GOST3411Provider extends Provider{

  public GOST3411Provider() {
    super("GOST3411Provider", 1.0, "Russian GOST 34.11-94 provider implementation");
    put("MessageDigest.GOST3411", "ru.zinal.gosthash.GOST3411MessageDigest");
  }

}
