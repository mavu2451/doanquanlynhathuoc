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
import entity.CTThuoc;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CTPhieuNhapNVController implements Initializable{
	PhieuNhap pn = new PhieuNhap();
	CTPhieuNhap ctpn = new CTPhieuNhap();
	ObservableList<CTPhieuNhap> ct = FXCollections.observableArrayList();
	ObservableList<CTPhieuNhap> ctpnList = FXCollections.observableArrayList();
	int maPN;
	@FXML
	private Button btnNgayNhap,btnThemThuoc;
	@FXML
	private DatePicker dpNgayNhap, dpHSD;
	@FXML
	private TextField txtSoLuong, txtMaThuoc, txtTimKiem, txtNSX, txtNCC, txtLoaiThuoc, txtDonViTinh,txtGiaNhap,txtGiaBan, txtSoLo,txtTenThuoc;
	@FXML
	private TextArea taGhiChu;
	@FXML
	private ComboBox<?> cbbNCC;
	@FXML
	TableView<CTPhieuNhap> table;
	@FXML 
	Label lblName, lblNV,lblGiaNhapQuyDoi, lblGiaBanQuyDoi, lblTongGiaNhap, lblTongGiaBan, lblMaPN;
	@FXML
	private MenuButton mb;
	public NhanVien dnc = DangNhapController.getNV();
	@FXML
	private TableColumn<CTPhieuNhap, Integer> maThuoc; 
	@FXML
	private TableColumn<CTPhieuNhap, String> tenThuoc;
	@FXML
	private TableColumn<CTPhieuNhap, String> dvt;
	@FXML
	private TableColumn<CTPhieuNhap, Float> giaNhap;
	@FXML
	private TableColumn<CTPhieuNhap, Float> giaBan;
	@FXML
	private TableColumn<CTPhieuNhap, Integer> soLuong;
	@FXML
	private TableColumn<CTPhieuNhap, String> soLo;
	@FXML
	private TableColumn<CTPhieuNhap, Date> hsd;
	@FXML
	private TableColumn<CTPhieuNhap, Float> tongGiaNhap;
	@FXML
	private TableColumn<CTPhieuNhap, Float> tongGiaBan;
	@FXML
	private ComboBox<String> cbbTrangThai;
	@FXML
	private TableColumn<CTPhieuNhap, String> trangThai;
	ObservableList<CTPhieuNhap> pnlist = FXCollections.observableArrayList();
	public PhieuNhap p;
	ActionEvent e;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	ObservableList<CTPhieuNhap> list = FXCollections.observableArrayList();
	String[] trangThaiList = {"Lưu tạm","Đã nhập hàng"};
	//new CTPhieuNhap(1,1,"test","test","test","test",10000,20000,2,new Date(100000),new Date(200000),"test","test",20000,40000)
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dpNgayNhap.setValue(LocalDate.now());
		cbbTrangThai.getItems().addAll(trangThaiList);
		cbbTrangThai.getSelectionModel().selectFirst();
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
//		getAllTenThuoc();
		getAllNCC();
		String t = "select * from PhieuNhap";
//		ps=con.prepareStatement(t);
//		rs=ps.executeQuery();
//		while(rs.next()) {
//			
//		}
//		table.setItems();
//		
		btnThemThuoc.setOnAction(args ->{
			ObservableList<Thuoc> thuocList = FXCollections.observableArrayList();
			BorderPane root = new BorderPane();
			ScrollPane scroll = new ScrollPane();
			TextField txtTimKiem = new TextField();
			Label lblTimKiem = new Label("Tìm kiếm tên thuốc");
			Button chon = new Button("Chọn sản phẩm");

			HBox h1 = new HBox(2);
			HBox h2 = new HBox(1);
			AnchorPane a1 = new AnchorPane();
			Stage stage = new Stage();
;			TableView tableView = new TableView<Thuoc>();
			TableColumn maThuoc = new TableColumn<Thuoc, Integer>("Mã thuốc");
			maThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, Integer>("maThuoc"));
			TableColumn tenThuoc = new TableColumn<Thuoc, String>("Tên thuốc");
			tenThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("tenThuoc"));
			TableColumn loaithuoc = new TableColumn<Thuoc, String>("Loại thuốc");
			loaithuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("loaiThuoc"));
			TableColumn donViTinh = new TableColumn<Thuoc, String>("Đơn vị tính");
			donViTinh.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("dvt"));
			TableColumn giaNhap = new TableColumn<Thuoc, Float>("Giá bán");
			giaNhap.setCellValueFactory(new PropertyValueFactory<Thuoc, Float>("giaNhap"));
			TableColumn giaBan = new TableColumn<Thuoc, Float>("Giá bán");
			giaBan.setCellValueFactory(new PropertyValueFactory<Thuoc, Float>("giaBan"));
			TableColumn trangThai = new TableColumn<Thuoc, Float>("Trạng thái");
			trangThai.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("trangThai"));
