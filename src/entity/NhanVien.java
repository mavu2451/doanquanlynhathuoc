package entity;

import java.sql.Date;

public class NhanVien {
	private int maNV;
	private String hoTen;
	private String matKhau;
	private String gioiTinh;
	private Date ngaySinh;
	private String cmnd;
	private String sdt;
	private String email;
	private String vaiTro;
	private String trangThai;

	public NhanVien(int maNV, String hoTen, String matKhau, String gioiTinh, Date ngaySinh, String cmnd, String sdt,
			String email, String vaiTro, String trangThai) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.matKhau = matKhau;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.cmnd = cmnd;
		this.sdt = sdt;
		this.email = email;
		this.vaiTro = vaiTro;
		this.trangThai = trangThai;
	}
	
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getMaNV() {
		return maNV;
	}
	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVaiTro() {
		return vaiTro;
	}
	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
}
