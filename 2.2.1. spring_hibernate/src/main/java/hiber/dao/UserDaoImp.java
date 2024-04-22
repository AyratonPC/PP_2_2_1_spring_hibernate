package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User userModelSeries(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      Query query = session.createQuery("select u from User u,Car c where u.car.id = c.id and c.model = :modelCar and" +
              " c.series = :seriesCar");
      return (User) query.setParameter("modelCar", model).setParameter("seriesCar", series).getSingleResult();
   }
}
