package org.upiita.spring.jdbc.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.upiita.spring.jdbc.entidades.Video;

@Component("videoDAOTemplate")
public class VideoDAOHibernate implements VideoDAO {

	@Autowired   
	private SessionFactory sessionFactory;
	
	public void creaVideo(Video video) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();  //Creamos una sesión de hibernate
		session.beginTransaction();   // Una vez que se tiene la sesión , debemos abrir una transacción
		
		session.saveOrUpdate(video);
		
		session.getTransaction().commit();
		
		session.close();
		
		
	}

	public Video obtenVideoPorId(Integer videoId) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();  
		session.beginTransaction();   
		Video video = (Video) session.get(Video.class, videoId);  
		
		session.getTransaction().commit();
		
		session.close();
		
		return video;
	}
	
	public List<Video> obtenPorTitulo(String nombre){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criterio = session.createCriteria(Video.class);
		criterio.add(Restrictions.like("titulo", "%" + nombre + "%"));
		
		List<Video> videos = criterio.list();
		
		session.getTransaction().commit();
		
		session.close();
		return videos;
	}
	
	public List<Video> obtenPorDescripcion(String descripcion){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criterio = session.createCriteria(Video.class);
		criterio.add(Restrictions.like("descripcion", "%" + descripcion + "%"));
		
		List<Video> videos = criterio.list();
		
		session.getTransaction().commit();
		
		session.close();
		return videos;
	}
	
}
