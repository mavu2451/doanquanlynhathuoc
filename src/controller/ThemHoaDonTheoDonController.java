package controller;

import java.awt.Desktop;
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

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import database.KetNoiDatabase;
import entity.CTDonThuocKhamBenh;
import entity.CTHoaDon;
import entity.CTPhieuDatThuoc;
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
import javafx.scene.control.TextArea;
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
	private TextArea txtGhiChu;
	@FXML
	private DatePicker dpNgayNhap;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	static ResultSet rs;
	@FXML
	Label lblName, lblThanhTien, lblTienThoi, lblMaDonThuoc, lblNV;
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
	int lan = 0;
	int hd = 0;
	int i1 = 1;
	private ObservableList<PhieuNhap> list = FXCollections.observableArrayList();
	ObservableList<CTHoaDon> cthoaDonList = FXCollections.observableArrayList();
	NhanVien dnc = DangNhapController.getNV();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//		getAllPN();
//		reload();
	
//		try {
//			while(rs.next()) {
//				lblName.setText("Xin chào, " + dnc.getHoTen());
		String sqlxc = "select * from NhanVien";
		try {
			ps = con.prepareStatement(sqlxc);
			rs = ps.executeQuery();

				lblName.setText("Xin chào, " + dnc.getHoTen());
				lblNV.setText(dnc.getHoTen());
				//Loi
				System.out.println(dnc.getMaNV());
				System.out.println(dnc.getHoTen());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
//					TextField txtTimKiem = new TextField();
//					Label lblTimKiem = new Label("Tìm kiếm đơn thuốc");
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
					thongTin.setPrefWidth(300);

					
					tableView.getColumns().add(maDonThuoc);
					tableView.getColumns().add(bacSiKeDon);
					tableView.getColumns().add(chanDoan);
					tableView.getColumns().add(loiDan);
					tableView.getColumns().add(ngayNhap);
					tableView.getColumns().add(thongTin);

//					tableView.getColumns().add(soLo);
//					tableView.getColumns().add(hanSuDung);
					root.setCenter(scroll);
					scroll.setContent(tableView);
//					h1.getChildren().addAll( lblTimKiem, txtTimKiem);
					h2.getChildren().addAll( chon);
					root.setTop(h1);
					root.setBottom(h2);

					String sql = "select * from DonThuocKhamBenh where maKH = '"+maKH+"'";
					try {
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
									String getDT = "select ct.maThuoc, t.tenThuoc, ct.soLuong from CTDonThuocKhamBenh ct left join Thuoc t on ct.maThuoc = t.maThuoc where maDonThuoc = '"+sqlMaDonThuoc+"' group by ct.maThuoc, t.tenThuoc,ct.soLuong";
									try {
										PreparedStatement ps1 = con.prepareStatement(getDT);
										ResultSet rs1 = ps1.executeQuery();
										while(rs1.next()) {
											CTPhieuDatThuoc ct1 = new CTPhieuDatThuoc();
											ct1.setMaThuoc(rs1.getInt("maThuoc"));
											int maThuocCoDon = rs1.getInt("maThuoc");
											System.out.println(maThuocCoDon);
											ct1.setTenThuoc(rs1.getString("tenThuoc"));
											ct1.setSoLuong(rs1.getInt("soLuong"));
//											ct1.setDonGia(rs1.getFloat("donGia"));
											int slDonThuoc = rs1.getInt("soLuong");
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
												if(slDonThuoc > slTonKho) {
													Alert alert = new Alert(AlertType.ERROR, "Thêm thất bại, số lượng sản phẩm của thuốc '"+tenT+"' trong kho không đủ", ButtonType.OK);
											  		alert.setTitle("Thông báo");
											  		alert.setHeaderText(null);
											  		alert.show();
												}
												else {
													System.out.println(slDonThuoc);
													System.out.println(slTonKho);
													float tong = tt.getGiaBan() * ct1.getSoLuong();
													CTHoaDon ct = new CTHoaDon();
													ct.setMaCTHD(i1++);
													ct.setMaThuoc(ct1.getMaThuoc());
													ct.setTenThuoc(ct1.getTenThuoc());
													System.out.println(ct1.getTenThuoc());
													ct.setDonViTinh(tt.getDonViTinh());
													ct.setDonGia(tt.getGiaBan());
													ct.setSoLuong(ct1.getSoLuong());
													ct.setTongGiaBan(tong);
													cthoaDonList.add(ct);
													table.setItems(cthoaDonList);

														float thanhTien1 = 0;
														for(int i = 0; i<table.getItems().size();i++) {
															float tong1  = tongGiaBan.getCellData(i); 
															System.out.println("tong tien: " + tong1);
															
															thanhTien1 = thanhTien1 + tong1;
															System.out.println("thanh tien: " + thanhTien1);
															lblThanhTien.setText(String.format("%.0f",thanhTien1)+"");
															txtTienNhan.setText(String.format("%.0f",thanhTien1)+"");
														}
														lblMaDonThuoc.setText(maDT + "");
													
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
					Scene scene = new Scene(root,500,400);
					stage.setScene(scene);
					stage.setResizable(true);
					stage.show();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
				sdt.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("sdt"));
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
				String sql1 = "select * from KhachHang";
				txtTimKiem.setOnKeyReleased(e->{
					if(txtTimKiem.getText().equals("")) {
						Khlist.clear();
						try {
							ps = con.prepareStatement(sql1);
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
								Khlist.add(kh);
								tableView.setItems(Khlist);
							}
						}catch (Exception e1) {
							// TODO: handle exception
						}
					}
					else {
						Khlist.clear();
						String sql2 = "select * from KhachHang where tenKH like '%"+txtTimKiem.getText()+"%'";
						try {
							ps = con.prepareStatement(sql2);
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
								Khlist.add(kh);
								tableView.setItems(Khlist);
							}
						}catch (Exception e1) {
							// TODO: handle exception
						}
					}
				});
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
						kh.setSdt(rs.getString("sdt"));
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
    public void logOut(ActionEvent e){
  	  System.exit(0);
    }
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
		public void timKiemGioHang(ActionEvent e) throws IOException {
		 	Stage stage = (Stage) mb.getScene().getWindow();
		 	FXMLLoader loader = new FXMLLoader();
		     loader.setLocation(getClass().getResource("/view/TimKiemDonDatThuoc.fxml"));
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
	     public void themDonThuoc(ActionEvent e) throws IOException {
		       	Stage stage = (Stage) mb.getScene().getWindow();
		       	FXMLLoader loader = new FXMLLoader();
		           loader.setLocation(getClass().getResource("/view/ThemDonThuoc.fxml"));
		           Parent sampleParent = loader.load();
		           Scene scene = new Scene(sampleParent);
		           stage.setScene(scene);
		   	}
		      public void timKiemDonThuoc(ActionEvent e) throws IOException {
		       	Stage stage = (Stage) mb.getScene().getWindow();
		       	FXMLLoader loader = new FXMLLoader();
		           loader.setLocation(getClass().getResource("/view/TimKiemDonThuoc.fxml"));
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
	  	public void thongKeThuocSapHetHang(ActionEvent e) throws IOException {
			Stage stage = (Stage) mb.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/view/ThongKeThuocSapHetHang.fxml"));
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
//				 cthd.setTongGiaBan(rs.getFloat("thanhTien"));
				 cthdList.add(cthd);
				 table.setItems(cthdList);
//				 System.out.println(maThuoc.getCellData(1));
			 }
//			 String tongTien = "select sum(thanhTien) as tt from CTHoaDon where maHD ='"+maHD+"'";
//			 ps = con.prepareStatement(tongTien);
//			 rs = ps.executeQuery();
//			 
//			 while(rs.next()) {
//				 float tong = rs.getFloat("tt");
//				 lblThanhTien.setText(tong + "");
//				 tienThoi();
//			 }
			 cell();
		 }
	 	 public void taoHD() throws SQLException {
			 NhanVien dnc = DangNhapController.getNV();
			 LocalDate ldNgayNhap = dpNgayNhap.getValue();
			 Date dNgayNhap = Date.valueOf(ldNgayNhap);
			 int maKH = getTTKhachHang();
			 if(hd == 0) {
				 hd += 1;
				 String taohd = "insert into HoaDon(maNV, ngayLapHD, maKH) values(?,?,?)";
				 System.out.println(taohd);
				 try {
					ps = con.prepareStatement(taohd);
					 ps.setInt(1, dnc.getMaNV());
					 ps.setDate(2, dNgayNhap);
					 ps.setFloat(3, maKH);
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
			      public void thanhToan(ActionEvent e) throws SQLException, IOException {
						int slpn = 0;
						taoHD();
						int maHD = getMaHD();
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
			    	  inHD1();

			    	  for(int i = 0; i<table.getItems().size();i++) {
			    		  String sqlMaThuoc = String.valueOf(maThuoc.getCellData(i));
			    		  int sl = Integer.parseInt(soLuong.getCellData(i).toString());
			    		  System.out.println(sl);
			    		  ObservableList<CTThuoc> Tlist = FXCollections.observableArrayList();
			    		  String sql = "select t.maThuoc, t.tenThuoc, lt.tenLoaiThuoc, donViTinh,sum(th.soLuongCon) as soLuongCon, t.giaNhap, t.giaBan as giaBan, min(hanSuDung) as hanSuDung from Thuoc t left join CTThuoc th on t.maThuoc = th.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc where th.soLuongCon > 0 and t.maThuoc = '"+sqlMaThuoc+"' group by t.maThuoc, tenThuoc, lt.tenLoaiThuoc, donViTinh, t.giaNhap, t.giaBan";
							try {
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
								}
									while(sl > 0) { //41
										String getsl = "select TOP 1(hanSuDung),soLuongCon from CTThuoc where maThuoc = '"+Integer.parseInt(sqlMaThuoc)+"'and soLuongCon > 0 order by hanSuDung";
										ps = con.prepareStatement(getsl);
										rs = ps.executeQuery();
										while(rs.next())
										slpn = rs.getInt("soLuongCon"); // 20
										
										sl = sl - slpn; // sl = 5, slpn = 4, sl = 1, slpn = 6 = -5 
										System.out.println(sl);
										if(sl > 0) { // sl = 1
											String update = "update CTThuoc set soLuongCon = 0 where maThuoc = '"+Integer.parseInt(sqlMaThuoc)+"' and hanSuDung = (select TOP 1(hanSuDung) from CTThuoc where soLuongCon > 0 and maThuoc = '"+Integer.parseInt(sqlMaThuoc)+"' order by hanSuDung)"; // slpn = 0
											ps = con.prepareStatement(update);
											ps.execute();
											
										}
										else if(sl <= 0) { // -5 < 0
											int slcl = sl + slpn; // -5 + 6
											slpn = slpn - slcl;
											String update2 = "update CTThuoc set soLuongCon = "+slpn+" where maThuoc = '"+Integer.parseInt(sqlMaThuoc)+"' and hanSuDung = (select TOP 1(hanSuDung)from CTThuoc where soLuongCon > 0 and maThuoc = '"+Integer.parseInt(sqlMaThuoc)+"' order by hanSuDung)";
											ps = con.prepareStatement(update2);
											ps.execute();
										}
								}
								
							
//								float tong = Float.parseFloat(lblThanhTien.getText());
//								float tienNhan = Float.parseFloat(txtTienNhan.getText());
//								float tienThoi = Float.parseFloat(lblTienThoi.getText());
//								String sql1 = "update HoaDon set "
//										+ "tongTien = '"+tong+"', tienNhan = '"+tienNhan+"', tienThua = '"+tienThoi+"', ghiChu = '"+txtGhiChu.getText()+"' where maHD = '"+maHD+"'";
//								ps = con.prepareStatement(sql1);
//								ps.execute();
								hd = 0;

			    	  }catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
			  		}
			  		}
			  			Alert thanhcong = new Alert(AlertType.INFORMATION);
						thanhcong.setTitle("Thông báo");
						thanhcong.setContentText("Hoá đơn đã được thêm thành công");
						thanhcong.setHeaderText(null);
						thanhcong.showAndWait();
						txtTenKH.setText("");
						txtGioiTinh.setText("");
						txtSdt.setText("");
						txtEmail.setText("");
						lblMaDonThuoc.setText("0");
						lblThanhTien.setText("0");
						txtTienNhan.setText("0");
						txtGhiChu.setText("");
						table.getItems().clear();
						
			  		}
			      }
			 
			 	 public void tienThoi() {
					 float tienNhan = Float.parseFloat(txtTienNhan.getText());
					 float thanhTien = Float.parseFloat(lblThanhTien.getText());
					 lblTienThoi.setText(String.valueOf(tienNhan - thanhTien));
				 }
			 	public void inHD1() throws IOException, SQLException {
			 		int maHD = getMaHD();
			 		String s = "select hd.maDonThuoc, bacSiKeDon, chanDoan, loiDan from HoaDon hd left join DonThuocKhamBenh dt on dt.maDonThuoc = hd.maDonThuoc where maHD = '"+maHD+"'";
			 		ps = con.prepareStatement(s);
			 		rs = ps.executeQuery();
			 		DonThuocKhamBenh dt = new DonThuocKhamBenh();
			 		ObservableList<DonThuocKhamBenh> list = FXCollections.observableArrayList();
			 		while(rs.next()) {
			 			dt.setMaDonThuoc(rs.getInt("maDonThuoc"));
			 			dt.setBacSiKeDon(rs.getString("bacSiKeDon"));
			 			dt.setChanDoan(rs.getString("chanDoan"));
			 			dt.setLoiDan(rs.getString("loiDan"));
			 			list.add(dt);
					dpNgayNhap.setValue(LocalDate.now());
					LocalDate ldNgayNhap = dpNgayNhap.getValue();
					Date dNgayNhap = Date.valueOf(ldNgayNhap);
					String p = "C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\pdf\\hoadontheodon.pdf";
					float tc = 285f;
					float twocol = tc + 150f;
					float three = tc + 50f;
					float twocolwidth[] = {twocol,three,tc};
					float half[] = {450f};
					float half2[] = {370f};
					float full[] = {770f};
					float sixcolwidth[] = {50f,150f,100f,70f,200f,200f};
					PdfWriter pdf = new PdfWriter(p);
					PdfFont pf = PdfFontFactory.createFont("C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\fonts\\Roboto-Black.ttf", "Identity-H", true);
					PdfFont pflight = PdfFontFactory.createFont("C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\fonts\\Roboto-Light.ttf", "Identity-H", true);
					PdfDocument pd = new PdfDocument(pdf);
					pd.setDefaultPageSize(PageSize.A4);
					Document d = new Document(pd);
					Table t = new Table(twocolwidth);
					t.addCell(new Cell().add(new Paragraph("NHÀ THUỐC THỊNH VƯỢNG").setFont(pf)).setBorder(Border.NO_BORDER));
					t.addCell(new Cell().add(new Paragraph("MÃ ĐƠN THUỐC: " + lblMaDonThuoc.getText()).setFont(pf)).setBorder(Border.NO_BORDER));
					t.addCell(new Cell().add(new Paragraph("MÃ HOÁ ĐƠN: " + maHD).setFont(pflight)).setBorder(Border.NO_BORDER));
					Table divide = new Table(full);
					Border g = new SolidBorder(1f/2f);
					divide.setBorder(g);
					Table t1 = new Table(full);
					t1.addCell(new Cell().add(new Paragraph("HOÁ ĐƠN THUỐC KÊ ĐƠN").setFont(pf)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontSize(24));
					t1.addCell(new Cell().add(new Paragraph("Ngày lập hoá đơn: " + dNgayNhap).setFont(pf)).setBorder(Border.NO_BORDER));
					t1.addCell(new Cell().add(new Paragraph("Họ tên: " + txtTenKH.getText()).setFont(pflight)).setBorder(Border.NO_BORDER));
					t1.addCell(new Cell().add(new Paragraph("Giới tính: " + txtGioiTinh.getText()).setFont(pflight)).setBorder(Border.NO_BORDER));
					t1.addCell(new Cell().add(new Paragraph("Số điện thoại: " + txtSdt.getText()).setFont(pflight)).setBorder(Border.NO_BORDER));
					t1.addCell(new Cell().add(new Paragraph("Email: " + txtEmail.getText()).setFont(pflight)).setBorder(Border.NO_BORDER));
					
//					d.add(new Paragraph("NHÀ THUỐC THỊNH VƯỢNG").setFont(pf));
//					d.add(new Paragraph("HOÁ ĐƠN BÁN HÀNG").setFont(pflight));
					Table t2 = new Table(sixcolwidth);
					t2.addCell(new Cell().add(new Paragraph("STT").setFont(pf)));
					t2.addCell(new Cell().add(new Paragraph("Tên thuốc").setFont(pf)));
					t2.addCell(new Cell().add(new Paragraph("Đơn vị tính").setFont(pf)));
					t2.addCell(new Cell().add(new Paragraph("Số lượng").setFont(pf)));
					t2.addCell(new Cell().add(new Paragraph("Đơn giá").setFont(pf)));
					t2.addCell(new Cell().add(new Paragraph("Tổng").setFont(pf)));
					Table t3 = new Table(full);
					int j = 1;
					for(int i = 0;i < table.getItems().size();i++) {
						t2.addCell(new Cell().add(new Paragraph(j++ + "").setFont(pflight).setTextAlignment(TextAlignment.RIGHT)));
						t2.addCell(new Cell().add(new Paragraph(tenThuoc.getCellData(i)).setFont(pflight)));
						t2.addCell(new Cell().add(new Paragraph(donViTinh.getCellData(i)).setFont(pflight)));
						t2.addCell(new Cell().add(new Paragraph(soLuong.getCellData(i).toString()).setFont(pflight)));
						t2.addCell(new Cell().add(new Paragraph(donGia.getCellData(i).toString()).setFont(pflight)));
						t2.addCell(new Cell().add(new Paragraph(tongGiaBan.getCellData(i).toString()).setFont(pflight)));
					}
					float tongtien = Float.parseFloat(lblThanhTien.getText());
					float tiennhan = Float.parseFloat(txtTienNhan.getText());
					float tienthoi = Float.parseFloat(lblTienThoi.getText());
					t3.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
					t3.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
					t3.addCell(new Cell().add(new Paragraph("Thành tiền: " + String.format("%.0f", tongtien) + " đồng").setFont(pf)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
					t3.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
					t3.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
					t3.addCell(new Cell().add(new Paragraph("Tiền nhận: " + String.format("%.0f", tiennhan) + " đồng").setFont(pf)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
					t3.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
					t3.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
					t3.addCell(new Cell().add(new Paragraph("Tiền thối: " + String.format("%.0f", tienthoi) + " đồng").setFont(pf)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
					Table divide1 = new Table(full);
					divide1.setBorder(g);
					Table t4 = new Table(half);
					Table t5 = new Table(half2);
					t4.addCell(new Cell().add(new Paragraph("          Ngày " + ldNgayNhap.getDayOfMonth() + " tháng " + ldNgayNhap.getMonthValue() + " năm " + ldNgayNhap.getYear()).setFont(pf)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setMarginRight(100f));
					t4.addCell(new Cell().add(new Paragraph("Người bán").setFont(pf)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setMarginRight(100f));
					t4.addCell(new Cell().add(new Paragraph("Ghi chú: \n" + txtGhiChu.getText()).setFont(pflight)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT).setMarginRight(100f));
					d.add(t);
					d.add(divide);
					d.add(t1);
					d.add(t2);
					d.add(t3);
					d.add(divide1);
					d.add(t4);
					d.add(t5);
					d.close();
					File file = new File("C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\pdf\\hoadontheodon.pdf");
					Desktop.getDesktop().open(file);
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
