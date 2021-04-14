package cn.peilion.utils.threadlocal;


import cn.peilion.model.pojos.User;

public class AppThreadLocalUtils {

    private final static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程中的用户
     *
     * @param user
     */
    public static void setUser(User user) {
        userThreadLocal.set(user);
    }

    /**
     * 获取线程中的用户
     *
     * @return
     */
    public static User getUser() {
        return userThreadLocal.get();
    }

}
