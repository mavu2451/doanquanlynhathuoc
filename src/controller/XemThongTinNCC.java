package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import database.KetNoiDatabase;
import entity.NhaCungCap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class XemThongTinNCC implements Initializable{
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;

	@FXML
	private TableColumn<NhaCungCap, Integer> maNCC;
	@FXML
	private TableColumn<NhaCungCap, String> tenNCC;
	@FXML
	TableView<NhaCungCap> table;
	@FXML
	ObservableList<NhaCungCap> nccList = FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		reload();
	}
	
	public ObservableList<NhaCungCap> getAllNCC(){
		ObservableList<NhaCungCap> nccList = FXCollections.observableArrayList();
		String query = "select * from NhaCungCap";
		
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				NhaCungCap t = new NhaCungCap();
				t.setMaNCC(rs.getInt("maNCC"));
				t.setTenNCC(rs.getString("tenNCC"));
				nccList.add(t);
			}
		}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
		return nccList;
		}

	public void cell() {
		maNCC.setCellValueFactory(new PropertyValueFactory<NhaCungCap, Integer>("maNCC"));
		tenNCC.setCellValueFactory(new PropertyValueFactory<NhaCungCap, String>("tenNCC"));
	}
	public void reload() {
		cell();
		nccList = getAllNCC();
		table.setItems(nccList);
	}
}
