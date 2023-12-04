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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import database.KetNoiDatabase;
import entity.KhachHang;
import entity.LoaiThuoc;
import entity.NhanVien;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ThemThuocNVController implements Initializable{
	@FXML
	private ImageView imageView;
	private Image image;
	private FileInputStream fis;
	private File file;
	ObservableList<String> countries = FXCollections.observableArrayList("Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe", "Palestine");
	ObservableList<String> thuocKeDon = FXCollections.observableArrayList("Thuốc kê đơn", "Thuốc không kê đơn");
	@FXML
	private Button nhapExcel;
	@FXML
	private MenuButton mb;
	@FXML
	private TextArea txtThongTin;
	@FXML
	private AnchorPane ap;
	@FXML
	private ComboBox<String> cbbThuocKeDon;
	@FXML
	private TextField  txtTenThuoc, txtXemLoai, txtDonViTinh, txtGiaNhap, txtGiaBan, txtQuyCach, txtCachDung, txtDinhMucSL, txtSoDangKy, txtTimKiem;
	@FXML
	private ComboBox<String> cbbLoaiThuoc;
	@FXML
	private Label lblMaThuoc, lblName;
	@FXML
	private ComboBox<String> cbbNSX;
	@FXML
	private ComboBox<String> cbbTrangThai;
	@FXML
	private TableColumn<Thuoc, Integer> maThuoc;
	@FXML
	private TableColumn<Thuoc, String> tenThuoc;
	@FXML
	private TableColumn<Thuoc, String> loaiThuoc;
	@FXML
	private TableColumn<Thuoc, String> dvt;
	@FXML
	private TableColumn<Thuoc, String> quyCachDongGoi;
	@FXML
	private TableColumn<Thuoc, Float> giaNhap;
	@FXML
	private TableColumn<Thuoc, Float> giaBan;
	@FXML
	private TableColumn<Thuoc, String> trangThai;
	@FXML
	private ComboBox<String> cbbTKD;
	
	@FXML
	private TableView<Thuoc> table;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	float gn = 0;
	float gb = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String sqlxc = "select * from NhanVien";
		NhanVien dnc = DangNhapController.getNV();
		try {
			ps = con.prepareStatement(sqlxc);
			rs = ps.executeQuery();

				lblName.setText("Xin chào, " + dnc.getHoTen());
				//Loi
				System.out.println(dnc.getMaNV());
				System.out.println(dnc.getHoTen());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		txtGiaNhap.setOnKeyReleased(arg-> {
			Float t = Float.parseFloat(txtGiaNhap.getText());

			txtGiaBan.setText((String.format("%.0f", t + (t*10/100))));
		});
		cbb();
		reload();
		getAllThuoc();
		cell();
		searchByName();
		nhapExcel.setOnAction(a->{
			String query = "insert into Thuoc(maThuoc, tenThuoc, donViTinh, nuocSanXuat, giaNhap, giaBan, cachDung, trangThai) values(?,?,?,?,?,?,?,?) select * from Thuoc t left join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc";
			try {
				ps = con.prepareStatement(query);
				FileInputStream fi = new FileInputStream(new File("ThemThuoc.xlsx"));
				XSSFWorkbook wb = new XSSFWorkbook(fi);
				XSSFSheet sheet = wb.getSheetAt(0);
				Row row;
				for(int i = 1; i<=sheet.getLastRowNum();i++) {
					row = sheet.getRow(i);
					ps.setInt(1, (int) row.getCell(0).getNumericCellValue());
					ps.setString(2, row.getCell(1).getStringCellValue());
					ps.setString(3, row.getCell(2).getStringCellValue());
					ps.setString(4, row.getCell(3).getStringCellValue());
					ps.setFloat(5, (float) row.getCell(5).getNumericCellValue());
					ps.setFloat(6, (float) row.getCell(6).getNumericCellValue());
					ps.setString(7, row.getCell(7).getStringCellValue());
					ps.setString(8, row.getCell(8).getStringCellValue());
					ps.execute();
				}	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
	}
	//Start Navbar
	public void trangChu(ActionEvent e) throws IOException {
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TrangChuNV.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
        stage.setScene(scene);
	}
	public void thuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThemThuocNV.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
    public void timKiemGioHang(ActionEvent e) throws IOException {
     	Stage stage = (Stage) mb.getScene().getWindow();
     	FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("/view/TimKiemDonDatThuocNV.fxml"));
         Parent sampleParent = loader.load();
         Scene scene = new Scene(sampleParent);
         stage.setScene(scene);
 	}
	public void nhapThuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThemCTPhieuNhapNV.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void timLoaiThuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TimKiemLoaiThuocNV.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void thuocTrongKho(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThuocTrongKhoNV.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}

	public void timThuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TimKiemThuocNV.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void timKiemNhapThuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TimKiemPhieuNhapNV.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void lapHoaDonKhongKeDon(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThemHoaDonKhongTheoDonNV.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void lapHoaDonKeDon(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThemHoaDonTheoDonNV.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void timKiemHoaDon(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TimKiemHoaDonNV.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}

	public void thongKeThuocSapHetHang(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThongKeThuocSapHetHangNV.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void thongKeThuocSapHetHan(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThongKeThuocSapHetHanNV.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}

     public void gioHang(ActionEvent e) throws IOException {
    	Stage stage = (Stage) mb.getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/GioHangNV.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
	}
     public void themKhachHang(ActionEvent e) throws IOException {
     	Stage stage = (Stage) mb.getScene().getWindow();
     	FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("/view/ThemKhachHangNV.fxml"));
         Parent sampleParent = loader.load();
         Scene scene = new Scene(sampleParent);
         stage.setScene(scene);
 	}

     public void timKiemKhachHang(ActionEvent e) throws IOException {
     	Stage stage = (Stage) mb.getScene().getWindow();
     	FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("/view/TimKiemKhachHangNV.fxml"));
         Parent sampleParent = loader.load();
         Scene scene = new Scene(sampleParent);
         stage.setScene(scene);
 	}

     public void themNCC(ActionEvent e) throws IOException {
      	Stage stage = (Stage) mb.getScene().getWindow();
      	FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/view/ThemNCCNV.fxml"));
          Parent sampleParent = loader.load();
          Scene scene = new Scene(sampleParent);
          stage.setScene(scene);
  	}
     public void timKiemNCC(ActionEvent e) throws IOException {
      	Stage stage = (Stage) mb.getScene().getWindow();
      	FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("/view/TimKiemNCCNV.fxml"));
          Parent sampleParent = loader.load();
          Scene scene = new Scene(sampleParent);
          stage.setScene(scene);
  	}
     public void thongTinCT(ActionEvent e) throws IOException {
    		Stage stage = new Stage();
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("/view/ThongTinChiTietNV.fxml"));
    		Parent parent = loader.load();
    		Scene scene = new Scene(parent);
    		ThongTinChiTietNVController c = loader.getController();
    		NhanVien dnc = DangNhapController.getNV();
    		c.getMaNV(dnc);
    		stage.setScene(scene);
    		stage.show();
    	}

     public void themDonThuoc(ActionEvent e) throws IOException {
	       	Stage stage = (Stage) mb.getScene().getWindow();
	       	FXMLLoader loader = new FXMLLoader();
	           loader.setLocation(getClass().getResource("/view/ThemDonThuocNV.fxml"));
	           Parent sampleParent = loader.load();
	           Scene scene = new Scene(sampleParent);
	           stage.setScene(scene);
	   	}
	      public void timKiemDonThuoc(ActionEvent e) throws IOException {
	       	Stage stage = (Stage) mb.getScene().getWindow();
	       	FXMLLoader loader = new FXMLLoader();
	           loader.setLocation(getClass().getResource("/view/TimKiemDonThuocNV.fxml"));
	           Parent sampleParent = loader.load();
	           Scene scene = new Scene(sampleParent);
	           stage.setScene(scene);
	   	}
      public void logOut(ActionEvent e){
    	  System.exit(0);
      }


	//End Navbar

	ObservableList<Thuoc> list = FXCollections.observableArrayList();
	public int getLT() throws SQLException {
		int mlt = 0;
		String sql = "select * from LoaiThuoc where tenLoaiThuoc = N'"+cbbLoaiThuoc.getSelectionModel().getSelectedItem().toString()+"'";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) 
		mlt = rs.getInt("maLoaiThuoc");
		return mlt;
	}
//
//	public void luu() {
//		Thuoc t = new Thuoc();
////		tenThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("tenThuoc"));
//		list.add(t);
//		table.setItems(list);
//	}
	public void add() throws SQLException, FileNotFoundException {

		String sql = "insert into Thuoc(tenThuoc,maLoaiThuoc, donViTinh, giaNhap, giaBan, quyCachDongGoi, cachDung, nuocSanXuat, trangThai, dinhMucSL, soDangKy, thongTin, thuocKeDon, hinhAnh) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//		String tlt = cbbLoaiThuoc.getSelectionModel().getSelectedItem().toString();
		int mlt = getLT();
//		int mncc = cbbNCC.getSelectionModel().getSelectedIndex() + 1;
		gn = Float.parseFloat(txtGiaNhap.getText());
		gb = Float.parseFloat(txtGiaBan.getText());
		System.out.println(mlt);
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, txtTenThuoc.getText().toString());
			ps.setInt(2, mlt);
			ps.setString(3, txtDonViTinh.getText().toString());
			ps.setFloat(4, gn);
			ps.setFloat(5, gb);
			ps.setString(6, txtQuyCach.getText().toString());
			ps.setString(7, txtCachDung.getText().toString());
			ps.setString(8, cbbNSX.getSelectionModel().getSelectedItem().toString());
			ps.setString(9, cbbTrangThai.getSelectionModel().getSelectedItem().toString());
			ps.setInt(10, Integer.parseInt(txtDinhMucSL.getText()));
			ps.setString(11, txtSoDangKy.getText().toString());
			ps.setString(12, txtThongTin.getText().toString());
			ps.setString(13, cbbThuocKeDon.getSelectionModel().getSelectedItem().toString());
			fis = new FileInputStream(file);
//			if(fis==null) {
//				
//			}
			ps.setBinaryStream(14, fis, file.length());

			ps.execute();
			reload();
			Alert alert = new Alert(AlertType.INFORMATION, "Thêm thành công", ButtonType.OK);
	  		alert.setTitle("Thông báo");
	  		alert.setHeaderText(null);
	  		alert.show();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR, "Thêm thất bại, số lượng sản phẩm trong kho không đủ", ButtonType.OK);
  		alert.setTitle("Thông báo");
  		alert.setHeaderText(null);
  		alert.show();
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
		cbbNSX.setItems(countries);
		cell();
		trangThai();
		getAllThuoc();
		cbbThuocKeDon.setItems(thuocKeDon);
	}
	public void cell() {
		maThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, Integer>("maThuoc"));
		tenThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("tenThuoc"));
		loaiThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("loaiThuoc"));
		dvt.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("dvt"));
		quyCachDongGoi.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("quyCachDongGoi"));
		giaNhap.setCellValueFactory(new PropertyValueFactory<Thuoc, Float>("giaNhap"));
		giaBan.setCellValueFactory(new PropertyValueFactory<Thuoc, Float>("giaBan"));
		trangThai.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("trangThai"));
	}
	public void reload() {
//		list = getAllNV();
		cbb();
//		// TODO Auto-generated method stub

	}
	@FXML
	public void mouseClicked(MouseEvent e) throws SQLException, IOException {
		InputStream is;
		Thuoc t = table.getSelectionModel().getSelectedItem();
		lblMaThuoc.setText(String.valueOf(t.getMaThuoc()));
		txtTenThuoc.setText(t.getTenThuoc());
		cbbLoaiThuoc.setValue(t.getLoaiThuoc());
		txtDonViTinh.setText(t.getDvt());
		txtQuyCach.setText(t.getQuyCachDongGoi());
		cbbNSX.setValue(t.getNsx());
		txtCachDung.setText(t.getCachDung());
		txtThongTin.setText(t.getThongTin());
		txtGiaNhap.setText(String.valueOf(t.getGiaNhap()));
		txtGiaBan.setText(String.valueOf(t.getGiaBan()));
		cbbThuocKeDon.setValue(t.getThuocKeDon());
		txtDinhMucSL.setText(String.valueOf(t.getDinhMucSL()));
		txtSoDangKy.setText(t.getSoDangKy());
		cbbTrangThai.setValue(t.getTrangThai());
		String query = "select * from Thuoc where maThuoc = '"+lblMaThuoc.getText()+"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next()) {
		is = rs.getBinaryStream("hinhAnh");
		if(is==null) {
			image = new Image("file:\\images\\avatar.png",imageView.getFitWidth(),imageView.getFitHeight(),true,true);
			imageView.setImage(image);
		}
		OutputStream os = new FileOutputStream(new File("photo.jpg"));
		byte[] content = new byte[1024];
		int size = 0;
		while((size = is.read(content))!=-1) {
			os.write(content, 0 ,size);
		
		}
		os.close();
		is.close();
		image = new Image("file:photo.jpg",imageView.getFitWidth(),imageView.getFitHeight(),true,true);
		imageView.setImage(image);
		}
	}
	public int getLoaiThuoc() throws SQLException {
		int i = 0;
		String s = "select * from LoaiThuoc where tenLoaiThuoc = N'"+cbbLoaiThuoc.getSelectionModel().getSelectedItem().toString()+"'";
		ps = con.prepareStatement(s);
		rs = ps.executeQuery();
		while(rs.next())
			i = rs.getInt("maLoaiThuoc");
		System.out.println(i);
		return i;
	}
	public void capNhat(ActionEvent e) throws SQLException {
		int i = getLoaiThuoc();
		String sql = "update Thuoc set tenThuoc = N'"+txtTenThuoc.getText()+"', maLoaiThuoc = '"+i+"', donViTinh = N'"+txtDonViTinh.getText()+"', nuocSanXuat = N'"+cbbNSX.getSelectionModel().getSelectedItem()+"', quyCachDongGoi = N'"+txtQuyCach.getText()+"', cachDung = N'"+txtCachDung.getText()+"', giaNhap ='"+Float.parseFloat(txtGiaNhap.getText())+"', giaBan = N'"+Float.parseFloat(txtGiaBan.getText())+"', soDangKy = N'"+txtSoDangKy.getText()+"', dinhMucSL = N'"+Integer.parseInt(txtDinhMucSL.getText())+"', thuocKeDon = N'"+cbbThuocKeDon.getSelectionModel().getSelectedItem()+"', thongTin = N'"+txtThongTin.getText()+"', trangThai = N'"+cbbTrangThai.getSelectionModel().getSelectedItem()+"' where maThuoc = N'"+lblMaThuoc.getText()+"'";
		ps = con.prepareStatement(sql);
		ps.execute();
		capNhatMessage();
		reload();
	}
	public void remove(ActionEvent e) {
		if(table.getSelectionModel().getSelectedIndex() <= -1) {
			xoaThatBaiMessage();
		}
		else {
			try {
				String query = "delete from Thuoc where maThuoc= '"+lblMaThuoc.getText()+"'";
				ps = con.prepareStatement(query);
				ps.execute();
				xoaThanhCongMessage();
				image = new Image("C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\images\\avatar.png",imageView.getFitWidth(),imageView.getFitHeight(),true,true);
				imageView.setImage(image);
				reload();
			}catch(Exception e1) {
				e1.printStackTrace();
				xoaThatBaiMessage();
			}
		}
	}
	public void xoaTrang(ActionEvent e) {
		txtTenThuoc.setText("");
		cbbLoaiThuoc.setValue(null);
		txtDonViTinh.setText("");
		cbbThuocKeDon.setValue(null);
		cbbNSX.setValue(null);
		txtGiaNhap.setText("");
		txtGiaBan.setText("");
		txtCachDung.setText("");
		txtQuyCach.setText("");
		cbbTrangThai.setValue(null);
		txtDinhMucSL.setText("");
		txtSoDangKy.setText("");
		image = new Image("C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\images\\avatar.png",imageView.getFitWidth(),imageView.getFitHeight(),true,true);
		imageView.setImage(image);
	}
	public void getAllLoaiThuoc(){
		String sql = "select * from LoaiThuoc";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			ObservableList loaiThuocList = FXCollections.observableArrayList();
			while(rs.next()) {
				String lt = rs.getString("tenLoaiThuoc");
				loaiThuocList.add(lt);
			}
			cbbLoaiThuoc.setItems(loaiThuocList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@FXML
	public ObservableList<Thuoc> getAllThuoc() {
		cbbTKD.setItems(FXCollections.observableArrayList("Tất cả", "Thuốc kê đơn", "Thuốc không kê đơn"));
		cbbTKD.getSelectionModel().selectFirst();
		String sql = "select * from Thuoc t left join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			list.clear();
			table.setItems(list);
			while(rs.next()) {
				Thuoc t = new Thuoc();
				t.setMaThuoc(rs.getInt("maThuoc"));
				t.setTenThuoc(rs.getString("tenThuoc"));
				t.setLoaiThuoc(rs.getString("tenLoaiThuoc"));
				t.setDvt(rs.getString("donViTinh"));
				t.setNsx(rs.getString("nuocSanXuat"));
				t.setQuyCachDongGoi(rs.getString("quyCachDongGoi"));
				t.setCachDung(rs.getString("cachDung"));
				t.setDinhMucSL(rs.getInt("dinhMucSL"));
				t.setThongTin(rs.getString("thongTin"));
				t.setTrangThai(rs.getString("trangThai"));
				t.setSoDangKy(rs.getString("soDangKy"));
				t.setGiaNhap(rs.getFloat("giaNhap"));
				t.setGiaBan(rs.getFloat("giaBan"));
				t.setThuocKeDon(rs.getString("thuocKeDon"));
				list.add(t);
				table.setItems(list);
			}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		cbbTKD.setOnAction(args->{
		if(cbbTKD.getSelectionModel().getSelectedItem()=="Tất cả") {
//			list.clear();
			String sql1 = "select * from Thuoc t left join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc";
			try {
				ps = con.prepareStatement(sql1);
				rs = ps.executeQuery();
				list.clear();
				table.setItems(list);
				while(rs.next()) {
					Thuoc t = new Thuoc();
					t.setMaThuoc(rs.getInt("maThuoc"));
					t.setTenThuoc(rs.getString("tenThuoc"));
					t.setLoaiThuoc(rs.getString("tenLoaiThuoc"));
					t.setDvt(rs.getString("donViTinh"));
					t.setNcc(rs.getString("nuocSanXuat"));
					t.setQuyCachDongGoi(rs.getString("quyCachDongGoi"));
					t.setCachDung(rs.getString("cachDung"));
					t.setDinhMucSL(rs.getInt("dinhMucSL"));
					t.setThongTin(rs.getString("thongTin"));
					t.setTrangThai(rs.getString("trangThai"));
					t.setSoDangKy(rs.getString("soDangKy"));
					t.setGiaNhap(rs.getFloat("giaNhap"));
					t.setGiaBan(rs.getFloat("giaBan"));
					t.setThuocKeDon(rs.getString("thuocKeDon"));
					list.add(t);
					table.setItems(list);
				}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
		}
		if(cbbTKD.getSelectionModel().getSelectedItem() == "Thuốc kê đơn") {
				String sql1 = "select * from Thuoc t left join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc where thuocKeDon = N'Thuốc kê đơn'";
				list.clear();
				table.setItems(list);
				try {
					ps = con.prepareStatement(sql1);
					rs = ps.executeQuery();
					while(rs.next()) {
						Thuoc t = new Thuoc();
						t.setMaThuoc(rs.getInt("maThuoc"));
						t.setTenThuoc(rs.getString("tenThuoc"));
						t.setLoaiThuoc(rs.getString("tenLoaiThuoc"));
						t.setDvt(rs.getString("donViTinh"));
						t.setNcc(rs.getString("nuocSanXuat"));
						t.setQuyCachDongGoi(rs.getString("quyCachDongGoi"));
						t.setCachDung(rs.getString("cachDung"));
						t.setDinhMucSL(rs.getInt("dinhMucSL"));
						t.setThongTin(rs.getString("thongTin"));
						t.setTrangThai(rs.getString("trangThai"));
						t.setSoDangKy(rs.getString("soDangKy"));
						t.setGiaNhap(rs.getFloat("giaNhap"));
						t.setGiaBan(rs.getFloat("giaBan"));
						t.setThuocKeDon(rs.getString("thuocKeDon"));
						list.add(t);
						table.setItems(list);
					}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
			}
		if(cbbTKD.getSelectionModel().getSelectedItem() == "Thuốc không kê đơn") {
				String sql1 = "select * from Thuoc t left join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc where thuocKeDon = N'Thuốc không kê đơn'";
				list.clear();
				table.setItems(null);
				try {
					ps = con.prepareStatement(sql1);
					rs = ps.executeQuery();
					while(rs.next()) {
						Thuoc t = new Thuoc();
						t.setMaThuoc(rs.getInt("maThuoc"));
						t.setTenThuoc(rs.getString("tenThuoc"));
						t.setLoaiThuoc(rs.getString("tenLoaiThuoc"));
						t.setDvt(rs.getString("donViTinh"));
						t.setNcc(rs.getString("nuocSanXuat"));
						t.setQuyCachDongGoi(rs.getString("quyCachDongGoi"));
						t.setCachDung(rs.getString("cachDung"));
						t.setDinhMucSL(rs.getInt("dinhMucSL"));
						t.setThongTin(rs.getString("thongTin"));
						t.setTrangThai(rs.getString("trangThai"));
						t.setSoDangKy(rs.getString("soDangKy"));
						t.setGiaNhap(rs.getFloat("giaNhap"));
						t.setGiaBan(rs.getFloat("giaBan"));
						t.setThuocKeDon(rs.getString("thuocKeDon"));
						list.add(t);
						table.setItems(list);
					}
					} catch (Exception e) {
						// TODO: handle exception
					}
			}
		});
		return list;
	}
	@FXML
	public void chooseImage(ActionEvent e) throws FileNotFoundException {
		Stage stage = (Stage) ap.getScene().getWindow();
		FileChooser fc = new FileChooser();
		fc.setTitle("Chọn ảnh");
		FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg","*.png");
		file = fc.showOpenDialog(stage);
		if(file !=null) {
			Image image  = new Image(file.toURI().toString(),100,130,false,true);
			imageView.setImage(image);
			FileInputStream fs = new FileInputStream(file);
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
		Alert alert = new Alert(AlertType.ERROR, "Thêm thất bại, ngày sinh không được bỏ trống", ButtonType.OK);
		alert.setTitle("Thông báo");
		alert.setHeaderText(null);
		alert.show();
	}
	@FXML
	private void capNhatMessage() {
		Alert alert = new Alert(AlertType.INFORMATION, "Cập nhật thành công", ButtonType.OK);
		alert.setTitle("Thông báo");
		alert.setHeaderText(null);
		alert.show();
	}
	@FXML
	private void xoaThanhCongMessage() {
		Alert alert = new Alert(AlertType.INFORMATION, "Xoá thành công", ButtonType.OK);
		alert.setTitle("Thông báo");
		alert.setHeaderText(null);
		alert.show();
	}
	@FXML
	private void xoaThatBaiMessage() {
		Alert alert = new Alert(AlertType.ERROR, "Xoá thất bại, vui lòng kiểm tra lại", ButtonType.OK);
		alert.setTitle("Thông báo");
		alert.setHeaderText(null);
		alert.show();
	}

	public void trangThai() {
		List trangThai = new ArrayList();
		trangThai.add("Đang kinh doanh");
		trangThai.add("Ngừng kinh doanh");
		ObservableList list = FXCollections.observableArrayList(trangThai);
		cbbTrangThai.setItems(list);
	}
	@FXML
	public void searchByName() {
		txtTimKiem.setOnKeyReleased(e->{
			if(txtTimKiem.getText().equals("")) {
				reload();
			}
			else {
				list.clear();
				String sql = " select * from Thuoc t left join LoaiThuoc lt on lt.maThuoc = t.maThuoc where tenThuoc like N'%" + txtTimKiem.getText() +"%'";
				try {
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					while(rs.next()) {
						Thuoc t = new Thuoc();
						t.setMaThuoc(rs.getInt("maThuoc"));
						t.setTenThuoc(rs.getString("tenThuoc"));
						t.setLoaiThuoc(rs.getString("tenLoaiThuoc"));
						t.setDvt(rs.getString("donViTinh"));
						t.setNcc(rs.getString("nuocSanXuat"));
						t.setQuyCachDongGoi(rs.getString("quyCachDongGoi"));
						t.setCachDung(rs.getString("cachDung"));
						t.setDinhMucSL(rs.getInt("dinhMucSL"));
						t.setThongTin(rs.getString("thongTin"));
						t.setTrangThai(rs.getString("trangThai"));
						t.setSoDangKy(rs.getString("soDangKy"));
						t.setGiaNhap(rs.getFloat("giaNhap"));
						t.setGiaBan(rs.getFloat("giaBan"));
						t.setThuocKeDon(rs.getString("thuocKeDon"));
						list.add(t);
						table.setItems(list);
					}
				}catch (Exception e1) {
					// TODO: handle exception
				}
			}
		});
	}
}
