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
public class Karyawan {
    private int id;
    private String nama;
    private String jabatan;
    private String departemen;
    
    public Karyawan(int id, String nama, String jabatan, String departemen) {
        this.id = id;
        this.nama = nama;
        this.jabatan = jabatan;
        this.departemen = departemen;
    }
    
    public static boolean addKaryawan(String nama, String jabatan, String departemen) {
        String sql = "INSERT INTO karyawan (nama, jabatan, departemen) VALUES (?, ?, ?)";
        try (Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nama);
            ps.setString(2, jabatan);
            ps.setString(3, departemen);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static List<Karyawan> getAllKaryawan() {
        List<Karyawan> karyawanList = new ArrayList<>();
        String query = "SELECT * FROM karyawan";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String jabatan = rs.getString("jabatan");
                String departemen = rs.getString("departemen");
                karyawanList.add(new Karyawan(id, nama, jabatan, departemen));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return karyawanList;
    }
    public static Karyawan getKaryawan(int id) {
        Karyawan karyawan = null;
        String query = "SELECT * FROM karyawan WHERE id = ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                karyawan = new Karyawan(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("jabatan"),
                    rs.getString("departemen")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return karyawan;
    }
    
    public static boolean updateKaryawan(int id, String nama, String jabatan, String departemen) {
        String sql = "UPDATE karyawan SET nama = ?, jabatan = ?, departemen = ? WHERE id = ?";
        try (Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nama);
            ps.setString(2, jabatan);
            ps.setString(3, departemen);
            ps.setInt(4, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static boolean deleteKaryawan(int id) {
        String sql = "DELETE FROM karyawan WHERE id = ?";
        try (Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public int getId() {
        return id;
    }
    public String getNama() {
        return nama;
    }
    public String getJabatan() {
        return jabatan;
    }
    public String getDepartemen() {
        return departemen;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }
}
