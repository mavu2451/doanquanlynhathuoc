module QuanLyHieuThuoc {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.media;
	requires javafx.base;
	requires java.desktop;
	requires org.apache.poi.ooxml;
	requires org.apache.poi.poi;
	requires jasperreports;
	
	opens view to javafx.graphics, javafx.fxml, javafx.base;
	opens controller to javafx.fxml, javafx.controls;
	opens entity to javafx.base;
}
