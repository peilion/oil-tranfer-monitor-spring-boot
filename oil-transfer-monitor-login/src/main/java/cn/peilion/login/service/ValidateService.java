package cn.peilion.login.service;


import cn.peilion.model.pojos.User;

/**
 * 对称加密算法 DES AES
 * 散列算法 MD5 扩展加盐 salt
 */
public interface ValidateService {

    /**
     * DES验证
     *
     * @param user
     * @param db
     * @return
     */
    boolean validDES(User user, User db);

    /**
     * MD5验证
     *
     * @param user
     * @param db
     * @return
     */
    boolean validMD5(User user, User db);

    /**
     * MD5加盐验证
     *
     * @param user
     * @param db
     * @return
     */
    boolean validMD5WithSalt(User user, User db);
}