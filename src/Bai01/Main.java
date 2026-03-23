package Bai01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.GetConnect;

public class Main {
    // P1: PreparedStatement tách SQL và dữ liệu, input không thể thay đổi cấu trúc câu lệnh, chống SQL Injection
    public static void main(String[] args) {
        String code = "doctor01";
        String pass = "12345";

        String sql = "SELECT * FROM Doctors WHERE code = ? AND password = ?";

        try (Connection con = GetConnect.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, code);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Đăng nhập thành công");
            } else {
                System.out.println("Sai tài khoản");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}