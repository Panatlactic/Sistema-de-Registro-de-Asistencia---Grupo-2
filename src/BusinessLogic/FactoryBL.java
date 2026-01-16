
package BusinessLogic;

import DataAccess_Component.Interfaces.IDAO;
import Infraestructure_Component.AppException;
import java.util.List;

public class FactoryBL<T>  {
    private final IDAO<T> oDAO;

    public FactoryBL(Class<? extends IDAO<T>> classDAO) {
        try {
            this.oDAO = classDAO.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            AppException er = new AppException(e, getClass(), "FactoryBL(...)");
            throw new RuntimeException(er);
        }
    }

    // Constructor que usa un Supplier para crear la instancia de T
    // public FactoryBL(Supplier<IDAO<T>> supplier) {
    //     this.oDAO = supplier.get(); 
    // }
 
    public List<T> getAll() throws AppException {
         return oDAO.readAll();
    }

    public T getBy(Integer id) throws AppException {
        return oDAO.readBy(id);
    }

    public boolean add(T oT) throws AppException {
        return oDAO.create(oT);
    }

    public boolean upd(T oT) throws AppException {
        return oDAO.update(oT);
    }

    public boolean del(Integer id) throws AppException {
        return oDAO.delete(id);
    }
}
