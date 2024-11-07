/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Transaksi {
    private int id_transaksi;
    private int id_karyawan;
    private int id_proyek;
    private String peran;
    
    public Transaksi(int id_transaksi, int id_karyawan, int id_proyek, String peran) {
        this.id_transaksi = id_transaksi;
        this.id_karyawan = id_karyawan;
        this.id_proyek = id_proyek;
        this.peran = peran;
    }
    
    public static boolean addTransaksi(int id_karyawan, int id_proyek, String peran) {
        String sql = "INSERT INTO transaksi (id_karyawan, id_proyek, peran) VALUES (?, ?, ?)";
        try (Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_karyawan);
            ps.setInt(2, id_proyek);
            ps.setString(3, peran);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static List<Transaksi> getAllTransaksi() {
        List<Transaksi> transaksiList = new ArrayList<>();
        String query = "SELECT * FROM transaksi";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id_transaksi = rs.getInt("id_transaksi");
                int id_karyawan = rs.getInt("id_karyawan");
                int id_proyek = rs.getInt("id_proyek");
                String peran = rs.getString("peran");
                transaksiList.add(new Transaksi(id_transaksi, id_karyawan, id_proyek, peran));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return transaksiList;
    }
    public static Transaksi getTransaksi(int id) {
        Transaksi transaksi = null;
        String query = "SELECT * FROM transaksi WHERE id_transaksi = ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                transaksi = new Transaksi(
                    rs.getInt("id_transaksi"),
                    rs.getInt("id_karyawan"),
                    rs.getInt("id_proyek"),
                    rs.getString("peran")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return transaksi;
    }
    
    public static boolean updateTransaksi(int id_transaksi, int id_karyawan, int id_proyek, String peran) {
        String sql = "UPDATE transaksi SET id_karyawan = ?, id_proyek = ?, peran = ? WHERE id_transaksi = ?";
        try (Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_karyawan);
            ps.setInt(2, id_proyek);
            ps.setString(3, peran);
            ps.setInt(4, id_transaksi);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static boolean deleteTransaksi(int id_transaksi) {
        String sql = "DELETE FROM transaksi WHERE id_transaksi = ?";
        try (Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_transaksi);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public int getId_transaksi() {
        return id_transaksi;
    }
    public int getId_karyawan() {
        return id_karyawan;
    }
    public int getId_proyek() {
        return id_proyek;
    }
    public String getPeran() {
        return peran;
    }
    
    public void setId_karyawan(int id_karyawan) {
        this.id_karyawan = id_karyawan;
    }
    public void setId_proyek(int id_proyek) {
        this.id_proyek = id_proyek;
    }
    public void setPeran(String peran) {
        this.peran = peran;
    }
}
