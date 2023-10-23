package entity;

public class NhaCungCap {
	private int maNCC;
	private String tenNCC;
	private int sdt;
	private String email;
	private String diaChi;
	public NhaCungCap(int maNCC, String tenNCC, int sdt, String email, String diaChi) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.sdt = sdt;
		this.email = email;
		this.diaChi = diaChi;
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
	public int getSdt() {
		return sdt;
	}
	public void setSdt(int sdt) {
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

	
}
