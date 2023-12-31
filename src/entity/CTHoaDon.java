package entity;

import java.sql.Date;
import java.util.Objects;

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
//	private String soLo;
	private Date hanSuDung;
	private String thuocKeDon;
	private float tongGiaBan;
	public CTHoaDon(int maCTHD,int maThuoc, int maHD, String tenThuoc, String tenLoaiThuoc, String donViTinh, float donGia,
			int soLuong, Date hanSuDung, String thuocKeDon, float tongGiaBan) {
		super();
		this.maCTHD = maCTHD;
		this.maHD = maHD;
		this.tenThuoc = tenThuoc;
		this.tenLoaiThuoc = tenLoaiThuoc;
		this.donViTinh = donViTinh;
		this.donGia = donGia;
		this.soLuong = soLuong;
//		this.soLo = soLo;
		this.hanSuDung = hanSuDung;
		this.thuocKeDon = thuocKeDon;
		this.tongGiaBan = tongGiaBan;
	}
	public CTHoaDon(String tenThuoc, String donViTinh, int soLuong, float donGia, float tongGiaBan) {
		super();
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

	public Date getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(Date hanSuDung) {
		this.hanSuDung = hanSuDung;
	}
	public String getThuocKeDon() {
		return thuocKeDon;
	}
	public void setThuocKeDon(String thuocKeDon) {
		this.thuocKeDon= thuocKeDon;
	}
	public float getTongGiaBan() {
		return tongGiaBan;
	}
	public void setTongGiaBan(float tongGiaBan) {
		this.tongGiaBan = tongGiaBan;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maThuoc);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CTHoaDon other = (CTHoaDon) obj;
		return maThuoc == other.maThuoc;
	}
	
}
