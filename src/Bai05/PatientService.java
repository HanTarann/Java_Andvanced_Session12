package Bai05;

import utils.GetConnect;

import java.sql.*;

public class PatientService {
    public void listPatients() {
        String sql = "SELECT id, name, age, department FROM Patients";
        try (Connection con = GetConnect.openConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Danh sách bệnh nhân:");
            System.out.println("ID\tTên\tTuổi\tKhoa");
            while (rs.next()) {
                System.out.printf("%d\t%s\t%d\t%s\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPatient(String name, int age, String department) {
        String sql = "INSERT INTO Patients (name, age, department) VALUES (?, ?, ?)";
        try (Connection con = GetConnect.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, department);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Tiếp nhận bệnh nhân thành công!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePatientRecord(int id, String newDiagnosis) {
        String sql = "UPDATE Patients SET diagnosis = ? WHERE id = ?";
        try (Connection con = GetConnect.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newDiagnosis);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Cập nhật bệnh án thành công!");
            } else {
                System.out.println("Không tìm thấy bệnh nhân với ID = " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dischargePatient(int id, int days) {
        String sql = "{CALL CALCULATE_DISCHARGE_FEE(?, ?)}";
        try (Connection con = GetConnect.openConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, days);
            cs.registerOutParameter(2, Types.DECIMAL);

            cs.execute();

            double totalFee = cs.getDouble(2);
            System.out.println("Bệnh nhân ID=" + id + " xuất viện. Tổng viện phí: " + totalFee);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}