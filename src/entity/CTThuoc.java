package entity;

import java.sql.Date;

public class CTThuoc {
	private int maKho;
	private int maThuoc;
	private String tenThuoc;
	private String donViTinh;
	private String tenNCC;
	private String tenLoaiThuoc;
	private int slTonKho;
	private int soLuong;
	private float giaNhap;
	private float giaBan;
	private String soLo;
	private Date hanSuDung;
	public CTThuoc(int maKho, int maThuoc, String tenThuoc, String donViTinh, String tenNCC, String tenLoaiThuoc,
			int slTonKho, float giaNhap, float giaBan, String soLo, Date hanSuDung, int soLuong) {
		super();
		this.maKho = maKho;
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.donViTinh = donViTinh;
		this.tenNCC = tenNCC;
		this.tenLoaiThuoc = tenLoaiThuoc;
		this.slTonKho = slTonKho;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.soLo = soLo;
		this.hanSuDung = hanSuDung;
		this.soLuong = soLuong;
	}
	public CTThuoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMaKho() {
		return maKho;
	}
	public void setMaKho(int maKho) {
		this.maKho = maKho;
	}
	public int getMaThuoc() {
		return maThuoc;
	}
	public void setMaThuoc(int maThuoc) {
		this.maThuoc = maThuoc;
	}
	public String getTenThuoc() {
		return tenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	public String getTenLoaiThuoc() {
		return tenLoaiThuoc;
	}
	public void setTenLoaiThuoc(String tenLoaiThuoc) {
		this.tenLoaiThuoc = tenLoaiThuoc;
	}
	public int getSlTonKho() {
		return slTonKho;
	}
	public void setSlTonKho(int slTonKho) {
		this.slTonKho = slTonKho;
	}
	public float getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(float giaNhap) {
		this.giaNhap = giaNhap;
	}
	public float getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}
	public String getSoLo() {
		return soLo;
	}
	public void setSoLo(String soLo) {
		this.soLo = soLo;
	}
	public Date getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(Date hanSuDung) {
		this.hanSuDung = hanSuDung;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
}
