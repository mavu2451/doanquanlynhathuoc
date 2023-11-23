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
import java.time.LocalDateTime;
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
import entity.CTHoaDon;
import entity.CTPhieuDatThuoc;
import entity.KhachHang;
import entity.CTThuoc;
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

public class ThemDonDatThuocController implements Initializable{
	@FXML
	private MenuButton mb;
	@FXML
	private TextField txtMaPN;

	@FXML
	private TextField txtNCC, txtNSX, txtTenThuoc, txtSL, txtTenKH, txtGioiTinh, txtSdt, txtEmail, txtTienNhan;
	@FXML 
	private Button btnThemKH, btnThemTenThuoc, btnThemPhieu;
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
	private TableColumn<CTPhieuDatThuoc, Integer> maPDT;
	@FXML
	private TableColumn<CTPhieuDatThuoc, String> tenThuoc;
//	@FXML
//	private TableColumn<CTPhieuDatThuoc, String> tenLoaiThuoc;
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
		lblThanhTien.setText("0 đồng");
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
		btnThemPhieu.setOnAction(arg01->{
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
				int maP = getMaPDT();
				if(result.get()==ButtonType.OK) {
					String sql = "update DonDatThuoc set tongTien = '"+Float.parseFloat(lblThanhTien.getText())+"'where maPDT = '"+maP+"'";
					ps = con.prepareStatement(sql);
					ps.execute();
					Alert alert2 = new Alert(AlertType.INFORMATION);
					alert2.setTitle("Thông báo");
					alert2.setContentText("Đơn hàng đã được thêm thành công");
					alert2.setHeaderText(null);
					alert2.showAndWait();
					inHD1();
					txtTenKH.setText("");
					txtGioiTinh.setText("");
					txtSdt.setText("");
					txtEmail.setText("");
					lblThanhTien.setText("0 đồng");
					table.setItems(null);
					hd = 0;
				}
				else if(result.get()==ButtonType.CANCEL) {
					
				}
			}
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		});
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
				ObservableList<CTThuoc> Tlist = FXCollections.observableArrayList();
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
				TableView tableView = new TableView<CTThuoc>();
				TableColumn maThuoc = new TableColumn<CTThuoc, Integer>("Mã thuốc");
				maThuoc.setCellValueFactory(new PropertyValueFactory<CTThuoc, Integer>("maThuoc"));
				TableColumn tenThuoc = new TableColumn<CTThuoc, String>("Tên thuốc");
				tenThuoc.setCellValueFactory(new PropertyValueFactory<CTThuoc, String>("tenThuoc"));
				TableColumn donViTinh = new TableColumn<CTThuoc, String>("Đơn vị tính");
				donViTinh.setCellValueFactory(new PropertyValueFactory<CTThuoc, String>("donViTinh"));
				TableColumn slTonKho = new TableColumn<CTThuoc, Integer>("Số lượng hiện có");
				slTonKho.setCellValueFactory(new PropertyValueFactory<CTThuoc, Integer>("slTonKho"));
				TableColumn giaBan = new TableColumn<CTThuoc, Float>("Giá bán");
				giaBan.setCellValueFactory(new PropertyValueFactory<CTThuoc, Float>("giaBan"));
//				TableColumn soLo = new TableColumn<Kho, String>("Số lô");
//				soLo.setCellValueFactory(new PropertyValueFactory<Kho, String>("soLo"));
//				TableColumn hanSuDung = new TableColumn<Kho, Date>("Hạn sử dụng");	
//				hanSuDung.setCellValueFactory(new PropertyValueFactory<Kho, Date>("hanSuDung"));
				
				tableView.getColumns().add(maThuoc);
				tableView.getColumns().add(tenThuoc);
				tableView.getColumns().add(donViTinh);
				tableView.getColumns().add(slTonKho);
				tableView.getColumns().add(giaBan);
