package Bai03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import utils.GetConnect;

public class Main {
    // P1: registerOutParameter() cho JDBC biết kiểu dữ liệu OUT
    public static void main(String[] args) {
        int surgeryId = 1;
        String sql = "{CALL GET_SURGERY_FEE(?, ?)}";

        try (Connection con = GetConnect.openConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, surgeryId);
            cs.registerOutParameter(2, java.sql.Types.DECIMAL);
            cs.execute();

            double totalCost = cs.getDouble(2);
            System.out.println("Tổng chi phí phẫu thuật (id=" + surgeryId + "): " + totalCost);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}