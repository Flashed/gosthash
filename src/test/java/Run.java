import ru.zinal.gosthash.GOST3411Provider;
import ru.zinal.gosthash.GostHashJava;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.UUID;

public class Run {

  public static void main(String[] args) throws NoSuchAlgorithmException {
    Security.addProvider(new GOST3411Provider());
    MessageDigest digest = MessageDigest.getInstance("GOST3411");
    String str = UUID.randomUUID().toString();
    String newH, oldH;
    digest.update(str.getBytes());
    digest.update(str.getBytes());
    System.out.println("New: " + (newH = hexString(digest.digest())));

    GostHashJava hashJava = new GostHashJava();
    hashJava.init();
    hashJava.startHash();
    hashJava.hashBlock(str.getBytes(), 0, str.length());
    hashJava.hashBlock(str.getBytes(), 0, str.length());
    System.out.println("Old: " + ( oldH = hexString(hashJava.finishHash())));
    if (oldH.equals(newH)){
      System.out.println("Success.");
    } else {
      System.out.println("Fail.");
    }
  }

  private static String hexString(byte[] byteData){
    StringBuilder hexString = new StringBuilder();
    for (int i=0;i<byteData.length;i++) {
      String hex=Integer.toHexString(0xff & byteData[i]);
      if(hex.length()==1) hexString.append('0');
      hexString.append(hex);
    }
    return hexString.toString();
  }

}
