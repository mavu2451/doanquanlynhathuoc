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
import entity.KhachHang;
import entity.NhanVien;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class KhachHangNVController implements Initializable{
	@FXML
	private ImageView imageView;
	private Image image;
	private FileInputStream fis;
	private File file;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	@FXML
	private MenuButton mb;
	@FXML
	private Label lblMaKH;
	@FXML
	private TextField txtHoTen, txtSDT, txtEmail, txtTimKiem;
	@FXML
	private TextArea txtDiaChi;
	@FXML
	private Button chooseImage;
	@FXML
	private AnchorPane ap;
	@FXML
	private PasswordField txtMatKhau;
	
	private String[] gt = {"Nam","Nữ","Khác"};

	@FXML
	private ComboBox<String> cbGioiTinh;

	@FXML
	private DatePicker dpNgaySinh = new DatePicker();
	@FXML
	TableView<KhachHang> table;
	@FXML
	private TableColumn<KhachHang, Integer> maKH;
	@FXML
	private TableColumn<KhachHang, String> tenKH;
	@FXML
	private TableColumn<KhachHang, String> gioiTinh;
	@FXML
	private TableColumn<KhachHang, Date> ngaySinh;

	@FXML
	private TableColumn<KhachHang, String> sdt;
	@FXML
	private TableColumn<KhachHang, String> email;
	@FXML
	private TableColumn<KhachHang, String> diaChi;


	@FXML
	private ObservableList<KhachHang> khList = FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			reload();
			dpNgaySinh.setValue(LocalDate.now());
			LocalDate ldNgayNhap = dpNgaySinh.getValue();
			Date dNgayNhap = Date.valueOf(ldNgayNhap);
//			edit();
			searchByName();
//			setCell();
			cbGioiTinh.setItems(FXCollections.observableArrayList("Nam", "Nữ", "Khác"));
//			cbGioiTinh.getItems().addAll(gt);
//			cbVaiTro.getItems().addAll(vt);
//			cbTrangThai.getItems().addAll(tt);
//			table.setOnMouseClicked(e->{
//				System.out.println("test");
//				InputStream is;
//				
//				try {
//					NhanVien nv = (NhanVien)table.getSelectionModel().getSelectedItem();
//					String query = "select * from NhanVien where maNV = ?";
//					ps = con.prepareStatement(query);
//					ps.setInt(1, nv.getMaNV());
//					rs = ps.executeQuery();
//					while(rs.next()) {
//					is = rs.getBinaryStream("image");
//					if(is==null) {
//						image = new Image("file:\\images\\avatar.png",imageView.getFitWidth(),imageView.getFitHeight(),true,true);
//						imageView.setImage(image);
//					}
//					OutputStream os = new FileOutputStream(new File("photo.jpg"));
//					byte[] content = new byte[1024];
//					int size = 0;
//					while((size = is.read(content))!=-1) {
//						os.write(content, 0 ,size);
//					
//					}
//					os.close();
//					is.close();
//					image = new Image("file:photo.jpg",imageView.getFitWidth(),imageView.getFitHeight(),true,true);
//					imageView.setImage(image);
//					
//					}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (FileNotFoundException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//			});

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
	
	public void add(ActionEvent e) {
		String query = "insert into KhachHang(tenKH, gioiTinh, ngaySinh, sdt, email, diaChi) values (?,?,?,?,?,?)";
		try {	
			ps = con.prepareStatement(query);
			ps.setString(1, txtHoTen.getText());
			ps.setString(2, cbGioiTinh.getValue());
			ps.setDate(3, Date.valueOf(dpNgaySinh.getValue()));
			ps.setInt(4, Integer.parseInt(txtSDT.getText()));
			ps.setString(5, txtEmail.getText());
			ps.setString(6, txtDiaChi.getText());
			ps.execute();
			themThanhCongMessage();
			reload();	
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			themThatBaiMessage();
		}
//		reset();
//		imageView.setImage("");
	}
	@FXML
	public void mouseClicked(MouseEvent e) {
		KhachHang kh = table.getSelectionModel().getSelectedItem();
		lblMaKH.setText(String.valueOf(kh.getMaKH()));
		txtHoTen.setText(String.valueOf(kh.getHoTen()));
		txtSDT.setText(String.valueOf(kh.getSdt()));
		cbGioiTinh.setValue(kh.getGioiTinh());
		dpNgaySinh.setValue(LocalDate.parse(String.valueOf(kh.getNgaySinh())));
		txtEmail.setText(String.valueOf(kh.getEmail()));
		txtDiaChi.setText(String.valueOf(kh.getDiaChi()));
	}
	@FXML
	public void capNhat(ActionEvent e) throws SQLException {
		String query = "update KhachHang set tenKH = N'"+txtHoTen.getText()+"', gioiTinh = N'"+cbGioiTinh.getValue()+"', ngaySinh = '"+Date.valueOf(dpNgaySinh.getValue())+"', sdt = '"+Integer.parseInt(txtSDT.getText())+"', email = N'"+txtEmail.getText()+"', diaChi = N'"+txtDiaChi.getText()+"' where maKH = '"+lblMaKH.getText()+"'";
		ps = con.prepareStatement(query);
		ps.execute();
		capNhatMessage();
		reload();
	}
//	public void edit() {
//		tenKH.setCellFactory(TextFieldTableCell.<NhanVien>forTableColumn());
//		tenKH.setOnEditCommit(event -> {
//			try {
////				int n = table.getSelectionModel().getSelectedIndex() + 7;
//				NhanVien nv = event.getTableView().getItems().get(event.getTablePosition().getRow());
//				int n = nv.getMaNV();
//				nv.settenKH(event.getNewValue());
//				String query = "update NhanVien set tenNV=N'"+ event.getNewValue()+"' where maNV=" + n ;
//				System.out.println(query);
//				
//				ps = con.prepareStatement(query);
//				ps.executeUpdate();
//				
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} 
//		});
//		
//		gioiTinh.setCellFactory(TextFieldTableCell.<NhanVien>forTableColumn());
//		gioiTinh.setOnEditCommit(event -> {
//			try {
//				NhanVien nv = event.getTableView().getItems().get(event.getTablePosition().getRow());
//				int n = nv.getMaNV();
//				nv.setGioiTinh(event.getNewValue());
//				String query = "update NhanVien set gioiTinh=N'"+ event.getNewValue()+"' where maNV=" + n ;
//				System.out.println(query);
//				
//				ps = con.prepareStatement(query);
//				ps.executeUpdate();
//			}catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		});
//		
//		cmnd.setCellFactory(TextFieldTableCell.<NhanVien>forTableColumn());
//		cmnd.setOnEditCommit(event -> {
//			try {
//				NhanVien nv = event.getTableView().getItems().get(event.getTablePosition().getRow());
//				int n = nv.getMaNV();
//				nv.setCmnd(event.getNewValue());
//				String query = "update NhanVien set cmnd=N'"+ event.getNewValue()+"' where maNV=" + n ;
//				System.out.println(query);
//				
//				ps = con.prepareStatement(query);
//				ps.executeUpdate();
//			}catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		});
//		
//		sdt.setCellFactory(TextFieldTableCell.<NhanVien>forTableColumn());
//		sdt.setOnEditCommit(event -> {
//			try {
//				NhanVien nv = event.getTableView().getItems().get(event.getTablePosition().getRow());
//				int n = nv.getMaNV();
//				nv.setSdt(event.getNewValue());
//				String query = "update NhanVien set sdt=N'"+ event.getNewValue()+"' where maNV=" + n ;
//				System.out.println(query);
//				
//				ps = con.prepareStatement(query);
//				ps.executeUpdate();
//			}catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		});
//		
//		email.setCellFactory(TextFieldTableCell.<NhanVien>forTableColumn());
//		email.setOnEditCommit(event -> {
//			try {
//				NhanVien nv = event.getTableView().getItems().get(event.getTablePosition().getRow());
//				int n = nv.getMaNV();
//				nv.setEmail(event.getNewValue());
//	
//				String query = "update NhanVien set email=N'"+event.getNewValue()+"' where maNV=" + n ;
//				System.out.println(query);
//				
//				ps = con.prepareStatement(query);
//				ps.executeUpdate();
//			}catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		});
//		
//		vaiTro.setCellFactory(TextFieldTableCell.<NhanVien>forTableColumn());
//		vaiTro.setOnEditCommit(event -> {
//			try {
//				NhanVien nv = event.getTableView().getItems().get(event.getTablePosition().getRow());
//				int n = nv.getMaNV();
//				nv.setVaiTro(event.getNewValue());
//				String query = "update NhanVien set vaiTro=N'"+ event.getNewValue()+"' where maNV=" + n ;
//				System.out.println(query);
//				ps = con.prepareStatement(query);
//				ps.executeUpdate();
//			}catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		});
//		
//		trangThai.setCellFactory(TextFieldTableCell.<NhanVien>forTableColumn());
//		trangThai.setOnEditCommit(event -> {
//			try {
//				NhanVien nv = event.getTableView().getItems().get(event.getTablePosition().getRow());
//				int n = nv.getMaNV();
//				nv.setTrangThai(event.getNewValue());
//				String query = "update NhanVien set trangThai=N'"+ event.getNewValue()+"' where maNV=" + n ;
//				System.out.println(query);
////				ps.setString(9, null);
//				ps = con.prepareStatement(query);
//				ps.executeUpdate();
//			}catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		});
//	}
	public void reload() {
//		list = getAllNV();
//		// TODO Auto-generated method stub
		cell();
		table.setItems(null);
		khList.clear();
		getAllKH();
	}
	public void cell() {
		maKH.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("maKH"));
		tenKH.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("hoTen"));
		gioiTinh.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("gioiTinh"));
		ngaySinh.setCellValueFactory(new PropertyValueFactory<KhachHang, Date>("ngaySinh"));
		sdt.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("sdt"));
		email.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("email"));
		diaChi.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("diaChi"));

	}
	public ObservableList<KhachHang> getAllKH(){

		String query = "select * from  KhachHang";
		
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setMaKH(rs.getInt("maKH"));
				kh.setHoTen(rs.getString("tenKH"));
				kh.setGioiTinh(rs.getString("gioiTinh"));
				kh.setNgaySinh(rs.getDate("ngaySinh"));
				kh.setSdt(rs.getString("sdt"));
				kh.setEmail(rs.getString("email"));
				kh.setDiaChi(rs.getString("diaChi"));
				khList.add(kh);
				table.setItems(khList);
			}
		}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
		return khList;
	}
	public void remove(ActionEvent e) {
		if(table.getSelectionModel().getSelectedIndex() <= -1) {
			xoaThatBaiMessage();
		}
		else {
			try {
				String query = "delete from KhachHang where maKH= '"+lblMaKH.getText()+"'";
				ps = con.prepareStatement(query);
				ps.execute();
				xoaThanhCongMessage();
				reload();
			}catch(Exception e1) {
				e1.printStackTrace();
				xoaThatBaiMessage();
			}
		}
	}
