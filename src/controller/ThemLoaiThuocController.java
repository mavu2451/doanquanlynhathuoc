package controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import database.KetNoiDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ThemLoaiThuocController implements Initializable{
	@FXML
	private TextField txtMaLoaiThuoc, txtLoaiThuoc;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void add(ActionEvent e) {
		String query ="Insert into LoaiSP(maLoaiSP ,loaiSP) values (?,?)";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, txtMaLoaiThuoc.getText().toString());
			ps.setString(2, txtLoaiThuoc.getText().toString());
			ps.execute();
			themThanhCongMessage();
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
			themThatBaiMessage();
		}
	}
	@FXML
	private void themThanhCongMessage() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Thông báo");
		alert.setContentText("Thêm thành công");
		alert.setHeaderText(null);
		alert.showAndWait();
	}
	@FXML
	private void themThatBaiMessage() {
		Alert alert = new Alert(AlertType.ERROR, "Thêm thất bại, không được bỏ trống", ButtonType.OK);
		alert.setTitle("Thông báo");
		alert.setHeaderText(null);
		alert.show();
	}
}
