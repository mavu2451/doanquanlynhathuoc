package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import database.KetNoiDatabase;
import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.Thuoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class XemThongTinLoaiThuoc implements Initializable{
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	@FXML
	private TableColumn<LoaiThuoc, Integer> maLoaiThuoc;
	@FXML
	private TableColumn<LoaiThuoc, String> LoaiThuoc;
	@FXML
	private ObservableList<LoaiThuoc> LoaiThuocList = FXCollections.observableArrayList();

	@FXML
	TableView<LoaiThuoc> table;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		reload();
		}
	public ObservableList<LoaiThuoc> getAllLoaiThuoc(){
		ObservableList<LoaiThuoc> LoaiThuocList = FXCollections.observableArrayList();
		String query = "select * from LoaiThuoc";	
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				LoaiThuoc lt = new LoaiThuoc();
				lt.setMaLoaiThuoc(rs.getInt("maLoaiThuoc"));
				lt.setLoaiThuoc(rs.getString("LoaiThuoc"));
				LoaiThuocList.add(lt);
			}
		}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
		return LoaiThuocList;
		}
	

	public void cell() {
		maLoaiThuoc.setCellValueFactory(new PropertyValueFactory<LoaiThuoc, Integer>("maLoaiThuoc"));
		LoaiThuoc.setCellValueFactory(new PropertyValueFactory<LoaiThuoc, String>("LoaiThuoc"));
	}
	public void reload() {
		LoaiThuocList = getAllLoaiThuoc();
		cell();
		table.setItems(LoaiThuocList);
	}
}
