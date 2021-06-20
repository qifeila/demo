package goct.query.demo.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class UserInfo implements RowMapper, Serializable {
    private int userId;
    private String account;
    private String name;
    private String type;
    private String remark;

    @Override
    public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(rs.getInt("UserId"));
        userInfo.setName(rs.getString("Name"));
        userInfo.setAccount(rs.getString("Account"));
        userInfo.setType(rs.getString("Type"));
        userInfo.setRemark(rs.getString("Remark"));
        return userInfo;
}}
