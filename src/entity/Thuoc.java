package entity;

import java.sql.Date;

public class Thuoc {
	private int maThuoc;
	private String tenThuoc;
	private String loaiThuoc;
	private String ncc;
	private String dvt;
	private int soLuong;
	private float giaNhap;
	private float giaBan;
	private String quyCachDongGoi;
	private String nsx;
	private String cachDung;
	private String thongTin;
	private String trangThai;
	private Date hanSuDung;
	private String thuocKeDon;
	private String soDangKy;
	private int dinhMucSL;
	public Thuoc(int maThuoc, String tenThuoc, String loaiThuoc, String ncc, String dvt, int soLuong, float giaNhap,
			float giaBan, String quyCachDongGoi, String nsx, String cachDung, String thongTin, String trangThai,
			Date hanSuDung, String thuocKeDon, String soDangKy, int dinhMucSL) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.loaiThuoc = loaiThuoc;
		this.ncc = ncc;
		this.dvt = dvt;
		this.soLuong = soLuong;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.quyCachDongGoi = quyCachDongGoi;
		this.nsx = nsx;
		this.cachDung = cachDung;
		this.thongTin = thongTin;
		this.trangThai = trangThai;
		this.hanSuDung = hanSuDung;
		this.thuocKeDon = thuocKeDon;
		this.soDangKy = soDangKy;
		this.dinhMucSL = dinhMucSL;
	}
	public Thuoc() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getLoaiThuoc() {
		return loaiThuoc;
	}
	public void setLoaiThuoc(String loaiThuoc) {
		this.loaiThuoc = loaiThuoc;
	}
	public String getNcc() {
		return ncc;
	}
	public void setNcc(String ncc) {
		this.ncc = ncc;
	}
	public String getDvt() {
		return dvt;
	}
	public void setDvt(String dvt) {
		this.dvt = dvt;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
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
	public String getQuyCachDongGoi() {
		return quyCachDongGoi;
	}
	public void setQuyCachDongGoi(String quyCachDongGoi) {
		this.quyCachDongGoi = quyCachDongGoi;
	}
	public String getNsx() {
		return nsx;
	}
	public void setNsx(String nsx) {
		this.nsx = nsx;
	}
	public String getCachDung() {
		return cachDung;
	}
	public void setCachDung(String cachDung) {
		this.cachDung = cachDung;
	}
	public String getThongTin() {
		return thongTin;
	}
	public void setThongTin(String thongTin) {
		this.thongTin = thongTin;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
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
		this.thuocKeDon = thuocKeDon;
	}
	public String getSoDangKy() {
		return soDangKy;
	}
	public void setSoDangKy(String soDangKy) {
		this.soDangKy = soDangKy;
	}
	public int getDinhMucSL() {
		return dinhMucSL;
	}
	public void setDinhMucSL(int dinhMucSL) {
		this.dinhMucSL = dinhMucSL;
	}
	
}