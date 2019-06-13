package com.my.vipzal.services;

import com.my.vipzal.table.Trainings;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("trainingsService")
public class TrainingsService {

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public void save(Trainings trainings){
        Session ses = sessionFactory.getCurrentSession();
        ses.save(trainings);
    }

    public Trainings get(long id){
        Session ses = sessionFactory.getCurrentSession();
        return ses.get(Trainings.class, id);
    }

    public List<Trainings> getForUser(long id){
        Session ses = sessionFactory.getCurrentSession();
        String sql = "FROM Trainings WHERE ID_USER = :id";
        Query q = ses.createQuery(sql);
        q.setParameter("id", id);
        List list = q.list();
        return list;
    }

    public List<Trainings> getForTrainer(long id){
        Session ses = sessionFactory.getCurrentSession();
        String sql = "FROM Trainings WHERE ID_TRAINER = :id";
        Query q = ses.createQuery(sql);
        q.setParameter("id", id);
        List list = q.list();
        return list;
    }

    public void update(Trainings trainings){
        Session ses = sessionFactory.getCurrentSession();
        ses.update(trainings);
    }

    public void delete(Trainings trainings){
        Session ses = sessionFactory.getCurrentSession();
        ses.delete(trainings);
    }
}
