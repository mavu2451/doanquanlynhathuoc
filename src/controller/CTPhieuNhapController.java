package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import database.KetNoiDatabase;
import entity.PhieuNhap;
import entity.Thuoc;
import entity.CTPhieuNhap;
import entity.Kho;
import entity.NhanVien;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Cell;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CTPhieuNhapController implements Initializable{
	PhieuNhap pn = new PhieuNhap();
	CTPhieuNhap ctpn = new CTPhieuNhap();
	ObservableList<CTPhieuNhap> ct = FXCollections.observableArrayList();
	ObservableList<CTPhieuNhap> ctpnList = FXCollections.observableArrayList();
	int maPN;
	@FXML
	private Button btnNgayNhap;
	@FXML
	private DatePicker dpNgayNhap, dpHSD;
	@FXML
	private TextField txtSoLuong, txtMaThuoc, txtTimKiem, txtNSX, txtNCC, txtLoaiThuoc, txtDonViTinh,txtGiaNhap,txtGiaBan, txtSoLo;
	@FXML
	private TextArea taGhiChu;
	@FXML
	private ComboBox<?> cbbThuoc, cbbNCC;
	@FXML
	TableView<CTPhieuNhap> table;
	@FXML 
	Label lblName, lblNV,lblGiaNhapQuyDoi, lblGiaBanQuyDoi, lblTongGiaNhap, lblTongGiaBan, lblMaPN;
	@FXML
	private MenuButton mb;
	public NhanVien dnc = DangNhapController.getNV();
	@FXML
	private TableColumn<CTPhieuNhap, Integer> tenThuoc;
	@FXML
	private TableColumn<CTPhieuNhap, String> dvt;
	@FXML
	private TableColumn<CTPhieuNhap, Float> giaNhap;
	@FXML
	private TableColumn<CTPhieuNhap, Float> giaBan;
	@FXML
	private TableColumn<CTPhieuNhap, Integer> soLuong;
	@FXML
	private TableColumn<CTPhieuNhap, Date> hsd;
	@FXML
	private TableColumn<CTPhieuNhap, Float> tongGiaNhap;
	@FXML
	private TableColumn<CTPhieuNhap, Float> tongGiaBan;
	@FXML
	private ComboBox<String> cbbTrangThai;
	public PhieuNhap p;
	ActionEvent e;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	ObservableList<CTPhieuNhap> list = FXCollections.observableArrayList();
	String[] trangThaiList = {"Đã nhập","Chưa nhập"};
	//new CTPhieuNhap(1,1,"test","test","test","test",10000,20000,2,new Date(100000),new Date(200000),"test","test",20000,40000)
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbbTrangThai.getItems().addAll(trangThaiList);
		txtSoLuong.setText("1");
		lblGiaBanQuyDoi.setText("0 đồng");
		float gb = 0;
		float tgn = 0;
		if(txtGiaBan.getText().toString() != null || txtSoLuong.getText().toString()!=null || txtGiaNhap.getText().toString()!=null) {
			txtGiaBan.setText("0");
			txtGiaNhap.setText("0");
			gb = Float.parseFloat(txtGiaBan.getText());
			tgn = Float.parseFloat(txtGiaNhap.getText());
			int sl = Integer.parseInt(txtSoLuong.getText());
			lblGiaBanQuyDoi.setText(gb*sl + " đồng");
			lblTongGiaNhap.setText(tgn*sl + " đồng");
			float quydoi = Float.parseFloat(txtGiaBan.getText().toString());
		}
		lblMaPN.setText("0");

		// TODO Auto-generated method stub
		reload();
		cell();
		getAllTenThuoc();
		getAllNCC();
		String t = "select * from PhieuNhap";
//		ps=con.prepareStatement(t);
//		rs=ps.executeQuery();
//		while(rs.next()) {
//			
//		}
//		table.setItems();
//		
		String sql = "select * from NhanVien";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();


				lblName.setText("Xin chào, " + dnc.getHoTen());
				lblNV.setText(dnc.getHoTen());
				//Loi
				System.out.println(dnc.getMaNV());
				System.out.println(dnc.getHoTen());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public int setMaPN(PhieuNhap p) {
//		lblMaPN.setText(String.valueOf(p.getMaPN()));
//		text = Integer.parseInt(lblMaPN.getText());
//		System.out.println(text);
//		return text;
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
	@FXML
	private void quayLai(ActionEvent e) throws IOException {
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/PhieuNhap.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
	}
	@FXML
	public void add(ActionEvent e) throws SQLException, Exception{
//		String nccSQL = "select * from NhaCungCap where tenNCC = '"+ cbbNCC.getSelectionModel().getSelectedItem().toString()+"'";
//		String pnSql = "insert into PhieuNhap(maNV, ngayNhap, maTuThuoc, maNCC) values(?,?,1,?) ";
//		ps = con.prepareStatement(nccSQL);
//		rs = ps.executeQuery();
//		while(rs.next()) {
//			int maNCC = rs.getInt("maNCC");
//			LocalDate ld = dpNgayNhap.getValue();
//			Date date = Date.valueOf(ld);
//			ps = con.prepareStatement(pnSql);
//			ps.setInt(1, dnc.getMaNV());
//			ps.setDate(2, date);
//			ps.setInt(3, maNCC);
//			ps.execute();
//		}
//		getTable();
//		for(CTPhieuNhap c: table.getItems()) {
//			System.out.println(c.getTenThuoc());
//		}
//		ObservableList<CTPhieuNhap> ctpn;
//		ctpn= table.getSelectionModel().getSelectedItems();

		
	}


//		String sql = "insert into CTPhieuNhap(maPN, maThuoc) values(?,?)";
//		try {
////				String maPN = lblMaPN.getText();
////				System.out.println(maPN);
////				int ma = Integer.parseInt(maPN);
////				System.out.println(ma);
//				String maThuoc = txtMaThuoc.getText();
//				ps = con.prepareStatement(sql);
////				ps.setInt(1, ma);
//				ps.setString(2, maThuoc);
//				ps.execute();	
//
//		} catch (Exception e2) {
//			// TODO: handle exception
//			e2.printStackTrace();
//		}
//		reload();
//	public boolean them() {
//		return true;
//	}
	@FXML
	public void themPN(ActionEvent e) throws SQLException{
		int maNCC = maNCC();

		if(dpNgayNhap.getValue()!=null) {
			System.out.println(dpNgayNhap.getValue());
			String sql = "insert into PhieuNhap(maNV, ngayNhap, maNCC) values (?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, dnc.getMaNV());
			LocalDate ld = dpNgayNhap.getValue();
			Date date = Date.valueOf(ld);
			ps.setDate(2, date);
			ps.setInt(3, maNCC);
			ps.execute();
			maPN();
	}	
	}
	
	public int maNCC() throws SQLException {
		String ncc = cbbNCC.getSelectionModel().getSelectedItem().toString();
		String sql = "select * from NhaCungCap where tenNCC = N'"+ncc+"'";
		int maNCC = 0;
		ps=con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			maNCC = rs.getInt("maNCC");
		}
		return maNCC;
	}
	public int maPN() throws SQLException {
		String pn = "select MAX(maPN) as m from PhieuNhap";
		ps=con.prepareStatement(pn);
		rs=ps.executeQuery();
		int maPN = 0;
		while(rs.next())
		maPN = rs.getInt("m");
		String maPNS = String.valueOf(maPN);
		lblMaPN.setText(maPNS);
		return maPN;
	}
	public int maCTPN() throws SQLException {
		int maCTPN = 0;
		String maCt = "select MAX(maCTPN) as mact from CTPhieuNhap";
		ps = con.prepareStatement(maCt);
		rs=ps.executeQuery();
		while(rs.next()) 
		maCTPN = rs.getInt("mact");
		return maCTPN;
	}
	public int maThuoc() throws SQLException {
		int maThuoc = 0;
		String mThuoc = "select * from Thuoc where tenThuoc = N'"+cbbThuoc.getSelectionModel().getSelectedItem().toString()+"'";
		ps = con.prepareStatement(mThuoc);
		rs = ps.executeQuery();
		while(rs.next()) 
		maThuoc = rs.getInt("maThuoc");
		return maThuoc;
	}

	public void insert(ActionEvent e) throws SQLException {

//		LocalDate nsx = dpNSX.getValue();
//		Date dnsx = Date.valueOf(nsx);

			LocalDate hsd = dpHSD.getValue();
			Date dhsd = Date.valueOf(hsd);
			int mapn = maPN();
			float gb = Float.parseFloat(txtGiaBan.getText());
			float gn = Float.parseFloat(txtGiaNhap.getText());
			int mt = Integer.parseInt(txtMaThuoc.getText());
			int sl = Integer.parseInt(txtSoLuong.getText());
//			float tonggianhap = gn * sl;
//			float tonggiaban = gb * sl;
//			ctpn = new CTPhieuNhap(mt,sl, String.valueOf(cbbThuoc.getSelectionModel().getSelectedItem().toString()),txtLoaiThuoc.getText(),txtDonViTinh.getText(),txtNSX.getText(),gn,gb,sl,null, null, null, null, tonggianhap,tonggiaban);
//			ctpn.setMaPN(mapn);
//			ctpn.setMaThuoc(mt);
//			ctpn.setTenThuoc(cbbThuoc.getSelectionModel().getSelectedItem().toString());
//			ctpn.setGiaNhap(gn);
//			ctpn.setGiaBan(gb);
//			ctpn.setSl(sl);
			ctpn.setTongGiaNhap(gn * sl);
			ctpn.setTongGiaBan(gb * sl);
			
//			pn.setHoTen(dnc.getHoTen());
//			System.out.println(pn.getHoTen());
			int maCTPN = maCTPN();
			int mThuoc = maThuoc();
			String tt = cbbTrangThai.getValue();
			
			String sql = "insert into CTPhieuNhap(maPN, maCTPN, maThuoc, giaNhap, giaBan, soLuong, ghiChu, soLo, hanSuDung, tongGiaNhap, tongGiaBan, trangThai ) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mapn);
			ps.setInt(2, maCTPN + 1);
			ps.setInt(3, mThuoc);
			ps.setFloat(4, gn);
			ps.setFloat(5, gb);
			ps.setInt(6, sl);
			ps.setString(7, taGhiChu.getText());
			ps.setString(8, txtSoLo.getText());
			ps.setDate(9, dhsd);
			ps.setFloat(10, gn * sl);
			ps.setFloat(11, gb * sl);
			ps.setString(12, tt);
			ps.execute();
			getAllCTPN(mapn);
			tinhTong(mapn);
			String kho = "select * from Tu where maThuoc = '"+mThuoc+"' and giaNhap ='"+gn+ "'and giaBan = '"+gb+"' and soLo='"+txtSoLo.getText()+"' and hanSuDung = '" +dhsd+ "'";
			ps = con.prepareStatement(kho);
			rs = ps.executeQuery();
			Kho k = new Kho();
			ObservableList<Kho> khoList = FXCollections.observableArrayList();
			String maTh = String.valueOf(mThuoc);
			String gNhap = String.valueOf(gn);
			String gBan = String.valueOf(gb);
			while(rs.next()) {
				k.setMaThuoc(rs.getInt("maThuoc"));
//				k.setTenThuoc(rs.getString("tenThuoc"));
				k.setGiaNhap(rs.getFloat("giaNhap"));
				k.setGiaBan(rs.getFloat("giaBan"));
				k.setSlTonKho(rs.getInt("slTonKho"));
				k.setSoLo(rs.getString("soLo"));
				k.setHanSuDung(rs.getDate("hanSuDung"));
				khoList.add(k);
				int tongsl = rs.getInt("slTonKho") + sl;
				System.out.println(gNhap.equals(String.valueOf(k.getGiaNhap())));
				String maThuocS = String.valueOf(k.getMaThuoc());
				System.out.println(maTh);
				System.out.println(maThuocS);
//				&&dnsx.equals(k.getNgaySanXuat())  &&dhsd.equals(k.getHanSuDung())
				if(maTh.equals(String.valueOf(k.getMaThuoc()))&& gNhap.equals(String.valueOf(k.getGiaNhap()))&&gBan.equals(String.valueOf(k.getGiaBan()))) {
					String themSl = "update Tu set slTonKho = '"+tongsl+"' where maThuoc ='"+mThuoc+"'";
					ps = con.prepareStatement(themSl);
					ps.execute();
					System.out.println(themSl);
				}
			}
//				if(maTh.equals(String.valueOf(k.getMaThuoc()))|| gNhap.equals(String.valueOf(k.getGiaNhap()))|| gBan.equals(String.valueOf(k.getGiaBan()))){
//				
//			}
			
//			String kho1 = "select * from Tu where maThuoc = '"+mThuoc+"' and giaNhap ='"+gn+ "'and giaBan = '"+gb+"' and ngaySanXuat='"+dnsx+"' and hanSuDung = '" +dhsd+ "'";

				String nhapKho = "insert into Tu(maThuoc, slTonKho, giaNhap, giaBan, soLo, hanSuDung, maPN) values (?,?,?,?,?,?,?)";
				ps = con.prepareStatement(nhapKho);
				ps.setInt(1, mThuoc);
				ps.setInt(2, sl);
				ps.setFloat(3, gn);
				ps.setFloat(4,gb);
				ps.setString(5, txtSoLo.getText());
				ps.setDate(6, dhsd);
				ps.setInt(7, mapn);
				ps.execute();
				
				String count = "SELECT COUNT(*) as maThuoc FROM Tu GROUP BY maThuoc, giaNhap, giaBan, soLo, hanSuDung HAVING COUNT(*) > 1";
				ps = con.prepareStatement(count);
				rs = ps.executeQuery();
				while(rs.next()) {
					int c = rs.getInt("maThuoc");
					if(c > 1) {
						String drop = "delete from Tu where maTu = (select max(maTu) from Tu)";
						ps = con.prepareStatement(drop);
						ps.execute();
						System.out.println(drop);
					}
		}
		
			}
//			String reset = "DBCC CHECKIDENT('Tu', RESEED, 0)";
//			ps = con.prepareStatement(reset);
//			ps.execute();

	public void tinhTong(int mapn) throws SQLException {
		String quydoi = "SELECT * FROM CTPhieuNhap\r\n"
				+ "WHERE maCTPN = (SELECT MAX(maCTPN) FROM CTPhieuNhap)";
		ps = con.prepareStatement(quydoi);
		rs = ps.executeQuery();
		while(rs.next()) {
			float giaNhap = rs.getFloat("giaNhap");
			float giaBan = rs.getFloat("giaBan");
			int soLuong = rs.getInt("soLuong");
			lblGiaNhapQuyDoi.setText(String.valueOf(giaNhap * soLuong + " đồng"));
			lblGiaBanQuyDoi.setText(String.valueOf(giaBan * soLuong + " đồng"));
		}
		String tong = "select sum(tongGiaNhap) as gn, sum(tongGiaBan) as gb from CTPhieuNhap where maPN = '"+mapn+"'";
		ps = con.prepareStatement(tong);
		rs = ps.executeQuery();
		while(rs.next()) {
			float tongGiaNhap = rs.getFloat("gn");
			float tongGiaBan = rs.getFloat("gb");
			lblTongGiaNhap.setText(String.valueOf(tongGiaNhap + " đồng"));
			lblTongGiaBan.setText(String.valueOf(tongGiaBan + " đồng"));
		}
	}
//
//	public void giaQuyDoi() {
//		float gb = 0f;
//		float gn = 0f;
//		float sl = 1f;
//		float tgn = 0f;
//		float gianhapquydoi = 0f;
//		float giabanquydoi = 0f;
//		if(txtGiaBan.getText()!=null || txtGiaNhap.getText()!=null) {
//			gn = Float.parseFloat(txtGiaNhap.getText());
//			gb = Float.parseFloat(txtGiaBan.getText());
//			sl = Float.parseFloat(txtSoLuong.getText());
//			gianhapquydoi = gn * sl;
//			giabanquydoi = gb * sl;
//			tgn = gn * sl;
//			lblGiaNhapQuyDoi.setText(String.valueOf(gianhapquydoi) + " đồng");
//			lblGiaBanQuyDoi.setText(String.valueOf(giabanquydoi) + " đồng");
//			lblTongGiaNhap.setText(String.valueOf(tgn) + " đồng");
//	}
//	}
	public void cell() {
		tenThuoc.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Integer>("tenThuoc"));
		dvt.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, String>("donViTinh"));
		giaNhap.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Float>("giaNhap"));
		giaBan.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Float>("giaBan"));
		soLuong.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Integer>("sl"));
		hsd.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Date>("hanSuDung"));
		tongGiaNhap.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Float>("tongGiaNhap"));
		tongGiaBan.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Float>("tongGiaBan"));
	}
	public void reload() {
//		list = getAllCTPN();
		cell();
//		table.setItems(list);
	}
	public ObservableList<CTPhieuNhap> getAllCTPN(int mapn) throws SQLException{
//		String maPN = lblMaPN.getText();
//		int ma = Integer.parseInt(maPN);
//		int text = Integer.parseInt(i);
////	int i = p.getMaPN();
		ObservableList<CTPhieuNhap> pnlist = FXCollections.observableArrayList();
//		String query = "select * from CTPhieuNhap";
		String getCTPN = "select * from CTPhieuNhap ct left join PhieuNhap p on p.maPN = ct.maPN inner join Thuoc t on t.maThuoc = ct.maThuoc where p.maPN = '"+mapn+"'";
		ps = con.prepareStatement(getCTPN);
		rs = ps.executeQuery();
		while(rs.next()) {
			ctpn.setTenThuoc(rs.getString("tenThuoc"));
			ctpn.setDonViTinh(rs.getString("donViTinh"));
			ctpn.setGiaNhap(rs.getFloat("giaNhap"));
			ctpn.setGiaBan(rs.getFloat("giaBan"));
			ctpn.setSl(rs.getInt("soLuong"));
			ctpn.setHanSuDung(rs.getDate("hanSuDung"));
			pnlist.add(ctpn);
			table.setItems(pnlist);
			}
		return pnlist;
	}
	public void getAllTenThuoc(){
		
		String sql = "select * from Thuoc order by tenThuoc";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			ObservableList thuocList = FXCollections.observableArrayList();
			while(rs.next()) {
				Thuoc t = new Thuoc();
				String ls = rs.getString("tenThuoc");
				thuocList.add(ls);
			}
			cbbThuoc.setItems(thuocList);
//			
//			int selectedItem = cbbThuoc.getSelectionModel().getSelectedIndex() + 1;

			
			if(cbbThuoc.getValue() != null) {
				Thuoc t = new Thuoc();
				String get = cbbThuoc.getSelectionModel().getSelectedItem().toString();
				String getIndex = "select * from Thuoc where tenThuoc = N'" + get +"'";
				ps = con.prepareStatement(getIndex);
				rs= ps.executeQuery();
				while(rs.next()) {
					t.setMaThuoc(rs.getInt("maThuoc"));
					t.setTenThuoc(rs.getString("tenThuoc"));
					System.out.println(t.getMaThuoc());
				}
				String q1 = "select * from Thuoc t inner join LoaiThuoc lt on t.maLoaiThuoc = lt.maLoaiThuoc"
						+ " where maThuoc = " + t.getMaThuoc() +"";
				try {
					ObservableList selectThuoc = FXCollections.observableArrayList();
					ps = con.prepareStatement(q1);
					rs = ps.executeQuery();
					while(rs.next()) {
						String mt = String.valueOf(rs.getInt("maThuoc"));
						String lt = rs.getString("tenLoaiThuoc");
						String tt = rs.getString("tenThuoc");
						String dvt = rs.getString("donViTinh");
						String gn = String.valueOf(rs.getFloat("giaNhap"));
						String gb = String.valueOf(rs.getFloat("giaBan"));
						String nsx = rs.getString("nuocSanXuat");
						txtMaThuoc.setText(mt);
						txtDonViTinh.setText(dvt);
						txtLoaiThuoc.setText(lt);
						txtGiaNhap.setText(gn);
						txtGiaBan.setText(gb);
//						txtSoLuong.getText();
						txtNSX.setText(nsx);
						String giaQD = String.valueOf(Float.parseFloat(txtGiaBan.getText()) * Integer.parseInt(txtSoLuong.getText()));
						selectThuoc.add(mt);

					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void getAllNCC() {
	String sql = "select * from NhaCungCap";
	try {
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		ObservableList nccList = FXCollections.observableArrayList();
		while(rs.next()) {
			String ncc = rs.getString("tenNCC");
			nccList.add(ncc);
		}
		cbbNCC.setItems(nccList);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
//	@FXML
//	private void searchByMaPN() {
//		txtTimKiem.setOnKeyReleased(e->{
//			if(txtTimKiem.getText().equals("")) {
//				reload();
//			}
//			else {
//				list.clear();
//				String sql = " select * from CTPhieuNhap where maPN like N'%" + txtTimKiem.getText() +"%'";
//				try {
//					ps = con.prepareStatement(sql);
//					rs = ps.executeQuery();
//					while(rs.next()) {
//						CTPhieuNhap nv = new CTPhieuNhap();
//						nv.setMaPN(rs.getInt("maPN"));
//						nv.setMaThuoc(rs.getInt("maThuoc"));
//						
//						list.add(nv);
//						table.setItems(list);
//					}
//				}catch (Exception e1) {
//					// TODO: handle exception
//				}
//			}
//		});
//	}
}
