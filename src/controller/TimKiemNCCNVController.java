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
import entity.NhaCungCap;
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

public class TimKiemNCCNVController implements Initializable{
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
	private Button btnTimKiem;
	@FXML
	private TextField txtNCC, txtEmail, txtSDT;
	@FXML
	private AnchorPane ap;
	@FXML
	TableView<NhaCungCap> table;
	@FXML
	private TableColumn<NhaCungCap, Integer> maNCC;
	@FXML
	private TableColumn<NhaCungCap, String> tenNCC;
	@FXML
	private TableColumn<NhaCungCap, String> sdt;
	@FXML
	private TableColumn<NhaCungCap, String> email;
	@FXML
	private TableColumn<NhaCungCap, String> diaChi;

	@FXML
	private ObservableList<NhaCungCap> list = FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			try {
				reload();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
//			edit();
//			searchByName();
//			reload();
//			setCell();
//			cbGioiTinh.setItems(FXCollections.observableArrayList("Nam", "Nữ", "Khác"));

//			table.setOnMouseClicked(e->{
//				System.out.println("test");
//				InputStream is;
//				
//				try {
//					NhanVien nv = (NhanVien)table.getSelectionModel().getSelectedItem();
//					String query = "select * from NhanVien where maNV = ?";
//					ps = con.prepareStatement(query);
////					ps.setInt(1, nv.getMaNV());
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
			btnTimKiem.setOnAction(a -> {
				String ncc = txtNCC.getText().toString();
				String sdt = txtSDT.getText().toString();
				String email = txtEmail.getText().toString();
				if(ncc == "" && sdt == "" && email == "") {
					table.getItems().clear();
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Thông báo");
					alert.setContentText("Không được để trống");
					alert.setHeaderText(null);
					alert.showAndWait();
					getAllNCC();
				}
				else {
				table.getItems().clear();
				String sql = "select * from NhaCungCap where tenNCC like N'%"+ncc+"%' and sdt like N'%"+sdt+"%' and email like N'%"+email+"%'";
				try {
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					while(rs.next()) {
						NhaCungCap nc = new NhaCungCap();
						nc.setMaNCC(rs.getInt("maNCC"));
						nc.setTenNCC(rs.getString("tenNCC"));
						nc.setSdt(rs.getString("sdt"));
						nc.setEmail(rs.getString("email"));
						nc.setDiaChi(rs.getString("diaChi"));
						list.add(nc);
						table.setItems(list);	
					}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	public void reload() throws SQLException {
//		list = getAllNV();
		// TODO Auto-generated method stub
		cell();
		getAllNCC();
		
	}
	public void cell() {
		maNCC.setCellValueFactory(new PropertyValueFactory<NhaCungCap, Integer>("maNCC"));
		tenNCC.setCellValueFactory(new PropertyValueFactory<NhaCungCap, String>("tenNCC"));
		sdt.setCellValueFactory(new PropertyValueFactory<NhaCungCap, String>("sdt"));
		email.setCellValueFactory(new PropertyValueFactory<NhaCungCap, String>("email"));
		diaChi.setCellValueFactory(new PropertyValueFactory<NhaCungCap, String>("diaChi"));
	}

	public ObservableList<NhaCungCap> getAllNCC(){
		String query = "select * from NhaCungCap";
		
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setMaNCC(rs.getInt("maNCC"));
				ncc.setTenNCC(rs.getString("tenNCC"));
				ncc.setSdt(rs.getString("sdt"));
				ncc.setEmail(rs.getString("email"));
				ncc.setDiaChi(rs.getString("diaChi"));
				ncc.setTrangThai(rs.getString("trangThai"));
				list.add(ncc);
				table.setItems(list);
			}
		}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
		return list;
	}

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
//				if(rs.getBinaryStream("image") == null) {
//						is = new FileInputStream("C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\images\\avatar.png");
//						image = new Image(is,imageView.getFitWidth(),imageView.getFitHeight(),true,true);
//						imageView.setImage(image);
//				}
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
//						NhaCungCap ncc = new NhaCungCap();
//						ncc.setMaNCC(rs.getInt("maNCC"));
//						ncc.setTenNCC(rs.getString("tenNCC"));
//						ncc.setSdt(rs.getInt("sdt"));
//						ncc.setEmail(rs.getString("email"));
//						ncc.setDiaChi(rs.getString("diaChi"));
//						list.add(ncc);
//						table.setItems(list);
//					}
//				}catch (Exception e1) {
//					// TODO: handle exception
//				}
//			}
//		});
//	}
}
