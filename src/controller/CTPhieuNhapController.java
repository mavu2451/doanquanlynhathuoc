package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.KetNoiDatabase;
import entity.PhieuNhap;
import entity.Thuoc;
import entity.CTPhieuNhap;
import entity.NhanVien;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CTPhieuNhapController implements Initializable{
	@FXML
	private TextField txtSoLuong, txtMaThuoc, txtTimKiem, txtNSX, txtNCC, txtLoaiThuoc, txtDonViTinh,txtGiaNhap,txtGiaBan;
	@FXML
	private ComboBox<?> cbbThuoc;
	@FXML
	TableView<CTPhieuNhap> table;
	@FXML 
	Label lblNV, lblGiaQuyDoi;
	@FXML
	private TableColumn<CTPhieuNhap, Integer> maPN;
	@FXML
	private TableColumn<CTPhieuNhap, Integer> maThuoc;
	@FXML
	private TableColumn<CTPhieuNhap, String> loaiSP;
	public PhieuNhap p;
	ActionEvent e;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;
	private ObservableList<CTPhieuNhap> list = FXCollections.observableArrayList();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtSoLuong.setText("1");
		lblGiaQuyDoi.setText("0 đồng");
//		float gb = 0;
//		
//		if(txtGiaBan.getText().toString() != null || txtSoLuong.getText().toString()!=null) {
//			txtGiaBan.setText("0");
////			gb = Float.parseFloat(txtGiaBan.getText());
//			int sl = Integer.parseInt(txtSoLuong.getText());
//			lblGiaQuyDoi.setText(gb*sl + " đồng");
////			float quydoi = Float.parseFloat(txtGiaBan.getText().toString());
//		}
		
	
		// TODO Auto-generated method stub
		reload();
		cell();
		getAllTenThuoc();
		NhanVien dnc = DangNhapController.getNV();
//		try {
//			while(rs.next()) {
//				lblNV.setText("Xin chào, " + dnc.getHoTen());
		lblNV.setText(dnc.getHoTen());
			
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
//	public int setMaPN(PhieuNhap p) {
//		lblMaPN.setText(String.valueOf(p.getMaPN()));
//		text = Integer.parseInt(lblMaPN.getText());
//		System.out.println(text);
//		return text;
//	}
	@FXML
	private void quayLai(ActionEvent e) throws IOException {
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/PhieuNhap.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
	}
	public void add(ActionEvent e) {
		
		String sql = "insert into CTPhieuNhap(maPN, maThuoc) values(?,?)";
		try {
//				String maPN = lblMaPN.getText();
//				System.out.println(maPN);
//				int ma = Integer.parseInt(maPN);
//				System.out.println(ma);
				String maThuoc = txtMaThuoc.getText();
				ps = con.prepareStatement(sql);
//				ps.setInt(1, ma);
				ps.setString(2, maThuoc);
				ps.execute();	

		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
		reload();
	}
	public void giaQuyDoi() {
		float gb = 0f;
		float sl = 1f;
		float tong = 0;
		if(txtGiaBan.getText()!=null) {
			gb = Float.parseFloat(txtGiaBan.getText());
			sl = Float.parseFloat(txtSoLuong.getText());
			tong = gb * sl;
			lblGiaQuyDoi.setText(String.valueOf(tong) + " đồng");
	}
	}
	public void cell() {
		maPN.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Integer>("maPN"));
		maThuoc.setCellValueFactory(new PropertyValueFactory<CTPhieuNhap, Integer>("maThuoc"));
		
	}
	public void reload() {
		list = getAllCTPN();
		cell();
		table.setItems(list);
	}
	public ObservableList<CTPhieuNhap> getAllCTPN(){
//		String maPN = lblMaPN.getText();
//		int ma = Integer.parseInt(maPN);
//		int text = Integer.parseInt(i);
////		int i = p.getMaPN();
		ObservableList<CTPhieuNhap> pnlist = FXCollections.observableArrayList();
//		String query = "select * from CTPhieuNhap";
		try {
//			String maPN = lblMaPN.getText();
			String query = "select * from CTPhieuNhap";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				CTPhieuNhap ctpn = new CTPhieuNhap();
				ctpn.setMaPN(rs.getInt("maPN"));
				ctpn.setMaThuoc(rs.getInt("maThuoc"));
				pnlist.add(ctpn);
			}
		}catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
				
			}
		return pnlist;
	}
	public void getAllTenThuoc(){
		String sql = "select * from Thuoc";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			ObservableList thuocList = FXCollections.observableArrayList();
			while(rs.next()) {
				Thuoc t = new Thuoc();
				String ls = rs.getString("tenThuoc");
				t.setNsx(rs.getString("nuocSanXuat"));
				thuocList.add(ls);
			}
			cbbThuoc.setItems(thuocList);
			int selectedItem = cbbThuoc.getSelectionModel().getSelectedIndex() + 1;
			String q1 = "select * from Thuoc t inner join LoaiThuoc lt on t.maLoaiThuoc = lt.maLoaiThuoc"
					+ " inner join NhaCungCap n on n.maNCC = t.maNCC"
					+ " where maThuoc = " + selectedItem +"";
			try {
				ps = con.prepareStatement(q1);
				rs = ps.executeQuery();
				ObservableList selectThuoc = FXCollections.observableArrayList();
				while(rs.next()) {
					String mt = String.valueOf(rs.getInt("maThuoc"));
					String lt = rs.getString("loaiThuoc");
					String tt = rs.getString("tenThuoc");
					String dvt = rs.getString("donViTinh");
					String gn = String.valueOf(rs.getFloat("giaNhap"));
					String gb = String.valueOf(rs.getFloat("giaBan"));
					String nsx = rs.getString("nuocSanXuat");
					String ncc = rs.getString("tenNCC");
					txtMaThuoc.setText(mt);
					txtDonViTinh.setText(dvt);
					txtLoaiThuoc.setText(lt);
					txtGiaNhap.setText(gn);
					txtGiaBan.setText(gb);
					txtSoLuong.getText();
					txtNSX.setText(nsx);
					txtNCC.setText(ncc);
					String giaQD = String.valueOf(Float.parseFloat(txtGiaBan.getText()) * Integer.parseInt(txtSoLuong.getText()));
					
					selectThuoc.add(mt);

				}
			} catch (Exception e) {
				// TODO: handle exception
			}


			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
