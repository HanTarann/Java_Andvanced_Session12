package Bai04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.GetConnect;

public class Main {
    public static void main(String[] args) {
        int patientId = 1;
        int totalTests = 1000;

        String sql = "INSERT INTO TestResults (patient_id, test_name, result) VALUES (?, ?, ?)";

        long startTime = System.currentTimeMillis();

        try (Connection con = GetConnect.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 1; i <= totalTests; i++) {
                ps.setInt(1, patientId);
                ps.setString(2, "Test_" + i);
                ps.setDouble(3, Math.random() * 10 + 30); // giả lập kết quả
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Đã nạp " + totalTests + " kết quả xét nghiệm trong " + (endTime - startTime) + " ms");
    }
}