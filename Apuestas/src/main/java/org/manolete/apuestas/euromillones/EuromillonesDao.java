package org.manolete.apuestas.euromillones;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "euromillonesDao")
public class EuromillonesDao {
	
	private EntityManager entityManager;	
	
	public static final int DEFAULT_NUMBER = 20;
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void insertar(Sorteo sorteo) {
		this.entityManager.persist(sorteo);
		this.entityManager.flush();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void borrar(Sorteo sorteo) {
		if (!this.entityManager.contains(sorteo)) {
			sorteo = this.entityManager.merge(sorteo);
			// this.entityManager.flush();			
		}
		
		this.entityManager.remove(sorteo);		
		this.entityManager.flush();
	}
	
	public List<Sorteo> find() {
		System.out.println("Euromillones.findAll");
		return this.find(null, 0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Sorteo> find(Date fecha, int numeroRegistros) {
		Query consulta = this.entityManager.createQuery("select s from Sorteo s where s.fecha_sorteo <= :fecha order by s.fecha_sorteo desc");
		
		consulta.setParameter("fecha", fecha == null ? new Date() : fecha);
		
		if (numeroRegistros != 0) {
			consulta.setMaxResults(numeroRegistros);
		}
		
		return consulta.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Sorteo getPrediccion(Date fecha, boolean masFrecuentes) {
		Sorteo salida = new Sorteo();
		StringBuilder sbConsulta = new StringBuilder("select num1 as numero from (select num1 from apuestas.euromillones union all select num2 from apuestas.euromillones union all select num3 from apuestas.euromillones union all select num4 from apuestas.euromillones union all select num5 from apuestas.euromillones) numbersub group by numero order by count(*)");
		
		if (masFrecuentes) {
			sbConsulta.append(" desc");
		} else {
			sbConsulta.append(" asc");
		}
		
		Query consulta = this.entityManager.createNativeQuery(sbConsulta.toString());
		consulta.setMaxResults(5);
		
		List<Byte> lista = consulta.getResultList();
		
		Collections.sort(lista);
		
		salida.setFecha_sorteo(fecha);
		salida.setNum1(lista.get(0));
		salida.setNum2(lista.get(1));
		salida.setNum3(lista.get(2));
		salida.setNum4(lista.get(3));
		salida.setNum5(lista.get(4));
		
		sbConsulta = new StringBuilder("select estrella1 as numero from (select estrella1 from apuestas.euromillones union all select estrella2 from apuestas.euromillones) estrellasub group by estrella1 order by count(*)");
		
		if (masFrecuentes) {
			sbConsulta.append(" desc");
		} else {
			sbConsulta.append(" asc");
		}
		
		consulta = this.entityManager.createNativeQuery(sbConsulta.toString());
		consulta.setMaxResults(2);
		
		lista = consulta.getResultList();
		
		Collections.sort(lista);
		
		salida.setEstrella1(lista.get(0));
		salida.setEstrella2(lista.get(1));
		
		return salida;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Byte> getFrecuenciaNumeros(boolean porcentaje) {
		Query consulta = this.entityManager.createNativeQuery("select (count(*) * 100) / total.todos as porcentaje from (select num1 from apuestas.euromillones union all select num2 from apuestas.euromillones union all select num3 from apuestas.euromillones union all select num4 from apuestas.euromillones union all select num5 from apuestas.euromillones) numbersub, (select count(*) as todos from apuestas.euromillones) total group by num1");
		
		return consulta.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Byte> getFrecuenciaEstrellas(boolean porcentaje) {
		Query consulta = this.entityManager.createNativeQuery("select (count(*) * 100) / total.todos as porcentaje from (select estrella1 from apuestas.euromillones union all select estrella2 from apuestas.euromillones) estrellasub, (select count(*) as todos from apuestas.euromillones) total group by estrella1");
		
		return consulta.getResultList();
	}
}
