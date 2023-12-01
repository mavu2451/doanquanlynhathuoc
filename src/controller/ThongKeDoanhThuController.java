package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;

import database.KetNoiDatabase;
import entity.NhanVien;
import entity.PhieuNhap;
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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;


public class ThongKeDoanhThuController implements Initializable{
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	@FXML
	private MenuButton mb;
	@FXML
	private DatePicker dpNgay, dpChiNgay;
	@FXML
	private ComboBox  cbbThang,cbbNam, cbbChiThang, cbbChiNam;
	@FXML
	private MenuItem mNhapHang;
	@FXML
	private Label lblName, lblDTNgay,lblDTNgay1, lblNgay, lblTong, lblTongThang, lblTongNam,lblTongNam1, lblThangNay, lblChiHomNay, lblChiThang, lblChiTong, lblTongThang1;
	@FXML
	private LineChart<?, ?> chart;
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
	 	public void thongKeThuocSapHetHang(ActionEvent e) throws IOException {
			Stage stage = (Stage) mb.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/view/ThongKeThuocSapHetHang.fxml"));
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LocalDate date = LocalDate.now();
		int d = date.getMonthValue();
		System.out.println(date);
		String ngay = "select sum(donGia * soLuong) as tongTien from hoaDon hd left join CTHoaDon ct on ct.maHD = hd.maHD where ngayLapHD = cast(getdate() as Date)";
		try {
			ps = con.prepareStatement(ngay);
			rs = ps.executeQuery();
			while(rs.next()) {
				lblNgay.setText(String.format("%.0f",rs.getFloat("tongTien"))+ " ĐỒNG");
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
			String thangnay = "select sum(donGia * soLuong) as tongTien from hoaDon hd left join CTHoaDon ct on ct.maHD = hd.maHD where month(ngayLapHD) = '"+d+"'";
			System.out.println(date.getMonth());
			try {
				ps = con.prepareStatement(thangnay);
				rs = ps.executeQuery();
				while(rs.next()) {
					lblThangNay.setText(String.format("%.0f",rs.getFloat("tongTien"))+ " ĐỒNG");
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		
		String tong = "select sum(donGia * soLuong) as tongTien from hoaDon hd left join CTHoaDon ct on ct.maHD = hd.maHD";
		try {
			ps = con.prepareStatement(tong);
			rs = ps.executeQuery();
			while(rs.next()) {
				lblTong.setText(String.format("%.0f",rs.getFloat("tongTien")) + " ĐỒNG");
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		String ngayChi = "select isNull(sum(tongGiaBan),0) as tongTien from CTPhieuNhap ct left join PhieuNhap p on p.maPN = ct.maPN where ngayNhap = cast(getdate() as Date) and trangThai = N'Đã nhập hàng'";
		try {
			ps = con.prepareStatement(ngayChi);
			rs = ps.executeQuery();
			while(rs.next()) {
				lblChiHomNay.setText(String.format("%.0f",rs.getFloat("tongTien"))+ " ĐỒNG");
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String thangChi = "select isNull(sum(tongGiaBan),0) as tongTien from CTPhieuNhap ct left join PhieuNhap p on p.maPN = ct.maPN where month(ngayNhap) = '"+d+"' and trangThai = N'Đã nhập hàng'";
		try {
			ps = con.prepareStatement(thangChi);
			rs = ps.executeQuery();
			while(rs.next()) {
				lblChiThang.setText(String.format("%.0f",rs.getFloat("tongTien"))+ " ĐỒNG");
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String tongChi = "select isNull(sum(tongGiaBan),0) as tongTien from CTPhieuNhap ct left join PhieuNhap p on p.maPN = ct.maPN where trangThai = N'Đã nhập hàng'";
		try {
			ps = con.prepareStatement(tongChi);
			rs = ps.executeQuery();
			while(rs.next()) {
				lblChiTong.setText(String.format("%.0f",rs.getFloat("tongTien"))+ " ĐỒNG");
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		ObservableList<String> theo = FXCollections.observableArrayList("Tháng","Ngày");
		String nam = "select distinct(year(ngaylapHD)) as ngayLapHD from HoaDon order by ngayLapHD desc";
		try {
			ps = con.prepareStatement(nam);
			rs = ps.executeQuery();
			while(rs.next())
			cbbNam.getItems().add(rs.getInt("ngayLapHD"));

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String chinam = "select distinct(year(ngayNhap)) as ngayNhap from PhieuNhap order by ngayNhap";
		try {
			ps = con.prepareStatement(chinam);
			rs = ps.executeQuery();
			while(rs.next())
			cbbChiNam.getItems().add(rs.getInt("ngayNhap"));

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dpNgay.setOnAction(args -> {
			String dtngay = "select sum(donGia * soLuong) as tongTien from hoaDon hd left join CTHoaDon ct on ct.maHD = hd.maHD where ngayLapHD = '"+dpNgay.getValue()+"'";
			try {
				ps = con.prepareStatement(dtngay);
				rs = ps.executeQuery();
				while(rs.next())
				lblDTNgay.setText(String.format("%.0f", rs.getFloat("tongTien"))+ " ĐỒNG");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		dpChiNgay.setOnAction(args -> {
			String dtngay = "select isNull(sum(tongGiaBan),0) as tongTien from CTPhieuNhap ct left join PhieuNhap p on p.maPN = ct.maPN where ngayNhap = '"+dpChiNgay.getValue()+"' and trangThai = N'Đã nhập hàng'";
			try {
				ps = con.prepareStatement(dtngay);
				rs = ps.executeQuery();
				while(rs.next())
				lblDTNgay1.setText(String.format("%.0f", rs.getFloat("tongTien"))+ " ĐỒNG");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		ObservableList<String> thang = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
		cbbChiThang.setItems(thang);
		cbbChiThang.getSelectionModel().selectFirst();
		cbbChiThang.setOnAction(args -> {
			String sql = "select isNull(sum(tongGiaBan),0) as tongTien from CTPhieuNhap ct left join PhieuNhap p on p.maPN = ct.maPN where month(ngayNhap) = '"+cbbChiThang.getSelectionModel().getSelectedItem()+"' and year(ngayNhap) = '"+cbbChiNam.getSelectionModel().getSelectedItem()+"' and trangThai = N'Đã nhập hàng'";
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next())
					lblTongThang1.setText(String.format("%.0f", rs.getFloat("tongTien")) + " ĐỒNG");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		cbbThang.setItems(thang);
		cbbThang.getSelectionModel().selectFirst();
		cbbThang.setOnAction(args -> {
			String sql = "select sum(donGia * soLuong) as tongTien from hoaDon hd left join CTHoaDon ct on ct.maHD = hd.maHD where month(ngayLapHD) = '"+cbbThang.getSelectionModel().getSelectedItem()+"' and year(ngayLapHD) = '"+cbbNam.getSelectionModel().getSelectedItem()+"'";
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next())
					lblTongThang.setText(String.format("%.0f", rs.getFloat("tongTien")) + " ĐỒNG");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		cbbNam.setOnAction(args -> {
			String sql = "select sum(donGia * soLuong) as tongTien from hoaDon hd left join CTHoaDon ct on ct.maHD = hd.maHD where year(ngayLapHD) = '"+cbbNam.getSelectionModel().getSelectedItem()+"'";
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next())
					lblTongNam.setText(String.format("%.0f", rs.getFloat("tongTien")) + " ĐỒNG");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		cbbChiNam.setOnAction(args -> {
			String sql = "select isNull(sum(tongGiaBan),0) as tongTien from CTPhieuNhap ct left join PhieuNhap p on p.maPN = ct.maPN where year(ngayNhap) = '"+cbbChiNam.getSelectionModel().getSelectedItem()+"'and trangThai = N'Đã nhập hàng'";
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next())
					lblTongNam1.setText(String.format("%.0f", rs.getFloat("tongTien")) + " ĐỒNG");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
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

				XYChart.Series series = new XYChart.Series();
				for(int i = 1; i<= 12;i++) {
					float tongGiaBan = 0;
					try {
						tongGiaBan = tongGiaBan(i);
					} catch (SQLException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				series.getData().add(new XYChart.Data("Tháng "+i+"",tongGiaBan));
				
				}
				series.setName("Tổng thu theo tháng");
				chart.getData().add(series);

				
				XYChart.Series series1 = new XYChart.Series();
				for(int i = 1; i<= 12;i++) {
					float tongGiaNhap = 0;
					try {
						tongGiaNhap = tongGiaNhap(i);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				series1.getData().add(new XYChart.Data("Tháng "+i+"",tongGiaNhap));
				}
				series1.setName("Tổng chi theo tháng");
				chart.getData().add(series1);
	}
			
	public float tongGiaNhap(int i) throws SQLException {
		float tongGia = 0;
		String sqlchi = "select sum(tongGiaNhap) as tongGiaNhap from CTPhieuNhap ct left join PhieuNhap hd on ct.maPN = hd.maPN where month(ngayNhap) = '"+i+"' and trangThai = N'Đã nhập hàng'";
		ps = con.prepareCall(sqlchi);
		rs = ps.executeQuery();
		while(rs.next()) 
			tongGia = rs.getFloat("tongGiaNhap");
		return tongGia;
	}
	public float tongGiaNhapLuuTam(int i) throws SQLException {
		float tongGia = 0;
		String sqlchi = "select sum(tongGiaNhap) as tongGiaNhap from CTPhieuNhap ct left join PhieuNhap hd on ct.maPN = hd.maPN where month(ngayNhap) = '"+i+"' and trangThai = N'Lưu tạm'";
		ps = con.prepareCall(sqlchi);
		rs = ps.executeQuery();
		while(rs.next()) 
			tongGia = rs.getFloat("tongGiaNhap");
		return tongGia;
	}
	public float tongGiaBan(int i) throws SQLException {
		float tongGia = 0;
		String sqlchi = "select sum(donGia * soLuong) as tongTien from hoaDon hd left join CTHoaDon ct on ct.maHD = hd.maHD where month(ngayLapHD)='"+i+"'";
		ps = con.prepareCall(sqlchi);
		rs = ps.executeQuery();
		while(rs.next()) 
			tongGia = rs.getFloat("tongTien");
		return tongGia;
	}
}
