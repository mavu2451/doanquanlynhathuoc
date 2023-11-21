package entity;

import java.sql.Date;

public class DonThuocKhamBenh {
	private int maDonThuoc;
	private int maKH;
	private String tenKH;
	private String bacSiKeDon;
	private Date ngayNhap;
	private String chanDoan;
	private String loiDan;
	private String thongTin;
	public DonThuocKhamBenh(int maDonThuoc, int maKH, String tenKH, String bacSiKeDon, Date ngayNhap, String chanDoan,
			String loiDan, String thongTin) {
		super();
		this.maDonThuoc = maDonThuoc;
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.bacSiKeDon = bacSiKeDon;
		this.ngayNhap = ngayNhap;
		this.chanDoan = chanDoan;
		this.loiDan = loiDan;
		this.thongTin = thongTin;
	}
	public DonThuocKhamBenh() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMaDonThuoc() {
		return maDonThuoc;
	}
	public void setMaDonThuoc(int maDonThuoc) {
		this.maDonThuoc = maDonThuoc;
	}
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getBacSiKeDon() {
		return bacSiKeDon;
	}
	public void setBacSiKeDon(String bacSiKeDon) {
		this.bacSiKeDon = bacSiKeDon;
	}
	public Date getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public String getChanDoan() {
		return chanDoan;
	}
	public void setChanDoan(String chanDoan) {
		this.chanDoan = chanDoan;
	}
	public String getLoiDan() {
		return loiDan;
	}
	public void setLoiDan(String loiDan) {
		this.loiDan = loiDan;
	}
	public String getThongTin() {
		return thongTin;
	}
	public void setThongTin(String thongTin) {
		this.thongTin = thongTin;
	}

}
