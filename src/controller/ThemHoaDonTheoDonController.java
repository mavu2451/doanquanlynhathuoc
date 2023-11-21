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
import java.util.Optional;
import java.util.ResourceBundle;


import database.KetNoiDatabase;
import entity.CTDonThuocKhamBenh;
import entity.CTHoaDon;
import entity.CTThuoc;
import entity.DonThuocKhamBenh;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuNhap;
import entity.Thuoc;
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

public class ThemHoaDonTheoDonController implements Initializable{
	@FXML
	private MenuButton mb;
	@FXML
	private TextField txtTenKH, txtGioiTinh, txtSdt, txtEmail, txtTienNhan;
	@FXML
	private Button btnThemDonThuoc, btnThemKH;
	@FXML
	private ComboBox<NhanVien> cbNhanVien;
	@FXML
	private TextField txtNCC;
	@FXML
	private DatePicker dpNgayNhap;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	static ResultSet rs;
	@FXML
	Label lblName, lblThanhTien, lblTienThoi, lblMaDonThuoc;
	@FXML
	TableView<CTHoaDon> table;
	@FXML
	TableView tableView = new TableView<DonThuocKhamBenh>();
	@FXML
	private TableColumn<PhieuNhap, Integer> maPN;
	@FXML
	private TableColumn<PhieuNhap, String> hoTen;
	@FXML
	private TableColumn<PhieuNhap, String> ngayNhap;
	@FXML
	private TableColumn<CTHoaDon, Integer> maCTHD;
	@FXML
	private TableColumn<CTHoaDon, Integer> maThuoc;
	@FXML
	private TableColumn<CTHoaDon, String> tenThuoc;
	@FXML
	private TableColumn<CTHoaDon, String> tenLoaiThuoc;
	@FXML
	private TableColumn<CTHoaDon, String> donViTinh;
	@FXML
	private TableColumn<CTHoaDon, Float> donGia;
	@FXML
	private TableColumn<CTHoaDon, Integer> soLuong;
	@FXML
	private TableColumn<CTHoaDon, Float> tongGiaBan;
	int hd = 0;
	private ObservableList<PhieuNhap> list = FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//		getAllPN();
//		reload();
		NhanVien dnc = DangNhapController.getNV();
//		try {
//			while(rs.next()) {
//				lblName.setText("Xin chào, " + dnc.getHoTen());
		
