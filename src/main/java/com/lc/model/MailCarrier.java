package com.lc.model;

/**
 * @author DELL
 * @date 2021/12/31 21:14
 */
public interface MailCarrier {
    void sendTo(Account account, String subject, String content);
}
