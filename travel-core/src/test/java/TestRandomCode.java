import cn.linstudy.travel.utils.VerifyCodeUtils;

/**
 * @Description
 * 
 * @Date 2021/4/9 20:35
 */

public class TestRandomCode {

  public static void main(String[] args) {
    String s = VerifyCodeUtils.generateVerifyCode(4);
    System.out.println(s);
  }
}
