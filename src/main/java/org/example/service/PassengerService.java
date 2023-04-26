package org.example.service;

import org.example.connection.ConnectionToDB;
import org.example.entity.Passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class PassengerService {
    Connection con= ConnectionToDB.getConnection();

    /**
     * Finds passenger by the given id
     * @param id
     * @return passenger
     */
    public Passenger getById(int id){
        checkId(id);
        String sql = "SELECT * FROM passengers WHERE id = ?";

        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()){
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String country = rs.getString("country");
                String city = rs.getString("city");
                return new Passenger(id, name, phone, country, city);
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
     * Gives set of all passengers
     * @return set of passengers
     */
    public Set<Passenger> getAll(){
        Set<Passenger> set = new LinkedHashSet<>();
        String sql = "SELECT * FROM passengers";

        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();
            while (rs.next()){
                Passenger passenger = new Passenger();
                passenger.setId(rs.getInt("id"));
                passenger.setName(rs.getString("name"));
                passenger.setPhone(rs.getString("phone"));
                passenger.setCountry(rs.getString("country"));
                passenger.setCity(rs.getString("city"));
                set.add(passenger);
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
     * Inserts given Passenger into table
     * @param passenger
     */
    public void save(Passenger passenger){
        String sql = "INSERT INTO passengers(id, name, phone, country, city) values(?, ?, ?, ?, ?)";

        PreparedStatement pst = null;

        try {
            pst = con.prepareStatement(sql);


            pst.setInt(1, passenger.getId());
            pst.setString(2, passenger.getName());
            pst.setString(3, passenger.getPhone());
            pst.setString(4, passenger.getCountry());
            pst.setString(5, passenger.getCity());

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
     * Updates existing passenger
     * @param passenger
     * @return the passenger to be updated
     */
    public Passenger update(Passenger passenger){
        int id = passenger.getId();
        Passenger old = getById(id);

        String sql = "UPDATE passengers SET  name=?,phone=?,country=?,city=? WHERE id=?";
        PreparedStatement pst = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, passenger.getName());
            pst.setString(2, passenger.getPhone());
            pst.setString(3, passenger.getCountry());
            pst.setString(4, passenger.getCity());
            pst.setInt(5, id);

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


    //todo
    /**
     * Delete passenger by the given id
     * @param id
     */
    public void delete(int id){
        checkId(id);
        String sql = "DELETE FROM passengers WHERE id = ?";

        PreparedStatement pst = null;
        try{
            pst = con.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Selects all passengers with the given limits
     * @param offset
     * @param perPage
     * @param sort
     * @return set of passengers
     */
    public Set<Passenger> get(int offset, int perPage, String sort){
        Set<Passenger> passengers = new LinkedHashSet<>();

        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = con.prepareStatement("select * from passenger order by " + sort + " limit ? offset ?");
            pst.setInt(1, perPage);
            pst.setInt(2, offset);
            rs = pst.executeQuery();

            while (rs.next()) {
                Passenger passenger = new Passenger();
                passenger.setId(rs.getInt(1));
                passenger.setName(rs.getString(2));
                passenger.setPhone(rs.getString(3));
                passengers.add(passenger);
            }
            return passengers;
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
