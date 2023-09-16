package entity;

import java.sql.Date;

public class CTPhieuNhap {
	private int maPN;
	private int maThuoc;
	private String tenThuoc;
	private String loaiSP;
	private String donViTinh;
	private String nuocSanXuat;
	private float giaNhap;
	private float giaBan;
	private Date ngayNhap;
	private Date hanSuDung;
	private String quyCachDongGoi;
	private String nhaCungCap;
	private String thongTin;
	public CTPhieuNhap(int maPN, int maThuoc, String tenThuoc, String loaiSP, String donViTinh, String nuocSanXuat,
			float giaNhap, float giaBan, Date ngayNhap, Date hanSuDung, String quyCachDongGoi, String nhaCungCap,
			String thongTin) {
		super();
		this.maPN = maPN;
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.loaiSP = loaiSP;
		this.donViTinh = donViTinh;
		this.nuocSanXuat = nuocSanXuat;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.ngayNhap = ngayNhap;
		this.hanSuDung = hanSuDung;
		this.quyCachDongGoi = quyCachDongGoi;
		this.nhaCungCap = nhaCungCap;
		this.thongTin = thongTin;
	}
	
	public CTPhieuNhap() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getLoaiSP() {
		return loaiSP;
	}
	public void setLoaiSP(String loaiSP) {
		this.loaiSP = loaiSP;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public String getNuocSanXuat() {
		return nuocSanXuat;
	}
	public void setNuocSanXuat(String nuocSanXuat) {
		this.nuocSanXuat = nuocSanXuat;
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
	public Date getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public Date getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(Date hanSuDung) {
		this.hanSuDung = hanSuDung;
	}
	public String getQuyCachDongGoi() {
		return quyCachDongGoi;
	}
	public void setQuyCachDongGoi(String quyCachDongGoi) {
		this.quyCachDongGoi = quyCachDongGoi;
	}
	public String getNhaCungCap() {
		return nhaCungCap;
	}
	public void setNhaCungCap(String nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	public String getThongTin() {
		return thongTin;
	}
	public void setThongTin(String thongTin) {
		this.thongTin = thongTin;
	}
	
}
