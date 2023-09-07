package entity;

public class NhaCungCap {
	private int maNCC;
	private String tenNCC;
	public NhaCungCap(int maNCC, String tenNCC) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
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
	
}
