package org.example.service;

import org.example.connection.ConnectionToDB;
import org.example.entity.Passenger;
import org.example.entity.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class TripService {
    private Connection con= ConnectionToDB.getConnection();

    /**
     * Finds trip by the given id
     * @param id
     * @return trip
     */
    public Trip getById(int id){
        Trip trip = new Trip();
        checkId(id);
        String sql = "SELECT * FROM tirp WHERE id = ?";

        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()){
                trip.setTripId(rs.getInt("trip_id"));
                trip.setCompanyId(rs.getInt("company_id"));
                trip.setAirplane(rs.getString("airplane"));
                trip.setTownFrom(rs.getString("town_from"));
                trip.setTownTo(rs.getString("town_to"));
                trip.setTimeOut(rs.getTimestamp("time_out"));
                trip.setTimeIn(rs.getTimestamp("time_in"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        return trip;
    }

    /**
     * Gives set of all trips
     * @return set of trips
     */
    public Set<Trip> getAll(){
        Set<Trip> set = new LinkedHashSet<>();
        String sql = "SELECT * FROM trip";

        PreparedStatement pst = null;
        ResultSet rs = null;

        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while(rs.next()){
                Trip trip = new Trip();
                trip.setTripId(rs.getInt("trip_id"));
                trip.setCompanyId(rs.getInt("company_id"));
                trip.setAirplane(rs.getString("airplane"));
                trip.setTownFrom(rs.getString("town_from"));
                trip.setTownTo(rs.getString("town_to"));
                trip.setTimeOut(rs.getTimestamp("time_out"));
                trip.setTimeIn(rs.getTimestamp("time_in"));
                set.add(trip);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
     * Inserts given Trip into table
     * @param trip
     */
    public void saveTrip(Trip trip){
        String sql = "INSERT INTO trip(trip_id,company_id,airplane,town_from,town_to,time_out,time_in) values(?,?,?,?,?,?,?)";
        PreparedStatement pst = null;

        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, trip.getTripId());
            pst.setInt(2, trip.getCompanyId());
            pst.setString(3, trip.getAirplane());
            pst.setString(4, trip.getTownFrom());
            pst.setString(5, trip.getTownTo());
            pst.setTimestamp(6, trip.getTimeOut());
            pst.setTimestamp(7, trip.getTimeIn());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
     * Updates existing trip
     * @param trip
     * @return
     */
    public Trip update(Trip trip){
        int id = trip.getTripId();
        Trip old = getById(id);

        String sql = "UPDATE trip SET company_id=?,airplane=?,town_from=?, town_to=?,time_out=?,time_in=? WHERE trip_id=?";
        PreparedStatement pst = null;

        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, trip.getCompanyId());
            pst.setString(2, trip.getAirplane());
            pst.setString(3, trip.getTownFrom());
            pst.setString(4, trip.getTownTo());
            pst.setTimestamp(5, trip.getTimeOut());
            pst.setTimestamp(6, trip.getTimeIn());
            pst.setInt(7, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
     * Checks whether given id is valid or not
     * @param id
     */
    private void checkId(int id){
        if(id < 2)
            throw new IllegalArgumentException("Invalid id number");
    }

}
