package entity;

public class LoaiThuoc {
	private int maLoaiThuoc;
	private String loaiThuoc;
	public LoaiThuoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoaiThuoc(int maLoaiThuoc, String loaiThuoc) {
		super();
		this.maLoaiThuoc = maLoaiThuoc;
		this.loaiThuoc = loaiThuoc;
	}
	public LoaiThuoc(String loaiThuoc) {
		super();
		this.loaiThuoc = loaiThuoc;
	}
	public int getMaLoaiThuoc() {
		return maLoaiThuoc;
	}
	public void setMaLoaiThuoc(int maLoaiThuoc) {
		this.maLoaiThuoc = maLoaiThuoc;
	}
	public String getLoaiThuoc() {
		return loaiThuoc;
	}
	public void setLoaiThuoc(String loaiThuoc) {
		this.loaiThuoc = loaiThuoc;
	}

}
