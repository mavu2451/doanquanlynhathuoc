package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.KetNoiDatabase;
import entity.HoaDon;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TimKiemHoaDonController implements Initializable{
	@FXML
	ObservableList<HoaDon> list = FXCollections.observableArrayList();
	@FXML
	TableView<HoaDon> table;
	@FXML
	private TableColumn<HoaDon, Integer> maHD;
	@FXML
	private TableColumn<HoaDon, String> tenNV;
	@FXML
	private TableColumn<HoaDon, String> tenKH;
	@FXML
	private TableColumn<HoaDon, Date> ngayLapHD;
	@FXML
	private TableColumn<HoaDon, Float> tongTien;
	@FXML
	private ComboBox<String> cbbTKD;

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
		getHoaDon();
		cell();
		NhanVien dnc = DangNhapController.getNV();
//		try {
//			while(rs.next()) {
				lblName.setText("Xin chào, " + dnc.getHoTen());
				System.out.println(dnc.getMaNV() + "Xem thuoc");
				System.out.println(dnc.getHoTen());
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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
		maHD.setCellValueFactory(new PropertyValueFactory<HoaDon, Integer>("maHD"));
		tenNV.setCellValueFactory(new PropertyValueFactory<HoaDon, String>("tenNV"));
		tenKH.setCellValueFactory(new PropertyValueFactory<HoaDon, String>("tenKH"));
		ngayLapHD.setCellValueFactory(new PropertyValueFactory<HoaDon, Date>("ngayLapHD"));
		tongTien.setCellValueFactory(new PropertyValueFactory<HoaDon, Float>("tongTien"));
	}

	@FXML
	public ObservableList<HoaDon> getHoaDon(){
		cbbTKD.setItems(FXCollections.observableArrayList("Tất cả", "Hoá đơn theo đơn", "Hoá đơn không theo đơn"));
		cbbTKD.getSelectionModel().selectFirst();
		String sql = "select * from HoaDon hd left join NhanVien nv on nv.maNV = hd.maNV inner join KhachHang kh on kh.maKH = hd.maKH";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			list.clear();
			table.setItems(list);
			while(rs.next()) {
				HoaDon t = new HoaDon();
				t.setMaHD(rs.getInt("maHD"));
				t.setTenNV(rs.getString("tenNV"));
				t.setTenKH(rs.getString("tenKH"));
				t.setNgayLapHD(rs.getDate("ngayLapHD"));
				t.setTongTien(rs.getFloat("tongTien"));
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
			String sql1 = "select * from HoaDon hd left join NhanVien nv on nv.maNV = hd.maNV  inner join KhachHang kh on kh.maKH = hd.maKH";
			try {
				ps = con.prepareStatement(sql1);
				rs = ps.executeQuery();
				list.clear();
				table.setItems(list);
				while(rs.next()) {
					HoaDon t = new HoaDon();
					t.setMaHD(rs.getInt("maHD"));
					t.setTenNV(rs.getString("tenNV"));
					t.setTenKH(rs.getString("tenKH"));
					t.setNgayLapHD(rs.getDate("ngayLapHD"));
					t.setTongTien(rs.getFloat("tongTien"));
					list.add(t);
					table.setItems(list);
					
				}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
		}
		if(cbbTKD.getSelectionModel().getSelectedItem() =="Hoá đơn theo đơn") {
				String sql1 = "select * from HoaDon hd left join NhanVien nv on nv.maNV = hd.maNV inner join KhachHang kh on kh.maKH = hd.maKH where maDonThuoc is not null";
				list.clear();
				table.setItems(list);
				try {
					ps = con.prepareStatement(sql1);
					rs = ps.executeQuery();
					while(rs.next()) {
						HoaDon t = new HoaDon();
						t.setMaHD(rs.getInt("maHD"));
						t.setTenNV(rs.getString("tenNV"));
						t.setTenKH(rs.getString("tenKH"));
						t.setNgayLapHD(rs.getDate("ngayLapHD"));
						t.setTongTien(rs.getFloat("tongTien"));
						list.add(t);
						table.setItems(list);
						
					}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
			}
		if(cbbTKD.getSelectionModel().getSelectedItem() == "Hoá đơn không theo đơn") {
				String sql1 = "select * from HoaDon hd left join NhanVien nv on nv.maNV = hd.maNV inner join KhachHang kh on kh.maKH = hd.maKH where maDonThuoc is null";
				list.clear();
				table.setItems(null);
				try {
					ps = con.prepareStatement(sql1);
					rs = ps.executeQuery();
					while(rs.next()) {
						HoaDon t = new HoaDon();
						t.setMaHD(rs.getInt("maHD"));
						t.setTenNV(rs.getString("tenNV"));
						t.setTenKH(rs.getString("tenKH"));
						t.setNgayLapHD(rs.getDate("ngayLapHD"));
						t.setTongTien(rs.getFloat("tongTien"));
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

	public void reload() {
		getHoaDon();
		// TODO Auto-generated method stub
		cell();
		table.setItems(list);
	}

}
