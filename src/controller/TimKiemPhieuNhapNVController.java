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
	private TextField txtMaPN;
	@FXML
	private ComboBox<NhanVien> cbNhanVien;
	@FXML
	private TextField txtNCC;
	@FXML
	private Button btnXemCT;
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
		NhanVien dnc = DangNhapController.getNV();
//		try {
//			while(rs.next()) {
//				lblName.setText("Xin chào, " + dnc.getHoTen());

		cell();
		getAllPN();
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
			TextField txtTimKiem = new TextField();
			Label lblTimKiem = new Label("Tìm kiếm tên thuốc");
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
			h1.getChildren().addAll( lblTimKiem, txtTimKiem);
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

								String nhapThuoc = "insert into CTThuoc(maThuoc, soLuongCon, giaNhap, giaBan, hanSuDung) values (?,?,?,?,?)";
								ps = con.prepareStatement(nhapThuoc);
								ps.setInt(1, Integer.parseInt(sqlMaThuoc));
								ps.setInt(2, sl);
								ps.setFloat(3, gn);
								ps.setFloat(4,gb);
								ps.setDate(5, dhsd);
//								ps.setInt(7, mapn);
								ps.execute();
								
									
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
	      public void getAllPN()  {
	    	  String sql = "select * from PhieuNhap pn left join NhanVien nv on nv.maNV = pn.maNV inner join NhaCungCap ncc on ncc.maNCC = pn.maNCC";
	    	  try {
				ps = con.prepareStatement(sql);
		    	  rs = ps.executeQuery();
		    	  while(rs.next()) {
		    		  PhieuNhap pn = new PhieuNhap();
		    		  pn.setMaPN(rs.getInt("maPN"));
		    		  pn.setHoTen(rs.getString("tenNV"));
		    		  pn.setTenNCC(rs.getString("tenNCC"));
		    		  pn.setNgayNhap(rs.getDate("ngayNhap"));
		    		  list.add(pn);
		    		  table.setItems(list);
		    	  }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	      public void cell() {
	    	maPN.setCellValueFactory(new PropertyValueFactory<PhieuNhap, Integer>("maPN"));
	  		hoTen.setCellValueFactory(new PropertyValueFactory<PhieuNhap, String>("hoTen"));
	  		tenNCC.setCellValueFactory(new PropertyValueFactory<PhieuNhap, String>("tenNCC"));
	  		ngayNhap.setCellValueFactory(new PropertyValueFactory<PhieuNhap, Date>("ngayNhap"));
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
	public void reload() throws SQLException {
		String sql = "select * from PhieuNhap p left join NhanVien n on p.maNV = n.maNV";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			PhieuNhap pn = new PhieuNhap();
			pn.setMaPN(rs.getInt("maPN"));
			pn.setHoTen(rs.getString("hoTen"));
//			pn.setMaNCC(rs.getString());
		}
	}
}
