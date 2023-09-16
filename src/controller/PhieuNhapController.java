package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import database.KetNoiDatabase;
import entity.NhanVien;
import entity.PhieuNhap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PhieuNhapController implements Initializable{

	@FXML
	private TextField txtMaPN;
	@FXML
	private ComboBox<NhanVien> cbNhanVien;
	@FXML
	private TextField txtNCC;
	@FXML
	private DatePicker dpNgayNhap;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	@FXML
	TableView<PhieuNhap> table;
	@FXML
	private TableColumn<PhieuNhap, Integer> maPN;
	@FXML
	private TableColumn<PhieuNhap, String> hoTen;
	@FXML
	private TableColumn<PhieuNhap, String> ngayNhap;

	
	private ObservableList<PhieuNhap> list = FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		getAllPN();
		reload();
	}
	public void themPN(ActionEvent e) {
//		
//		String sql = "insert into PhieuNhap(maNV) values (?)";
//		try {
////			String maPN = txtMaPN.getText();
//			ps = con.prepareStatement(sql);
//			ps.setInt(1, getNV());
//			System.out.println(getNV());
//			ps.execute();		
//
//		}catch(Exception e1) {
//			e1.printStackTrace();
//		}
//		reload();
		
	}
	public int getNV() {
		NhanVien nv = new NhanVien();
		String sql = "select tenNV from NhanVien";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				nv.setMaNV(rs.getInt("maNV"));
				nv.setHoTen(rs.getString("tenNV"));
				return nv.getMaNV();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nv.getMaNV();
	}
	public void themTT(ActionEvent e){
		try {
			String sql = "select * from NhanVien";
			PreparedStatement ps;
			try {
				ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					NhanVien nv = new NhanVien();
					nv.setMaNV(rs.getInt("maNV"));
					themPhieuNhap(nv.getMaNV());
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/view/CTPhieuNhap.fxml"));
	        Parent sampleParent;
			sampleParent = loader.load();
	        Scene scene = new Scene(sampleParent);
//	        CTPhieuNhapController ct = loader.getController();
	        PhieuNhap pn = table.getSelectionModel().getSelectedItem();     
//	        ct.setMaPN(pn);
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			themThatBaiMessage();
		}
	}
	public void themPhieuNhap(int i){
		try {
			String sql = "insert into PhieuNhap(maNV) values (?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, i);
			ps.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void trangChu(ActionEvent e) throws IOException {
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TrangChuQL.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
	}
	
	public void cell() {
		maPN.setCellValueFactory(new PropertyValueFactory<PhieuNhap, Integer>("maPN"));
		hoTen.setCellValueFactory(new PropertyValueFactory<PhieuNhap, String>("hoTen"));
		ngayNhap.setCellValueFactory(new PropertyValueFactory<PhieuNhap, String>("ngayNhap"));

		
	}
	public void reload() {
		list = getAllPN();
		cell();
		table.setItems(list);
	}
	public ObservableList<PhieuNhap> getAllPN(){
		ObservableList<PhieuNhap> pnlist = FXCollections.observableArrayList();
		String query = "select maPN, tenNV, ngayNhap from PhieuNhap p inner join NhanVien n on n.maNV = p.maNV";
		
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				PhieuNhap pn = new PhieuNhap();
				pn.setMaPN(rs.getInt("maPN"));
				pn.setHoTen(rs.getString("tenNV"));
				pn.setNgayNhap(rs.getDate("ngayNhap"));
				pnlist.add(pn);
			}
		}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
		return pnlist;
}
	@FXML
	private void themThatBaiMessage() {
		Alert alert = new Alert(AlertType.ERROR, "Mời chọn cột phiếu nhập", ButtonType.OK);
		alert.setTitle("Thông báo");
		alert.setHeaderText(null);
		alert.show();
	}
}
