package entity;

public class Thuoc {
	private int maThuoc;
	private String tenThuoc;
	private LoaiThuoc loaiThuoc;
	private NhaCungCap ncc;
	private String dvt;
	private int soLuong;
	private float giaNhap;
	private float giaBan;
	private String nsx;
	private String cachDung;
	private String thongTin;
	private String trangThai;
	public Thuoc(int maThuoc, String tenThuoc, LoaiThuoc loaiThuoc, NhaCungCap ncc, String dvt, int soLuong, float giaNhap,
			float giaBan, String nsx, String cachDung, String thongTin, String trangThai) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.loaiThuoc = loaiThuoc;
		this.ncc = ncc;
		this.dvt = dvt;
		this.soLuong = soLuong;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.nsx = nsx;
		this.cachDung = cachDung;
		this.thongTin = thongTin;
		this.trangThai = trangThai;
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
	public LoaiThuoc getLoaiThuoc() {
		return loaiThuoc;
	}
	public void setLoaiThuoc(LoaiThuoc loaiThuoc) {
		this.loaiThuoc = loaiThuoc;
	}
	public NhaCungCap getNcc() {
		return ncc;
	}
	public void setNcc(NhaCungCap ncc) {
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
	
	
}
