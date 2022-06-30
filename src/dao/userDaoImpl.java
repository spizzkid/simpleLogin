package dao;

import pojo.user;
import util.jdbcUtil;

import java.sql.SQLException;

public class userDaoImpl implements userDao{

    @Override
    public int add(user user) {
        return jdbcUtil.executeUpdate("insert into user(id,username,password,phone,email,question,answer) values(?,?,?,?,?,?,?)",
            user.getId(),user.getUsername(),user.getPassword(),user.getPhone(),user.getEmail(),user.getQuestion(),user.getAnswer());
    }

    @Override
    public user selectUser(String username) {
        return jdbcUtil.executeOne("select * from user where username=?", rs->{
            user u = new user();
            try{
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setPhone(rs.getString("phone"));
                u.setEmail(rs.getString("email"));
                u.setQuestion(rs.getString("question"));
                u.setAnswer(rs.getString("answer"));
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return u;
        },username);
    }

    @Override
    public int update(user user) {
        return jdbcUtil.executeUpdate("update user set password=? where username=?",
                user.getPassword(),user.getUsername());
    }


}
