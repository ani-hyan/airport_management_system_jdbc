package org.example.service;

import org.example.connection.ConnectionToDB;
import org.example.entity.Company;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class CompanyService {
    private Connection con= ConnectionToDB.getConnection();

    /**
     * Finds company by the given id
     * @param id
     * @return company
     */
    public Company getById(int id){
        checkId(id);
        String sql = "SELECT * FROM companies WHERE id = ?";

        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()){
                String name = rs.getString("name");
                Date date = rs.getDate("found_date");

                return new Company(id, name, date);
            }
        }
        catch(SQLException e){
            throw  new RuntimeException(e);
        }
        finally {
            try{
                assert pst != null;
                pst.close();
                assert rs != null;
                rs.close();
            }
            catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * Gives set of all companies
     * @return set of companies
     */
    public Set<Company> getAll(){
        Set<Company> set = new LinkedHashSet<>();
        String sql = "SELECT * FROM companies";

        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();
            while (rs.next()){
                Company company = new Company();
                company.setCompanyId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                company.setFoundDate(rs.getDate("found_date"));

                set.add(company);
            }
        }
        catch(SQLException e){
            throw  new RuntimeException(e);
        }
        finally {
            try{
                assert pst != null;
                pst.close();
                assert rs != null;
                rs.close();
            }
            catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return set;
    }

    /**
     * Inserts given Company into table
     * @param company
     */
    public void save(Company company){
        String sql = "INSERT INTO companies(id, name, found_date) values(?, ?, ?)";

        PreparedStatement pst = null;

        try {
            pst = con.prepareStatement(sql);


            pst.setInt(1, company.getCompanyId());
            pst.setString(2, company.getName());
            pst.setDate(3, company.getFoundDate());


            pst.executeUpdate();
        }
        catch(SQLException e){
            throw  new RuntimeException(e);
        }
        finally {
            try{
                assert pst != null;
                pst.close();

            }
            catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Updates company with the given id
     * @param company
     * @param id
     * @return the company to be updated
     */
    public Company update(Company company, int id){
        Company old = getById(id);

        String sql = "UPDATE companies SET  name=?,found_date WHERE id=?";
        PreparedStatement pst = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, company.getName());
            pst.setDate(2, company.getFoundDate());
            pst.setInt(3, id);

            pst.executeUpdate();
        }
        catch(SQLException e){
            throw  new RuntimeException(e);
        }
        finally {
            try{
                assert pst != null;
                pst.close();

            }
            catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return old;
    }

    /**
     * Selects all companies with the given limits
     * @param offset
     * @param perPage
     * @param sort
     * @return set of companies
     */
    public Set<Company> get(int offset, int perPage, String sort){
        Set<Company> companies = new LinkedHashSet<>();

        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = con.prepareStatement("select * from companies order by " + sort + " limit ? offset ?");
            pst.setInt(1, perPage);
            pst.setInt(2, offset);
            rs = pst.executeQuery();

            while (rs.next()) {
                Company company = new Company();
                company.setCompanyId(rs.getInt(1));
                company.setName(rs.getString(2));
                company.setFoundDate(rs.getDate(3));

               companies.add(company);
            }
            return companies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                assert pst != null;
                assert rs != null;
                pst.close();
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Checks whether given id is valid or not
     * @param id
     */
    private void checkId(int id){
        if(id < 2)
            throw new IllegalArgumentException("Invalid id number");
    }
}