//			TableColumn soLo = new TableColumn<Kho, String>("Số lô");
//			soLo.setCellValueFactory(new PropertyValueFactory<Kho, String>("soLo"));
//			TableColumn hanSuDung = new TableColumn<Kho, Date>("Hạn sử dụng");	
//			hanSuDung.setCellValueFactory(new PropertyValueFactory<Kho, Date>("hanSuDung"));
			
			tableView.getColumns().add(maThuoc);
			tableView.getColumns().add(tenThuoc);
			tableView.getColumns().add(loaithuoc);
			tableView.getColumns().add(donViTinh);
			tableView.getColumns().add(giaNhap);
			tableView.getColumns().add(giaBan);
			tableView.getColumns().add(trangThai);
//			tableView.getColumns().add(soLo);
//			tableView.getColumns().add(hanSuDung);
			a1.getChildren().addAll(chon);
			root.getChildren().addAll(a1);
			chon.setLayoutX(200);
			root.setCenter(scroll);
			scroll.setContent(tableView);
			h1.getChildren().addAll( lblTimKiem, txtTimKiem);
			h2.getChildren().addAll( chon);
			root.setTop(h1);
			root.setBottom(h2);
			tableView.setPrefWidth(700);
			
			String thuoc = "select * from Thuoc t left join LoaiThuoc lt on t.maLoaiThuoc = lt.maLoaiThuoc where trangThai = N'Đang kinh doanh'";

			try {
				ps = con.prepareStatement(thuoc);
				rs = ps.executeQuery();
				while(rs.next()) {
					Thuoc th = new Thuoc();
					th.setMaThuoc(rs.getInt("maThuoc"));
					th.setTenThuoc(rs.getString("tenThuoc"));
					th.setLoaiThuoc(rs.getString("tenLoaiThuoc"));
					th.setDvt(rs.getString("donViTinh"));
					th.setNsx(rs.getString("nuocSanXuat"));
					th.setQuyCachDongGoi(rs.getString("quyCachDongGoi"));
					th.setCachDung(rs.getString("cachDung"));
					th.setGiaNhap(rs.getFloat("giaNhap"));
					th.setGiaBan(rs.getFloat("giaBan"));
					th.setTrangThai(rs.getString("trangThai"));
					thuocList.add(th);
					tableView.setItems(thuocList);
					chon.setOnAction(arg2->{
						int index = tableView.getSelectionModel().getSelectedIndex();
						if(index<=-1) {
							return;
						}
						else {
							String sqlMaThuoc = String.valueOf(maThuoc.getCellData(index).toString());
							String sqlTenThuoc = String.valueOf(tenThuoc.getCellData(index).toString());
							String select = "select * from Thuoc t left join LoaiThuoc lt on t.maLoaiThuoc = lt.maLoaiThuoc where t.maThuoc = '"+sqlMaThuoc+"'";
							try {
								ps = con.prepareStatement(select);
								rs = ps.executeQuery();
								while(rs.next()) {
									Thuoc h = new Thuoc();
									txtMaThuoc.setText(String.valueOf(rs.getInt("maThuoc")));
									txtTenThuoc.setText(rs.getString("tenThuoc"));
									txtLoaiThuoc.setText(rs.getString("tenLoaiThuoc"));
									txtDonViTinh.setText(rs.getString("donViTinh"));
									txtNSX.setText(rs.getString("nuocSanXuat"));
									txtGiaNhap.setText(String.valueOf(rs.getFloat("giaNhap")));
									txtGiaBan.setText(String.valueOf(rs.getFloat("giaBan")));
									stage.close();
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

			Scene scene = new Scene(root,700,300);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		});
		
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
	public void themPN(ActionEvent e) {
		try {

		if(table.getItems().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Không có thông tin");
			alert.showAndWait();
		}
		else if(dpNgayNhap.getValue()!=null) {
			int maNCC;
			maNCC = maNCC();
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
			int maPN = maPN();
			for(int i = 0; i < table.getItems().size();i++) {
				String sql1 = "insert into CTPhieuNhap(maPN, maThuoc, giaNhap, giaBan, soLuong, soLo, hanSuDung, tongGiaNhap, tongGiaBan, trangThai ) values(?,?,?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(sql1);
				int mThuoc = maThuoc.getCellData(i);
				float gn = giaNhap.getCellData(i);
				float gb = giaBan.getCellData(i);
				int sl = soLuong.getCellData(i);
				Date dhsd = hsd.getCellData(i);
				ps.setInt(1, maPN);
				ps.setInt(2, mThuoc);
				ps.setFloat(3, gn);
				ps.setFloat(4, gb);
				ps.setInt(5, sl);
				ps.setString(6, soLo.getCellData(i));
				ps.setDate(7, dhsd);
				ps.setFloat(8, tongGiaNhap.getCellData(i));
				ps.setFloat(9, tongGiaBan.getCellData(i));
				ps.setString(10, trangThai.getCellData(i));
				ps.execute();
				if(trangThai.getCellData(i).contains("Lưu tạm")) {
					luuThanhCong();
				}
				else {
				String ctt1 = "select * from CTThuoc where maThuoc = "+mThuoc+" and giaNhap ='"+gn+ "'and giaBan = '"+gb+"' and hanSuDung = '"+dhsd+"'";
				System.out.println(ctt1);
				PreparedStatement ps2 = con.prepareStatement(ctt1);
				ResultSet rs2 = ps2.executeQuery();
				System.out.println(rs2);
				CTThuoc k = new CTThuoc();
				ObservableList<CTThuoc> khoList = FXCollections.observableArrayList();
				String maTh = String.valueOf(mThuoc);
				String gNhap = String.valueOf(gn);
				String gBan = String.valueOf(gb);
				String hs = String.valueOf(dhsd);
				int count = 0;
				while(rs2.next()) {
					k.setMaThuoc(rs2.getInt("maThuoc"));
//					k.setTenThuoc(rs.getString("tenThuoc"));
					k.setGiaNhap(rs2.getFloat("giaNhap"));
					k.setGiaBan(rs2.getFloat("giaBan"));
					k.setSlTonKho(rs2.getInt("soLuongCon"));
					k.setHanSuDung(rs2.getDate("hanSuDung"));
					khoList.add(k);
					int tongsl = rs2.getInt("soLuongCon") + sl;

					System.out.println(tongsl);
					System.out.println("han su dung nhap: " + hs);
					System.out.println("han su dung trong db: " + k.getHanSuDung());
//					String maThuocS = String.valueOf(k.getMaThuoc());
//					&&dnsx.equals(k.getNgaySanXuat())  &&dhsd.equals(k.getHanSuDung())
					if(maTh.equals(String.valueOf(k.getMaThuoc()))&& gNhap.equals(String.valueOf(k.getGiaNhap()))&&gBan.equals(String.valueOf(k.getGiaBan()))&& hs.equals(String.valueOf(k.getHanSuDung()))) {
						String themSl = "update CTThuoc set soLuongCon = '"+tongsl+"' where maThuoc ='"+mThuoc+"' and hanSuDung = '"+dhsd+"'";
						System.out.println(themSl);
						ps = con.prepareStatement(themSl);
						ps.execute();
					}
					count++;
				}if(count < 1) {
					String nhapThuoc = "insert into CTThuoc(maThuoc, soLuongCon, giaNhap, giaBan, hanSuDung) values (?,?,?,?,?)";
					PreparedStatement ps1 = con.prepareStatement(nhapThuoc);
					System.out.println(nhapThuoc);
					ps1.setInt(1, mThuoc);
					ps1.setInt(2, sl);
					ps1.setFloat(3, gn);
					ps1.setFloat(4,gb);
//			ps1.setString(5, txtSoLo.getText());
					ps1.setDate(5, dhsd);
//			ps.setInt(7, mapn);
					ps1.execute();
			}
				
				}
				luuThanhCong1();
			}

			table.getItems().clear();
			}	
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Thuốc đã có sẳn, bạn hãy xoá thông tin thuốc đó trước");
			alert.showAndWait();
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
	
//	public int maThuoc() throws SQLException {
//		int maThuoc = 0;
//		String mThuoc = "select * from Thuoc where tenThuoc = N'"+cbbThuoc.getSelectionModel().getSelectedItem().toString()+"'";
//		ps = con.prepareStatement(mThuoc);
//		rs = ps.executeQuery();
//		while(rs.next()) 
//		maThuoc = rs.getInt("maThuoc");
//		return maThuoc;
//	}

	float tgn = 0;
	float tgb = 0;
		public void insert(ActionEvent e) {
			float gn = Float.parseFloat(txtGiaNhap.getText());
			float gb = Float.parseFloat(txtGiaBan.getText());
			int sl = Integer.parseInt(txtSoLuong.getText());
			float tong = 0;
			CTPhieuNhap ct = new CTPhieuNhap();
			ct.setMaThuoc(Integer.parseInt(txtMaThuoc.getText()));
			ct.setTenThuoc(txtTenThuoc.getText());
			ct.setDonViTinh(txtDonViTinh.getText());
//			ct.setLoaiThuoc(txtLoaiThuoc.getText());
			ct.setGiaNhap(gn);
			ct.setGiaBan(gb);
			ct.setSoLo(txtSoLo.getText());
			ct.setHanSuDung(Date.valueOf(dpHSD.getValue()));
			ct.setSl(sl);
			ct.setTongGiaNhap(gn * sl);
			ct.setTongGiaBan(gb * sl);
			ct.setTrangThai(cbbTrangThai.getValue());
			lblGiaNhapQuyDoi.setText(String.valueOf(gn * sl));
			lblGiaBanQuyDoi.setText(String.valueOf(gb* sl)); 
			tgn = tgn + (gn* sl);
			tgb = tgb + (gb* sl);
			lblTongGiaNhap.setText(String.valueOf(tgn));
			lblTongGiaBan.setText(String.valueOf(tgb));
			list.add(ct);
			table.setItems(list);

		}
		public void remove(ActionEvent e) {
			int select = table.getSelectionModel().getSelectedIndex();
			table.getItems().remove(select);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("Thuốc đã được xoá");
			alert.showAndWait();
		}
		
			
//			String reset = "DBCC CHECKIDENT('Tu', RESEED, 0)";
//			ps = con.prepareStatement(reset);
//			ps.execute();

	public void tinhTong(int mapn) throws SQLException {
		String quydoi = "SELECT * FROM CTPhieuNhap\r\n"
				+ "WHERE maPN = (SELECT MAX(maPN) FROM CTPhieuNhap)";
		ps = con.prepareStatement(quydoi);
		rs = ps.executeQuery();
		while(rs.next()) {
			float giaNhap = rs.getFloat("giaNhap");
			float giaBan = rs.getFloat("giaBan");
			int soLuong = rs.getInt("soLuong");
			lblGiaNhapQuyDoi.setText(String.valueOf(giaNhap * soLuong + " đồng"));
			lblGiaBanQuyDoi.setText(String.valueOf(giaBan * soLuong + " đồng"));
		}
		String tong = "select sum(GiaNhap * soLuong) as gn, sum(GiaBan * soLuong) as gb from CTPhieuNhap where maPN = '"+mapn+"'";
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
		maThuoc.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Integer>("maThuoc"));
		tenThuoc.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, String>("tenThuoc"));
		dvt.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, String>("donViTinh"));
		giaNhap.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Float>("giaNhap"));
		giaBan.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Float>("giaBan"));
		soLuong.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Integer>("sl"));
		soLo.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, String>("soLo"));
		hsd.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Date>("hanSuDung"));
		tongGiaNhap.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Float>("tongGiaNhap"));
		tongGiaBan.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Float>("tongGiaBan"));
		trangThai.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, String>("trangThai"));
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
		pnlist.clear();
		table.setItems(null);