		try {
			cell();
////			int mahd = getMaHD();
////			String tongGiaBan = "select sum(thanhTien) as tong from CTHoaDon where maHD ='"+mahd+"'";
////			ps = con.prepareStatement(tongGiaBan);
////			rs = ps.executeQuery();
////			while(rs.next()) {
////				float tong = rs.getFloat("tong");
////				String tongS = String.valueOf(tong);
////				
////				lblThanhTien.setText(tongS);
////				txtTienNhan.setText(tongS);
////				float tienThoi = Float.parseFloat(txtTienNhan.getText()) - Float.parseFloat(tongS);
//				lblTienThoi.setText(String.valueOf(tienThoi));
//			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		table.setItems(null);
		dpNgayNhap.setValue(LocalDate.now());
		LocalDate ldNgayNhap = dpNgayNhap.getValue();
		Date dNgayNhap = Date.valueOf(ldNgayNhap);
		btnThemDonThuoc.setOnAction(arg -> {
			 int maKH;
				try {
					maKH = getTTKhachHang();
				if(maKH == 0) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Thông báo");
					alert.setContentText("Mời bạn chọn khách hàng trước");
					alert.setHeaderText(null);
					alert.showAndWait();
					}
					else {	
					ObservableList<DonThuocKhamBenh> Tlist = FXCollections.observableArrayList();

					ScrollPane scroll = new ScrollPane(tableView);
					BorderPane root = new BorderPane(scroll);
					TextField txtTimKiem = new TextField();
					Label lblTimKiem = new Label("Tìm kiếm đơn thuốc");
					Button chon = new Button("Chọn sản phẩm");
					HBox h1 = new HBox(3);
					HBox h2 = new HBox(1);
					
					Stage stage = new Stage();

					TableColumn maDonThuoc= new TableColumn<DonThuocKhamBenh, Integer>("Mã đơn thuốc");
					maDonThuoc.setCellValueFactory(new PropertyValueFactory<DonThuocKhamBenh, Integer>("maDonThuoc"));
					TableColumn bacSiKeDon = new TableColumn<DonThuocKhamBenh, String>("Bác sĩ kê đơn");
					bacSiKeDon.setCellValueFactory(new PropertyValueFactory<DonThuocKhamBenh, String>("bacSiKeDon"));
					TableColumn ngayNhap = new TableColumn<DonThuocKhamBenh, String>("Ngày nhập");
					ngayNhap.setCellValueFactory(new PropertyValueFactory<DonThuocKhamBenh, Date>("ngayNhap"));
					TableColumn chanDoan = new TableColumn<DonThuocKhamBenh, String>("Chẩn đoán");
					chanDoan.setCellValueFactory(new PropertyValueFactory<DonThuocKhamBenh, Date>("chanDoan"));
					TableColumn loiDan = new TableColumn<DonThuocKhamBenh, Integer>("Lời dặn");
					loiDan.setCellValueFactory(new PropertyValueFactory<DonThuocKhamBenh, String>("loiDan"));
					TableColumn thongTin = new TableColumn<DonThuocKhamBenh, String>("Thông tin chi tiết");
					thongTin.setCellValueFactory(new PropertyValueFactory<DonThuocKhamBenh, String>("thongTin"));

					
					tableView.getColumns().add(maDonThuoc);
					tableView.getColumns().add(bacSiKeDon);
					tableView.getColumns().add(loiDan);
					tableView.getColumns().add(ngayNhap);
					tableView.getColumns().add(thongTin);

//					tableView.getColumns().add(soLo);
//					tableView.getColumns().add(hanSuDung);
					root.setCenter(scroll);
					scroll.setContent(tableView);
					h1.getChildren().addAll( lblTimKiem, txtTimKiem);
					h2.getChildren().addAll( chon);
					root.setTop(h1);
					root.setBottom(h2);

					String sql = "select * from DonThuocKhamBenh where maKH = '"+maKH+"'";
					try {
						taoHD();
						int maHD = getMaHD();
						ps = con.prepareStatement(sql);
						rs = ps.executeQuery();
						while(rs.next()) {
							DonThuocKhamBenh t = new DonThuocKhamBenh();
							t.setMaDonThuoc(rs.getInt("maDonThuoc"));
							int maDT = rs.getInt("maDonThuoc");
							t.setBacSiKeDon(rs.getString("bacSiKeDon"));
							t.setNgayNhap(rs.getDate("ngayNhap"));
							t.setChanDoan(rs.getString("chanDoan"));
							t.setLoiDan(rs.getString("loiDan"));
							t.setThongTin(rs.getString("thongTinChiTiet"));
							Tlist.add(t);
							tableView.setItems(Tlist);
							chon.setOnAction(arg02 ->{
								int index = tableView.getSelectionModel().getSelectedIndex();
								if(index<=-1) {
									return;
								}
								else {
									String sqlMaDonThuoc = String.valueOf(maDonThuoc.getCellData(index).toString());
									String getDT = "select ct.maThuoc, t.tenThuoc, ct.soLuong, ct.cachDung from CTDonThuocKhamBenh ct left join Thuoc t on ct.maThuoc = t.maThuoc where maDonThuoc = '"+sqlMaDonThuoc+"' group by ct.maThuoc, t.tenThuoc,ct.soLuong,ct.cachDung";
									try {
										String updateDonThuoc = "update HoaDon set maDonThuoc = '"+maDT+"'where maHD ='"+maHD+"'";
										PreparedStatement psUpdate = con.prepareStatement(updateDonThuoc);
										psUpdate.execute();
										PreparedStatement ps1 = con.prepareStatement(getDT);
										ResultSet rs1 = ps1.executeQuery();
										while(rs1.next()) {
											CTDonThuocKhamBenh ct1 = new CTDonThuocKhamBenh();
											ct1.setMaThuoc(rs1.getInt("maThuoc"));
											int maThuocCoDon = rs1.getInt("maThuoc");
											System.out.println(maThuocCoDon);
											ct1.setTenThuoc(rs1.getString("tenThuoc"));
											ct1.setSoLuong(rs1.getInt("soLuong"));
											int slDonThuoc = rs1.getInt("soLuong");
											ct1.setCachDung(rs1.getString("cachDung"));
											String getCTThuoc = "select t.maThuoc, t.tenThuoc, lt.tenLoaiThuoc, donViTinh,sum(th.soLuongCon) as soLuongCon, t.giaNhap, t.giaBan as giaBan, min(hanSuDung) as hanSuDung from Thuoc t left join CTThuoc th on t.maThuoc = th.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc where th.soLuongCon > 0 and t.maThuoc = '"+maThuocCoDon+"'  group by t.maThuoc, tenThuoc, lt.tenLoaiThuoc, donViTinh, t.giaNhap, t.giaBan";
											PreparedStatement psd = con.prepareStatement(getCTThuoc);
											ResultSet rs2 = psd.executeQuery();
											while(rs2.next()) {
												CTThuoc tt = new CTThuoc();
												tt.setMaThuoc(rs2.getInt("maThuoc"));
												int maT = rs2.getInt("maThuoc");
												tt.setTenThuoc(rs2.getString("tenThuoc"));
												String tenT = rs2.getString("tenThuoc");
												System.out.println(maT + "ma Thuoc");
												System.out.println(tenT);
//												k.setTenLoaiThuoc(rs.getString("tenLoaiThuoc"));
												tt.setSlTonKho(rs2.getInt("soLuongCon"));
												int slTonKho = rs2.getInt("soLuongCon");
												tt.setDonViTinh(rs2.getString("donViTinh"));
												tt.setGiaBan(rs2.getFloat("giaBan"));
												float giaBan = rs2.getFloat("giaBan");
												if(slTonKho == 0) {
													Alert alert = new Alert(AlertType.ERROR, "Thêm thất bại, số lượng sản phẩm của thuốc '"+tenT+"' trong kho không đủ", ButtonType.OK);
											  		alert.setTitle("Thông báo");
											  		alert.setHeaderText(null);
											  		alert.show();
												}
												else if(slDonThuoc > slTonKho) {
													Alert alert = new Alert(AlertType.ERROR, "Thêm thất bại, số lượng sản phẩm của thuốc '"+tenT+"' trong kho không đủ", ButtonType.OK);
											  		alert.setTitle("Thông báo");
											  		alert.setHeaderText(null);
											  		alert.show();
												}
												else {
													lblMaDonThuoc.setText(String.valueOf(maDT));
													String insert = "insert into CTHoaDon(maHD, maThuoc, soLuong, donGia, thanhTien) values(?,?,?,?,?)";
													PreparedStatement psInsert = con.prepareStatement(insert);
													psInsert.setInt(1, maHD);
													psInsert.setInt(2, maT);
													psInsert.setInt(3, slDonThuoc);
													psInsert.setFloat(4, giaBan);
													psInsert.setFloat(5, giaBan * slDonThuoc);
													psInsert.execute();
													getCTHoaDon();
												}
											}
										}
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									}
									stage.close();
							});
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Scene scene = new Scene(root,700,400);
					stage.setScene(scene);
					stage.setResizable(true);
					stage.show();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}});

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
				TableView tableView = new TableView<KhachHang>();
				tableView.setPrefWidth(650);
				TableColumn maKH = new TableColumn<KhachHang, Integer>("Mã khách hàng");
				maKH.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("maKH"));
				TableColumn tenKH = new TableColumn<KhachHang, String>("Tên khách hàng");
				tenKH.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("hoTen"));
				TableColumn gioiTinh = new TableColumn<KhachHang, String>("Giới tính");
				gioiTinh.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("gioiTinh"));
				TableColumn ngaySinh = new TableColumn<KhachHang, Integer>("Ngày sinh");
				ngaySinh.setCellValueFactory(new PropertyValueFactory<KhachHang, Date>("ngaySinh"));
				TableColumn sdt = new TableColumn<KhachHang, Float>("Số điện thoại");
				sdt.setCellValueFactory(new PropertyValueFactory<KhachHang, Integer>("sdt"));
				TableColumn email = new TableColumn<KhachHang, String>("Email");
				email.setCellValueFactory(new PropertyValueFactory<CTThuoc, String>("email"));
				TableColumn diaChi = new TableColumn<KhachHang, Date>("Địa chỉ");	
				diaChi.setCellValueFactory(new PropertyValueFactory<CTThuoc, String>("diaChi"));
		
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
						kh.setHoTen(rs.getString("tenKH"));
						kh.setGioiTinh(rs.getString("gioiTinh"));
						kh.setNgaySinh(rs.getDate("ngaySinh"));
						kh.setSdt(rs.getInt("sdt"));
						kh.setEmail(rs.getString("email"));
						kh.setDiaChi(rs.getString("diaChi"));
						Khlist.add(kh);
						tableView.setItems(Khlist);
//						getCTHoaDon();
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
	      public int getTTKhachHang() throws SQLException{
	  		String getKH1 = "select * from KhachHang where tenKH = N'"+txtTenKH.getText()+"' and gioiTinh = N'"+txtGioiTinh.getText()+"'";
	  		ps = con.prepareStatement(getKH1);
	  		rs = ps.executeQuery();
	  		int maKH = 0;
	  		while(rs.next())
	  		maKH = rs.getInt("maKH");
	  		System.out.println(maKH);
	  		return maKH;
	  	  }
	 	 public void cell() throws SQLException {
			 maCTHD.setCellValueFactory(new PropertyValueFactory<CTHoaDon, Integer>("maCTHD"));
			 maThuoc.setCellValueFactory(new PropertyValueFactory<CTHoaDon, Integer>("maThuoc"));
			 tenThuoc.setCellValueFactory(new PropertyValueFactory<CTHoaDon, String>("tenThuoc"));
			 tenLoaiThuoc.setCellValueFactory(new PropertyValueFactory<CTHoaDon, String>("tenLoaiThuoc"));
			 donViTinh.setCellValueFactory(new PropertyValueFactory<CTHoaDon, String>("donViTinh"));
			 donGia.setCellValueFactory(new PropertyValueFactory<CTHoaDon, Float>("donGia"));
			 soLuong.setCellValueFactory(new PropertyValueFactory<CTHoaDon, Integer>("soLuong"));
			 tongGiaBan.setCellValueFactory(new PropertyValueFactory<CTHoaDon, Float>("tongGiaBan"));

		 }
	 	 public void getCTHoaDon() throws SQLException {
			 int maHD = getMaHD();
			 String sql = "select * from CTHoaDon cthd left join Thuoc t on t.maThuoc = cthd.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc where maHD = '"+maHD+"'";
			 ps = con.prepareStatement(sql);
			 rs = ps.executeQuery();
			 ObservableList<CTHoaDon> cthdList = FXCollections.observableArrayList();
			 int i = 0;
			 while(rs.next()) {
				 CTHoaDon cthd = new CTHoaDon();
				 cthd.setMaHD(maHD);
				 cthd.setMaCTHD(i = i + 1);
				 cthd.setMaThuoc(rs.getInt("maThuoc"));
				 cthd.setTenThuoc(rs.getString("tenThuoc"));
				 cthd.setDonViTinh(rs.getString("donViTinh"));
				 cthd.setTenLoaiThuoc(rs.getString("tenLoaiThuoc"));
//				 cthd.setSoLo(rs.getString("soLo"));
				 cthd.setDonGia(rs.getFloat("donGia"));
				 cthd.setSoLuong(rs.getInt("soLuong"));
				 cthd.setTongGiaBan(rs.getFloat("thanhTien"));
				 cthdList.add(cthd);
				 table.setItems(cthdList);
//				 System.out.println(maThuoc.getCellData(1));
			 }
			 String tongTien = "select sum(thanhTien) as tt from CTHoaDon where maHD ='"+maHD+"'";
			 ps = con.prepareStatement(tongTien);
			 rs = ps.executeQuery();
			 
			 while(rs.next()) {
				 float tong = rs.getFloat("tt");
				 lblThanhTien.setText(tong + "");
				 tienThoi();
			 }
			 cell();
		 }
	 	 public void taoHD() {
			 NhanVien dnc = DangNhapController.getNV();
			 LocalDate ldNgayNhap = dpNgayNhap.getValue();
			 Date dNgayNhap = Date.valueOf(ldNgayNhap);
			 if(hd == 0) {
				 hd += 1;
				 String taohd = "insert into HoaDon(maNV, ngayLapHD, tongTien) values(?,?,?)";
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
			public int getMaHD() throws SQLException {
				 String chonhd = "select max(maHD) as maHD from HoaDon";
				 ps = con.prepareStatement(chonhd);
				 rs = ps.executeQuery();
				 int maHD = 0;
				 while(rs.next()) 
				 maHD = rs.getInt("maHD");
				 return maHD;
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
			      public void thanhToan(ActionEvent e) throws SQLException {
						int slpn = 0;
			    	Alert alert = new Alert(AlertType.CONFIRMATION);
			    	alert.setTitle("Thông báo");
			  		alert.setContentText("Bạn có chắc muốn thanh toán hoá đơn này không?");
			  		alert.setHeaderText(null);
			  		Optional<ButtonType> result = alert.showAndWait();
			  		if(result.get()==ButtonType.OK) {
			  			int ma = getTTKhachHang();
			  			if(ma == 0) {
			  				Alert thatbai = new Alert(AlertType.ERROR);
			  				thatbai.setTitle("Thông báo");
			  				thatbai.setContentText("Bạn chưa chọn khách hàng");
			  				thatbai.setHeaderText(null);
			  				thatbai.showAndWait();
			  			}
			  		else {
			    	  int getMaHD = getMaHD();
			    	  for(int i = 0; i<tableView.getItems().size();i++) {
			    		  String sqlMaThuoc = String.valueOf(maThuoc.getCellData(i));
			    		  String soL = String.valueOf(soLuong.getCellData(i));
			    		  int sl = Integer.parseInt(soL);
			    		  ObservableList<CTThuoc> Tlist = FXCollections.observableArrayList();
			    		  String sql = "select t.maThuoc, t.tenThuoc, lt.tenLoaiThuoc, donViTinh,sum(th.soLuongCon) as soLuongCon, t.giaNhap, t.giaBan as giaBan, min(hanSuDung) as hanSuDung from Thuoc t left join CTThuoc th on t.maThuoc = th.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc where th.soLuongCon > 0 and t.maThuoc = '"+sqlMaThuoc+"' group by t.maThuoc, tenThuoc, lt.tenLoaiThuoc, donViTinh, t.giaNhap, t.giaBan";
							try {
								taoHD();
								int maHD = getMaHD();
								ps = con.prepareStatement(sql);
								rs = ps.executeQuery();
								while(rs.next()) {
									CTThuoc t = new CTThuoc();
									t.setMaThuoc(rs.getInt("maThuoc"));
									int maThuoc = t.getMaThuoc();
									t.setTenThuoc(rs.getString("tenThuoc"));
									String tenT = rs.getString("tenThuoc");
//									k.setTenLoaiThuoc(rs.getString("tenLoaiThuoc"));
									t.setSlTonKho(rs.getInt("soLuongCon"));
									t.setDonViTinh(rs.getString("donViTinh"));
									t.setGiaBan(rs.getFloat("giaBan"));
									
									while(sl > 0) {
										String getsl = "select TOP 1(hanSuDung),soLuongCon from CTThuoc where maThuoc = '"+Integer.parseInt(sqlMaThuoc)+"'and soLuongCon > 0 order by hanSuDung";
										ps = con.prepareStatement(getsl);
										rs = ps.executeQuery();
										while(rs.next())
										slpn = rs.getInt("soLuongCon");
										sl = sl - slpn; // sl = 5, slpn = 4, sl = 1, slpn = 6 = -5
										System.out.println(sl);
										if(sl > 0) { // sl = 1
											String update = "update CTThuoc set soLuongCon = 0 where maThuoc = '"+Integer.parseInt(sqlMaThuoc)+"' and hanSuDung = (select TOP 1(hanSuDung) from CTThuoc where soLuongCon > 0 and maThuoc = '"+Integer.parseInt(sqlMaThuoc)+"' order by hanSuDung)"; // slpn = 0
											ps = con.prepareStatement(update);
											ps.execute();
											
										}
										if(sl <= 0) { // -5 < 0
											int slcl = sl + slpn; // -5 + 6
											slpn = slpn - slcl;
											String update2 = "update CTThuoc set soLuongCon = "+slpn+" where maThuoc = '"+Integer.parseInt(sqlMaThuoc)+"' and hanSuDung = (select TOP 1(hanSuDung)from CTThuoc where soLuongCon > 0 and maThuoc = '"+Integer.parseInt(sqlMaThuoc)+"' order by hanSuDung)";
											ps = con.prepareStatement(update2);
											ps.execute();
										}
								}
								}
								Alert thanhcong = new Alert(AlertType.INFORMATION);
								thanhcong.setTitle("Thông báo");
								thanhcong.setContentText("Hoá đơn đã được thêm thành công");
								thanhcong.setHeaderText(null);
								thanhcong.showAndWait();
						}catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
			    	  }	
			  		}
			  		}
			      }
			 	 public void tienThoi() {
					 float tienNhan = Float.parseFloat(txtTienNhan.getText());
					 float thanhTien = Float.parseFloat(lblThanhTien.getText());
					 lblTienThoi.setText(String.valueOf(tienNhan - thanhTien));
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
