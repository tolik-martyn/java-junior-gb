package hw4_databases;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        try {
            createDatabase(sessionFactory);
            insertCourse(sessionFactory);
            insertCourse(sessionFactory);
            readCourse(sessionFactory);
            updateCourse(sessionFactory);
            readCourse(sessionFactory);
            deleteCourse(sessionFactory);
            readCourse(sessionFactory);

        } finally {
            sessionFactory.close();
        }
    }

    private static void createDatabase(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Create tables if not exists
                session.createSQLQuery("CREATE TABLE IF NOT EXISTS courses (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "title VARCHAR(255) NOT NULL," +
                        "duration INT)").executeUpdate();

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.err.println("Ошибка в методе createDatabase");
            }
        }
    }

    private static void insertCourse(SessionFactory sessionFactory) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Insert
            Course course = new Course("Java Programming", 10);
            session.save(course);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Ошибка в методе insertCourse");
        }
    }

    private static void readCourse(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Read
                Course retrievedCourse = session.get(Course.class, 1L);
                System.out.println("Retrieved Course: " + retrievedCourse);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.err.println("Ошибка в методе readCourse");
            }
        }
    }

    private static void updateCourse(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Update
                Course retrievedCourse = session.get(Course.class, 1L);
                retrievedCourse.setDuration(20);
                session.update(retrievedCourse);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.err.println("Ошибка в методе updateCourse");
            }
        }
    }

    private static void deleteCourse(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Delete
                Course retrievedCourse = session.get(Course.class, 1L);
                session.delete(retrievedCourse);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.err.println("Ошибка в методе deleteCourse");
            }
        }
    }
}