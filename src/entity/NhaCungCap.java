package entity;

public class NhaCungCap {
	private int maNCC;
	private String tenNCC;
	private String sdt;
	private String email;
	private String diaChi;
	private String trangThai;
	public NhaCungCap(int maNCC, String tenNCC, String sdt, String email, String diaChi, String trangThai) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.sdt = sdt;
		this.email = email;
		this.diaChi = diaChi;
		this.trangThai = trangThai;
	}
	public NhaCungCap() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(int maNCC) {
		this.maNCC = maNCC;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	
	
}
