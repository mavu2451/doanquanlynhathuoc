package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.KetNoiDatabase;
import entity.LoaiThuoc;
import entity.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LoaiThuocController implements Initializable {
	@FXML
	private SplitMenuButton smb;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	@FXML
	TableView<LoaiThuoc> table;
	@FXML
	private TableColumn<LoaiThuoc, Integer> maLoaiSP;
	@FXML
	private TableColumn<LoaiThuoc, String> loaiSP;
	@FXML
	private ObservableList<LoaiThuoc> loaiThuocList = FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		reload();
		getAllLoaiThuoc();
	}
	public void thuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) smb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Thuoc.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void loaiThuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) smb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/LoaiThuoc.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}

public void trangChu(ActionEvent e) throws IOException {
	Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/TrangChuQL.fxml"));
    Parent sampleParent = loader.load();
    Scene scene = new Scene(sampleParent);
    stage.setScene(scene);
}
public void nhanVien(ActionEvent e) throws IOException {
	Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/view/NhanVien.fxml"));
    Parent sampleParent = loader.load();
//    NhanVienController nv = loader.getController();
    Scene scene = new Scene(sampleParent);
    stage.setScene(scene);
}
	public void cell() {
		maLoaiSP.setCellValueFactory(new PropertyValueFactory<LoaiThuoc, Integer>("maLoaiSP"));
		loaiSP.setCellValueFactory(new PropertyValueFactory<LoaiThuoc, String>("loaiSP"));
	}
	public void themThuoc(ActionEvent e) throws IOException {
//		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThemLoaiThuoc.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
        stage.show();
	}
	public ObservableList<LoaiThuoc> getAllLoaiThuoc(){
		ObservableList<LoaiThuoc> loaiThuocList = FXCollections.observableArrayList();
		String sql = "select * from LoaiSP";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				LoaiThuoc lt = new LoaiThuoc();
				lt.setMaLoaiSP(rs.getInt("maLoaiSP"));
				lt.setLoaiSP(rs.getString("loaiSP"));
				loaiThuocList.add(lt);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loaiThuocList;
	}
	public void reload() {
		loaiThuocList = getAllLoaiThuoc();
		cell();
		table.setItems(loaiThuocList);
	}
}
