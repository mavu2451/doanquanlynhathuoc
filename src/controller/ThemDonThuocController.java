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
import entity.CTDonThuocKhamBenh;
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

public class ThemDonThuocController implements Initializable{
	@FXML
	private MenuButton mb;
	@FXML
	private TextField txtTenKH, txtGioiTinh, txtSdt, txtEmail, txtBacSiKeDon, txtChanDoan, txtLoiKhuyen;
	@FXML
	private TextArea txtThongTin;
	@FXML
	private Button btnThemTenThuoc, btnThemKH, btnTaoDonThuoc;
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
	Label lblName;
	@FXML
	TableView<CTDonThuocKhamBenh> table;
	@FXML
	private TableColumn<CTDonThuocKhamBenh, Integer> maCT;
	@FXML
	private TableColumn<CTDonThuocKhamBenh, Integer> maThuoc;
	@FXML
	private TableColumn<CTDonThuocKhamBenh, String> tenThuoc;
	@FXML
	private TableColumn<CTDonThuocKhamBenh, String> tenLoaiThuoc;
	@FXML
	private TableColumn<CTDonThuocKhamBenh, String> donViTinh;
	@FXML
	private TableColumn<CTDonThuocKhamBenh, Integer> soLuong;

	int i= 1;
	int hd = 0;
	private ObservableList<CTDonThuocKhamBenh> list = FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//		getAllPN();
//		reload();
		cell();
		NhanVien dnc = DangNhapController.getNV();
		String sqlxc = "select * from NhanVien";
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
		dpNgayNhap.setValue(LocalDate.now());
//		try {
//			while(rs.next()) {
//				lblName.setText("Xin chào, " + dnc.getHoTen());
		 btnThemTenThuoc.setOnAction(arg01 -> {
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
				ObservableList<Thuoc> Tlist = FXCollections.observableArrayList();
				TableView tableView = new TableView<Thuoc>();
				ScrollPane scroll = new ScrollPane(tableView);
				BorderPane root = new BorderPane(scroll);
				TextField txtTimKiem = new TextField();
				Label lblTimKiem = new Label("Tìm kiếm tên thuốc");
				Button chon = new Button("Chọn sản phẩm");
				TextField soLuong = new TextField("0");
				Label lblCachDung = new Label("Cách dùng");
				TextField cachDung = new TextField();
				Label chonSL = new Label("Chọn số lượng");
				HBox h1 = new HBox(3);
				HBox h2 = new HBox(3);
				
				Stage stage = new Stage();

				TableColumn maThuoc = new TableColumn<Thuoc, Integer>("Mã thuốc");
				maThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, Integer>("maThuoc"));
				TableColumn tenThuoc = new TableColumn<Thuoc, String>("Tên thuốc");
				tenThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("tenThuoc"));
				TableColumn donViTinh = new TableColumn<Thuoc, String>("Đơn vị tính");
				donViTinh.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("dvt"));
				TableColumn loaiThuoc = new TableColumn<Thuoc, Integer>("Loại thuốc");
				loaiThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("loaiThuoc"));
				TableColumn nsx = new TableColumn<Thuoc, Integer>("Nước sản xuất");
				nsx.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("nsx"));
				TableColumn thuocKeDon = new TableColumn<Thuoc, Float>("Thuốc kê đơn");
				thuocKeDon.setCellValueFactory(new PropertyValueFactory<Thuoc, Float>("thuocKeDon"));
//				TableColumn soLo = new TableColumn<Kho, String>("Số lô");
//				soLo.setCellValueFactory(new PropertyValueFactory<Kho, String>("soLo"));
//				TableColumn hanSuDung = new TableColumn<Kho, Date>("Hạn sử dụng");	
//				hanSuDung.setCellValueFactory(new PropertyValueFactory<Kho, Date>("hanSuDung"));
				
				tableView.getColumns().add(maThuoc);
				tableView.getColumns().add(tenThuoc);
				tableView.getColumns().add(loaiThuoc);
				tableView.getColumns().add(donViTinh);
				tableView.getColumns().add(nsx);
				tableView.getColumns().add(thuocKeDon);
