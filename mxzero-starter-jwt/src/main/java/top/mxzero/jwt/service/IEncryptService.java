package top.mxzero.jwt.service;

public interface IEncryptService {
    public String getEncryptPassword(String password, String salt);
}
