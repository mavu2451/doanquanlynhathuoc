package entity;

import java.sql.Date;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class CTPhieuNhap {
	private int maCTPN;
	private int maPN;
	private int maThuoc;
	private String tenThuoc;
	private String donViTinh;
	private String loaiThuoc;
	private float giaNhap;
	private float giaBan;
	private int sl;
	private String soLo;
	private Date hanSuDung;
	private String thongTin;
	private float tongGiaNhap;
	private float tongGiaBan;
	private String trangThai;
	public CTPhieuNhap(int maCTPN, int maPN, int maThuoc, String tenThuoc, String donViTinh,String loaiThuoc, float giaNhap,
			float giaBan, int sl, String soLo, Date hanSuDung, String thongTin, float tongGiaNhap,
			float tongGiaBan, String trangThai) {
		super();
		this.maCTPN = maCTPN;
		this.maPN = maPN;
		this.loaiThuoc = loaiThuoc;
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.donViTinh = donViTinh;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.sl = sl;
		this.soLo = soLo;
		this.hanSuDung = hanSuDung;
		this.thongTin = thongTin;
		this.tongGiaNhap = tongGiaNhap;
		this.tongGiaBan = tongGiaBan;
		this.trangThai = trangThai;
	}
	public CTPhieuNhap() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMaCTPN() {
		return maCTPN;
	}
	public void setMaCTPN(int maCTPN) {
		this.maCTPN = maCTPN;
	}
	public int getMaPN() {
		return maPN;
	}
	public void setMaPN(int maPN) {
		this.maPN = maPN;
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
	public int getSl() {
		return sl;
	}
	public void setSl(int sl) {
		this.sl = sl;
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
	public String getThongTin() {
		return thongTin;
	}
	public void setThongTin(String thongTin) {
		this.thongTin = thongTin;
	}
	public float getTongGiaNhap() {
		return tongGiaNhap;
	}
	public void setTongGiaNhap(float tongGiaNhap) {
		this.tongGiaNhap = tongGiaNhap;
	}
	public float getTongGiaBan() {
		return tongGiaBan;
	}
	public void setTongGiaBan(float tongGiaBan) {
		this.tongGiaBan = tongGiaBan;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public String getLoaiThuoc() {
		return loaiThuoc;
	}
	public void setLoaiThuoc(String loaiThuoc) {
		this.loaiThuoc = loaiThuoc;
	}
	


}
