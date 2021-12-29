package com.lc.model;

/**
 * @author DELL
 * @date 2021/12/29 18:24
 */
public interface AccountDAO {
    boolean isUserExisted(Account account);

    void addAccount(Account account);

    Account getAccount(Account account);
}
