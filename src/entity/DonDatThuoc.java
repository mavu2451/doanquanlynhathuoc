package entity;

import java.sql.Date;

public class DonDatThuoc {
	private int maPDT;
	private String tenNV;
	private String tenKH;
	private Date ngayLapDon;
	private float tongTien;
	public DonDatThuoc(int maPDT, String tenNV, String tenKH, Date ngayLapDon, float tongTien) {
		super();
		this.maPDT = maPDT;
		this.tenNV = tenNV;
		this.tenKH = tenKH;
		this.ngayLapDon = ngayLapDon;
		this.tongTien = tongTien;
	}
	public DonDatThuoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMaPDT() {
		return maPDT;
	}
	public void setMaPDT(int maPDT) {
		this.maPDT = maPDT;
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
	public Date getNgayLapDon() {
		return ngayLapDon;
	}
	public void setNgayLapDon(Date ngayLapDon) {
		this.ngayLapDon = ngayLapDon;
	}
	public float getTongTien() {
		return tongTien;
	}
	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}
	
	
}
