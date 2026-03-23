package Bai05;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PatientService service = new PatientService();

        while (true) {
            System.out.println("\n=== Rikkei Hospital Management System ===");
            System.out.println("1. Danh sách bệnh nhân");
            System.out.println("2. Tiếp nhận bệnh nhân mới");
            System.out.println("3. Cập nhật bệnh án");
            System.out.println("4. Xuất viện & Tính phí");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    service.listPatients();
                    break;
                case "2":
                    System.out.print("Tên bệnh nhân: ");
                    String name = sc.nextLine();
                    System.out.print("Tuổi: ");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.print("Khoa: ");
                    String dept = sc.nextLine();
                    service.addPatient(name, age, dept);
                    break;
                case "3":
                    System.out.print("ID bệnh nhân: ");
                    int idUpdate = Integer.parseInt(sc.nextLine());
                    System.out.print("Bệnh án mới: ");
                    String diagnosis = sc.nextLine();
                    service.updatePatientRecord(idUpdate, diagnosis);
                    break;
                case "4":
                    System.out.print("ID bệnh nhân: ");
                    int idDischarge = Integer.parseInt(sc.nextLine());
                    System.out.print("Số ngày nhập viện: ");
                    int days = Integer.parseInt(sc.nextLine());
                    service.dischargePatient(idDischarge, days);
                    break;
                case "5":
                    System.out.println("Thoát chương trình.");
                    sc.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}