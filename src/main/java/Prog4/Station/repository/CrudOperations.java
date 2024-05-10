package Prog4.Station.repository;


import java.sql.SQLException;
import java.util.List;

public interface CrudOperations<T> {
    List<T> findAll();

    T findById(int id) throws SQLException;

    void save(T toSave);

    void saveAll(List<T> toSave);

    void update(T toUpdate);

    void delete(int id);
}
