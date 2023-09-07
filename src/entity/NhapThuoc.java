package entity;

import java.sql.Date;

public class NhapThuoc {
	private String maThuoc;
	private String tenThuoc;
	private String loaiSP;
	private String donViTinh;
	private float giaNhap;
	private float giaBan;
	private int slTonKho;
	private int maNCC;
	private String nuocSanXuat;
	private Date ngaySanXuat;
	private Date hanSuDung;
	private String quyCachDongGoi;
	private String thongTin;
	private int trangThai;
	
	public NhapThuoc(String maThuoc, String tenThuoc, String loaiSP, String donViTinh, float giaNhap, float giaBan,
			int slTonKho, int maNCC, String nuocSanXuat, Date ngaySanXuat, Date hanSuDung, String quyCachDongGoi,
			String thongTin, int trangThai) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.loaiSP = loaiSP;
		this.donViTinh = donViTinh;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.slTonKho = slTonKho;
		this.maNCC = maNCC;
		this.nuocSanXuat = nuocSanXuat;
		this.ngaySanXuat = ngaySanXuat;
		this.hanSuDung = hanSuDung;
		this.quyCachDongGoi = quyCachDongGoi;
		this.thongTin = thongTin;
		this.trangThai = trangThai;
	}
	
	public NhapThuoc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaThuoc() {
		return maThuoc;
	}
	public void setMaThuoc(String maThuoc) {
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
	public int getSlTonKho() {
		return slTonKho;
	}
	public void setSlTonKho(int slTonKho) {
		this.slTonKho = slTonKho;
	}
	public int getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(int maNCC) {
		this.maNCC = maNCC;
	}
	public String getNuocSanXuat() {
		return nuocSanXuat;
	}
	public void setNuocSanXuat(String nuocSanXuat) {
		this.nuocSanXuat = nuocSanXuat;
	}
	public Date getNgaySanXuat() {
		return ngaySanXuat;
	}
	public void setNgaySanXuat(Date ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
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
	public String getThongTin() {
		return thongTin;
	}
	public void setThongTin(String thongTin) {
		this.thongTin = thongTin;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	
}
