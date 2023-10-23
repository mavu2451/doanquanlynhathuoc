package entity;

public class LoaiThuoc {
	private int maLoaiThuoc;
	private String tenLoaiThuoc;
	public LoaiThuoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoaiThuoc(int maLoaiThuoc, String tenLoaiThuoc) {
		super();
		this.maLoaiThuoc = maLoaiThuoc;
		this.tenLoaiThuoc = tenLoaiThuoc;
	}
	public LoaiThuoc(String loaiThuoc) {
		super();
		this.tenLoaiThuoc = loaiThuoc;
	}
	public int getMaLoaiThuoc() {
		return maLoaiThuoc;
	}
	public void setMaLoaiThuoc(int maLoaiThuoc) {
		this.maLoaiThuoc = maLoaiThuoc;
	}
	public String getTenLoaiThuoc() {
		return tenLoaiThuoc;
	}
	public void setTenLoaiThuoc(String tenLoaiThuoc) {
		this.tenLoaiThuoc = tenLoaiThuoc;
	}

}
