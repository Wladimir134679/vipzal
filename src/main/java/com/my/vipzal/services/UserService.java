package com.my.vipzal.services;

import com.my.vipzal.table.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("userService")
public class UserService {

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public void save(User user){
        Session ses = sessionFactory.getCurrentSession();
        ses.save(user);
    }

    public User get(String email, String pass){
        Session ses = sessionFactory.getCurrentSession();
        String sql = "FROM User WHERE PASSWORD= :pass AND EMAIL = :email";
        Query q = ses.createQuery(sql);
        q.setParameter("pass", pass);
        q.setParameter("email", email);
        List list = q.list();
        if(list != null && list.size() > 0)
            return (User)list.get(0);
        return null;
    }

    public User get(long id){
        Session ses = sessionFactory.getCurrentSession();
        User user = ses.get(User.class, id);
        return user;
    }

    public User update(User user){
        Session ses = sessionFactory.getCurrentSession();
        ses.update(user);
        return user;
    }

    public List<User> getAll(){
        Session ses = sessionFactory.getCurrentSession();
        Query q = ses.createQuery("FROM User");
        return q.list();
    }
}
