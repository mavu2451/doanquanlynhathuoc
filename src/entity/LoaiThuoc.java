package entity;

public class LoaiThuoc {
	private int maLoaiSP;
	private String loaiSP;
	public LoaiThuoc(int maLoaiSP, String loaiSP) {
		super();
		this.maLoaiSP = maLoaiSP;
		this.loaiSP = loaiSP;
	}
	public LoaiThuoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMaLoaiSP() {
		return maLoaiSP;
	}
	public void setMaLoaiSP(int maLoaiSP) {
		this.maLoaiSP = maLoaiSP;
	}
	public String getLoaiSP() {
		return loaiSP;
	}
	public void setLoaiSP(String loaiSP) {
		this.loaiSP = loaiSP;
	}
	
}
