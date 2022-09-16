package top.mxzero.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.mxzero.jwt.config.EncryptConfigProperties;
import top.mxzero.jwt.service.IEncryptService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptServiceImpl implements IEncryptService {
    @Autowired
    private EncryptConfigProperties encryptConfigProperties; // 属性配置

    private static MessageDigest MD5_DIGEST; // MD5加密处理

    private static final Base64.Encoder BASE64_ENCODER = Base64.getEncoder(); // 加密器

    static {    // 初始化操作
        try {
            MD5_DIGEST = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getEncryptPassword(String password, String salt) {
        String saltPassword = "{" + salt + "}" + password;
        for (int x = 0 ; x < this.encryptConfigProperties.getRepeat(); x ++) {
            saltPassword = BASE64_ENCODER.encodeToString(MD5_DIGEST.digest(saltPassword.getBytes()));
        }
        return saltPassword;
    }
}
