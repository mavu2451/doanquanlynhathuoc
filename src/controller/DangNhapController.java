package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import database.KetNoiDatabase;
import entity.NhanVien;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DangNhapController implements Initializable{
	private NhanVienController getNV = new NhanVienController();
//	@FXML
//	ComboBox<?> cbbChucVu;
	String[] chucVu = new String[]{"Khách hàng", "Nhân viên", "Người quản lý"};
	FXMLLoader loader = new FXMLLoader();
	PreparedStatement ps;
	static ResultSet rs;
	@FXML
	private TextField txtTaiKhoan, txtMatKhau;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//		loaiChucVu();
	}
	
	public void quenMatKhau(ActionEvent e) throws IOException {
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/QuenMatKhau.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
	}
	public void goBack(ActionEvent e) throws IOException {
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/DangNhap.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
	}
	public void dangNhap(ActionEvent e) {
		String taiKhoan = txtTaiKhoan.getText();
		String matKhau = txtMatKhau.getText();
		String trangThai;
		String sql = "select * from NhanVien where email = ? and matKhau=?";
		if(taiKhoan.equals("") || matKhau.equals("")) {
			trong();
		}
		else
		try {
			Connection con = KetNoiDatabase.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, taiKhoan);
			ps.setString(2, matKhau);
//			ps.setString(3, trangThai);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				thanhCong();
				getNV();
				Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(getClass().getResource("/view/TrangChuQL.fxml"));
		        Parent sampleParent = loader.load();
		        Scene scene = new Scene(sampleParent);   
		        scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		        stage.setScene(scene);
			}
//			else if(rs.next() && trangThai.equals("Nghỉ việc")) {
//				nghiViec();
//			}
			else {
				saiMK();
				txtTaiKhoan.setText("");
				txtMatKhau.setText("");
			}
		}catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}
	//Dang nhap
	public static NhanVien getNV() {
		NhanVien nv = new NhanVien();
//		try {
			nv.setMaNV(1);
			nv.setHoTen("test1"); //xoa cai nay neu can dang nhap
//			nv.setHoTen(rs.getString("tenNV"));
//			nv.setMaNV(rs.getInt("maNV"));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return nv;
	}
	@FXML
	private void saiMK() {
		Alert alert = new Alert(AlertType.INFORMATION, "Sai mật khẩu, mời bạn xem lại", ButtonType.OK);
		alert.setTitle("Thông báo");
		alert.setHeaderText(null);
		alert.show();
	}
	@FXML
	private void nghiViec() {
		Alert alert = new Alert(AlertType.INFORMATION, "Nhân viên đã nghỉ việc", ButtonType.OK);
		alert.setTitle("Thông báo");
		alert.setHeaderText(null);
		alert.show();
	}
	@FXML
	private void thanhCong() {
		Alert alert = new Alert(AlertType.INFORMATION, "Đăng nhập thành công", ButtonType.OK);
		alert.setTitle("Thông báo");
		alert.setHeaderText(null);
		alert.show();
	}
	@FXML
	private void trong() {
		Alert alert = new Alert(AlertType.INFORMATION, "Tài khoản hoặc mật khẩu không được để trống", ButtonType.OK);
		alert.setTitle("Thông báo");
		alert.setHeaderText(null);
		alert.show();
	}
//	@FXML
//	public void loaiChucVu() {
//		ObservableList list = FXCollections.observableArrayList(chucVu);
//		cbbChucVu.setItems(list);
//	}

}
