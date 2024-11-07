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
public class Proyek {
    private int id;
    private String nama_proyek;
    private String durasi_pengerjaan;
    
    public Proyek(int id, String nama_proyek, String durasi_pengerjaan) {
        this.id = id;
        this.nama_proyek = nama_proyek;
        this.durasi_pengerjaan = durasi_pengerjaan;
    }
    
    public static boolean addProyek(String nama_proyek, String durasi_pengerjaan) {
        String sql = "INSERT INTO proyek (nama_proyek, durasi_pengerjaan) VALUES (?, ?)";
        try (Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nama_proyek);
            ps.setString(2, durasi_pengerjaan);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static List<Proyek> getAllProyek() {
        List<Proyek> proyekList = new ArrayList<>();
        String query = "SELECT * FROM proyek";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama_proyek = rs.getString("nama_proyek");
                String durasi_pengerjaan = rs.getString("durasi_pengerjaan");
                proyekList.add(new Proyek(id, nama_proyek, durasi_pengerjaan));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return proyekList;
    }
    public static Proyek getProyek(int id) {
        Proyek proyek = null;
        String query = "SELECT * FROM proyek WHERE id = ?";
        
        try (Connection connection = koneksi.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                proyek = new Proyek(
                    rs.getInt("id"),
                    rs.getString("nama_proyek"),
                    rs.getString("durasi_pengerjaan")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return proyek;
    }
    
    public static boolean updateProyek(int id, String nama_proyek, String durasi_pengerjaan) {
        String sql = "UPDATE proyek SET nama_proyek = ?, durasi_pengerjaan = ? WHERE id = ?";
        try (Connection conn = koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nama_proyek);
            ps.setString(2, durasi_pengerjaan);
            ps.setInt(3, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error Tambah Data di:" + e.getMessage());
            return false;
        }
    }
    
    public static boolean deleteProyek(int id) {
        String sql = "DELETE FROM proyek WHERE id = ?";
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
    public String getNama_proyek() {
        return nama_proyek;
    }
    public String getDurasi_pengerjaan() {
        return durasi_pengerjaan;
    }
    
    public void setNama_proyek(String nama_proyek) {
        this.nama_proyek = nama_proyek;
    }
    public void setDurasi_pengerjaan(String durasi_pengerjaan) {
        this.durasi_pengerjaan = durasi_pengerjaan;
    }
}
