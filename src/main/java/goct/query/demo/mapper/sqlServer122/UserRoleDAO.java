package goct.query.demo.mapper.sqlServer122;

import goct.query.demo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class UserRoleDAO {
    @Autowired
    @Qualifier("ds3JdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    //根据用户电话查找拥有外修箱场权限的用户
    public UserInfo findPassUser(String phone) {
        String sql = "SELECT top(1) a.UserID,a.Account,a.Name,a.Type,a.Remark FROM [ePortal].SystemManagement.[User] a \n" +
                "                left join [ePortal].SystemManagement.[UserRole] b on a.UserID = b.UserID \n" +
                "                left join [ePortal].SystemManagement.[Role] c on b.RoleID = c.RoleID \n" +
                "                where c.Name = '外修箱场' and ? like concat('%',a.Remark,'%')";
        String[] params = {phone};
        UserInfo userInfo = null;
        try {
            userInfo = (UserInfo) jdbcTemplate.queryForObject(sql, params, new UserInfo());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    //根据账号验证拥有外修箱场权限的用户
    public UserInfo findByAccout(String account){
        String sql = "SELECT top(1) a.UserID,a.Account,a.Name,a.Type,a.Remark FROM [ePortal].SystemManagement.[User] a \n" +
                "                left join [ePortal].SystemManagement.[UserRole] b on a.UserID = b.UserID \n" +
                "                left join [ePortal].SystemManagement.[Role] c on b.RoleID = c.RoleID \n" +
                "                where c.Name = '外修箱场' and a.Account = ?";
        String[] params = {account};
        UserInfo userInfo = null;
        try {
            userInfo = (UserInfo) jdbcTemplate.queryForObject(sql, params, new UserInfo());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
}