//	public void chooseImage(ActionEvent e) throws FileNotFoundException {
//		Stage stage = (Stage) ap.getScene().getWindow();
//		FileChooser fc = new FileChooser();
//		fc.setTitle("Chọn ảnh");
//		FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg","*.png");
//		file = fc.showOpenDialog(stage);
//		if(file !=null) {
//			Image image  = new Image(file.toURI().toString(),150,200,false,true);
//			imageView.setImage(image);
//			FileInputStream fs = new FileInputStream(file);
//		}
//	}
	public void images() {
		
	}
	public void resetField(ActionEvent e) {
		reset();
	}
	public void reset() {
		lblMaKH.setText(0 + "");
		dpNgaySinh.setValue(LocalDate.now());
		LocalDate ldNgayNhap = dpNgaySinh.getValue();
		Date dNgayNhap = Date.valueOf(ldNgayNhap);
		txtHoTen.setText("");
		dpNgaySinh.setValue(ldNgayNhap);
		cbGioiTinh.setValue("Nam");
		txtDiaChi.setText("");
		txtSDT.setText("");
		txtEmail.setText("");
	}
//	@FXML
//	public void getItem(MouseEvent event) {
//		int n = table.getSelectionModel().getSelectedIndex();
//		if(n<=-1) {
//			return;
//		}
//		txttenKH.setText(tenKH.getCellData(n).toString());
////		cbGioiTinh.setSelectionModel(gioiTinh.getCellData(n));
//		txtCMND.setText(cmnd.getCellData(n).toString());
//		txtSDT.setText(sdt.getCellData(n).toString());
//		txtEmail.setText(email.getCellData(n).toString());
//	}
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
//	private void setCell() {
//		table.setOnMouseClicked(e->{
//			NhanVien nv = table.getItems().get(table.getSelectionModel().getFocusedIndex());
//			showImage(nv.getMaNV());
//		});
//	}
//	private void showImage(int maNV) {
//		String sql = "select image from NhanVien where maNV = ?";
//		try {
//			ps = con.prepareStatement(sql);
//			ps.setInt(1, maNV);
//			rs = ps.executeQuery();
//			if(rs.next()) {
//				InputStream is = rs.getBinaryStream(1);
//				OutputStream os = new FileOutputStream(new File("data/photo.jpg"));
//				byte[] content = new byte[1024];
//				int size = 0;
//				while((size=is.read(content))!=-1) {
//					os.write(content,0,size);
//				}
//				image = new Image("file:avatar:jpg",imageView.getFitWidth(),imageView.getFitHeight(),true,true);
//				imageView.setImage(image);
////				if(rs.getBinaryStream("image") == null) {
////						is = new FileInputStream("C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\images\\avatar.png");
////						image = new Image(is,imageView.getFitWidth(),imageView.getFitHeight(),true,true);
////						imageView.setImage(image);
////				}
//			}
//		
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	@FXML
	public void searchByName() {
		txtTimKiem.setOnKeyReleased(e->{
			if(txtTimKiem.getText().equals("")) {
				reload();
			}
			else {
				khList.clear();
				String sql = " select * from KhachHang where tenKH like N'%" + txtTimKiem.getText() +"%'";
				try {
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					while(rs.next()) {
						KhachHang kh = new KhachHang();
						kh.setMaKH(rs.getInt("maKH"));
						kh.setHoTen(rs.getString("tenKH"));
						kh.setGioiTinh(rs.getString("gioiTinh"));
						kh.setNgaySinh(rs.getDate("ngaySinh"));
						kh.setSdt(rs.getString("sdt"));
						kh.setEmail(rs.getString("email"));
						kh.setDiaChi(rs.getString("diaChi"));
						khList.add(kh);
						table.setItems(khList);
					}
				}catch (Exception e1) {
					// TODO: handle exception
				}
			}
		});
	}
}
