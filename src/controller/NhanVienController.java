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
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
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

public class NhanVienController implements Initializable{
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
	private TextField txtHoTen, txtCMND, txtSDT, txtEmail, txtTimKiem;
	@FXML
	private Button chooseImage;
	@FXML
	private AnchorPane ap;
	@FXML
	private PasswordField txtMatKhau;
	
	private String[] gt = {"Nam","Nữ","Khác"};
	private String[] vt = {"Nhân viên", "Quản lý"};
	private String[] tt = {"Đang làm việc", "Nghỉ việc"};
	@FXML
	private ComboBox<String> cbGioiTinh;
	@FXML
	private ComboBox<String> cbVaiTro;
	@FXML
	private ComboBox<String> cbTrangThai;
	@FXML
	private DatePicker dpNgaySinh = new DatePicker();
//	@FXML
//	TableView<NhanVien> table;
//	@FXML
//	private TableColumn<NhanVien, Integer> maNV;
//	@FXML
//	private TableColumn<NhanVien, String> hoTen;
//	@FXML
//	private TableColumn<NhanVien, String> gioiTinh;
//	@FXML
//	private TableColumn<NhanVien, String> ngaySinh;
//	@FXML
//	private TableColumn<NhanVien, String> cmnd;
//	@FXML
//	private TableColumn<NhanVien, String> sdt;
//	@FXML
//	private TableColumn<NhanVien, String> email;
//	@FXML
//	private TableColumn<NhanVien, String> vaiTro;
//	@FXML
//	private TableColumn<NhanVien, String> trangThai;
//	@FXML
//	private ObservableList<NhanVien> list = FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//			reload();
//			edit();
//			searchByName();
//			setCell();
//			cbGioiTinh.setItems(FXCollections.observableArrayList("Nam", "Nữ", "Khác"));
			cbGioiTinh.getItems().addAll(gt);
			cbVaiTro.getItems().addAll(vt);
			cbTrangThai.getItems().addAll(tt);
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
	public void capNhatThuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/CapNhatThuoc.fxml"));
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
	public void thuocTrongKho(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThuocTrongKho.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void capNhatLoaiThuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/CapNhatLoaiThuoc.fxml"));
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
	public void lapHoaDonKhongKeDon(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThemHoaDonKhongTheoDon.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void lapHoaDonKeDon(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThemHoaDonTheoDon.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void timKiemHoaDon(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TimKiemHoaDon.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void thongKeDoanhThu(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThongKeDoanhThu.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void thongKeKhachHang(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThongKeKhachHang.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void thongKeThuocSapHetHan(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThongKeThuocSapHetHan.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void capNhatNhanVien(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/CapNhatNhanVien.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	  public void gioHang(ActionEvent e) throws IOException {
	    	Stage stage = (Stage) mb.getScene().getWindow();
	    	FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/view/GioHang.fxml"));
	        Parent sampleParent = loader.load();
	        Scene scene = new Scene(sampleParent);
	        stage.setScene(scene);
		}
	     public void themKhachHang(ActionEvent e) throws IOException {
	     	Stage stage = (Stage) mb.getScene().getWindow();
	     	FXMLLoader loader = new FXMLLoader();
	         loader.setLocation(getClass().getResource("/view/ThemKhachHang.fxml"));
	         Parent sampleParent = loader.load();
	         Scene scene = new Scene(sampleParent);
	         stage.setScene(scene);
	 	}

	     public void timKiemKhachHang(ActionEvent e) throws IOException {
	     	Stage stage = (Stage) mb.getScene().getWindow();
	     	FXMLLoader loader = new FXMLLoader();
	         loader.setLocation(getClass().getResource("/view/TimKiemKhachHang.fxml"));
	         Parent sampleParent = loader.load();
	         Scene scene = new Scene(sampleParent);
	         stage.setScene(scene);
	 	}

	     public void capNhatKhachHang(ActionEvent e) throws IOException {
	     	Stage stage = (Stage) mb.getScene().getWindow();
	     	FXMLLoader loader = new FXMLLoader();
	         loader.setLocation(getClass().getResource("/view/CapNhatKhachHang.fxml"));
	         Parent sampleParent = loader.load();
	         Scene scene = new Scene(sampleParent);
	         stage.setScene(scene);
	 	}
	     public void themNCC(ActionEvent e) throws IOException {
	      	Stage stage = (Stage) mb.getScene().getWindow();
	      	FXMLLoader loader = new FXMLLoader();
	          loader.setLocation(getClass().getResource("/view/ThemNCC.fxml"));
	          Parent sampleParent = loader.load();
	          Scene scene = new Scene(sampleParent);
	          stage.setScene(scene);
	  	}
	     public void timKiemNCC(ActionEvent e) throws IOException {
	      	Stage stage = (Stage) mb.getScene().getWindow();
	      	FXMLLoader loader = new FXMLLoader();
	          loader.setLocation(getClass().getResource("/view/TimKiemNCC.fxml"));
	          Parent sampleParent = loader.load();
	          Scene scene = new Scene(sampleParent);
	          stage.setScene(scene);
	  	}
	     public void capNhatNCC(ActionEvent e) throws IOException {
	      	Stage stage = (Stage) mb.getScene().getWindow();
	      	FXMLLoader loader = new FXMLLoader();
	          loader.setLocation(getClass().getResource("/view/CapNhatNCC.fxml"));
	          Parent sampleParent = loader.load();
	          Scene scene = new Scene(sampleParent);
	          stage.setScene(scene);
	  	}
	     public void themDonThuocMau(ActionEvent e) throws IOException {
	       	Stage stage = (Stage) mb.getScene().getWindow();
	       	FXMLLoader loader = new FXMLLoader();
	           loader.setLocation(getClass().getResource("/view/ThemDonThuocMau.fxml"));
	           Parent sampleParent = loader.load();
	           Scene scene = new Scene(sampleParent);
	           stage.setScene(scene);
	   	}
	      public void timKiemDonThuocMau(ActionEvent e) throws IOException {
	       	Stage stage = (Stage) mb.getScene().getWindow();
	       	FXMLLoader loader = new FXMLLoader();
	           loader.setLocation(getClass().getResource("/view/TimKiemDonThuocMau.fxml"));
	           Parent sampleParent = loader.load();
	           Scene scene = new Scene(sampleParent);
	           stage.setScene(scene);
	   	}
	      public void capNhatDonThuocMau(ActionEvent e) throws IOException {
	       	Stage stage = (Stage) mb.getScene().getWindow();
	       	FXMLLoader loader = new FXMLLoader();
	           loader.setLocation(getClass().getResource("/view/CapNhatDonThuocMau.fxml"));
	           Parent sampleParent = loader.load();
	           Scene scene = new Scene(sampleParent);
	           stage.setScene(scene);
	   	}

	//End Navbar
	
	public void add(ActionEvent e) {
		String query = "insert into NhanVien(tenNV,matKhau,gioiTinh,ngaySinh,cmnd,sdt,email,vaiTro,trangThai,image) values (?,?,?,?,?,?,?,?,?,?)";
		int id = 1;
		try {	
			String gTinh = cbGioiTinh.getValue();
			String vTro = cbVaiTro.getValue();
			String tThai = cbTrangThai.getValue();
			LocalDate ld = dpNgaySinh.getValue();
			LocalDate defaultDate = LocalDate.of(2000, 01, 01);
			Date date = Date.valueOf(ld);
//			NhanVien nv = new NhanVien();
			ps = con.prepareStatement(query);
			ps.setString(1, txtHoTen.getText());
			ps.setString(2, txtMatKhau.getText());
			ps.setString(3, gTinh);
			ps.setDate(4, date);
			ps.setString(5, txtCMND.getText());
			ps.setString(6, txtSDT.getText());
			ps.setString(7, txtEmail.getText());	
			ps.setString(8, vTro);
			ps.setString(9, tThai);
			fis = new FileInputStream(file);
//			if(fis==null) {
//				
//			}
			ps.setBinaryStream(10, fis, file.length());

			ps.execute();
			themThanhCongMessage();
//			reload();	
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			themThatBaiMessage();
		}
//		reset();
//		imageView.setImage("");
	}
//	public void edit() {
//		hoTen.setCellFactory(TextFieldTableCell.<NhanVien>forTableColumn());
//		hoTen.setOnEditCommit(event -> {
//			try {
////				int n = table.getSelectionModel().getSelectedIndex() + 7;
//				NhanVien nv = event.getTableView().getItems().get(event.getTablePosition().getRow());
//				int n = nv.getMaNV();
//				nv.setHoTen(event.getNewValue());
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
//	public void reload() {
//		list = getAllNV();
//		// TODO Auto-generated method stub
//		cell();
//		table.setItems(list);
//	}
//	public void cell() {
//		maNV.setCellValueFactory(new PropertyValueFactory<NhanVien, Integer>("maNV"));
//		hoTen.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("hoTen"));
//		gioiTinh.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("gioiTinh"));
//		ngaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
//		cmnd.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("cmnd"));
//		sdt.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("sdt"));
//		email.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("email"));
//		vaiTro.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("vaiTro"));
//		trangThai.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("trangThai"));
//	}
	public ObservableList<NhanVien> getAllNV(){
		ObservableList<NhanVien> nvList = FXCollections.observableArrayList();
		String query = "select * from NhanVien";
		
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNV(rs.getInt("maNV"));
				nv.setHoTen(rs.getString("tenNV"));
				nv.setGioiTinh(rs.getString("gioiTinh"));
				nv.setNgaySinh(rs.getDate("ngaySinh"));
				nv.setCmnd(rs.getString("cmnd"));
				nv.setSdt(rs.getString("sdt"));
				nv.setEmail(rs.getString("email"));
				nv.setVaiTro(rs.getString("vaiTro"));
				nv.setTrangThai(rs.getString("trangThai"));
				nvList.add(nv);
				
			}
		}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
		return nvList;
	}
//	public void remove(ActionEvent e) {
//		try {
//			ObservableList<NhanVien> list;
//			list = table.getSelectionModel().getSelectedItems();
//			int i = list.get(0).getMaNV();
//			String query = "delete from NhanVien where maNV="+i;
//			ps = con.prepareStatement(query);
//			ps.execute();
//			xoaThanhCongMessage();
//			reload();
//		}catch(Exception e1) {
//			e1.printStackTrace();
//			xoaThatBaiMessage();
//		}
//	}
	public void chooseImage(ActionEvent e) throws FileNotFoundException {
		Stage stage = (Stage) ap.getScene().getWindow();
		FileChooser fc = new FileChooser();
		fc.setTitle("Chọn ảnh");
		FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg","*.png");
		file = fc.showOpenDialog(stage);
		if(file !=null) {
			Image image  = new Image(file.toURI().toString(),150,200,false,true);
			imageView.setImage(image);
			FileInputStream fs = new FileInputStream(file);
		}
	}
	public void images() {
		
	}
	public void resetField(ActionEvent e) {
		reset();
	}
	public void reset() {
		txtHoTen.setText("");
		txtMatKhau.setText("");
		txtCMND.setText("");
		txtSDT.setText("");
		txtEmail.setText("");
	}
//	@FXML
//	public void getItem(MouseEvent event) {
//		int n = table.getSelectionModel().getSelectedIndex();
//		if(n<=-1) {
//			return;
//		}
//		txtHoTen.setText(hoTen.getCellData(n).toString());
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
	private void showImage(int maNV) {
		String sql = "select image from NhanVien where maNV = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, maNV);
			rs = ps.executeQuery();
			if(rs.next()) {
				InputStream is = rs.getBinaryStream(1);
				OutputStream os = new FileOutputStream(new File("data/photo.jpg"));
				byte[] content = new byte[1024];
				int size = 0;
				while((size=is.read(content))!=-1) {
					os.write(content,0,size);
				}
				image = new Image("file:avatar:jpg",imageView.getFitWidth(),imageView.getFitHeight(),true,true);
				imageView.setImage(image);
//				if(rs.getBinaryStream("image") == null) {
//						is = new FileInputStream("C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\images\\avatar.png");
//						image = new Image(is,imageView.getFitWidth(),imageView.getFitHeight(),true,true);
//						imageView.setImage(image);
//				}
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
		
	}
//	@FXML
//	private void searchByName() {
//		txtTimKiem.setOnKeyReleased(e->{
//			if(txtTimKiem.getText().equals("")) {
//				reload();
//			}
//			else {
//				list.clear();
//				String sql = " select * from NhanVien where tenNV collate SQL_Latin1_General_CP1_CI_AI like N'%" + txtTimKiem.getText() +"%'";
//				try {
//					ps = con.prepareStatement(sql);
//					rs = ps.executeQuery();
//					while(rs.next()) {
//						NhanVien nv = new NhanVien();
//						nv.setMaNV(rs.getInt("maNV"));
//						nv.setHoTen(rs.getString("tenNV"));
//						nv.setGioiTinh(rs.getString("gioiTinh"));
//						nv.setNgaySinh(rs.getDate("ngaySinh"));
//						nv.setCmnd(rs.getString("cmnd"));
//						nv.setSdt(rs.getString("sdt"));
//						nv.setEmail(rs.getString("email"));
//						nv.setVaiTro(rs.getString("vaiTro"));
//						nv.setTrangThai(rs.getString("trangThai"));
//						list.add(nv);
//						table.setItems(list);
//					}
//				}catch (Exception e1) {
//					// TODO: handle exception
//				}
//			}
//		});
//	}
}
