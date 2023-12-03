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
import entity.CTHoaDon;
import entity.CTThuoc;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ThongKeThuocSapHetHanNVController implements Initializable{
	Connection con = KetNoiDatabase.getConnection();
	@FXML
	private MenuButton mb;
	@FXML
	private Button btnTimKiem;
	@FXML
	private MenuItem mNhapHang;
	@FXML
	private Label lblName, lblHetHan, lblHetHan1;
	@FXML
	private DatePicker dpHanSuDung;
	@FXML
	private TextField txtTenThuoc, txtDonViTinh, txtLoaiThuoc;
	@FXML
	private TableView<CTThuoc> table;
	@FXML
	private ComboBox<String> cbbThuocSapHet;
	@FXML
	private TableColumn<CTThuoc, Integer> maThuoc;
	@FXML
	private TableColumn<CTThuoc, String> tenThuoc;
	@FXML
	private TableColumn<CTThuoc, String> donViTinh;
	@FXML
	private TableColumn<CTThuoc, String> loaiThuoc;
	@FXML
	private TableColumn<CTThuoc, Integer> slTon;
	@FXML
	private TableColumn<CTThuoc, Date> hanSuDung;
	
	private ObservableList<CTThuoc> list = FXCollections.observableArrayList();
	PreparedStatement ps;
	ResultSet rs;
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
    public void timKiemGioHang(ActionEvent e) throws IOException {
     	Stage stage = (Stage) mb.getScene().getWindow();
     	FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("/view/TimKiemDonDatThuocNV.fxml"));
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		btnTimKiem.setOnAction(arg->{
			reload();
			LocalDate ld = dpHanSuDung.getValue();
			Date date = Date.valueOf(ld);
			String s = "SELECT * FROM CTThuoc ct left join Thuoc t on t.maThuoc = ct.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc WHERE soLuongCon > 0 and tenThuoc like N'%"+txtTenThuoc.getText().toString()+"%'and donViTinh like N'%"+txtDonViTinh.getText().toString()+"%' and tenLoaiThuoc like N'"+ txtLoaiThuoc.getText().toString()+"%' and hanSuDung = '"+date+"'";
			try {
				ps = con.prepareStatement(s);
				rs = ps.executeQuery();
				int i = 1;
				while(rs.next()) {
					CTThuoc ct = new CTThuoc();
					ct.setMaThuoc(i++);
					ct.setTenThuoc(rs.getString("tenThuoc"));
					ct.setTenLoaiThuoc(rs.getString("tenLoaiThuoc"));
					ct.setDonViTinh(rs.getString("donViTinh"));
					ct.setSlTonKho(rs.getInt("soLuongCon"));
					ct.setHanSuDung(rs.getDate("hanSuDung"));
					list.add(ct);
					table.setItems(list);
				}
			}catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		
		});

		thuocSapHetHan();
		// TODO Auto-generated method stub
		cell();
		
		String hh = "  SELECT count(*) as tong FROM CTThuoc ct left join Thuoc t on t.maThuoc = ct.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc WHERE soLuongCon > 0 and datediff(day,GETDATE(),hanSuDung) <= 0 ";
		try {
			ps = con.prepareStatement(hh);
			rs = ps.executeQuery();
			while(rs.next()) {
				lblHetHan.setText(rs.getInt("tong") + "");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String hh1 = "  SELECT count(*) as tong FROM CTThuoc ct left join Thuoc t on t.maThuoc = ct.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc WHERE soLuongCon > 0 and datediff(day,GETDATE(),hanSuDung) <= 0 ";
		try {
			ps = con.prepareStatement(hh1);
			rs = ps.executeQuery();
			while(rs.next()) {
				lblHetHan1.setText(rs.getInt("tong") + "");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
		ObservableList<String> het = FXCollections.observableArrayList("Thuốc sắp hết hạn","Thuốc đã hết hạn");
		cbbThuocSapHet.setItems(het);
		cbbThuocSapHet.getSelectionModel().selectFirst();
		cbbThuocSapHet.setOnAction(arg->{
			if(cbbThuocSapHet.getSelectionModel().getSelectedItem().toString().equals("Thuốc sắp hết hạn")) {
				list.clear();
				table.setItems(null);
				thuocSapHetHan();
			}
			else if(cbbThuocSapHet.getSelectionModel().getSelectedItem().toString().equals("Thuốc đã hết hạn")) {
				list.clear();
				table.setItems(null);
				String sql1 = "  SELECT * FROM CTThuoc ct left join Thuoc t on t.maThuoc = ct.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc WHERE soLuongCon > 0 and datediff(day,GETDATE(),hanSuDung) <= 0 ORDER BY hanSuDung ";
				try {
					PreparedStatement ps1 = con.prepareStatement(sql1);
					rs = ps1.executeQuery();
					int i = 1;
					while(rs.next()) {
						CTThuoc ct = new CTThuoc();
						ct.setMaThuoc(i++);
						ct.setTenThuoc(rs.getString("tenThuoc"));
						ct.setTenLoaiThuoc(rs.getString("tenLoaiThuoc"));
						ct.setDonViTinh(rs.getString("donViTinh"));
						ct.setSlTonKho(rs.getInt("soLuongCon"));
						ct.setHanSuDung(rs.getDate("hanSuDung"));
						list.add(ct);
						table.setItems(list);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	
		
	}
	public void cell() {
		maThuoc.setCellValueFactory(new PropertyValueFactory<CTThuoc, Integer>("maThuoc"));
		 tenThuoc.setCellValueFactory(new PropertyValueFactory<CTThuoc, String>("tenThuoc"));
		 loaiThuoc.setCellValueFactory(new PropertyValueFactory<CTThuoc, String>("tenLoaiThuoc"));
		 donViTinh.setCellValueFactory(new PropertyValueFactory<CTThuoc, String>("donViTinh"));
		 slTon.setCellValueFactory(new PropertyValueFactory<CTThuoc, Integer>("slTonKho"));
		 hanSuDung.setCellValueFactory(new PropertyValueFactory<CTThuoc,Date>("hanSuDung"));
	}
	public ObservableList<CTThuoc> thuocSapHetHan() {
		String sql1 = "  SELECT * FROM CTThuoc ct left join Thuoc t on t.maThuoc = ct.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc WHERE soLuongCon > 0 and datediff(day,GETDATE(),hanSuDung) > 0 and datediff(day,GETDATE(),hanSuDung) < 30 ORDER BY hanSuDung ";
		try {
			PreparedStatement ps1 = con.prepareStatement(sql1);
			rs = ps1.executeQuery();
			int i = 1;
			table.setItems(null);
			list.clear();
			while(rs.next()) {
				CTThuoc ct = new CTThuoc();
				ct.setMaThuoc(i++);
				ct.setTenThuoc(rs.getString("tenThuoc"));
				ct.setTenLoaiThuoc(rs.getString("tenLoaiThuoc"));
				ct.setDonViTinh(rs.getString("donViTinh"));
				ct.setSlTonKho(rs.getInt("soLuongCon"));
//				ct.setSoLo(rs.getString("soLo"));
				ct.setHanSuDung(rs.getDate("hanSuDung"));
				list.add(ct);
				table.setItems(list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public void reload() {
		table.setItems(null);
		list.clear();
		list = thuocSapHetHan();
		// TODO Auto-generated method stub
		cell();
		table.setItems(list);
	}
}
