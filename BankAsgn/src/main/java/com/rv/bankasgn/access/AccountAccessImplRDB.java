package com.rv.bankasgn.access;

import com.rv.bankasgn.util.ConnectionFactory;
import com.rv.bankasgn.pojos.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountAccessImplRDB implements AccountAccess {

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        boolean updated = false;
        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "SELECT * FROM accounts";
            Statement s = c.createStatement();
            ResultSet res = s.executeQuery(query);

            while(res.next()) {
                Account cur = new Account();

                cur.setAccountId(res.getInt("account_id"));
                cur.setBalance(res.getDouble("balance"));
                cur.setUserId(res.getInt("user_id"));

                accounts.add(cur);
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated ? accounts : null;
    }

    @Override
    public List<Account> getAccountsByUserId(int id) {
        List<Account> accounts = new ArrayList<>();
        boolean updated = false;
        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "SELECT * FROM accounts WHERE user_id = ?";
            PreparedStatement s = c.prepareStatement(query);
            s.setInt(1,id);
            ResultSet res = s.executeQuery();

            while(res.next()) {
                Account cur = new Account();

                cur.setAccountId(res.getInt("account_id"));
                cur.setBalance(res.getDouble("balance"));
                cur.setUserId(res.getInt("user_id"));

                accounts.add(cur);
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated ? accounts: null;
    }

    @Override
    public Account getAccountById(int id) {
        Account target = new Account();
        boolean updated = false;
        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "SELECT * FROM accounts WHERE account_id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet res = ps.executeQuery();

            while(res.next()) {
                target.setAccountId(res.getInt("account_id"));
                target.setBalance(res.getDouble("balance"));
                target.setUserId(res.getInt("user_id"));
                updated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException n) {
            return null;
        }

        return updated ? target : null;
    }

    @Override
    public void updateAccount(Account a) {

        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "UPDATE accounts " +
                           "SET balance = ?, user_id = ? " +
                           "WHERE account_id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setDouble(1, a.getBalance());
            ps.setInt(2, a.getUserId());
            ps.setInt(3, a.getAccountId());

            int updateValue = ps.executeUpdate();
            //System.out.println(updateValue + " updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveAccount(Account a) {

        try(Connection c = ConnectionFactory.getInstance().getConnection()) {
            String query = "INSERT INTO accounts (balance, user_id)" +
                           "VALUES (?,?)";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setDouble(1, a.getBalance());
            ps.setInt(2, a.getUserId());

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
