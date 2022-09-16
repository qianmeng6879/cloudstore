package top.mxzero.common.utils;


public class PasswordUtil {
    private PasswordUtil() {
    }

    public static String hashPassword(String password, String salt) {

        String hashSalt = MD5Util.md5(salt);

        return MD5Util.md5(password + hashSalt);
    }

    public static boolean checkPassword(String password, String hashPwd, String salt){
        return hashPwd.equals(hashPassword(password, salt));
    }

}
