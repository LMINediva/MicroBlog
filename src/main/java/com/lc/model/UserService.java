package com.lc.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author DELL
 * @date 2021/12/19 10:56
 */
public class UserService {
    private String USERS;

    public UserService(String USERS) {
        this.USERS = USERS;
    }

    public boolean isUserExisted(String username) {
        return isInvalidUsername(username);
    }

    /**
     * 是否为不合法用户名称
     *
     * @param username 用户名称
     * @return 是否为不合法用户名称
     */
    public boolean isInvalidUsername(String username) {
        File users = new File(USERS);
        if (!users.exists()) {
            users.mkdir();
        }
        for (String file : users.list()) {
            if (file.equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 创建用户目录与基本资料
     *
     * @param email    用户邮箱
     * @param username 用户名
     * @param password 用户密码
     * @throws IOException IO异常
     */
    public void createUserData(String email, String username, String password) throws IOException {
        File userhome = new File(USERS + "/" + username);
        if (!userhome.exists()) {
            userhome.mkdir();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(userhome + "/profile"));
        writer.write(email + "\t" + password);
        writer.close();
    }

    /**
     * 检查登录用户名称与密码
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 用户登录名称与密码是否正确
     * @throws IOException IO异常
     */
    public boolean checkLogin(String username, String password) throws IOException {
        if (username != null && password != null) {
            for (String file : new File(USERS).list()) {
                if (file.equals(username)) {
                    BufferedReader reader = new BufferedReader(new FileReader(USERS + "/" + file + "/profile"));
                    String passwd = reader.readLine().split("\t")[1];
                    if (passwd.equals(password)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 用以过滤.txt文件名
     */
    private class TxtFilenameFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".txt");
        }
    }

    private TxtFilenameFilter filenameFilter = new TxtFilenameFilter();

    /**
     * TreeMap排序用，因为希望信息的日期越近的在越上头显示
     */
    private class DateComparator implements Comparator<Date> {
        @Override
        public int compare(Date d1, Date d2) {
            return -d1.compareTo(d2);
        }
    }

    private DateComparator comparator = new DateComparator();

    /**
     * 读取消息
     *
     * @param blah 消息对象
     * @return 消息对象数组
     * @throws IOException IO异常
     */
    public List<Blah> getBlahs(Blah blah) throws IOException {
        File border = new File(USERS + "/" + blah.getUsername());
        String[] txts = border.list(filenameFilter);

        Map<Date, String> messages = new TreeMap<>(comparator);
        for (String txt : txts) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(USERS + "/" + blah.getUsername() + "/" + txt), StandardCharsets.UTF_8));
            String text = null;
            StringBuilder builder = new StringBuilder();
            while ((text = reader.readLine()) != null) {
                builder.append(text);
            }
            Date date = new Date(Long.parseLong(txt.substring(0, txt.indexOf(".txt"))));
            messages.put(date, builder.toString());
            reader.close();
        }

        List<Blah> blahs = new ArrayList<>();
        for (Date date : messages.keySet()) {
            String txt = messages.get(date);
            blahs.add(new Blah(blah.getUsername(), date, txt));
        }
        return blahs;
    }

    /**
     * 添加信息
     *
     * @param blah 消息对象
     * @throws IOException IO异常
     */
    public void addBlah(Blah blah) throws IOException {
        String file = USERS + "/" + blah.getUsername() + "/" +
                blah.getDate().getTime() + ".txt";
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
        writer.write(blah.getTxt());
        writer.close();
    }

    /**
     * 删除消息
     *
     * @param blah 消息对象
     */
    public void deleteBlah(Blah blah) {
        File file = new File(USERS + "/" + blah.getUsername() + "/" +
                blah.getDate().getTime() + ".txt");
        if (file.exists()) {
            file.delete();
        }
    }
}
