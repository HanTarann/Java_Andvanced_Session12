package Bai02;

import utils.GetConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    // P1: setDouble và setInt chuẩn hóa dữ liệu, không lo dấu chấm/dấu phẩy theo Locale
    public static void main(String[] args) {

        int patientId = 1;
        double newTemperature = 37.5;
        int newHeartRate = 75;

        String sql = "UPDATE Patients SET temperature = ?, heart_rate = ? WHERE id = ?";

        try (Connection con = GetConnect.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Gán giá trị an toàn, không lo locale
            ps.setDouble(1, newTemperature);
            ps.setInt(2, newHeartRate);
            ps.setInt(3, patientId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Cập nhật chỉ số sinh tồn thành công!");
            } else {
                System.out.println("Không tìm thấy bệnh nhân với id = " + patientId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}