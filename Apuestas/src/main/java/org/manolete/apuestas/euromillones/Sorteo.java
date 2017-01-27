package org.manolete.apuestas.euromillones;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "euromillones")
public class Sorteo {
	
	private Date fecha_sorteo;
	private byte num1;
	private byte num2;
	private byte num3;
	private byte num4;
	private byte num5;
	private byte estrella1;
	private byte estrella2;
	
	@Id
	@Temporal(TemporalType.DATE)
	@Column(nullable = false) 
	public Date getFecha_sorteo() {
		return fecha_sorteo;
	}
	
	@Basic
	@Column(precision = 2, nullable = false)
	public byte getNum1() {
		return num1;
	}
	
	@Basic
	@Column(precision = 2, nullable = false)
	public byte getNum2() {
		return num2;
	}
	
	@Basic
	@Column(precision = 2, nullable = false)
	public byte getNum3() {
		return num3;
	}
	
	@Basic
	@Column(precision = 2, nullable = false)
	public byte getNum4() {
		return num4;
	}
	
	@Basic
	@Column(precision = 2, nullable = false)
	public byte getNum5() {
		return num5;
	}
	
	@Basic
	@Column(precision = 2, nullable = false)
	public byte getEstrella1() {
		return estrella1;
	}
	
	@Basic
	@Column(precision = 2, nullable = false)
	public byte getEstrella2() {
		return estrella2;
	}
	
	@Basic
	@Column(precision = 2, nullable = false)
	public void setFecha_sorteo(Date fecha_sorteo) {
		this.fecha_sorteo = fecha_sorteo;
	}
	
	public void setNum1(byte num1) {
		this.num1 = num1;
	}
	
	public void setNum2(byte num2) {
		this.num2 = num2;
	}
	
	public void setNum3(byte num3) {
		this.num3 = num3;
	}
	
	public void setNum4(byte num4) {
		this.num4 = num4;
	}
	
	public void setNum5(byte num5) {
		this.num5 = num5;
	}
	
	public void setEstrella1(byte estrella1) {
		this.estrella1 = estrella1;
	}
	
	public void setEstrella2(byte estrella2) {
		this.estrella2 = estrella2;
	}
}
