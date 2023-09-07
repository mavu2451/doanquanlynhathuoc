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
import entity.NhaCungCap;
import entity.Thuoc;
import entity.Thuoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ThuocController implements Initializable{
	@FXML
	ObservableList<Thuoc> thuocList = FXCollections.observableArrayList();
	@FXML
	TableView<Thuoc> table;
	@FXML
	private TableColumn<Thuoc, Integer> maThuoc;
	@FXML
	private TableColumn<Thuoc, String> tenThuoc;
	@FXML
	private TableColumn<Thuoc, String> loaiThuoc;
	@FXML
	private TableColumn<Thuoc, String> ncc;
	@FXML
	private TableColumn<Thuoc, String> nsx;
	@FXML
	private TableColumn<Thuoc, String> dvt;
	@FXML
	private TableColumn<Thuoc, Float> giaNhap;
	@FXML
	private TableColumn<Thuoc, Float> giaBan;
	@FXML
	private TableColumn<Thuoc, String> cachDung;
	@FXML
	private TableColumn<Thuoc, String> trangThai;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	@FXML
	private SplitMenuButton smb;
//	@FXML
//	private MenuItem mNhapHang;
	@FXML
	private Label lblName;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		reload();
		myName();
	}
	public void themThuoc(ActionEvent e) throws IOException {
//		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThemThuoc.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
        stage.show();
	}
	public void Thuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Thuoc.fxml"));
        Parent sampleParent = loader.load();
//        ThuocController nv = loader.getController();
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
	public void nhanVien(ActionEvent e) throws IOException {
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/NhanVien.fxml"));
        Parent sampleParent = loader.load();
//        NhanVienController nv = loader.getController();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
	}

	
	public void cell() {
		maThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, Integer>("maThuoc"));
		tenThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("tenThuoc"));
		loaiThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("loaiThuoc"));
		dvt.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("dvt"));
		ncc.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("ncc"));
		nsx.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("nsx"));
		giaNhap.setCellValueFactory(new PropertyValueFactory<Thuoc, Float>("giaNhap"));
		giaBan.setCellValueFactory(new PropertyValueFactory<Thuoc, Float>("giaBan"));
		cachDung.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("cachDung"));
		trangThai.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("trangThai"));
	}
	public void myName() {
		String sql = "select tenNV from NhanVien";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				lblName.setText("Xin chào, " + rs.getString("tenNV"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ObservableList<Thuoc> getAllThuoc(){
		ObservableList<Thuoc> thuocList = FXCollections.observableArrayList();
		String query = "\r\n"
				+ "select maThuoc, tenThuoc, loaiSP,tenNCC, donViTinh, giaNhap, giaBan, nuocSanXuat,cachDung, trangThai "
				+ "from Thuoc t inner join LoaiSP l on t.maLoaiSP = l.maLoaiSP inner join NhaCungCap n on"
				+ " n.maNCC = t.maNCC";
		
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				Thuoc t = new Thuoc();
				NhaCungCap ncc = new NhaCungCap();
				t.setMaThuoc(rs.getInt("maThuoc"));
				t.setTenThuoc(rs.getString("tenThuoc"));
//				t.setLoaiThuoc(rs.getString("loaiSP"));
				t.setNcc(ncc);
				t.setNsx(rs.getString("nuocSanXuat"));
				t.setDvt(rs.getString("donViTinh"));
				t.setGiaNhap(rs.getFloat("giaNhap"));
				t.setGiaBan(rs.getFloat("giaBan"));
				t.setCachDung(rs.getString("cachDung"));
				t.setTrangThai(rs.getString("trangThai"));
				thuocList.add(t);
				
			}
		}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
		return thuocList;
		}
	public void reload() {
		thuocList = getAllThuoc();
		// TODO Auto-generated method stub
		cell();
		table.setItems(thuocList);
	}

}