//		String query = "select * from CTPhieuNhap";
		String getCTPN = "select * from CTPhieuNhap ct left join PhieuNhap p on p.maPN = ct.maPN inner join Thuoc t on t.maThuoc = ct.maThuoc where p.maPN = '"+mapn+"'";
		ps = con.prepareStatement(getCTPN);
		rs = ps.executeQuery();
		while(rs.next()) {
			ctpn.setMaThuoc(rs.getInt("maThuoc"));
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
//	public void getAllTenThuoc(){
//		
//		String sql = "select * from Thuoc order by tenThuoc";
//		
//		try {
//			ps = con.prepareStatement(sql);
//			rs = ps.executeQuery();
//			ObservableList thuocList = FXCollections.observableArrayList();
//			while(rs.next()) {
//				Thuoc t = new Thuoc();
//				String ls = rs.getString("tenThuoc");
//				thuocList.add(ls);
//			}
//			cbbThuoc.setItems(thuocList);
////			
////			int selectedItem = cbbThuoc.getSelectionModel().getSelectedIndex() + 1;
//
//			
//			if(cbbThuoc.getValue() != null) {
//				Thuoc t = new Thuoc();
//				String get = cbbThuoc.getSelectionModel().getSelectedItem().toString();
//				String getIndex = "select * from Thuoc where tenThuoc = N'" + get +"'";
//				ps = con.prepareStatement(getIndex);
//				rs= ps.executeQuery();
//				while(rs.next()) {
//					t.setMaThuoc(rs.getInt("maThuoc"));
//					t.setTenThuoc(rs.getString("tenThuoc"));
//					System.out.println(t.getMaThuoc());
//				}
//				String q1 = "select * from Thuoc t inner join LoaiThuoc lt on t.maLoaiThuoc = lt.maLoaiThuoc"
//						+ " where maThuoc = " + t.getMaThuoc() +"";
//				try {
//					ObservableList selectThuoc = FXCollections.observableArrayList();
//					ps = con.prepareStatement(q1);
//					rs = ps.executeQuery();
//					while(rs.next()) {
//						String mt = String.valueOf(rs.getInt("maThuoc"));
//						String lt = rs.getString("tenLoaiThuoc");
//						String tt = rs.getString("tenThuoc");
//						String dvt = rs.getString("donViTinh");
//						String gn = String.valueOf(rs.getFloat("giaNhap"));
//						String gb = String.valueOf(rs.getFloat("giaBan"));
//						String nsx = rs.getString("nuocSanXuat");
//						txtMaThuoc.setText(mt);
//						txtDonViTinh.setText(dvt);
//						txtLoaiThuoc.setText(lt);
//						txtGiaNhap.setText(gn);
//						txtGiaBan.setText(gb);
////						txtSoLuong.getText();
//						txtNSX.setText(nsx);
//						String giaQD = String.valueOf(Float.parseFloat(txtGiaBan.getText()) * Integer.parseInt(txtSoLuong.getText()));
//						selectThuoc.add(mt);
//
//					}
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	public void getAllNCC() {
	String sql = "select * from NhaCungCap where trangThai = N'Đang hợp tác'";
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
	public void luuThanhCong() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setContentText("Lưu thành công");
		alert.showAndWait();
	}
	public void luuThanhCong1() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setContentText("Lưu thành công, thuốc đã được mang vào kho");
		alert.showAndWait();
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
