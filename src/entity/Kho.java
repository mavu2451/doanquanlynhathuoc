package entity;

import java.sql.Date;

public class Kho {
	private int maKho;
	private int maThuoc;
	private String tenThuoc;
	private String donViTinh;
	private int slTonKho;
	private float giaNhap;
	private float giaBan;
	private String soLo;
	private Date hanSuDung;
	public Kho(int maKho, int maThuoc, String tenThuoc, String donViTinh,int slTonKho, float giaNhap, float giaBan, String soLo,
			Date hanSuDung) {
		super();
		this.maKho = maKho;
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.donViTinh = donViTinh;
		this.slTonKho = slTonKho;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.soLo = soLo;
		this.hanSuDung = hanSuDung;
	}
	public Kho() {
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
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	
}
