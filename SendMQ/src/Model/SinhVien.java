package Model;

import java.io.Serializable;
import java.util.Date;

public class SinhVien implements Serializable{
	private long mssv;
	private String hoten;
	private String ngaysinh;
	
	public SinhVien(long mssv, String hoten, String ngaysinh) 
	{
		this.mssv = mssv;
		this.hoten = hoten;
		this.ngaysinh = ngaysinh;
	}
	public SinhVien() {
	}
	public long getMssv() {
		return mssv;
	}
	public void setMssv(long mssv) {
		this.mssv = mssv;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	@Override
	public String toString() {
		return mssv+"\t"+hoten+"\t"+ngaysinh;
	}
}
