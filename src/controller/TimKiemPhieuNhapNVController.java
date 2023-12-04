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
import entity.CTPhieuNhap;
import entity.CTThuoc;
import entity.NhanVien;
import entity.PhieuNhap;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TimKiemPhieuNhapNVController implements Initializable{
	@FXML
	private MenuButton mb;

	@FXML
	private ComboBox<String> cbbTrangThai;
	@FXML
	private TextField txtNCC, txtNhanVien, txtMaPN;
	@FXML
	private Button btnXemCT, btnTimKiem;
	@FXML
	private DatePicker dpNgayNhap;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	static ResultSet rs;
	@FXML
	Label lblName;

	@FXML
	TableView<PhieuNhap> table;
	@FXML
	private TableColumn<PhieuNhap, Integer> maPN;
	@FXML
	private TableColumn<PhieuNhap, String> hoTen;
	@FXML
	private TableColumn<PhieuNhap, Date> ngayNhap;
	@FXML
	private TableColumn<PhieuNhap, String> tenNCC;
	@FXML
	private TableColumn<PhieuNhap, String> trangThai;

	
	private ObservableList<PhieuNhap> list = FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//		getAllPN();
//		reload();
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
//		try {
//			while(rs.next()) {
//				lblName.setText("Xin chào, " + dnc.getHoTen());

		dpNgayNhap.setValue(LocalDate.now());
		reload();
		getAllPN();
		cell();
		btnXemCT.setOnAction(arg->{
			int index = table.getSelectionModel().getSelectedIndex();
			if(index<=-1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Mời bạn chọn phiếu nhập");
				alert.showAndWait();
			}
			else {
			String sqlMaPN = String.valueOf(maPN.getCellData(index).toString());
			ObservableList<Thuoc> thuocList = FXCollections.observableArrayList();
			BorderPane root = new BorderPane();
			ScrollPane scroll = new ScrollPane();

			Button chon = new Button("Đã nhập hàng");
			HBox h1 = new HBox(2);
			HBox h2 = new HBox(1);
			
			Stage stage = new Stage();
;			TableView tableView = new TableView<CTPhieuNhap>();
			TableColumn maThuoc = new TableColumn<CTPhieuNhap, Integer>("Mã thuốc");
			maThuoc.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Integer>("maThuoc"));
			TableColumn tenThuoc = new TableColumn<CTPhieuNhap, String>("Tên thuốc");
			tenThuoc.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, String>("tenThuoc"));
			TableColumn giaNhap = new TableColumn<CTPhieuNhap, Float>("Giá bán");
			giaNhap.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Float>("giaNhap"));
			TableColumn giaBan = new TableColumn<CTPhieuNhap, Float>("Giá bán");
			giaBan.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Float>("giaBan"));
			TableColumn soLuong = new TableColumn<CTPhieuNhap, Integer>("Số lượng nhập");
			soLuong.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Integer>("sl"));
			TableColumn tongGiaNhap = new TableColumn<CTPhieuNhap, Float>("Tổng giá nhập");
			tongGiaNhap.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Float>("tongGiaNhap"));
			TableColumn tongGiaBan = new TableColumn<CTPhieuNhap, Float>("Tổng giá bán");
			tongGiaBan.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Float>("tongGiaBan"));
			TableColumn soLo = new TableColumn<CTPhieuNhap, String>("Số lô");
			soLo.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, String>("soLo"));
			TableColumn hanSuDung = new TableColumn<CTPhieuNhap, Date>("Hạn sử dụng");
			hanSuDung.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Date>("hanSuDung"));
			TableColumn trangThai = new TableColumn<CTPhieuNhap, String>("Trạng thái");
			trangThai.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, String>("trangThai"));
			
			tableView.getColumns().add(maThuoc);
			tableView.getColumns().add(tenThuoc);
			tableView.getColumns().add(giaNhap);
			tableView.getColumns().add(giaBan);
			tableView.getColumns().add(soLuong);
			tableView.getColumns().add(tongGiaNhap);
			tableView.getColumns().add(tongGiaBan);
			tableView.getColumns().add(soLo);
			tableView.getColumns().add(hanSuDung);
			tableView.getColumns().add(trangThai);
			root.setCenter(scroll);
			scroll.setContent(tableView);
			h2.getChildren().addAll( chon);
			root.setTop(h1);
			root.setBottom(h2);
			String sql = "select * from CTPhieuNhap ct left join Thuoc t on t.maThuoc = ct.maThuoc where maPN = '"+sqlMaPN+"'";
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				ObservableList<CTPhieuNhap> ctList = FXCollections.observableArrayList();
				while(rs.next()) {
					CTPhieuNhap ctpn = new CTPhieuNhap();
					ctpn.setMaThuoc(rs.getInt("maThuoc"));
					ctpn.setTenThuoc(rs.getString("tenThuoc"));
					ctpn.setDonViTinh(rs.getString("donViTinh"));
					ctpn.setGiaNhap(rs.getFloat("giaNhap"));
					ctpn.setGiaBan(rs.getFloat("giaBan"));
					ctpn.setSl(rs.getInt("soLuong"));
					ctpn.setTongGiaNhap(rs.getFloat("tongGiaNhap"));
					ctpn.setTongGiaBan(rs.getFloat("tongGiaBan"));
					ctpn.setSoLo(rs.getString("soLo"));
					ctpn.setHanSuDung(rs.getDate("hanSuDung"));
					ctpn.setTrangThai(rs.getString("trangThai"));
					ctList.add(ctpn);
					tableView.setItems(ctList);
					chon.setOnAction(args ->{
						int indexCT = tableView.getSelectionModel().getSelectedIndex();
						String sqlTrangThai = String.valueOf(trangThai.getCellData(indexCT).toString());
						if(indexCT <= -1) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setHeaderText(null);
							alert.setContentText("Mời bạn chọn thông tin");
							alert.showAndWait();
						}
						else if(sqlTrangThai.equals("Đã nhập hàng")) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setHeaderText(null);
							alert.setContentText("Thuốc đã được nhập");
							alert.showAndWait();
						}
						else {
							int mThuoc = (int) maThuoc.getCellData(indexCT);
						String sqlMaThuoc = String.valueOf(maThuoc.getCellData(indexCT).toString());
//						int mThuoc = Integer.parseInt(tableView.getSelectionModel().getSelectedItem().toString());
						float gn = Float.parseFloat(giaNhap.getCellData(indexCT).toString());
						float gb = Float.parseFloat(giaBan.getCellData(indexCT).toString());
						String txtSoLo = String.valueOf(soLo.getCellData(indexCT).toString());
						Date dhsd = Date.valueOf(hanSuDung.getCellData(indexCT).toString());
						String update = "update CTPhieuNhap set trangThai = N'Đã nhập hàng' where maPN = '"+sqlMaPN+"'and maThuoc = '"+sqlMaThuoc+"'";
						try {
							ps = con.prepareStatement(update);
							ps.execute();
							Alert alert1 = new Alert(AlertType.INFORMATION);
							alert1.setHeaderText(null);
							alert1.setContentText("Cập nhật trạng thái thành công");
							alert1.showAndWait();
							String ctt = "select * from CTThuoc where maThuoc = '"+sqlMaThuoc+"' and giaNhap ='"+gn+ "'and giaBan = '"+gb+"' and hanSuDung = '" +dhsd+ "'";
							ps = con.prepareStatement(ctt);
							rs = ps.executeQuery();
							CTThuoc k = new CTThuoc();
							ObservableList<CTThuoc> khoList = FXCollections.observableArrayList();
							String maTh = String.valueOf(sqlMaThuoc);
							String gNhap = String.valueOf(gn);
							String gBan = String.valueOf(gb);
							int sl = Integer.parseInt(soLuong.getCellData(indexCT).toString());

//							String kho1 = "select * from CTThuoc where maThuoc = '"+mThuoc+"' and giaNhap ='"+gn+ "'and giaBan = '"+gb+"' soLo='"+1+"' and hanSuDung = '" +dhsd+ "'";

							String ctt1 = "select * from CTThuoc where maThuoc = "+sqlMaThuoc+" and giaNhap ='"+gn+ "'and giaBan = '"+gb+"' and hanSuDung = '"+dhsd+"'";
							System.out.println(ctt1);
							PreparedStatement ps2 = con.prepareStatement(ctt1);
							ResultSet rs2 = ps2.executeQuery();
							System.out.println(rs2);


							String hs = String.valueOf(dhsd);
							int count = 0;
							while(rs2.next()) {
								k.setMaThuoc(rs2.getInt("maThuoc"));
//								k.setTenThuoc(rs.getString("tenThuoc"));
								k.setGiaNhap(rs2.getFloat("giaNhap"));
								k.setGiaBan(rs2.getFloat("giaBan"));
								k.setSlTonKho(rs2.getInt("soLuongCon"));
								k.setHanSuDung(rs2.getDate("hanSuDung"));
								khoList.add(k);
								int tongsl = rs2.getInt("soLuongCon") + sl;

								System.out.println(tongsl);
								System.out.println("han su dung nhap: " + hs);
								System.out.println("han su dung trong db: " + k.getHanSuDung());
//								String maThuocS = String.valueOf(k.getMaThuoc());
//								&&dnsx.equals(k.getNgaySanXuat())  &&dhsd.equals(k.getHanSuDung())
								if(maTh.equals(String.valueOf(k.getMaThuoc()))&& gNhap.equals(String.valueOf(k.getGiaNhap()))&&gBan.equals(String.valueOf(k.getGiaBan()))&& hs.equals(String.valueOf(k.getHanSuDung()))) {
									String themSl = "update CTThuoc set soLuongCon = '"+tongsl+"' where maThuoc ='"+sqlMaThuoc+"' and hanSuDung = '"+dhsd+"'";
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
//						ps1.setString(5, txtSoLo.getText());
								ps1.setDate(5, dhsd);
//						ps.setInt(7, mapn);
								ps1.execute();
						}
								
									
							String sql1 = "select * from CTPhieuNhap ct left join Thuoc t on t.maThuoc = ct.maThuoc where maPN = '"+sqlMaPN+"'";
							ps = con.prepareStatement(sql1);
							rs = ps.executeQuery();
							ObservableList<CTPhieuNhap> ctList1 = FXCollections.observableArrayList();
							while(rs.next()) {
									CTPhieuNhap ctpn1 = new CTPhieuNhap();
									ctpn1.setMaThuoc(rs.getInt("maThuoc"));
									ctpn1.setTenThuoc(rs.getString("tenThuoc"));
									ctpn1.setDonViTinh(rs.getString("donViTinh"));
									ctpn1.setGiaNhap(rs.getFloat("giaNhap"));
									ctpn1.setGiaBan(rs.getFloat("giaBan"));
									ctpn1.setSl(rs.getInt("soLuong"));
									ctpn1.setTongGiaNhap(rs.getFloat("tongGiaNhap"));
									ctpn1.setTongGiaBan(rs.getFloat("tongGiaBan"));
									ctpn1.setSoLo(rs.getString("soLo"));
									ctpn1.setHanSuDung(rs.getDate("hanSuDung"));
									ctpn1.setTrangThai(rs.getString("trangThai"));
									ctList1.add(ctpn1);
									tableView.setItems(ctList1);
								
							
						}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
					});
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			Scene scene = new Scene(root,800,400);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			}
		});
		
		btnTimKiem.setOnAction(a -> {
			String maPN = txtMaPN.getText().toString();
			String tenNCC = txtNCC.getText().toString();
			String tenNV = txtNhanVien.getText().toString();

			LocalDate ldNgayNhap = dpNgayNhap.getValue();
			Date d = Date.valueOf(ldNgayNhap);
			
			if(maPN == "" && tenNCC == "" && tenNV == "" && dpNgayNhap.getValue()==null) {
				table.getItems().clear();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Thông báo");
				alert.setContentText("Không được để trống");
				alert.setHeaderText(null);
				alert.showAndWait();
				getAllPN();
			}
			else {
			table.getItems().clear();
			String sql = "select * from PhieuNhap pn left join NhanVien nv on nv.maNV = pn.maNV inner join NhaCungCap ncc on ncc.maNCC = pn.maNCC where maPN like N'%"+maPN+"%' and tenNCC like N'%"+tenNCC+"%' and tenNV like N'%"+tenNV+"%' and ngayNhap like '%"+d+"%'";
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					PhieuNhap pn = new PhieuNhap();
		    		  pn.setMaPN(rs.getInt("maPN"));
		    		  pn.setHoTen(rs.getString("tenNV"));
		    		  pn.setTenNCC(rs.getString("tenNCC"));
		    		  pn.setNgayNhap(rs.getDate("ngayNhap"));
		    		  pn.setTrangThai(rs.getString("trangThai"));
		    		  list.add(pn);
		    		  table.setItems(list);
		    	  
				}	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
	}
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
          loader.setLocation(getClass().getResource("/view/TimKiemDonDatThuocNV.fxml"));
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
      public ObservableList<PhieuNhap> getAllPN()  {
    	  cbbTrangThai.setItems(FXCollections.observableArrayList("Tất cả", "Đã nhập hàng", "Lưu tạm"));
    	  cbbTrangThai.getSelectionModel().selectFirst();
    	  String sql = "select distinct(ct.maPN) as maPN,tenNV,ngayNhap, tenNCC, ct.trangThai from PhieuNhap pn left join CTPhieuNhap ct on ct.maPN = pn.maPN inner join NhanVien nv on nv.maNV = pn.maNV inner join NhaCungCap ncc on ncc.maNCC = pn.maNCC where ct.trangThai is not null";
    	  try {
			ps = con.prepareStatement(sql);
	    	  rs = ps.executeQuery();
	    	  list.clear();
				table.setItems(list);
	    	  while(rs.next()) {
	    		  PhieuNhap pn = new PhieuNhap();
	    		  pn.setMaPN(rs.getInt("maPN"));
	    		  pn.setHoTen(rs.getString("tenNV"));
	    		  pn.setTenNCC(rs.getString("tenNCC"));
	    		  pn.setNgayNhap(rs.getDate("ngayNhap"));
	    		  pn.setTrangThai(rs.getString("trangThai"));
	    		  list.add(pn);
	    		  table.setItems(list);
	    	  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  cbbTrangThai.setOnAction(args->{
    			if(cbbTrangThai.getSelectionModel().getSelectedItem()=="Tất cả") {
    				 String sql1 = "select distinct(ct.maPN) as maPN,tenNV,ngayNhap, tenNCC, ct.trangThai from PhieuNhap pn left join CTPhieuNhap ct on ct.maPN = pn.maPN inner join NhanVien nv on nv.maNV = pn.maNV inner join NhaCungCap ncc on ncc.maNCC = pn.maNCC where ct.trangThai is not null";
    		    	  try {
    					ps = con.prepareStatement(sql1);
    			    	  rs = ps.executeQuery();
    			    	  list.clear();
    						table.setItems(list);
    			    	  while(rs.next()) {
    			    		  PhieuNhap pn = new PhieuNhap();
    			    		  pn.setMaPN(rs.getInt("maPN"));
    			    		  pn.setHoTen(rs.getString("tenNV"));
    			    		  pn.setTenNCC(rs.getString("tenNCC"));
    			    		  pn.setNgayNhap(rs.getDate("ngayNhap"));
    			    		  pn.setTrangThai(rs.getString("trangThai"));
    			    		  list.add(pn);
    			    		  table.setItems(list);
    			    	  }
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    		    	  
    			}
    			if(cbbTrangThai.getSelectionModel().getSelectedItem()=="Đã nhập hàng") {
    				 String sql1 = "select distinct(ct.maPN) as maPN,tenNV,ngayNhap, tenNCC, ct.trangThai from PhieuNhap pn left join CTPhieuNhap ct on ct.maPN = pn.maPN inner join NhanVien nv on nv.maNV = pn.maNV inner join NhaCungCap ncc on ncc.maNCC = pn.maNCC where ct.trangThai is not null and ct.trangThai = N'Đã nhập hàng'";
    		    	  try {
    		    		  list.clear();
    		  			table.setItems(list);
    					ps = con.prepareStatement(sql1);
    			    	  rs = ps.executeQuery();
    			    	  while(rs.next()) {
    			    		  PhieuNhap pn = new PhieuNhap();
    			    		  pn.setMaPN(rs.getInt("maPN"));
    			    		  pn.setHoTen(rs.getString("tenNV"));
    			    		  pn.setTenNCC(rs.getString("tenNCC"));
    			    		  pn.setNgayNhap(rs.getDate("ngayNhap"));
    			    		  pn.setTrangThai(rs.getString("trangThai"));
    			    		  list.add(pn);
    			    		  table.setItems(list);
    			    	  }
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    			if(cbbTrangThai.getSelectionModel().getSelectedItem()=="Lưu tạm") {
    				 String sql1 = "select distinct(ct.maPN) as maPN,tenNV,ngayNhap, tenNCC, ct.trangThai from PhieuNhap pn left join CTPhieuNhap ct on ct.maPN = pn.maPN inner join NhanVien nv on nv.maNV = pn.maNV inner join NhaCungCap ncc on ncc.maNCC = pn.maNCC where ct.trangThai is not null and ct.trangThai = N'Lưu tạm'";
    		    	  try {
    		    		  list.clear();
    		  			table.setItems(list);
    					ps = con.prepareStatement(sql1);
    			    	  rs = ps.executeQuery();
    			    	  while(rs.next()) {
    			    		  PhieuNhap pn = new PhieuNhap();
    			    		  pn.setMaPN(rs.getInt("maPN"));
    			    		  pn.setHoTen(rs.getString("tenNV"));
    			    		  pn.setTenNCC(rs.getString("tenNCC"));
    			    		  pn.setNgayNhap(rs.getDate("ngayNhap"));
    			    		  pn.setTrangThai(rs.getString("trangThai"));
    			    		  list.add(pn);
    			    		  table.setItems(list);
    			    	  }
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}});
    			
    	  return list;
      }
      public void cell() {
    	maPN.setCellValueFactory(new PropertyValueFactory<PhieuNhap, Integer>("maPN"));
  		hoTen.setCellValueFactory(new PropertyValueFactory<PhieuNhap, String>("hoTen"));
  		tenNCC.setCellValueFactory(new PropertyValueFactory<PhieuNhap, String>("tenNCC"));
  		ngayNhap.setCellValueFactory(new PropertyValueFactory<PhieuNhap, Date>("ngayNhap"));
  		trangThai.setCellValueFactory(new PropertyValueFactory<PhieuNhap, String>("trangThai"));
      }
//public int getNV() {
//	NhanVien nv = new NhanVien();
//	String sql = "select tenNV from NhanVien";
//	PreparedStatement ps;
//	try {
//		ps = con.prepareStatement(sql);
//		ResultSet rs = ps.executeQuery();
//		while(rs.next()) {
//			nv.setMaNV(rs.getInt("maNV"));
//			nv.setHoTen(rs.getString("tenNV"));
//			return nv.getMaNV();
//		}
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	return nv.getMaNV();
//}
public void reload() {
	getAllPN();
	cell();
	table.setItems(list);
}
}
