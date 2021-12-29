package com.lc.model;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DELL
 * @date 2021/12/29 22:30
 */
public class BlahDAOJdbcImpl implements BlahDAO {
    private DataSource dataSource;

    public BlahDAOJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Blah> getBlahs(Blah blah) {
        Connection conn = null;
        PreparedStatement stmt = null;
        SQLException ex = null;
        List<Blah> blahs = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(
                    "SELECT date, txt FROM t_blah WHERE name = ?");
            stmt.setString(1, blah.getUsername());
            ResultSet rs = stmt.executeQuery();
            blahs = new ArrayList<>();
            while (rs.next()) {
                blahs.add(new Blah(blah.getUsername(),
                        rs.getTimestamp(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            ex = e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    if (ex == null) {
                        ex = e;
                    }
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    if (ex == null) {
                        ex = e;
                    }
                }
            }
        }
        if (ex != null) {
            throw new RuntimeException(ex);
        }
        return blahs;
    }

    @Override
    public void addBlah(Blah blah) {
        Connection conn = null;
        PreparedStatement stmt = null;
        SQLException ex = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO t_blah(name, date, txt) VALUES(?, ?, ?)");
            stmt.setString(1, blah.getUsername());
            stmt.setTimestamp(2, new Timestamp(blah.getDate().getTime()));
            stmt.setString(3, blah.getTxt());
            stmt.executeUpdate();
        } catch (SQLException e) {
            ex = e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    if (ex == null) {
                        ex = e;
                    }
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    if (ex == null) {
                        ex = e;
                    }
                }
            }
        }
        if (ex != null) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteBlah(Blah blah) {
        Connection conn = null;
        PreparedStatement stmt = null;
        SQLException ex = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(
                    "DELETE FROM t_blah WHERE date = ?");
            stmt.setTimestamp(1, new Timestamp(blah.getDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            ex = e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    if (ex == null) {
                        ex = e;
                    }
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    if (ex == null) {
                        ex = e;
                    }
                }
            }
        }
        if (ex != null) {
            throw new RuntimeException(ex);
        }
    }
}
