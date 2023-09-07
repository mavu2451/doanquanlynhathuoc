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
	private TableColumn<LoaiThuoc, Integer> maLoaiSP;
	@FXML
	private TableColumn<LoaiThuoc, String> loaiSP;
	@FXML
	private ObservableList<LoaiThuoc> loaiSPList = FXCollections.observableArrayList();

	@FXML
	TableView<LoaiThuoc> table;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		reload();
		}
	public ObservableList<LoaiThuoc> getAllLoaiThuoc(){
		ObservableList<LoaiThuoc> loaiSPList = FXCollections.observableArrayList();
		String query = "select * from LoaiSP";	
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				LoaiThuoc lt = new LoaiThuoc();
				lt.setMaLoaiSP(rs.getInt("maLoaiSP"));
				lt.setLoaiSP(rs.getString("loaiSP"));
				loaiSPList.add(lt);
			}
		}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
		return loaiSPList;
		}
	

	public void cell() {
		maLoaiSP.setCellValueFactory(new PropertyValueFactory<LoaiThuoc, Integer>("maLoaiSP"));
		loaiSP.setCellValueFactory(new PropertyValueFactory<LoaiThuoc, String>("loaiSP"));
	}
	public void reload() {
		loaiSPList = getAllLoaiThuoc();
		cell();
		table.setItems(loaiSPList);
	}
}
