package entity;

import java.sql.Date;

public class HoaDon {
	private int maHD;
	private String tenNV;
	private String tenKH;
	private Date ngayLapHD;
	private float tongTien;
	private float tienNhan; 
	private float tienThua;
	private String ghiChu;
	private int maDonThuoc;
	public HoaDon(int maHD, String tenNV, String tenKH, Date ngayLapHD, float tongTien, float tienNhan, float tienThua,
			String ghiChu, int maDonThuoc) {
		super();
		this.maHD = maHD;
		this.tenNV = tenNV;
		this.tenKH = tenKH;
		this.ngayLapHD = ngayLapHD;
		this.tongTien = tongTien;
		this.tienNhan = tienNhan;
		this.tienThua = tienThua;
		this.ghiChu = ghiChu;
		this.maDonThuoc = maDonThuoc;
	}
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMaHD() {
		return maHD;
	}
	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public Date getNgayLapHD() {
		return ngayLapHD;
	}
	public void setNgayLapHD(Date ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}
	public float getTongTien() {
		return tongTien;
	}
	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}
	public float getTienNhan() {
		return tienNhan;
	}
	public void setTienNhan(float tienNhan) {
		this.tienNhan = tienNhan;
	}
	public float getTienThua() {
		return tienThua;
	}
	public void setTienThua(float tienThua) {
		this.tienThua = tienThua;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public int getMaDonThuoc() {
		return maDonThuoc;
	}
	public void setMaDonThuoc(int maDonThuoc) {
		this.maDonThuoc = maDonThuoc;
	}

	
}
