package entity;

import java.sql.Date;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class CTHoaDon {
	private int maCTHD;
	private int maHD;
	private int maThuoc;
	private String tenThuoc;
	private String tenLoaiThuoc;
	private String donViTinh;
	private float donGia;
	private int soLuong;
	private String soLo;
	private Date hanSuDung;
	private String cachDung;
	private float tongGiaBan;
	public CTHoaDon(int maCTHD,int maThuoc, int maHD, String tenThuoc, String tenLoaiThuoc, String donViTinh, float donGia,
			int soLuong, String soLo, Date hanSuDung, String cachDung, float tongGiaBan) {
		super();
		this.maCTHD = maCTHD;
		this.maHD = maHD;
		this.tenThuoc = tenThuoc;
		this.tenLoaiThuoc = tenLoaiThuoc;
		this.donViTinh = donViTinh;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.soLo = soLo;
		this.hanSuDung = hanSuDung;
		this.cachDung = cachDung;
		this.tongGiaBan = tongGiaBan;
	}
	public CTHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMaCTHD() {
		return maCTHD;
	}
	public void setMaCTHD(int maCTHD) {
		this.maCTHD = maCTHD;
	}
	
	public int getMaThuoc() {
		return maThuoc;
	}
	public void setMaThuoc(int maThuoc) {
		this.maThuoc = maThuoc;
	}
	public int getMaHD() {
		return maHD;
	}
	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}
	public String getTenThuoc() {
		return tenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}
	public String getTenLoaiThuoc() {
		return tenLoaiThuoc;
	}
	public void setTenLoaiThuoc(String tenLoaiThuoc) {
		this.tenLoaiThuoc = tenLoaiThuoc;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public float getDonGia() {
		return donGia;
	}
	public void setDonGia(float donGia) {
		this.donGia = donGia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
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
	public String getCachDung() {
		return cachDung;
	}
	public void setCachDung(String cachDung) {
		this.cachDung = cachDung;
	}
	public float getTongGiaBan() {
		return tongGiaBan;
	}
	public void setTongGiaBan(float tongGiaBan) {
		this.tongGiaBan = tongGiaBan;
	}
	
}
