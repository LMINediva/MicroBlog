package com.lc.model;

import java.util.List;

/**
 * @author DELL
 * @date 2021/12/29 18:32
 */
public interface BlahDAO {
    List<Blah> getBlahs(Blah blah);

    void addBlah(Blah blah);

    void deleteBlah(Blah blah);
}
