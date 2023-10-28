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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import database.KetNoiDatabase;
import entity.CTPhieuDatThuoc;
import entity.KhachHang;
import entity.Kho;
import entity.NhanVien;
import entity.PhieuNhap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.ScrollPane;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ThemGioHangController implements Initializable{
	@FXML
	private MenuButton mb;
	@FXML
	private TextField txtMaPN;

	@FXML
	private TextField txtNCC, txtNSX, txtTenThuoc, txtSL, txtTenKH, txtGioiTinh, txtSdt, txtEmail, txtTienNhan;
	@FXML 
	private Button btnThemKH, btnThemTenThuoc;
	@FXML
	private DatePicker dpNgayNhap;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	static ResultSet rs;
	@FXML
	Label lblName, lblThanhTien, lblTienThoi;
	@FXML
	TableView<CTPhieuDatThuoc> table;
	@FXML
	private TableColumn<CTPhieuDatThuoc, Integer> maCTPDT;
	@FXML
	private TableColumn<CTPhieuDatThuoc, String> tenThuoc;
	@FXML
	private TableColumn<CTPhieuDatThuoc, String> tenLoaiThuoc;
	@FXML
	private TableColumn<CTPhieuDatThuoc, String> donViTinh;
	@FXML
	private TableColumn<CTPhieuDatThuoc, Float> donGia;
	@FXML
	private TableColumn<CTPhieuDatThuoc, Integer> soLuong;
	@FXML
	private TableColumn<CTPhieuDatThuoc, Float> tongGiaBan;
	@FXML
	private Button btnTimThuoc;

    String ten;
	int hd = 0;
	URL arg0;
	ResourceBundle arg1;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//		getAllPN();
//		reload();
		NhanVien dnc = DangNhapController.getNV();
		dpNgayNhap.setValue(LocalDate.now());
		try {
			cell();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		table.setItems(null);
//		try {
//			while(rs.next()) {
//				lblName.setText("Xin chào, " + dnc.getHoTen());
		
		 btnThemTenThuoc.setOnAction(arg01 -> {
			
			ObservableList<Kho> Klist = FXCollections.observableArrayList();
			BorderPane root = new BorderPane();
			ScrollPane scroll = new ScrollPane();
			TextField txtTimKiem = new TextField();
			Label lblTimKiem = new Label("Tìm kiếm tên thuốc");
			Button chon = new Button("Chọn sản phẩm");
			TextField soLuong = new TextField("0");
			Label chonSL = new Label("Chọn số lượng");
			HBox h1 = new HBox(3);
			HBox h2 = new HBox(3);
			
			Stage stage = new Stage();
			TableView tableView = new TableView<Kho>();
			TableColumn maThuoc = new TableColumn<Kho, Integer>("Mã thuốc");
			maThuoc.setCellValueFactory(new PropertyValueFactory<Kho, Integer>("maThuoc"));
			TableColumn tenThuoc = new TableColumn<Kho, String>("Tên thuốc");
			tenThuoc.setCellValueFactory(new PropertyValueFactory<Kho, String>("tenThuoc"));
			TableColumn donViTinh = new TableColumn<Kho, String>("Đơn vị tính");
			donViTinh.setCellValueFactory(new PropertyValueFactory<Kho, String>("donViTinh"));
			TableColumn slTonKho = new TableColumn<Kho, Integer>("Số lượng hiện có");
			slTonKho.setCellValueFactory(new PropertyValueFactory<Kho, Integer>("slTonKho"));
			TableColumn giaBan = new TableColumn<Kho, Float>("Giá bán");
			giaBan.setCellValueFactory(new PropertyValueFactory<Kho, Float>("giaBan"));
			TableColumn soLo = new TableColumn<Kho, String>("Số lô");
			soLo.setCellValueFactory(new PropertyValueFactory<Kho, String>("soLo"));
			TableColumn hanSuDung = new TableColumn<Kho, Date>("Hạn sử dụng");	
			hanSuDung.setCellValueFactory(new PropertyValueFactory<Kho, Date>("hanSuDung"));
			
			tableView.getColumns().add(maThuoc);
			tableView.getColumns().add(tenThuoc);
			tableView.getColumns().add(donViTinh);
			tableView.getColumns().add(slTonKho);
			tableView.getColumns().add(giaBan);
			tableView.getColumns().add(soLo);
			tableView.getColumns().add(hanSuDung);
			root.setCenter(scroll);
			scroll.setContent(tableView);
			h1.getChildren().addAll( lblTimKiem, txtTimKiem);
			h2.getChildren().addAll(chonSL, soLuong, chon);
			root.setTop(h1);
			root.setBottom(h2);
			


			
			String sql = "select * from Tu t left join Thuoc th on t.maThuoc = th.maThuoc left join LoaiThuoc l on l.maLoaiThuoc = th.maLoaiThuoc where slTonKho > 0 order by tenThuoc";
			try {
				taoHD();
				int maHD = getMaHD();
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					Kho k = new Kho();
					k.setMaKho(rs.getInt("maTu"));
					k.setMaThuoc(rs.getInt("maThuoc"));
					k.setTenThuoc(rs.getString("tenThuoc"));
					String tenT = rs.getString("tenThuoc");
					k.setTenLoaiThuoc(rs.getString("tenLoaiThuoc"));
					k.setSlTonKho(rs.getInt("slTonKho"));
					k.setDonViTinh(rs.getString("donViTinh"));
					k.setGiaBan(rs.getFloat("giaBan"));
					k.setSoLo(rs.getString("soLo"));
					k.setHanSuDung(rs.getDate("hanSuDung"));
					Klist.add(k);
					tableView.setItems(Klist);
					chon.setOnAction(arg02 ->{
						int index = tableView.getSelectionModel().getSelectedIndex();
						if(index<=-1) {
							return;
						}
						if(Integer.parseInt(slTonKho.getCellData(index).toString()) < Integer.parseInt(soLuong.getText().toString())) {
							themThatBaiMessage1();
						}
						else if(Integer.parseInt(soLuong.getText().toString()) == 0){
							themThatBaiMessage2();
						}
						
						else {
							String sqlTenThuoc = String.valueOf(tenThuoc.getCellData(index).toString());
							String sqlDVT = String.valueOf(donViTinh.getCellData(index).toString());
							String sqlGiaBan = String.valueOf(giaBan.getCellData(index).toString());
							String sqlSoLo = String.valueOf(soLo.getCellData(index).toString());
							String sqlHSD = String.valueOf(hanSuDung.getCellData(index).toString());
							System.out.println(sqlTenThuoc + " " + sqlDVT +" " + sqlGiaBan + " " + sqlSoLo + " " + sqlHSD + " ");
							String get = "select maTu,t.maThuoc,th.tenThuoc,th.donViTinh,t.giaBan,t.soLo,t.hanSuDung from Tu t left join Thuoc th on th.maThuoc = t.maThuoc where tenThuoc = '" +sqlTenThuoc+"' and donViTinh = '" +sqlDVT+"' and t.giaBan = '" +sqlGiaBan+ "' and soLo ='"+sqlSoLo+"' and hanSuDung='"+sqlHSD+"'" ;
							try {
								ps = con.prepareStatement(get);
								rs = ps.executeQuery();
								
								while(rs.next()) {
									Kho kho = new Kho();
									kho.setMaKho(rs.getInt("maTu"));
									kho.setMaThuoc(rs.getInt("maThuoc"));
									kho.setTenThuoc(rs.getString("tenThuoc"));
									kho.setDonViTinh(rs.getString("donViTinh"));
									kho.setGiaBan(rs.getFloat("giaBan"));
									kho.setSoLo(rs.getString("soLo"));
									kho.setHanSuDung(rs.getDate("hanSuDung"));
									String add = "insert into CTPhieuDatThuoc(maThuoc, maTu, donGia, soLuong,maPDT, thanhTien) values(?,?,?,?,?,?)";
									ps = con.prepareStatement(add);
									ps.setInt(1, kho.getMaThuoc());
									ps.setInt(2, kho.getMaKho());
									ps.setFloat(3, kho.getGiaBan());
									ps.setFloat(4, Float.parseFloat(soLuong.getText()));
									ps.setInt(5, maHD);
									ps.setFloat(6, kho.getGiaBan()*Float.parseFloat(soLuong.getText()));
									System.out.println(ps.execute());
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
//							txtTenThuoc.setText(tenThuoc.getCellData(index).toString());
//							txtSL.setText(soLuong.getText().toString());
							try {
								getCTPhieuDatThuoc();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							stage.close();
						}
					});
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Scene scene = new Scene(root,570,300);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			 
		});
		btnThemKH.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ObservableList<KhachHang> Khlist = FXCollections.observableArrayList();
				BorderPane root = new BorderPane();
				ScrollPane scroll = new ScrollPane();
				TextField txtTimKiem = new TextField();
				Label lblTimKiem = new Label("Tìm kiếm khách hàng");
		
				Button chon = new Button("Chọn khách hàng");
				HBox h1 = new HBox(2);
				HBox h2 = new HBox(2);
				
				Stage stage = new Stage();
				TableView tableView = new TableView<Kho>();
				tableView.setPrefWidth(650);
				TableColumn maKH = new TableColumn<Kho, Integer>("Mã khách hàng");
				maKH.setCellValueFactory(new PropertyValueFactory<Kho, Integer>("maKH"));
				TableColumn tenKH = new TableColumn<Kho, String>("Tên khách hàng");
				tenKH.setCellValueFactory(new PropertyValueFactory<Kho, String>("hoTen"));
				TableColumn gioiTinh = new TableColumn<Kho, String>("Giới tính");
				gioiTinh.setCellValueFactory(new PropertyValueFactory<Kho, String>("gioiTinh"));
				TableColumn ngaySinh = new TableColumn<Kho, Integer>("Ngày sinh");
				ngaySinh.setCellValueFactory(new PropertyValueFactory<Kho, Integer>("ngaySinh"));
				TableColumn sdt = new TableColumn<Kho, Float>("Số điện thoại");
				sdt.setCellValueFactory(new PropertyValueFactory<Kho, Float>("sdt"));
				TableColumn email = new TableColumn<Kho, String>("Email");
				email.setCellValueFactory(new PropertyValueFactory<Kho, String>("email"));
				TableColumn diaChi = new TableColumn<Kho, Date>("Địa chỉ");	
				diaChi.setCellValueFactory(new PropertyValueFactory<Kho, Date>("diaChi"));
				
				tableView.getColumns().add(maKH);
				tableView.getColumns().add(tenKH);
				tableView.getColumns().add(gioiTinh);
				tableView.getColumns().add(ngaySinh);
				tableView.getColumns().add(sdt);
				tableView.getColumns().add(email);
				tableView.getColumns().add(diaChi);
				root.setCenter(scroll);
				scroll.setContent(tableView);
				h1.getChildren().addAll(lblTimKiem, txtTimKiem);
			
				h2.getChildren().addAll(chon);
				root.setTop(h1);
				root.setBottom(h2);
				String sql = "select * from KhachHang";
				try {
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					while(rs.next()) {
						KhachHang kh = new KhachHang();
						kh.setMaKH(rs.getInt("maKH"));
						kh.setHoTen(rs.getString("hoTenKH"));
						kh.setGioiTinh(rs.getString("gioiTinh"));
						kh.setNgaySinh(rs.getDate("ngaySinh"));
						kh.setSdt(rs.getInt("soDienThoai"));
						kh.setEmail(rs.getString("email"));
						kh.setDiaChi(rs.getString("diaChi"));
						Khlist.add(kh);
						tableView.setItems(Khlist);
						chon.setOnAction(arg02 ->{
							int index = tableView.getSelectionModel().getSelectedIndex();
							if(index<=-1) {
								return;
							}
							else {
								
								txtTenKH.setText(tenKH.getCellData(index).toString());
								txtSdt.setText(sdt.getCellData(index).toString());
								txtEmail.setText(email.getCellData(index).toString());
								txtGioiTinh.setText(gioiTinh.getCellData(index).toString());
								stage.close();
							}
						});
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				Scene scene = new Scene(root,650,350);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
		});
	}
	public void getKH(ActionEvent e) throws SQLException, IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/XemThongTinThuoc.fxml"));
		Parent sampleParent = loader.load();
		XemThongTinThuocController xc = loader.getController();
		xc.getAllThuocTonKho();
		Stage stage = new Stage();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
        stage.show();
		System.out.println("test thu");
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
	public void lapPhieuDatThuocKhongKeDon(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThemPhieuDatThuocKhongTheoDon.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void lapPhieuDatThuocKeDon(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThemPhieuDatThuocTheoDon.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
       
	}
	public void timKiemPhieuDatThuoc(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TimKiemPhieuDatThuoc.fxml"));
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
	      
	      private void themThatBaiMessage1() {
	  		Alert alert = new Alert(AlertType.ERROR, "Thêm thất bại, số lượng sản phẩm trong kho không đủ", ButtonType.OK);
	  		alert.setTitle("Thông báo");
	  		alert.setHeaderText(null);
	  		alert.show();
	  	}
	      private void themThatBaiMessage2() {
		  		Alert alert = new Alert(AlertType.ERROR, "Thêm thất bại, bạn chưa nhập số lượng", ButtonType.OK);
		  		alert.setTitle("Thông báo");
		  		alert.setHeaderText(null);
		  		alert.show();

		  	}

	//End Navbar
//	public void themThuoc() throws IOException, SQLException {
//		String sql = "select * from Tu k left join Thuoc t on t.maThuoc = k.maThuoc order by tenThuoc";
//		ps = con.prepareStatement(sql);
//		rs = ps.executeQuery();
//		ObservableList list = FXCollections.observableArrayList();
//		Kho k = new Kho();
//		while(rs.next()) 
//		{
//			k.setMaKho(rs.getInt("maTu"));
//			String tenThuoc = rs.getString("tenThuoc");
//			list.add(tenThuoc);
//		}
//		
//		cbbTenThuoc.setItems(list);
//	}
//
//	public String getInfor(String text) throws SQLException {
//		return text;
//	}

	  public int getTTKhachHang() throws SQLException{
		String getKH1 = "select * from KhachHang where hoTenKH = N'"+txtTenKH.getText()+"' and gioiTinh = N'"+txtGioiTinh.getText()+"'";
		ps = con.prepareStatement(getKH1);
		rs = ps.executeQuery();
		int maKH = 0;
		while(rs.next())
		maKH = rs.getInt("maKH");
		System.out.println(maKH);
		return maKH;
	  }
		public int getMaHD() throws SQLException {
			 String chonhd = "select max(maPDT) as maHD from PhieuDatThuoc";
			 ps = con.prepareStatement(chonhd);
			 rs = ps.executeQuery();
			 int maHD = 0;
			 while(rs.next()) 
			 maHD = rs.getInt("maHD");
			 return maHD;
		}
	 public void tienThoi() {
		 float tienNhan = Float.parseFloat(txtTienNhan.getText());
		 float thanhTien = Float.parseFloat(lblThanhTien.getText());
		 lblTienThoi.setText(String.valueOf(tienNhan - thanhTien));
	 }
	 public void cell() throws SQLException {
		 maCTPDT.setCellValueFactory(new PropertyValueFactory<CTPhieuDatThuoc, Integer>("maCTHD"));
		 tenThuoc.setCellValueFactory(new PropertyValueFactory<CTPhieuDatThuoc, String>("tenThuoc"));
		 tenLoaiThuoc.setCellValueFactory(new PropertyValueFactory<CTPhieuDatThuoc, String>("tenLoaiThuoc"));
		 donViTinh.setCellValueFactory(new PropertyValueFactory<CTPhieuDatThuoc, String>("donViTinh"));
		 donGia.setCellValueFactory(new PropertyValueFactory<CTPhieuDatThuoc, Float>("donGia"));
		 soLuong.setCellValueFactory(new PropertyValueFactory<CTPhieuDatThuoc, Integer>("soLuong"));
		 tongGiaBan.setCellValueFactory(new PropertyValueFactory<CTPhieuDatThuoc, Float>("tongGiaBan"));

	 }
	 public void getCTPhieuDatThuoc() throws SQLException {
		 int maHD = getMaHD();
		 String sql = "select * from CTPhieuDatThuoc cthd left join Thuoc t on t.maThuoc = cthd.maThuoc inner join Tu tu on tu.maTu = cthd.maTu inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc where maPDT = '"+maHD+"'";
		 ps = con.prepareStatement(sql);
		 rs = ps.executeQuery();
		 ObservableList<CTPhieuDatThuoc> cthdList = FXCollections.observableArrayList();
		 int i = 0;
		 while(rs.next()) {
			 CTPhieuDatThuoc cthd = new CTPhieuDatThuoc();
			 cthd.setMaHD(maHD);
			 cthd.setMaCTHD(i = i + 1);
			 cthd.setTenThuoc(rs.getString("tenThuoc"));
			 cthd.setDonViTinh(rs.getString("donViTinh"));
			 cthd.setTenLoaiThuoc(rs.getString("tenLoaiThuoc"));
			 cthd.setSoLo(rs.getString("soLo"));
			 cthd.setDonGia(rs.getFloat("donGia"));
			 cthd.setSoLuong(rs.getInt("soLuong"));
			 cthd.setTongGiaBan(rs.getFloat("thanhTien"));
			 cthdList.add(cthd);
			 table.setItems(cthdList);
		 }
		 String tongTien = "select sum(thanhTien) as tt from CTPhieuDatThuoc where maPDT ='"+maHD+"'";
		 ps = con.prepareStatement(tongTien);
		 rs = ps.executeQuery();
		 
		 while(rs.next()) {
			 float tong = rs.getFloat("tt");
			 lblThanhTien.setText(tong + "");
			 tienThoi();
		 }
	 }
	 public void taoHD() {
		 NhanVien dnc = DangNhapController.getNV();
		 LocalDate ldNgayNhap = dpNgayNhap.getValue();
		 Date dNgayNhap = Date.valueOf(ldNgayNhap);
		 if(hd == 0) {
			 hd += 1;
			 String taohd = "insert into PhieuDatThuoc(maNV, ngayLapHD, tongTien) values(?,?,?)";
			 System.out.println(taohd);
			 try {
				ps = con.prepareStatement(taohd);
				 ps.setInt(1, dnc.getMaNV());
				 ps.setDate(2, dNgayNhap);
				 ps.setFloat(3, 0);
				 ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 
	 }

	@FXML
	public void thanhToan(ActionEvent e) throws SQLException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Thông báo");
		alert.setContentText("Bạn có chắc muốn thanh toán hoá đơn này không?");
		alert.setHeaderText(null);
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get()==ButtonType.OK) {
			int maHD = getMaHD();
			int ma = getTTKhachHang();
			System.out.println("ma hoa don" + maHD);
			String tongTien = "select sum(thanhTien) as tt from CTPhieuDatThuoc where maPDT ='"+maHD+"'";
			ps = con.prepareStatement(tongTien);
			rs = ps.executeQuery();
			while(rs.next()) { 
			float tong = rs.getFloat("tt");
			System.out.println(rs.getFloat("tt"));
			lblThanhTien.setText(tong+ "");
			System.out.println("in hoá đơn thành công");
			String sql1 = "update PhieuDatThuoc set tongTien = '"+tong+"', maKH = '"+ma+"' where maPDT = '"+maHD+"'";
			ps = con.prepareStatement(sql1);
			ps.execute();
			txtTenKH.setText("");
			txtSdt.setText("");
			txtGioiTinh.setText("");
			txtEmail.setText("");
			lblThanhTien.setText("0");
			txtTienNhan.setText("0");
			lblTienThoi.setText("0");
			}
		}
			else if(result.get()==ButtonType.CANCEL) {
				System.out.println("Không");
			}
	}

//	public int getNV() {
//		NhanVien nv = new NhanVien();
//		String sql = "select tenNV from NhanVien";
//		PreparedStatement ps;
//		try {
//			ps = con.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				nv.setMaNV(rs.getInt("maNV"));
//				nv.setHoTen(rs.getString("tenNV"));
//				return nv.getMaNV();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return nv.getMaNV();
//	}

}
