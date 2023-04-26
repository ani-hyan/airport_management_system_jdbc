package org.example.insert_data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class Inserter {
    private static final String filePath = "C:\\Users\\PC\\IdeaProjects\\airport_management_system_jdbc\\src\\main\\resources";

    public static void insertIntoCompany(Connection con) {
        String sql = "INSERT INTO companies(name, found_date) values(?, ?)";
        try {
            FileReader fileReader = new FileReader(filePath + "\\companies.txt");
            BufferedReader bReader = new BufferedReader(fileReader);

            String line;
            while ((line = bReader.readLine()) != null) {
                try {
                    String[] array = line.split(",");
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, array[0]);
                    String[] dateParts = array[1].split("/");
                    ps.setDate(
                            2,
                            Date.valueOf(
                                    LocalDate.of(
                                            Integer.parseInt(dateParts[2]),
                                            Integer.parseInt(dateParts[0]),
                                            Integer.parseInt(dateParts[1])
                                    )
                            )
                    );
                    ps.executeUpdate();
                    ps.close();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertIntoPassengers(Connection con) {
        String sql = "INSERT INTO passengers(name, phone, country, city) values(?, ?, ?, ?)";
        try {
            FileReader fileReader = new FileReader(filePath + "\\passengers.txt");
            BufferedReader bReader = new BufferedReader(fileReader);

            String line;
            while ((line = bReader.readLine()) != null) {
                try {
                    String[] array = line.split(",");
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, array[0]);
                    ps.setString(2, array[1]);
                    ps.setString(3, array[2]);
                    ps.setString(4, array[3]);

                    ps.executeUpdate();
                    ps.close();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteRows(Connection con, int id){
        String sql = "DELETE FROM passengers WHERE id = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void insertIntoTrip(Connection con) {
        String sql = "INSERT INTO trip(trip_id, company_id, airplane, town_from, town_to, time_out, time_in) values(?, ?, ?, ?, ?, ?, ?)";
        try {
            FileReader fileReader = new FileReader(filePath + "\\trip.txt");
            BufferedReader bReader = new BufferedReader(fileReader);

            String line;
            while ((line = bReader.readLine()) != null) {
                try {
                    String[] array = line.split(",");
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, Integer.parseInt(array[0]));
                    ps.setInt(2, Integer.parseInt(array[1]));
                    ps.setString(3, array[2]);
                    ps.setString(4, array[3]);
                    ps.setString(5, array[4]);
                    ps.setTimestamp(6, Timestamp.valueOf(array[5]));
                    ps.setTimestamp(7, Timestamp.valueOf(array[6]));

                    ps.executeUpdate();
                    ps.close();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertIntoPassInTrip(Connection con) {
        String sql = "insert into pass_in_trip(trip_id, passenger_id, date, place) values(?, ?, ?, ?)";
        try {
            FileReader fileReader = new FileReader(filePath + "\\trip.txt");
            BufferedReader bReader = new BufferedReader(fileReader);

            String line;
            while ((line = bReader.readLine()) != null) {
                try {
                    String[] array = line.split(",");
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, Integer.parseInt(array[0]));
                    ps.setInt(2, Integer.parseInt(array[1]));
                    ps.setTimestamp(3, Timestamp.valueOf(array[2]));
                    ps.setString(4, array[3]);


                    ps.executeUpdate();
                    ps.close();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
