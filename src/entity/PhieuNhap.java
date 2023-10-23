package entity;

import java.sql.Date;

public class PhieuNhap {
	private int maPN;
	private String hoTen;
	private Date ngayNhap;
	private int maNCC;
	public PhieuNhap(int maPN, String hoTen, Date ngayNhap, int maNCC) {
		super();
		this.maPN = maPN;
		this.hoTen = hoTen;
		this.ngayNhap = ngayNhap;
		this.maNCC = maNCC;
	}
	public PhieuNhap() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMaPN() {
		return maPN;
	}
	public void setMaPN(int maPN) {
		this.maPN = maPN;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public Date getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public int getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(int maNCC) {
		this.maNCC = maNCC;
	}
	
}
