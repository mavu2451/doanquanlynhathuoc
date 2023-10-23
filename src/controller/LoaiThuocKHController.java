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
import javafx.scene.control.MenuButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LoaiThuocKHController implements Initializable {
	@FXML
	private MenuButton mb;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	@FXML
	TableView<LoaiThuoc> table;
	@FXML
	private TableColumn<LoaiThuoc, Integer> maLoaiThuoc;
	@FXML
	private TableColumn<LoaiThuoc, String> loaiThuoc;
	@FXML
	private ObservableList<LoaiThuoc> loaiThuocList = FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		reload();
		getAllLoaiThuoc();
	}
	//Start Navbar
	public void trangChu(ActionEvent e) throws IOException {
//		Stage stage = (Stage) mb.getScene().getWindow();
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TrangChuKH.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	
	public void timThuoc(ActionEvent e) throws IOException {
//		Stage stage = (Stage) mb.getScene().getWindow();
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TimKiemThuocKH.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void timLoaiThuoc(ActionEvent e) throws IOException {
//		Stage stage = (Stage) mb.getScene().getWindow();
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TimKiemLoaiThuocKH.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void dangNhap(ActionEvent e) throws IOException {
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/DangNhap.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
        stage.show();
	}
	//End Navbar
	public void cell() {
		maLoaiThuoc.setCellValueFactory(new PropertyValueFactory<LoaiThuoc, Integer>("maLoaiThuoc"));
		loaiThuoc.setCellValueFactory(new PropertyValueFactory<LoaiThuoc, String>("loaiThuoc"));
	}
	public ObservableList<LoaiThuoc> getAllLoaiThuoc(){
		ObservableList<LoaiThuoc> loaiThuocList = FXCollections.observableArrayList();
		String sql = "select * from loaiThuoc";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				LoaiThuoc lt = new LoaiThuoc();
				lt.setMaLoaiThuoc(rs.getInt("maLoaiThuoc"));
				lt.setTenLoaiThuoc(rs.getString("tenLoaiThuoc"));
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
