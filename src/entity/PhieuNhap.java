package entity;

import java.sql.Date;

public class PhieuNhap {
	private int maPN;
	private String hoTen;
	private Date ngayNhap;
	public PhieuNhap(int maPN, String hoTen, Date ngayNhap) {
		super();
		this.maPN = maPN;
		this.hoTen = hoTen;
		this.ngayNhap = ngayNhap;

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

}
