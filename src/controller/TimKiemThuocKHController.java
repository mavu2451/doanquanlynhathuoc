package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import database.KetNoiDatabase;
import entity.CTThuoc;
import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.NhanVien;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TimKiemThuocKHController implements Initializable{
	@FXML
	private Button btnTimKiem, xuatExcel, btnTTCT;
	@FXML
	private TextField txtTimThuoc, txtTimLoaiThuoc, txtTimDVT, txtTimNSX;
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
	private MenuButton mb;
//	@FXML
//	private MenuItem mNhapHang;
	@FXML
	private Label lblName;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		reload();
//		NhanVien dnc = DangNhapController.getNV();
////		try {
////			while(rs.next()) {
//				lblName.setText("Xin chào, " + dnc.getHoTen());
//				System.out.println(dnc.getMaNV() + "Xem thuoc");
//				System.out.println(dnc.getHoTen());
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			

			
	
				btnTimKiem.setOnAction(a -> {
					String timThuoc = txtTimThuoc.getText().toString();
					String timLoaiThuoc = txtTimLoaiThuoc.getText().toString();
					String timNSX = txtTimNSX.getText().toString();
					String timDVT = txtTimDVT.getText().toString();
					if(timThuoc == "" && timLoaiThuoc == "" && timNSX == "" && timDVT == "") {
						table.getItems().clear();
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Thông báo");
						alert.setContentText("Không được để trống");
						alert.setHeaderText(null);
						alert.showAndWait();
						getAllThuoc();
					}
					else {
					table.getItems().clear();
					String sql = "select t.maThuoc, t.tenThuoc, lt.tenLoaiThuoc, t.nuocSanXuat, t.donViTinh, t.giaNhap, t.giaBan, t.cachDung, t.trangThai from Thuoc t left join LoaiThuoc lt on "
							+ "lt.maLoaiThuoc = t.maLoaiThuoc where tenThuoc like N'%"+timThuoc+"%' "
									+ "and tenLoaiThuoc like N'%"+timLoaiThuoc+"%'"
									+ "and nuocSanXuat like N'%"+timNSX+"%'"
									+ "and donViTinh like N'%"+timDVT+"%'";
					try {
						ps = con.prepareStatement(sql);
						rs = ps.executeQuery();
						while(rs.next()) {
							Thuoc t = new Thuoc();
							t.setMaThuoc(rs.getInt("maThuoc"));
							t.setTenThuoc(rs.getString("tenThuoc"));
							t.setLoaiThuoc(rs.getString("tenLoaiThuoc"));
							t.setNsx(rs.getString("nuocSanXuat"));
							t.setDvt(rs.getString("donViTinh"));
							t.setGiaNhap(rs.getFloat("giaNhap"));
							t.setGiaBan(rs.getFloat("giaBan"));
							t.setCachDung(rs.getString("cachDung"));
							t.setTrangThai(rs.getString("trangThai"));
							thuocList.add(t);
							table.setItems(thuocList);	
						}	
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				});
	}
//	public void thuoc(ActionEvent e) throws IOException {
////		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
////		Stage stage = (Stage) mb.getScene().getWindow();
////		FXMLLoader loader = new FXMLLoader();
////        loader.setLocation(getClass().getResource("/view/Thuoc.fxml"));
////        Parent sampleParent = loader.load();
////        Scene scene = new Scene(sampleParent);
////        scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
////        stage.setScene(scene);
////        stage.show();
//	}
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
	public void timKiemNhapThuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TimKiemPhieuNhap.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void dangNhap(ActionEvent e) throws IOException {
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.close();
		stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/DangNhap.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
        stage.show();
	}

	//End Navbar

	
	public void cell() {
		maThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, Integer>("maThuoc"));
		tenThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("tenThuoc"));
		loaiThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("loaiThuoc"));
		dvt.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("dvt"));
		nsx.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("nsx"));
		giaNhap.setCellValueFactory(new PropertyValueFactory<Thuoc, Float>("giaNhap"));
		giaBan.setCellValueFactory(new PropertyValueFactory<Thuoc, Float>("giaBan"));
		cachDung.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("cachDung"));
		trangThai.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("trangThai"));
	}

	public ObservableList<Thuoc> getAllThuoc(){
		ObservableList<Thuoc> thuocList = FXCollections.observableArrayList();
		String query = "\r\n"
				+ "select maThuoc, tenThuoc, tenLoaiThuoc, donViTinh, giaNhap, giaBan, nuocSanXuat,cachDung, trangThai "
				+ "from Thuoc t inner join LoaiThuoc l on t.maLoaiThuoc = l.maLoaiThuoc order by maThuoc, trangThai"
				;

		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				Thuoc t = new Thuoc();
				t.setMaThuoc(rs.getInt("maThuoc"));
				t.setTenThuoc(rs.getString("tenThuoc"));
				t.setLoaiThuoc(rs.getString("tenLoaiThuoc"));
				t.setNsx(rs.getString("nuocSanXuat"));
				t.setDvt(rs.getString("donViTinh"));
				t.setGiaNhap(rs.getFloat("giaNhap"));
				t.setGiaBan(rs.getFloat("giaBan"));
				t.setCachDung(rs.getString("cachDung"));
				t.setTrangThai(rs.getString("trangThai"));
				thuocList.add(t);
				table.setItems(thuocList);
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
	public void thongTin(ActionEvent e) throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/ThongTinChiTietThuoc.fxml"));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		ThongTinChiTietThuocController c = loader.getController();
		Thuoc t  = table.getSelectionModel().getSelectedItem();
		c.getMaThuoc(t);
		stage.setScene(scene);
		stage.show();
	}
}
