package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import database.KetNoiDatabase;
import entity.LoaiThuoc;
import entity.Thuoc;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ThemThuocController implements Initializable{
	String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe", "Palestine"};
//	@FXML
//	private Button btnXemLoai;
	@FXML
	private MenuButton mb;
	@FXML
	private TextArea txtThongTin;
	@FXML
	private TextField txtMaThuoc, txtTenThuoc, txtXemLoai, txtDonViTinh, txtGiaNhap, txtGiaBan, txtQuyCach, txtCachDung;
	@FXML
	private ComboBox<?> cbbLoaiThuoc;
	@FXML
	private ComboBox<?> cbbNCC;
	@FXML
	private ComboBox<?> cbbNSX;
	@FXML
	private ComboBox<?> cbbTrangThai;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		cbb();
	}
	//Start Navbar
	public void nhanVien(ActionEvent e) throws IOException {
		try {
			Stage stage = (Stage) mb.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/view/ThemNhanVien.fxml"));
	        Parent sampleParent = loader.load();
//	        NhanVienController nv = loader.getController();
	        Scene scene = new Scene(sampleParent);
	        scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
	        stage.setScene(scene);

			
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}

	}	public void timNhanVien(ActionEvent e) throws IOException {
		try {
			Stage stage = (Stage) mb.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/view/TimKiemNhanVien.fxml"));
	        Parent sampleParent = loader.load();
//	        NhanVienController nv = loader.getController();
	        Scene scene = new Scene(sampleParent);
	        scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
	        stage.setScene(scene);
			
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}

	}
	public void trangChu(ActionEvent e) throws IOException {
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TrangChuQL.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
        stage.setScene(scene);
	}
	public void thuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThemThuoc.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void loaiThuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThemLoaiThuoc.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void nhapThuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThemCTPhieuNhap.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void timLoaiThuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TimKiemLoaiThuoc.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void timThuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TimKiemThuoc.fxml"));
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
	
	//End Navbar

	public void add() {
		String sql = "insert into Thuoc(maThuoc, tenThuoc, maLoaiThuoc, maNCC, donViTinh, giaNhap, giaBan, quyCachDongGoi, cachDung, nuocSanXuat, trangThai, thongTin, soLuong) values (?,?,?,?,?,?,?,?,?,?,?,?,0)";
		int mlt = cbbLoaiThuoc.getSelectionModel().getSelectedIndex() + 1;
		int mncc = cbbNCC.getSelectionModel().getSelectedIndex() + 1;
		float gn = Float.parseFloat(txtGiaNhap.getText());
		float gb = Float.parseFloat(txtGiaBan.getText());
		System.out.println(mlt);
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(txtMaThuoc.getText()));
			ps.setString(2, txtTenThuoc.getText().toString());
			ps.setInt(3, mlt);
			ps.setInt(4, mncc);
			ps.setString(5, txtDonViTinh.getText().toString());
			ps.setFloat(6, gn);
			ps.setFloat(7, gb);
			ps.setString(8, txtQuyCach.getText().toString());
			ps.setString(9, txtCachDung.getText().toString());
			ps.setString(10, cbbNSX.getSelectionModel().getSelectedItem().toString());
			ps.setString(11, cbbTrangThai.getSelectionModel().getSelectedItem().toString());
			ps.setString(12, txtThongTin.getText().toString());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public void xemLoai() throws IOException {
//		Stage stage = new Stage();
//		FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/view/XemThongTinLoaiThuoc.fxml"));
//        Parent sampleParent = loader.load();
//        Scene scene = new Scene(sampleParent);
//        stage.setScene(scene);
//        stage.show();
//	}
//	public void xemNCC() throws IOException {
//		Stage stage = new Stage();
//		FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/view/XemThongTinNCC.fxml"));
//        Parent sampleParent = loader.load();
//        Scene scene = new Scene(sampleParent);
//        stage.setScene(scene);
//        stage.show();
//	}
//	public LoaiThuoc timLoaiThuoc(String sp) {
//		LoaiThuoc result = null;
//		String query = "select * from LoaiSP where loaiSP = ?";
//		ArrayList<Object> arr = new ArrayList<>();
//		arr.add(sp);
//		try {
//			ps = con.prepareStatement(query);
//			rs = ps.executeQuery();
//			while(rs.next()) {
//				result = new LoaiThuoc(rs.getInt("maLoaiSP"),rs.getString("loaiSP"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
	public void cbb() {
		getAllLoaiThuoc();
		loaiNSX();
		trangThai();
		getAllNCC();
	}
	public void getAllLoaiThuoc(){
		String sql = "select * from LoaiThuoc";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			ObservableList loaiThuocList = FXCollections.observableArrayList();
			while(rs.next()) {
				String ls = rs.getString("loaiThuoc");
				loaiThuocList.add(ls);
			}
			cbbLoaiThuoc.setItems(loaiThuocList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void getAllNCC() {
		String sql = "select * from NhaCungCap";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			ObservableList nccList = FXCollections.observableArrayList();
			while(rs.next()) {
				String ncc = rs.getString("tenNCC");
				nccList.add(ncc);
			}
			cbbNCC.setItems(nccList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void loaiNSX() {
		ObservableList list = FXCollections.observableArrayList(countries);
		cbbNSX.setItems(list);
		}

	public void trangThai() {
		List trangThai = new ArrayList();
		trangThai.add("Đang kinh doanh");
		trangThai.add("Ngừng kinh doanh");
		ObservableList list = FXCollections.observableArrayList(trangThai);
		cbbTrangThai.setItems(list);
	}

}
