package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import database.KetNoiDatabase;
import entity.NhanVien;
import entity.Thuoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ThongTinChiTietThuocController implements Initializable{
	@FXML
	private ImageView imageView;
	private Image image;
	private FileInputStream fis;
	private File file;
	private InputStream is;
	Connection con = KetNoiDatabase.getConnection();
	PreparedStatement ps;
	ResultSet rs;

	@FXML
	private Label lblMaThuoc, lblTenThuoc, lblLoaiThuoc, lblQuyCach, lblDVT, lblNSX, lblGiaBan, lblCachDung, lblSoDangKy, lblTrangThai, lblTT;
	@FXML
	private Button chooseImage;
	NhanVien nv;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	public void getMaThuoc(Thuoc t) {
		String sql = "select * from Thuoc t left join LoaiThuoc lt on lt.maLoaiThuoc = t.maLoaiThuoc where maThuoc = '"+t.getMaThuoc()+"'";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				lblMaThuoc.setText(rs.getInt("maThuoc") + "");
				lblTenThuoc.setText(rs.getString("tenThuoc"));
				lblLoaiThuoc.setText(rs.getString("tenLoaiThuoc"));
				lblQuyCach.setText(rs.getString("quyCachDongGoi"));
				lblDVT.setText(rs.getString("donViTinh"));
				lblNSX.setText(rs.getString("nuocSanXuat"));
				lblGiaBan.setText(String.format("%.0f", rs.getFloat("giaBan")) + " đồng");
				lblCachDung.setText(rs.getString("cachDung"));
				lblSoDangKy.setText(rs.getString("soDangKy"));
				lblTT.setText(rs.getString("thongTin"));
				if(rs.getString("trangThai").equals("Đang kinh doanh")) {
					lblTrangThai.setText(rs.getString("trangThai"));
					lblTrangThai.setTextFill(Color.GREEN);
				}
				else if(rs.getString("trangThai").equals("Ngừng kinh doanh")) {
					lblTrangThai.setText(rs.getString("trangThai"));
					lblTrangThai.setTextFill(Color.RED);
				}
				is = rs.getBinaryStream("hinhAnh");
				if(is==null) {
					image = new Image("file:\\images\\avatar.png",imageView.getFitWidth(),imageView.getFitHeight(),true,true);
					imageView.setImage(image);
				}
				OutputStream os = new FileOutputStream(new File("photo.jpg"));
				byte[] content = new byte[1024];
				int size = 0;
				while((size = is.read(content))!=-1) {
					os.write(content, 0 ,size);
				
				}
				os.close();
				is.close();
				image = new Image("file:photo.jpg",imageView.getFitWidth(),imageView.getFitHeight(),true,true);
				imageView.setImage(image);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
