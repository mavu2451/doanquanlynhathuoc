package entity;

import java.sql.Date;
import java.util.Objects;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class CTDonThuocKhamBenh {

	private int maCT;
	private int maThuoc;
	private String tenThuoc;
	private String tenLoaiThuoc;
	private String donViTinh;
	private String thuocKeDon;
	private int soLuong;
	private String soLo;
	private Date hanSuDung;
	private String cachDung;
	private float tongGiaBan;
	private String bacSiKeDon;
	private String chanDoan;
	private String loiDan;
	private String thongTin;
	public CTDonThuocKhamBenh(int maCT, int maThuoc, String tenThuoc, String tenLoaiThuoc, String donViTinh,
			String thuocKeDon, int soLuong, String soLo, Date hanSuDung, String cachDung, float tongGiaBan,
			String bacSiKeDon, String chanDoan, String loiDan, String thongTin) {
		super();
		this.maCT = maCT;
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.tenLoaiThuoc = tenLoaiThuoc;
		this.donViTinh = donViTinh;
		this.thuocKeDon = thuocKeDon;
		this.soLuong = soLuong;
		this.soLo = soLo;
		this.hanSuDung = hanSuDung;
		this.cachDung = cachDung;
		this.tongGiaBan = tongGiaBan;
		this.bacSiKeDon = bacSiKeDon;
		this.chanDoan = chanDoan;
		this.loiDan = loiDan;
		this.thongTin = thongTin;
	}
	
	public CTDonThuocKhamBenh() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getMaCT() {
		return maCT;
	}
	public void setMaCT(int maCT) {
		this.maCT = maCT;
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
	public String getThuocKeDon() {
		return thuocKeDon;
	}
	public void setThuocKeDon(String thuocKeDon) {
		this.thuocKeDon = thuocKeDon;
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
	public String getBacSiKeDon() {
		return bacSiKeDon;
	}
	public void setBacSiKeDon(String bacSiKeDon) {
		this.bacSiKeDon = bacSiKeDon;
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
		CTDonThuocKhamBenh other = (CTDonThuocKhamBenh) obj;
		return maThuoc == other.maThuoc;
	}
	
	
}
