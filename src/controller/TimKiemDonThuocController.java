package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import database.KetNoiDatabase;
import entity.CTDonThuocKhamBenh;
import entity.CTHoaDon;
import entity.CTPhieuNhap;
import entity.DonDatThuoc;
import entity.DonThuocKhamBenh;
import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.Thuoc;
import entity.Thuoc;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TimKiemDonThuocController implements Initializable{
	@FXML
	private Button btnXemCT, btnTimKiem;
	@FXML
	TableView<DonThuocKhamBenh> table;
	@FXML
	private DatePicker dpNgayNhap;
	@FXML
	private TextField txtNV, txtKH, txtMaDT;
	@FXML
	private TableColumn<DonThuocKhamBenh, Integer> maDT;
	@FXML
	private TableColumn<DonThuocKhamBenh, Date> ngayNhap;
	@FXML
	private TableColumn<DonThuocKhamBenh, String> tenKH;
	@FXML
	private TableColumn<DonThuocKhamBenh, String> tenNV;
	ObservableList<DonThuocKhamBenh> list = FXCollections.observableArrayList();
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	@FXML
	private MenuButton mb;
//	@FXML
//	private MenuItem mNhapHang;
	@FXML
	private Label lblName;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		reload();
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
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}btnTimKiem.setOnAction(a -> {
				btnTimKiem.setOnAction(a -> {
				String maHD = txtMaDT.getText().toString();
				String tenKH = txtKH.getText().toString();
				String tenNV = txtNV.getText().toString();

				LocalDate ldNgayNhap = dpNgayNhap.getValue();
				Date d = Date.valueOf(ldNgayNhap);
				
				if(maHD == "" && tenKH == "" && tenNV == "" && dpNgayNhap.getValue()==null) {
					table.getItems().clear();
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Thông báo");
					alert.setContentText("Không được để trống");
					alert.setHeaderText(null);
					alert.showAndWait();
					getAllThuoc();
				}
				else {
				table.getItems().clear();
				String sql = "select * from DonThuocKhamBenh pn left join NhanVien nv on nv.maNV = pn.maNV inner join KhachHang ncc on ncc.maKH = pn.maKH where maDonThuoc like N'%"+maHD+"%' and tenKH like N'%"+tenKH+"%' and tenNV like N'%"+tenNV+"%' and ngayNhap like '%"+d+"%' ";
				try {
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					while(rs.next()) {
						DonThuocKhamBenh pn = new DonThuocKhamBenh();
			    		  pn.setMaDonThuoc(rs.getInt("maDonThuoc"));
			    		  pn.setTenNV(rs.getString("tenNV"));
			    		  pn.setTenKH(rs.getString("tenKH"));
			    		  pn.setNgayNhap(rs.getDate("ngayNhap"));
//			    		  pn.setTongTien(rs.getFloat("tongTien"));
			    		  list.add(pn);
			    		  table.setItems(list);
			    	  
					}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				
			});
				
				btnXemCT.setOnAction(arg->{
					int index = table.getSelectionModel().getSelectedIndex();
					if(index<=-1) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setHeaderText(null);
						alert.setContentText("Mời bạn chọn phiếu nhập");
						alert.showAndWait();
					}
					else {
					String sqlMaHD = String.valueOf(maDT.getCellData(index).toString());
					ObservableList<Thuoc> thuocList = FXCollections.observableArrayList();
					BorderPane root = new BorderPane();
					ScrollPane scroll = new ScrollPane();
					TextField txtTimKiem = new TextField();
					Label lblBacSi = new Label("Bác sĩ kê đơn: ");
					Label lblBacSi2 = new Label();
					Label lblChanDoan = new Label("Chẩn đoán: ");
					Label lblChanDoan2 = new Label();
					Label lblLoiDan = new Label("Lời dặn: ");
					Label lblLoiDan2 = new Label();
					Label lblThongTin = new Label("Bác sĩ kê đơn: ");
					Label lblThongTin2 = new Label();
					HBox h1 = new HBox(2);
					HBox h2 = new HBox(1);
					AnchorPane a = new AnchorPane();
					Stage stage = new Stage();
		;			TableView tableView = new TableView<CTDonThuocKhamBenh>();
					TableColumn maThuoc = new TableColumn<CTDonThuocKhamBenh, Integer>("Mã thuốc");
					maThuoc.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Integer>("maThuoc"));
					TableColumn tenThuoc = new TableColumn<CTDonThuocKhamBenh, String>("Tên thuốc");
					tenThuoc.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, String>("tenThuoc"));
					TableColumn soLuong = new TableColumn<CTDonThuocKhamBenh, Integer>("Số lượng");
					soLuong.setCellValueFactory(new PropertyValueFactory<CTDonThuocKhamBenh, Integer>("soLuong"));

					lblBacSi2.setLayoutX(100);
					a.setPrefHeight(100);

					
					tableView.getColumns().add(maThuoc);
					tableView.getColumns().add(tenThuoc);
					tableView.getColumns().add(soLuong);

					
					root.setCenter(scroll);
					scroll.setContent(tableView);
					root.setTop(h1);
					String sql = "select * from CTDonThuocKhamBenh ct left join Thuoc t on t.maThuoc = ct.maThuoc inner join DonThuocKhamBenh d on d.maDonThuoc = ct.maDonThuoc where ct.maDonThuoc = '"+sqlMaHD+"'";
					try {
						ps = con.prepareStatement(sql);
						rs = ps.executeQuery();
						ObservableList<CTDonThuocKhamBenh> ctList = FXCollections.observableArrayList();
						while(rs.next()) {
							CTDonThuocKhamBenh ct  = new CTDonThuocKhamBenh();
							ct.setMaThuoc(rs.getInt("maThuoc"));
							ct.setTenThuoc(rs.getString("tenThuoc"));
							ct.setSoLuong(rs.getInt("soLuong"));
							ct.setThuocKeDon(rs.getString("thuocKeDon"));
							ct.setBacSiKeDon(rs.getString("bacSiKeDon"));
							lblBacSi2.setText(ct.getBacSiKeDon());
							ct.setChanDoan(rs.getString("chanDoan"));
							ct.setLoiDan(rs.getString("loiDan"));
							ct.setThongTin(rs.getString("thongTin"));
							ctList.add(ct);
							tableView.setItems(ctList);

						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					Scene scene = new Scene(root,250,250);
					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();
					}
				});
}
//	public void thuoc(ActionEvent e) throws IOException {
////		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
////		Stage stage = (Stage) mb.getScene().getWindow();
////		FXMLLoader loader = new FXMLLoader();
////        loader.setLocation(getClass().getResource("/view/Thuoc.fxml"));
////        Parent sampleParent = loader.load();
////        Scene scene = new Scene(sampleParent);
////        scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
////        stage.setScene(scene);
////        stage.show();
//	}
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
	  	public void thongKeThuocSapHetHang(ActionEvent e) throws IOException {
			Stage stage = (Stage) mb.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/view/ThongKeThuocSapHetHang.fxml"));
	        Parent sampleParent = loader.load();
	        Scene scene = new Scene(sampleParent);
	        stage.setScene(scene);
	       
		}

	//End Navbar
	
	public void boLocThuoc(ActionEvent e) throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/LocThuoc.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
        stage.show();
	}
	
	public void cell() {
		maDT.setCellValueFactory(new PropertyValueFactory<DonThuocKhamBenh, Integer>("maDonThuoc"));
		ngayNhap.setCellValueFactory(new PropertyValueFactory<DonThuocKhamBenh, Date>("ngayNhap"));
		tenKH.setCellValueFactory(new PropertyValueFactory<DonThuocKhamBenh, String>("tenKH"));
		tenNV.setCellValueFactory(new PropertyValueFactory<DonThuocKhamBenh, String>("tenNV"));

	}

	public ObservableList<DonThuocKhamBenh> getAllThuoc(){

		String query = "select * from DonThuocKhamBenh d left join NhanVien nv on nv.maNV = d.maNV inner join KhachHang kh on kh.maKH = d.maKH";
		
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {

				DonThuocKhamBenh t = new DonThuocKhamBenh();
				t.setMaDonThuoc(rs.getInt("maDonThuoc"));
				t.setNgayNhap(rs.getDate("ngayNhap"));
				t.setTenNV(rs.getString("tenNV"));
				t.setTenKH(rs.getString("tenKH"));
				list.add(t);
				table.setItems(list);
			}
		}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
		return list;
		}
	public void reload() {
		list = getAllThuoc();
		// TODO Auto-generated method stub
		cell();
		table.setItems(list);
	}

}
