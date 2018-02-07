package ru.zinal.gosthash;

import java.security.MessageDigest;

public class GOST3411MessageDigest  extends MessageDigest implements Cloneable {

  private GostHashIface hash;

  public GOST3411MessageDigest() {
    super("GOST3411");
    hash = new GostHashJava();
    hash.init();
    hash.startHash();
  }

  @Override
  protected void engineUpdate(byte input) {
    hash.hashBlock(new byte[]{input}, 0, 1);
  }

  @Override
  protected void engineUpdate(byte[] input, int offset, int len) {
    hash.hashBlock(input, offset, len);
  }

  @Override
  protected byte[] engineDigest() {
    return hash.finishHash();
  }

  @Override
  protected void engineReset() {
    hash.done();
    hash.init();
    hash.startHash();
  }
}
