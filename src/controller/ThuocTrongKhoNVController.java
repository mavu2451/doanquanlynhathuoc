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

public class ThuocTrongKhoNVController implements Initializable{
	@FXML
	private MenuButton mb;
	@FXML
	private Button btnTTCT;
	@FXML
	private TextField txtMaPN;
	@FXML
	private ComboBox<NhanVien> cbNhanVien;
	@FXML
	private TextField txtNCC;
	@FXML
	private DatePicker dpNgayNhap;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	static ResultSet rs;
	@FXML
	Label lblName;
	@FXML
	TableView<Thuoc> table;
	@FXML
	private TableColumn<Thuoc, Integer> maThuoc;
	@FXML
	private TableColumn<Thuoc, String> tenThuoc;
	@FXML
	private TableColumn<Thuoc, String> tenLoaiThuoc;
	@FXML
	private TableColumn<Thuoc, String> donViTinh;
	@FXML
	private TableColumn<Thuoc, Integer> slTonKho;
	@FXML
	private TableColumn<Thuoc, Float> giaNhap;
	@FXML
	private TableColumn<Thuoc, Float> giaBan;
	int i = 1;
	@FXML
	ObservableList<Thuoc> thuocList = FXCollections.observableArrayList();
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
		try {
			reload();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		btnTTCT.setOnAction(b->{
			System.out.println("đang chọn bảng");
			if(table.getSelectionModel().getSelectedItem()==null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Mời bạn chọn mục thông tin");
				alert.showAndWait();
			}
			else {
			ObservableList<CTThuoc> Tlist = FXCollections.observableArrayList();
			BorderPane root = new BorderPane();
			ScrollPane scroll = new ScrollPane();
			Stage stage = new Stage();
			TextField txtXoa = new TextField();
			txtXoa.setPromptText("Nhập số lượng xoá");
			Button chon = new Button("Xoá");
			HBox h2 = new HBox(2);
			TableView tableView = new TableView<CTThuoc>();
			TableColumn maCT = new TableColumn<CTThuoc, Integer>("STT");
			maCT.setCellValueFactory(new PropertyValueFactory<CTThuoc, Integer>("maCT"));
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
			TableColumn hanSuDung = new TableColumn<CTThuoc, Float>("Hạn sử dụng");
			hanSuDung.setCellValueFactory(new PropertyValueFactory<CTThuoc, Float>("hanSuDung"));
			tableView.getColumns().add(maCT);
			tableView.getColumns().add(maThuoc);
			maThuoc.setPrefWidth(0);
			maThuoc.setMinWidth(0);
			maThuoc.setMaxWidth(0);
			tableView.getColumns().add(tenThuoc);
			tableView.getColumns().add(donViTinh);
			tableView.getColumns().add(slTonKho);
			tableView.getColumns().add(giaBan);
			tableView.getColumns().add(hanSuDung);
//					tableView.getColumns().add(soLo);
//					tableView.getColumns().add(hanSuDung);
			root.setCenter(scroll);
			scroll.setContent(tableView);
			h2.getChildren().addAll(txtXoa, chon);
			root.setBottom(h2);
			
			String sql;

			try {
				sql = "select * from CTThuoc ct left join Thuoc t on t.maThuoc = ct.maThuoc where soLuongCon > 0 and ct.maThuoc = '"+maThuoc()+"'order by hanSuDung";
				try {
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					while(rs.next()) {
						CTThuoc t = new CTThuoc();
						t.setMaCT(i++);
						t.setMaThuoc(rs.getInt("maThuoc"));
						t.setTenThuoc(rs.getString("tenThuoc"));
						String tenT = rs.getString("tenThuoc");
						t.setHanSuDung(rs.getDate("hanSuDung"));
						t.setSlTonKho(rs.getInt("soLuongCon"));
						t.setDonViTinh(rs.getString("donViTinh"));
//						t.setSoLo(rs.getString("soLo"));
						t.setGiaBan(rs.getFloat("giaBan"));
						Tlist.add(t);
						tableView.setItems(Tlist);
					}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			chon.setOnAction(args->{
				if(tableView.getSelectionModel().getSelectedIndex() <= -1) {
					Alert alert = new Alert(AlertType.ERROR, "Bạn chưa chọn thuốc", ButtonType.OK);
			  		alert.setTitle("Thông báo");
			  		alert.setHeaderText(null);
			  		alert.show();
				}
				else {
				int slxoa = Integer.parseInt(txtXoa.getText());
				int index = tableView.getSelectionModel().getSelectedIndex();
				int maT = Integer.parseInt(maThuoc.getCellData(index).toString());
				int sltonkho = Integer.parseInt(slTonKho.getCellData(index).toString());
				int conlai = sltonkho - slxoa;
				Date hsd = Date.valueOf(hanSuDung.getCellData(index).toString());
				String update = "update CTThuoc set soLuongCon = '"+conlai+"' where maThuoc = '"+maT+"' and hanSuDung = '"+hsd+"'";
				try {
					ps = con.prepareStatement(update);
					ps.execute();
					Alert alert = new Alert(AlertType.INFORMATION, "Thuốc đã được cập nhật thành công", ButtonType.OK);
			  		alert.setTitle("Thông báo");
			  		alert.setHeaderText(null);
			  		alert.show();
			  		tableView.getItems().clear();
			  		String sql1 = "select * from CTThuoc ct left join Thuoc t on t.maThuoc = ct.maThuoc where soLuongCon > 0 and ct.maThuoc = '"+maThuoc()+"'order by hanSuDung";
			  		ps = con.prepareStatement(sql1);
					rs = ps.executeQuery();
					while(rs.next()) {
						CTThuoc t = new CTThuoc();
						t.setMaCT(i++);
						t.setMaThuoc(rs.getInt("maThuoc"));
						t.setTenThuoc(rs.getString("tenThuoc"));
						String tenT = rs.getString("tenThuoc");
						t.setHanSuDung(rs.getDate("hanSuDung"));
						t.setSlTonKho(rs.getInt("soLuongCon"));
						t.setDonViTinh(rs.getString("donViTinh"));
//						t.setSoLo(rs.getString("soLo"));
						t.setGiaBan(rs.getFloat("giaBan"));
						Tlist.add(t);
						tableView.setItems(Tlist);
					}
					stage.close();
					table.getItems().clear();
					String sql2 = "select t.maThuoc, t.tenThuoc, lt.tenLoaiThuoc, donViTinh,sum(th.soLuongCon) as slTonkho, sum(th.giaNhap) as giaNhap, sum(th.giaBan) as giaBan, min(hanSuDung) as hanSuDung from Thuoc t left join CTThuoc th on t.maThuoc = th.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc group by t.maThuoc, tenThuoc, lt.tenLoaiThuoc, donViTinh, t.giaNhap, t.giaBan order by t.maThuoc";
					ps = con.prepareStatement(sql2);
					rs = ps.executeQuery();
					while(rs.next()) {
						Thuoc k = new Thuoc();
						k.setMaThuoc(rs.getInt("maThuoc"));
						k.setTenThuoc(rs.getString("tenThuoc"));
						k.setSoLuong(rs.getInt("slTonKho"));
						k.setDvt(rs.getString("donViTinh"));
						k.setGiaNhap(rs.getFloat("giaNhap"));
						k.setGiaBan(rs.getFloat("giaBan"));
						k.setLoaiThuoc(rs.getString("tenLoaiThuoc"));
						thuocList.add(k);
						table.setItems(thuocList);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			});		
			Scene scene = new Scene(root,500,300);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			}});
		// TODO Auto-generated method stub
//		getAllPN();
//		reload();
		
//		try {
//			while(rs.next()) {
//				lblName.setText("Xin chào, " + dnc.getHoTen());

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
	public void cell() {
		maThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, Integer>("maThuoc"));
		giaNhap.setCellValueFactory(new PropertyValueFactory<Thuoc, Float>("giaNhap"));
		giaBan.setCellValueFactory(new PropertyValueFactory<Thuoc, Float>("giaBan"));
		slTonKho.setCellValueFactory(new PropertyValueFactory<Thuoc, Integer>("soLuong"));
		tenThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("tenThuoc"));
		tenLoaiThuoc.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("loaiThuoc"));
		donViTinh.setCellValueFactory(new PropertyValueFactory<Thuoc, String>("dvt"));
	}
	public ObservableList<Thuoc> getAllThuocTonKho() throws SQLException {
		String sql = "select t.maThuoc, t.tenThuoc, lt.tenLoaiThuoc, donViTinh,sum(th.soLuongCon) as slTonkho, sum(th.giaNhap) as giaNhap, sum(th.giaBan) as giaBan, min(hanSuDung) as hanSuDung from Thuoc t left join CTThuoc th on t.maThuoc = th.maThuoc inner join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc group by t.maThuoc, tenThuoc, lt.tenLoaiThuoc, donViTinh, t.giaNhap, t.giaBan order by t.maThuoc";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			Thuoc k = new Thuoc();
			k.setMaThuoc(rs.getInt("maThuoc"));
			k.setTenThuoc(rs.getString("tenThuoc"));
			k.setSoLuong(rs.getInt("slTonKho"));
			k.setDvt(rs.getString("donViTinh"));
			k.setGiaNhap(rs.getFloat("giaNhap"));
			k.setGiaBan(rs.getFloat("giaBan"));
			k.setLoaiThuoc(rs.getString("tenLoaiThuoc"));
			thuocList.add(k);
//			table.setItems(thuocList);
		}
		return thuocList;
	}
	 public int maThuoc() throws SQLException{
		 int maThuoc = 0;
		 int chon = table.getSelectionModel().getSelectedIndex();
		String sql1 = "select * from Thuoc where maThuoc = '"+table.getItems().get(chon).getMaThuoc()+"'";
		ps = con.prepareStatement(sql1);
		rs = ps.executeQuery();
		while(rs.next()) 
		maThuoc = rs.getInt("maThuoc");
		System.out.println(sql1 + " dang chọn ");
		return maThuoc;
	 }
		public void reload() throws SQLException {
			thuocList = getAllThuocTonKho();
			// TODO Auto-generated method stub
			cell();
			table.setItems(thuocList);
		}
}
