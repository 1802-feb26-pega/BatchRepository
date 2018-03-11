package com.rv.bankasgn.access;

import com.rv.bankasgn.pojos.User;
import com.rv.bankasgn.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAccessImplRDB implements UserAccess{
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        boolean updated = false;
        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "SELECT * FROM users";
            Statement s = c.createStatement();
            ResultSet res = s.executeQuery(query);

            while(res.next()) {
                User cur = new User();

                cur.setUserId(res.getInt("user_id"));
                cur.setEmail(res.getString("username"));
                cur.setPassword(res.getString("pw"));
                cur.setFirstname(res.getString("firstname"));
                cur.setLastname(res.getString("lastname"));

                users.add(cur);
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return updated ? users : null;
    }

    @Override
    public User getUserByEmail(String email) {
        User target = new User();
        boolean updated = false;
        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "SELECT * FROM users " +
                           "WHERE username = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1,email);

            ResultSet res = ps.executeQuery();
            while(res.next()) {
                target.setUserId(res.getInt("user_id"));
                target.setEmail(res.getString("username"));
                target.setPassword(res.getString("pw"));
                target.setFirstname(res.getString("firstname"));
                target.setLastname(res.getString("lastname"));
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated ? target : null;
    }

    @Override
    public User getUserById(int id) {
        User target = new User();
        boolean updated = false;
        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "SELECT * FROM accounts WHERE account_id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet res = ps.executeQuery();

            while(res.next()) {
                target.setUserId(res.getInt("user_id"));
                target.setEmail(res.getString("username"));
                target.setPassword(res.getString("pw"));
                target.setFirstname(res.getString("firstname"));
                target.setLastname(res.getString("lastname"));
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return updated ? target : null;
    }

    @Override
    public void saveUser(User u) {
        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "INSERT INTO users (firstname,lastname,username,pw)" +
                    "VALUES (?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1,u.getFirstname());
            ps.setString(2,u.getLastname());
            ps.setString(3,u.getEmail());
            ps.setString(4,u.getPassword());

            int updateValue = ps.executeUpdate();
            System.out.println(updateValue + " updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateUser(User u) {
        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "{CALL update_user(?,?,?,?,?)}";
            CallableStatement ps = c.prepareCall(query);
            ps.setString(2,u.getFirstname());
            ps.setString(3,u.getLastname());
            ps.setString(4,u.getEmail());
            ps.setString(5,u.getPassword());
            ps.setInt(1, u.getUserId());

            int updateValue = ps.executeUpdate();
            System.out.println(updateValue + " updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeAll() {

    }
}