//				tableView.getColumns().add(soLo);
//				tableView.getColumns().add(hanSuDung);
				root.setCenter(scroll);
				scroll.setContent(tableView);
				h1.getChildren().addAll( lblTimKiem, txtTimKiem);
				h2.getChildren().addAll(chonSL, soLuong, chon);
				root.setTop(h1);
				root.setBottom(h2);
				txtTimKiem.setOnKeyReleased(e->{
					if(txtTimKiem.getText().equals("")) {
						Tlist.clear();
						String sql = "select * from Thuoc t left join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc where thuocKeDon = N'Thuốc kê đơn'";
						try {
							ps = con.prepareStatement(sql);
							rs = ps.executeQuery();
							while(rs.next()) {
								Thuoc t = new Thuoc();
								t.setMaThuoc(rs.getInt("maThuoc"));
								t.setTenThuoc(rs.getString("tenThuoc"));
//								k.setTenLoaiThuoc(rs.getString("tenLoaiThuoc"));
								t.setLoaiThuoc(rs.getString("tenLoaiThuoc"));
								t.setDvt(rs.getString("donViTinh"));
								t.setNsx(rs.getString("nuocSanXuat"));
								t.setThuocKeDon(rs.getString("thuocKeDon"));
								Tlist.add(t);
								tableView.setItems(Tlist);
							}
						}catch (Exception e1) {
							// TODO: handle exception
						}
					}
					else {
						Tlist.clear();
						String sql = "select * from Thuoc t left join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc where tenThuoc like N'%"+txtTimKiem.getText().toString()+"%'and thuocKeDon = N'Thuốc kê đơn'";
						try {
							ps = con.prepareStatement(sql);
							rs = ps.executeQuery();
							while(rs.next()) {
								Thuoc t = new Thuoc();
								t.setMaThuoc(rs.getInt("maThuoc"));
								t.setTenThuoc(rs.getString("tenThuoc"));
//								k.setTenLoaiThuoc(rs.getString("tenLoaiThuoc"));
								t.setLoaiThuoc(rs.getString("tenLoaiThuoc"));
								t.setDvt(rs.getString("donViTinh"));
								t.setNsx(rs.getString("nuocSanXuat"));
								t.setThuocKeDon(rs.getString("thuocKeDon"));
								Tlist.add(t);
								tableView.setItems(Tlist);
							}
						}catch (Exception e1) {
							// TODO: handle exception
						}
					}
				});

				String sql = "select * from Thuoc t left join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc where thuocKeDon = N'Thuốc kê đơn'";
				try {
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					while(rs.next()) {
						Thuoc t = new Thuoc();
						t.setMaThuoc(rs.getInt("maThuoc"));
						t.setTenThuoc(rs.getString("tenThuoc"));
//						k.setTenLoaiThuoc(rs.getString("tenLoaiThuoc"));
						t.setLoaiThuoc(rs.getString("tenLoaiThuoc"));
						t.setDvt(rs.getString("donViTinh"));
						t.setNsx(rs.getString("nuocSanXuat"));
						t.setThuocKeDon(rs.getString("thuocKeDon"));
						Tlist.add(t);
						tableView.setItems(Tlist);
						chon.setOnAction(arg02 ->{
							int index = tableView.getSelectionModel().getSelectedIndex();
							if(index<=-1) {
								return;
							}
							else if(Integer.parseInt(soLuong.getText().toString()) == 0){
								themThatBaiMessage2();
							}	
							else {
								int sl = Integer.parseInt(soLuong.getText());
								table.setItems(null);
								String sqlMaThuoc = String.valueOf(maThuoc.getCellData(index).toString());
								System.out.println(sqlMaThuoc);
								String sqlTenThuoc = String.valueOf(tenThuoc.getCellData(index).toString());
								String sqlDVT = String.valueOf(donViTinh.getCellData(index).toString());
								String sqlLoaiThuoc = String.valueOf(loaiThuoc.getCellData(index).toString());

								CTDonThuocKhamBenh ct = new CTDonThuocKhamBenh();
								int mt = Integer.parseInt(sqlMaThuoc);
								System.out.println(mt);
								ct.setMaCT(i++);
								ct.setMaThuoc(mt);
								ct.setTenThuoc(sqlTenThuoc);
								ct.setTenLoaiThuoc(sqlLoaiThuoc);
								ct.setDonViTinh(sqlDVT);
								ct.setSoLuong(Integer.parseInt(soLuong.getText()));
								list.add(ct);
								table.setItems(list);
//								try {
//									String hd = "insert into CTDonThuocKhamBenh(maDonThuoc,maThuoc,soLuong, cachDung) values (?,?,?,?)";
//									ps = con.prepareStatement(hd);
//									ps.setInt(1, maDT);
//									ps.setInt(2, Integer.parseInt(sqlMaThuoc));
//									ps.setInt(3, sl);
//									ps.setString(4, cachDung.getText());
//									ps.execute();
//									
//									String get = "select * from CTDonThuocKhamBenh ct left join Thuoc t on t.maThuoc = ct.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc where maDonThuoc = '"+maDT+"'";
//									ps = con.prepareStatement(get);
//									rs = ps.executeQuery();
//									int i = 1;
//									ObservableList<CTDonThuocKhamBenh> ctDonThuoc = FXCollections.observableArrayList();
//									while(rs.next()) {
//										CTDonThuocKhamBenh ct = new CTDonThuocKhamBenh();
//										ct.setMaCT(i++);
//										ct.setTenThuoc(rs.getString("tenThuoc"));
//										ct.setTenLoaiThuoc(rs.getString("tenLoaiThuoc"));
//										ct.setDonViTinh(rs.getString("donViTinh"));
//										ct.setSoLuong(rs.getInt("soLuong"));
//										ct.setCachDung(rs.getString("cachDung"));
//										ctDonThuoc.add(ct);
//										table.setItems(ctDonThuoc);
//									}	
//								} catch (SQLException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
								stage.close();
								
							}
						});
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Scene scene = new Scene(root,400,400);
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
					String sql = "select * from KhachHang";
					txtTimKiem.setOnKeyReleased(e->{
						if(txtTimKiem.getText().equals("")) {
							Khlist.clear();
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
								}
							}catch (Exception e1) {
								// TODO: handle exception
							}
						}
						else {
							Khlist.clear();
							String sql1 = "select * from KhachHang where tenKH like '%"+txtTimKiem.getText()+"%'";
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
					});
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
//							getCTHoaDon();
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
	
			btnTaoDonThuoc.setOnAction(arg -> {
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
					Alert alert1 = new Alert(AlertType.CONFIRMATION);
					alert1.setTitle("Thông báo");
					alert1.setContentText("Bạn có muốn thêm phiếu chứ?");
					alert1.setHeaderText(null);
					alert1.showAndWait();
					Optional<ButtonType> result = alert1.showAndWait();

					if(result.get()==ButtonType.OK) {
						taoDonThuoc();
						int maDT = getMaDonThuoc();
						String sql = "update DonThuocKhamBenh set bacSiKeDon = N'"+txtBacSiKeDon.getText()+"',chanDoan = N'"+txtChanDoan.getText()+"',loiDan = N'"+txtLoiKhuyen.getText()+"',thongTinChiTiet = N'"+txtThongTin.getText()+"' where maDonThuoc = '"+maDT+"'";
						ps = con.prepareStatement(sql);
						ps.execute();
						try {
							String hd = "insert into CTDonThuocKhamBenh(maDonThuoc,maThuoc,soLuong) values (?,?,?)";
							ps = con.prepareStatement(hd);
							ps.setInt(1, maDT);
							for(int i = 0; i<table.getItems().size();i++) {
								int sqlMaThuoc = maThuoc.getCellData(i);
								ps.setInt(2, sqlMaThuoc);
								ps.setInt(3, soLuong.getCellData(i));
								ps.execute();
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Alert alert2 = new Alert(AlertType.INFORMATION);
						alert2.setTitle("Thông báo");
						alert2.setContentText("Đơn hàng đã được thêm thành công");
						alert2.setHeaderText(null);
						alert2.showAndWait();
						inHD1();
						table.getItems().clear();
						txtTenKH.setText("");
						txtGioiTinh.setText("");
						txtSdt.setText("");
						txtEmail.setText("");
						txtThongTin.setText("");
						txtBacSiKeDon.setText("");
						txtChanDoan.setText("");
						txtLoiKhuyen.setText("");
//						table.setItems(null);
						hd = 0;
					}
					else if(result.get()==ButtonType.CANCEL) {
						
					}
				}
			}catch(SQLException | IOException e) {
				e.printStackTrace();
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
	public void thongKeThuocSapHetHang(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThongKeThuocSapHetHang.fxml"));
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

	//End Navbar
	      public int getTTKhachHang() throws SQLException{
	  		String getKH1 = "select * from KhachHang where tenKH = N'"+txtTenKH.getText()+"' and gioiTinh = N'"+txtGioiTinh.getText()+"' and sdt = '"+txtSdt.getText()+"'";
	  		ps = con.prepareStatement(getKH1);
	  		rs = ps.executeQuery();
	  		int maKH = 0;
	  		while(rs.next())
	  		maKH = rs.getInt("maKH");
	  		System.out.println(maKH);
	  		return maKH;
	  	  }
	  		public int getMaDonThuoc() throws SQLException {
	  			 String chonhd = "select max(maDonThuoc) as maDonThuoc from DonThuocKhamBenh";
	  			 ps = con.prepareStatement(chonhd);
	  			 rs = ps.executeQuery();
	  			 int maPDT = 0;
	  			 while(rs.next()) 
	  			 maPDT = rs.getInt("maDonThuoc");
	  			 return maPDT;
	  		}
//	  	 public void tienThoi() {
//	  		 float tienNhan = Float.parseFloat(txtTienNhan.getText());
//	  		 float thanhTien = Float.parseFloat(lblThanhTien.getText());
//	  		 lblTienThoi.setText(String.valueOf(tienNhan - thanhTien));
//	  	 }
			 public void cell(){
				 maCT.setCellValueFactory(new PropertyValueFactory<CTDonThuocKhamBenh, Integer>("maCT"));
				 maThuoc.setCellValueFactory(new PropertyValueFactory<CTDonThuocKhamBenh, Integer>("maThuoc"));
				 tenThuoc.setCellValueFactory(new PropertyValueFactory<CTDonThuocKhamBenh, String>("tenThuoc"));
				 tenLoaiThuoc.setCellValueFactory(new PropertyValueFactory<CTDonThuocKhamBenh, String>("tenLoaiThuoc"));
				 donViTinh.setCellValueFactory(new PropertyValueFactory<CTDonThuocKhamBenh, String>("donViTinh"));
				 soLuong.setCellValueFactory(new PropertyValueFactory<CTDonThuocKhamBenh, Integer>("soLuong"));
//				 tongGiaBan.setCellValueFactory(new PropertyValueFactory<CTDonThuocKhamBenh, Float>("tongGiaBan"));

			 }
				public void remove(ActionEvent e) {
					int select = table.getSelectionModel().getSelectedIndex();
					table.getItems().remove(select);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setContentText("Thuốc đã được xoá");
					alert.showAndWait();
					
				}
	  	 public void getCTDonThuocKhamBenh() throws SQLException {
	  		 int maDT = getMaDonThuoc();
	  		 String sql = "select * from CTDonThuocKhamBenh cthd left join Thuoc t on t.maThuoc = cthd.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc where maDonThuoc = '"+maDT+"'";
	  		 ps = con.prepareStatement(sql);
	  		 rs = ps.executeQuery();
	  		 ObservableList<CTDonThuocKhamBenh> cthdList = FXCollections.observableArrayList();
	  		 int i = 0;
	  		 while(rs.next()) {
	  			 CTDonThuocKhamBenh cthd = new CTDonThuocKhamBenh();
	  			 cthd.setMaCT(i = i + 1);
//	  			 cthd.setMaCTHD(i = i + 1);
	  			 cthd.setTenThuoc(rs.getString("tenThuoc"));
	  			 cthd.setDonViTinh(rs.getString("donViTinh"));
	  			 cthd.setTenLoaiThuoc(rs.getString("tenLoaiThuoc"));
	  			 cthd.setSoLuong(rs.getInt("soLuong"));
	  			 cthd.setCachDung(rs.getString("cachDung"));
	  			 cthdList.add(cthd);
	  			 table.setItems(cthdList);
	  		 }
	  	 }

	  	 public void taoDonThuoc() throws SQLException {
	  		 NhanVien dnc = DangNhapController.getNV();
	  		 LocalDate ldNgayNhap = dpNgayNhap.getValue();
	  		 Date dNgayNhap = Date.valueOf(ldNgayNhap);
	  		 int maKH = getTTKhachHang();
	  		 if(hd == 0) {
	  			 hd += 1;
	  			 String taodt = "insert into DonThuocKhamBenh(maKH, ngayNhap, maNV) values(?,?,?)";
	  			 System.out.println(taodt);
	  			 try {
	  				ps = con.prepareStatement(taodt);
	  				 ps.setInt(1, maKH);
	  				 ps.setDate(2, dNgayNhap);
	  				 ps.setInt(3, dnc.getMaNV());
	  				 ps.execute();
	  			} catch (SQLException e) {
	  				// TODO Auto-generated catch block
	  				e.printStackTrace();
	  			}
	  		 }
	  	 }
	  		public void inHD1() throws IOException, SQLException {
		 		int maHD = getMaDonThuoc();
		 		String s = "select hd.maDonThuoc, bacSiKeDon, chanDoan, loiDan from DonThuocKhamBenh hd left join CTDonThuocKhamBenh dt on dt.maDonThuoc = hd.maDonThuoc where hd.maDonThuoc = '"+maHD+"'";
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
				String p = "C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\pdf\\donthuockhambenh.pdf";
				float tc = 285f;
				float twocol = tc + 150f;
				float three = tc + 50f;
				float twocolwidth[] = {twocol,three,tc};
				float half[] = {450f};
				float half2[] = {370f};
				float full[] = {770f};
				float sixcolwidth[] = {50f,300f,100f,70f};
				PdfWriter pdf = new PdfWriter(p);
				PdfFont pf = PdfFontFactory.createFont("C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\fonts\\Roboto-Black.ttf", "Identity-H", true);
				PdfFont pflight = PdfFontFactory.createFont("C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\fonts\\Roboto-Light.ttf", "Identity-H", true);
				PdfDocument pd = new PdfDocument(pdf);
				pd.setDefaultPageSize(PageSize.A4);
				Document d = new Document(pd);
				Table t = new Table(twocolwidth);
				t.addCell(new Cell().add(new Paragraph("NHÀ THUỐC THỊNH VƯỢNG").setFont(pf)).setBorder(Border.NO_BORDER));
//				t.addCell(new Cell().add(new Paragraph("MÃ ĐƠN THUỐC: " + dt.getMaDonThuoc()).setFont(pf)).setBorder(Border.NO_BORDER));
				t.addCell(new Cell().add(new Paragraph("MÃ HOÁ ĐƠN: " + maHD).setFont(pflight)).setBorder(Border.NO_BORDER));
				Table divide = new Table(full);
				Border g = new SolidBorder(1f/2f);
				divide.setBorder(g);
				Table t1 = new Table(full);
				t1.addCell(new Cell().add(new Paragraph("ĐƠN THUỐC").setFont(pf)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontSize(24));
				t1.addCell(new Cell().add(new Paragraph("Ngày lập hoá đơn: " + dNgayNhap).setFont(pf)).setBorder(Border.NO_BORDER));
				t1.addCell(new Cell().add(new Paragraph("Họ tên: " + txtTenKH.getText()).setFont(pflight)).setBorder(Border.NO_BORDER));
				t1.addCell(new Cell().add(new Paragraph("Giới tính: " + txtGioiTinh.getText()).setFont(pflight)).setBorder(Border.NO_BORDER));
				t1.addCell(new Cell().add(new Paragraph("Số điện thoại: " + txtSdt.getText()).setFont(pflight)).setBorder(Border.NO_BORDER));
				t1.addCell(new Cell().add(new Paragraph("Email: " + txtEmail.getText()).setFont(pflight)).setBorder(Border.NO_BORDER));
				t1.addCell(new Cell().add(new Paragraph("Bác sĩ kê đơn: " + dt.getBacSiKeDon()).setFont(pflight)).setBorder(Border.NO_BORDER));
				t1.addCell(new Cell().add(new Paragraph("Chẩn đoán: " + dt.getChanDoan()).setFont(pflight)).setBorder(Border.NO_BORDER));
				t1.addCell(new Cell().add(new Paragraph("Lời dặn: " + dt.getLoiDan()).setFont(pflight)).setBorder(Border.NO_BORDER));
				
//				d.add(new Paragraph("NHÀ THUỐC THỊNH VƯỢNG").setFont(pf));
//				d.add(new Paragraph("HOÁ ĐƠN BÁN HÀNG").setFont(pflight));
				Table t2 = new Table(sixcolwidth);
				t2.addCell(new Cell().add(new Paragraph("STT").setFont(pf)));
				t2.addCell(new Cell().add(new Paragraph("Tên thuốc").setFont(pf)));
				t2.addCell(new Cell().add(new Paragraph("Đơn vị tính").setFont(pf)));
				t2.addCell(new Cell().add(new Paragraph("Số lượng").setFont(pf)));
				int j = 1;
				for(int i = 0;i < table.getItems().size();i++) {
					t2.addCell(new Cell().add(new Paragraph(j++ + "").setFont(pflight).setTextAlignment(TextAlignment.RIGHT)));
					t2.addCell(new Cell().add(new Paragraph(tenThuoc.getCellData(i)).setFont(pflight)));
					t2.addCell(new Cell().add(new Paragraph(donViTinh.getCellData(i)).setFont(pflight)));
					t2.addCell(new Cell().add(new Paragraph(soLuong.getCellData(i).toString()).setFont(pflight)));
			
				}
				Table t3 = new Table(full);
				t3.addCell(new Cell().add(new Paragraph("\n\n")).setBorder(Border.NO_BORDER));
				Table divide1 = new Table(full);
				divide1.setBorder(g);
				Table t4 = new Table(half);
				Table t5 = new Table(half2);
				t4.addCell(new Cell().add(new Paragraph("          Ngày " + ldNgayNhap.getDayOfMonth() + " tháng " + ldNgayNhap.getMonthValue() + " năm " + ldNgayNhap.getYear()).setFont(pf)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setMarginRight(100f));
				t4.addCell(new Cell().add(new Paragraph("Người bán").setFont(pf)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setMarginRight(100f));
				t4.addCell(new Cell().add(new Paragraph("Đi mua thuốc kê đơn cần mang theo phiếu này!").setFont(pflight)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT).setMarginRight(100f));
				t4.addCell(new Cell().add(new Paragraph("Ghi chú: \n" + txtThongTin.getText()).setFont(pflight)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT).setMarginRight(100f));
				d.add(t);
				d.add(divide);
				d.add(t1);
				d.add(t2);
				d.add(t3);
				d.add(divide1);
				d.add(t4);
				d.add(t5);
				d.close();
				File file = new File("C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\pdf\\donthuockhambenh.pdf");
				Desktop.getDesktop().open(file);
			}

	  		 
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
		      }