//				tableView.getColumns().add(soLo);
//				tableView.getColumns().add(hanSuDung);
				root.setCenter(scroll);
				scroll.setContent(tableView);
				h1.getChildren().addAll( lblTimKiem, txtTimKiem);
				h2.getChildren().addAll(chonSL, soLuong, chon);
				root.setTop(h1);
				root.setBottom(h2);

				String sql = "select t.maThuoc, t.tenThuoc, lt.tenLoaiThuoc, donViTinh,sum(th.soLuongCon) as soLuongCon, t.giaNhap, t.giaBan as giaBan, min(hanSuDung) as hanSuDung from Thuoc t left join CTThuoc th on t.maThuoc = th.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc where th.soLuongCon > 0  group by t.maThuoc, tenThuoc, lt.tenLoaiThuoc, donViTinh, t.giaNhap, t.giaBan";
				try {
					taoHD();
					int maPDT = getMaPDT();
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					while(rs.next()) {
						CTThuoc t = new CTThuoc();
						t.setMaThuoc(rs.getInt("maThuoc"));
						t.setTenThuoc(rs.getString("tenThuoc"));
						String tenT = rs.getString("tenThuoc");
//						k.setTenLoaiThuoc(rs.getString("tenLoaiThuoc"));
						t.setSlTonKho(rs.getInt("soLuongCon"));
						t.setDonViTinh(rs.getString("donViTinh"));
						t.setGiaBan(rs.getFloat("giaBan"));
						Tlist.add(t);
						tableView.setItems(Tlist);
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
								int sl = Integer.parseInt(soLuong.getText());
								table.setItems(null);
								String sqlMaThuoc = String.valueOf(maThuoc.getCellData(index).toString());
								String sqlTenThuoc = String.valueOf(tenThuoc.getCellData(index).toString());
								String sqlDVT = String.valueOf(donViTinh.getCellData(index).toString());
								String sqlGiaBan = String.valueOf(giaBan.getCellData(index).toString());
								int slpn=0;
								
								try {
									String hd = "insert into CTDonDatThuoc(maPDT,maThuoc,soLuong,donGia,thanhTien) values (?,?,?,?,?)";
									ps = con.prepareStatement(hd);
									ps.setInt(1, maPDT);
									ps.setInt(2, Integer.parseInt(sqlMaThuoc));
									ps.setInt(3, sl);
									ps.setFloat(4, Float.parseFloat(sqlGiaBan));
									float thanhTien = Float.parseFloat(sqlGiaBan) * sl;
									ps.setFloat(5, thanhTien);
									ps.execute();
									
									String get = "select * from CTDonDatThuoc ct left join Thuoc t on t.maThuoc = ct.maThuoc where maPDT = '"+maPDT+"'";
									ps = con.prepareStatement(get);
									rs = ps.executeQuery();
									int i = 1;
									ObservableList<CTPhieuDatThuoc> ctPhieuDatThuocList = FXCollections.observableArrayList();
									while(rs.next()) {
										CTPhieuDatThuoc ct = new CTPhieuDatThuoc();
										ct.setMaPDT(i++);
										ct.setTenThuoc(rs.getString("tenThuoc"));
										ct.setDonViTinh(rs.getString("donViTinh"));
										ct.setDonGia(rs.getFloat("donGia"));
										ct.setSoLuong(rs.getInt("soLuong"));
										ct.setTongGiaBan(rs.getFloat("thanhTien"));
										ctPhieuDatThuocList.add(ct);
										table.setItems(ctPhieuDatThuocList);
									}	
										String tongGiaBan = "select sum(thanhTien) as tong from CTDonDatThuoc where maPDT ='"+maPDT+"'";
										ps = con.prepareStatement(tongGiaBan);
										rs = ps.executeQuery();
										while(rs.next()) {
											float tong = rs.getFloat("tong");
											String tongS = String.valueOf(tong);
											lblThanhTien.setText(tongS);
									}
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
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
	
								}
////								String sqlSoLo = String.valueOf(soLo.getCellData(index).toString());
////								String sqlHSD = String.valueOf(hanSuDung.getCellData(index).toString());
//								System.out.println(sqlTenThuoc + " " + sqlDVT +" " + sqlGiaBan + " ");
//								String get = "select maTu,t.maThuoc,th.tenThuoc,th.donViTinh,t.giaBan,t.soLo,t.hanSuDung from Tu t left join Thuoc th on th.maThuoc = t.maThuoc where tenThuoc = '" +sqlTenThuoc+"' and donViTinh = '" +sqlDVT+"' and t.giaBan = '" +sqlGiaBan+ "' and soLo ='"+sqlSoLo+"' and hanSuDung='"+sqlHSD+"'" ;
//								try {
////									ps = con.prepareStatement(get);
////									rs = ps.executeQuery();
////									
////									while(rs.next()) {
////										Kho kho = new Kho();
////										kho.setMaKho(rs.getInt("maTu"));
////										kho.setMaThuoc(rs.getInt("maThuoc"));
////										kho.setTenThuoc(rs.getString("tenThuoc"));
////										kho.setDonViTinh(rs.getString("donViTinh"));
////										kho.setGiaBan(rs.getFloat("giaBan"));
////										kho.setSoLo(rs.getString("soLo"));
////										kho.setHanSuDung(rs.getDate("hanSuDung"));
////										String add = "insert into CTHoaDon(maThuoc, maTu, donGia, soLuong,maPDT, thanhTien) values(?,?,?,?,?,?)";
////										ps = con.prepareStatement(add);
////										ps.setInt(1, kho.getMaThuoc());
////										ps.setInt(2, kho.getMaKho());
////										ps.setFloat(3, kho.getGiaBan());
////										ps.setFloat(4, Float.parseFloat(soLuong.getText()));
////										ps.setInt(5, maPDT);
////										ps.setFloat(6, kho.getGiaBan()*Float.parseFloat(soLuong.getText()));
////										System.out.println(ps.execute());
////										int tongsl = Integer.parseInt(slTonKho.getCellData(index).toString()) - Integer.parseInt(soLuong.getText().toString());
////										System.out.println(tongsl);
////										String updateSl = "update Tu set slTonKho='"+tongsl+"' where maTu='"+kho.getMaKho()+"'";
////										ps = con.prepareStatement(updateSl);
////										ps.execute();
////										
////									}
//								} catch (SQLException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//								}
//								
////								txtTenThuoc.setText(tenThuoc.getCellData(index).toString());
////								txtSL.setText(soLuong.getText().toString());
//								try {
//									getCTHoaDon();
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
				try {
					int maPDT = getMaPDT();
					String tongGiaBan = "select sum(thanhTien) as tong from CTDonDatThuoc where maPDT ='"+maPDT+"'";
					ps = con.prepareStatement(tongGiaBan);
					rs = ps.executeQuery();
					while(rs.next()) {
						float tong = rs.getFloat("tong");
						String tongS = String.valueOf(tong);
						lblThanhTien.setText(tongS);
					}
				} catch (Exception e) {
		// TODO: handle exception
					e.printStackTrace();
					}
				
				Scene scene = new Scene(root,400,300);
				stage.setScene(scene);
				stage.setResizable(false);
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
		String getKH1 = "select * from KhachHang where tenKH = N'"+txtTenKH.getText()+"' and gioiTinh = N'"+txtGioiTinh.getText()+"'";
		ps = con.prepareStatement(getKH1);
		rs = ps.executeQuery();
		int maKH = 0;
		while(rs.next())
		maKH = rs.getInt("maKH");
		System.out.println(maKH);
		return maKH;
	  }
		public int getMaPDT() throws SQLException {
			 String chonhd = "select max(maPDT) as maPDT from DonDatThuoc";
			 ps = con.prepareStatement(chonhd);
			 rs = ps.executeQuery();
			 int maPDT = 0;
			 while(rs.next()) 
			 maPDT = rs.getInt("maPDT");
			 return maPDT;
		}
//	 public void tienThoi() {
//		 float tienNhan = Float.parseFloat(txtTienNhan.getText());
//		 float thanhTien = Float.parseFloat(lblThanhTien.getText());
//		 lblTienThoi.setText(String.valueOf(tienNhan - thanhTien));
//	 }
	 public void cell() throws SQLException {
		 maPDT.setCellValueFactory(new PropertyValueFactory<CTPhieuDatThuoc, Integer>("maPDT"));
		 tenThuoc.setCellValueFactory(new PropertyValueFactory<CTPhieuDatThuoc, String>("tenThuoc"));
//		 tenLoaiThuoc.setCellValueFactory(new PropertyValueFactory<CTPhieuDatThuoc, String>("tenLoaiThuoc"));
		 donViTinh.setCellValueFactory(new PropertyValueFactory<CTPhieuDatThuoc, String>("donViTinh"));
		 donGia.setCellValueFactory(new PropertyValueFactory<CTPhieuDatThuoc, Float>("donGia"));
		 soLuong.setCellValueFactory(new PropertyValueFactory<CTPhieuDatThuoc, Integer>("soLuong"));
		 tongGiaBan.setCellValueFactory(new PropertyValueFactory<CTPhieuDatThuoc, Float>("tongGiaBan"));

	 }
	 public void getCTPhieuDatThuoc() throws SQLException {
		 int maPDT = getMaPDT();
		 String sql = "select * from CTDonDatThuoc cthd left join Thuoc t on t.maThuoc = cthd.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc where maPDT = '"+maPDT+"'";
		 ps = con.prepareStatement(sql);
		 rs = ps.executeQuery();
		 ObservableList<CTPhieuDatThuoc> cthdList = FXCollections.observableArrayList();
		 int i = 0;
		 while(rs.next()) {
			 CTPhieuDatThuoc cthd = new CTPhieuDatThuoc();
			 cthd.setMaPDT(i = i + 1);
//			 cthd.setMaCTHD(i = i + 1);
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
		 String tongTien = "select sum(thanhTien) as tt from CTDonDatThuoc where maPDT ='"+maPDT+"'";
		 ps = con.prepareStatement(tongTien);
		 rs = ps.executeQuery();
		 
		 while(rs.next()) {
			 float tong = rs.getFloat("tt");
			 lblThanhTien.setText(tong + "");
//			 tienThoi();
		 }
	 }
	 public void taoHD() throws SQLException {
		 NhanVien dnc = DangNhapController.getNV();
		 LocalDate ldNgayNhap = dpNgayNhap.getValue();
		 Date dNgayNhap = Date.valueOf(ldNgayNhap);
		 int maKH = getTTKhachHang();
		 if(hd == 0) {
			 hd += 1;
			 String taohd = "insert into DonDatThuoc(maNV, ngayLapDon, tongTien, maKH, trangThai) values(?,?,?,?,N'Phiếu tạm')";
			 System.out.println(taohd);
			 try {
				ps = con.prepareStatement(taohd);
				 ps.setInt(1, dnc.getMaNV());
				 ps.setDate(2, dNgayNhap);
				 ps.setFloat(3, 0);
				 ps.setInt(4, maKH);
				 ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 
	 }

	@FXML
	public void thanhToan(ActionEvent e) throws SQLException, IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Thông báo");
		alert.setContentText("Bạn có chắc muốn thanh toán hoá đơn này không?");
		alert.setHeaderText(null);
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get()==ButtonType.OK) {
			int maPDT = getMaPDT();
			int ma = getTTKhachHang();
			System.out.println("ma phieu " + maPDT);
			String tongTien = "select sum(thanhTien) as tt from CTPhieuDatThuoc where maPDT ='"+maPDT+"'";
			ps = con.prepareStatement(tongTien);
			rs = ps.executeQuery();
			while(rs.next()) { 
			float tong = rs.getFloat("tt");
			System.out.println(rs.getFloat("tt"));
			lblThanhTien.setText(tong+ "");
			System.out.println("in hoá đơn thành công");
			String sql1 = "update PhieuDatThuoc set tongTien = '"+tong+"', maKH = '"+ma+"' where maPDT = '"+maPDT+"'";
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
	public void inHD1() throws IOException, SQLException {
		dpNgayNhap.setValue(LocalDate.now());
		LocalDate ldNgayNhap = dpNgayNhap.getValue();
		Date dNgayNhap = Date.valueOf(ldNgayNhap);
		String p = "C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\pdf\\phieudatthuoc.pdf";
		float tc = 285f;
		float twocol = tc + 150f;
		float three = tc + 50f;
		float twocolwidth[] = {twocol,three,tc};
		float half[] = {450f};
		float half2[] = {370f};
		float full[] = {770f};
		float sixcolwidth[] = {50f,150f,100f,70f,200f,200f};
		int maPDT = getMaPDT();
		PdfWriter pdf = new PdfWriter(p);
		PdfFont pf = PdfFontFactory.createFont("C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\fonts\\Roboto-Black.ttf", "Identity-H", true);
		PdfFont pflight = PdfFontFactory.createFont("C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\fonts\\Roboto-Light.ttf", "Identity-H", true);
		PdfDocument pd = new PdfDocument(pdf);
		pd.setDefaultPageSize(PageSize.A4);
		Document d = new Document(pd);
		Table t = new Table(twocolwidth);
		t.addCell(new Cell().add(new Paragraph("NHÀ THUỐC THỊNH VƯỢNG").setFont(pf)).setBorder(Border.NO_BORDER));
		t.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
		t.addCell(new Cell().add(new Paragraph("MÃ PHIẾU ĐẶT THUỐC: " + maPDT).setFont(pflight)).setBorder(Border.NO_BORDER));
		Table divide = new Table(full);
		Border g = new SolidBorder(1f/2f);
		divide.setBorder(g);
		Table t1 = new Table(full);
		t1.addCell(new Cell().add(new Paragraph("PHIẾU ĐẶT THUỐC").setFont(pf)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setFontSize(24));
		t1.addCell(new Cell().add(new Paragraph("Họ tên: " + txtTenKH.getText()).setFont(pflight)).setBorder(Border.NO_BORDER));
		t1.addCell(new Cell().add(new Paragraph("Giới tính: " + txtGioiTinh.getText()).setFont(pflight)).setBorder(Border.NO_BORDER));
		t1.addCell(new Cell().add(new Paragraph("Số điện thoại: " + txtSdt.getText()).setFont(pflight)).setBorder(Border.NO_BORDER));
		t1.addCell(new Cell().add(new Paragraph("Email: " + txtEmail.getText()).setFont(pflight)).setBorder(Border.NO_BORDER));
//		d.add(new Paragraph("NHÀ THUỐC THỊNH VƯỢNG").setFont(pf));
//		d.add(new Paragraph("HOÁ ĐƠN BÁN HÀNG").setFont(pflight));
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
		t3.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
		t3.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
		t3.addCell(new Cell().add(new Paragraph("Thành tiền: " + lblThanhTien.getText()).setFont(pf)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
		Table divide1 = new Table(full);
		divide1.setBorder(g);
		Table t4 = new Table(half);
		Table t5 = new Table(half2);
		t4.addCell(new Cell().add(new Paragraph("          Ngày " + ldNgayNhap.getDayOfMonth() + " tháng " + ldNgayNhap.getMonthValue() + " năm " + ldNgayNhap.getYear()).setFont(pf)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setMarginRight(100f));
		t4.addCell(new Cell().add(new Paragraph("Người lập phiếu").setFont(pf)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setMarginRight(100f));
		d.add(t);
		d.add(divide);
		d.add(t1);
		d.add(t2);
		d.add(t3);
		d.add(divide1);
		d.add(t4);
		d.add(t5);
		d.close();
		File file = new File("C:\\Users\\mavuv\\Desktop\\QuanLyHieuThuoc\\QuanLyHieuThuoc\\pdf\\phieudatthuoc.pdf");
		Desktop.getDesktop().open(file);
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
