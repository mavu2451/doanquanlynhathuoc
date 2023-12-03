package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class TrangChuController implements Initializable{
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	@FXML
	private MenuButton mb;
	@FXML
	private MenuItem mNhapHang;

	@FXML
	private Label lblName, lblKhachHang, lblThuocSapHetHan, lblThuocSapHetHang, lblDTThang;
	@FXML
	private TableView<NhaCungCap> table;
	@FXML
	private ObservableList<NhaCungCap> list = FXCollections.observableArrayList();
	@FXML
	private TableColumn<NhaCungCap, Integer> stt;
	@FXML
	private TableColumn<NhaCungCap, String> tenNCC;
	@FXML
	private TableColumn<NhaCungCap, Float> tongGiaNhap;

	@FXML
	private BarChart<?, ?> barchart;
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
	public void thongKeThuocSapHetHang(ActionEvent e) throws IOException {
		Stage stage = (Stage) mb.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ThongKeThuocSapHetHang.fxml"));
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
      public void logOut(ActionEvent e){
    	  System.exit(0);
      }
      

	//End Navbar
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		cell();
		String sql = "select * from NhanVien";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			NhanVien dnc = DangNhapController.getNV();

				lblName.setText("Xin chào, " + dnc.getHoTen());
				//Loi
				System.out.println(dnc.getMaNV());
				System.out.println(dnc.getHoTen());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	String ncc = "select ncc.tenNCC, isNull(sum(ct.tongGiaNhap),0) as tongGiaNhap from PhieuNhap pn left join NhaCungCap ncc on ncc.maNCC = pn.maNCC inner join CTPhieuNhap ct on ct.maPN = pn.maPN group by tenNCC order by tongGiaNhap desc";
	try {
		ps = con.prepareStatement(ncc);
		rs = ps.executeQuery();
		int i = 1;
		while(rs.next()) {
			NhaCungCap n = new NhaCungCap();
			n.setMaNCC(i++);
			n.setTenNCC(rs.getString("tenNCC"));	
			n.setTongGiaNhap(rs.getFloat("tongGiaNhap"));
			list.add(n);
			table.setItems(list);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String hh = "    SELECT count(*) as tong FROM CTThuoc ct left join Thuoc t on t.maThuoc = ct.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc WHERE soLuongCon > 0 and dinhMucSL>= soLuongCon and trangThai = N'Đang kinh doanh' ";
	try {
		ps = con.prepareStatement(hh);
		rs = ps.executeQuery();
		while(rs.next())
			lblThuocSapHetHang.setText(rs.getInt("tong") + "");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String hethan = " SELECT count(*) as tong FROM CTThuoc ct left join Thuoc t on t.maThuoc = ct.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc WHERE soLuongCon > 0 and datediff(day,GETDATE(),hanSuDung) > 0";
	try {
		ps = con.prepareStatement(hethan);
		rs = ps.executeQuery();
		while(rs.next())
			lblThuocSapHetHan.setText(rs.getInt("tong") + "");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String kh = "select count(maKH) as kh from KhachHang";
	try {
		ps = con.prepareStatement(kh);
		rs = ps.executeQuery();
		while(rs.next())
			lblKhachHang.setText(rs.getInt("kh") + "");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	LocalDate d = LocalDate.now();
	String dt = "select sum(donGia * soLuong) as tongTien from hoaDon hd left join CTHoaDon ct on ct.maHD = hd.maHD where month(ngayLapHD) = '"+d.getMonthValue()+"'";
	try {
		ps = con.prepareStatement(dt);
		rs = ps.executeQuery();
		while(rs.next())
			lblDTThang.setText(String.format("%.0f", rs.getFloat("tongTien")) + "");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String dtngay = "select sum(donGia * soLuong) as tongTien from hoaDon hd left join CTHoaDon ct on ct.maHD = hd.maHD where ngayLapHD ='"+d.minusDays(2)+"'";
	try {
		ps = con.prepareStatement(dtngay);
		rs = ps.executeQuery();
		while(rs.next()) {
			XYChart.Series series = new XYChart.Series();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY"); 
			series.getData().add(new XYChart.Data(formatter.format(d.minusDays(2)), rs.getFloat("tongTien")));
			series.setName("Doanh thu ngày "+formatter.format(d.minusDays(2))+" là : " + String.format("%.0f", rs.getFloat("tongTien"))  + " đồng");
			barchart.getData().add(series);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String dtngay1 = "select sum(donGia * soLuong) as tongTien from hoaDon hd left join CTHoaDon ct on ct.maHD = hd.maHD where ngayLapHD ='"+d.minusDays(1)+"'";
	try {
		ps = con.prepareStatement(dtngay1);
		rs = ps.executeQuery();
		while(rs.next()) {
			XYChart.Series series = new XYChart.Series();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY"); 
			series.getData().add(new XYChart.Data(formatter.format(d.minusDays(1)), rs.getFloat("tongTien")));
			series.setName("Doanh thu ngày "+formatter.format(d.minusDays(1))+" là : " + String.format("%.0f", rs.getFloat("tongTien"))  + " đồng");
			barchart.getData().add(series);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String dtngay2 = "select sum(donGia * soLuong) as tongTien from hoaDon hd left join CTHoaDon ct on ct.maHD = hd.maHD where ngayLapHD ='"+d+"'";
	try {
		ps = con.prepareStatement(dtngay2);
		rs = ps.executeQuery();
		while(rs.next()) {
			XYChart.Series series = new XYChart.Series();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY"); 
			series.getData().add(new XYChart.Data(formatter.format(d), rs.getFloat("tongTien")));
			series.setName("Doanh thu ngày hôm nay là : " + String.format("%.0f", rs.getFloat("tongTien"))  + " đồng");
			barchart.getData().add(series);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
}
public void cell() {
	tenNCC.setCellValueFactory(new PropertyValueFactory<NhaCungCap, String>("tenNCC"));
	tongGiaNhap.setCellValueFactory(new PropertyValueFactory<NhaCungCap, Float>("tongGiaNhap"));
	stt.setCellValueFactory(new PropertyValueFactory<NhaCungCap, Integer>("maNCC"));
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

}
