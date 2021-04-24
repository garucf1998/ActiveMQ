package Model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;




@XmlRootElement
@XmlType(propOrder={"mssv","hoten","ngaysinh"})
public class SinhVien implements Serializable{
	private String mssv;
	private String hoten;
	private String ngaysinh;
	
	public SinhVien(String mssv, String hoten, String ngaysinh) 
	{
		this.mssv = mssv;
		this.hoten = hoten;
		this.ngaysinh = ngaysinh;
	}
	public SinhVien() {
	}
	public String getMssv() {
		return mssv;
	}
	public void setMssv(String mssv) {
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
