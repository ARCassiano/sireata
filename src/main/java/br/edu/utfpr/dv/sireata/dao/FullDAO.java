package br.edu.utfpr.dv.sireata.dao;


import br.edu.utfpr.dv.sireata.algorithms.DeleteDAO;
import br.edu.utfpr.dv.sireata.algorithms.ReadDAO;
import br.edu.utfpr.dv.sireata.algorithms.UpdateDAO;
import java.sql.SQLException;
import java.util.List;

public abstract class FullDAO {
    
    protected ReadDAO read;
    protected UpdateDAO update;
    protected DeleteDAO delete;

    public DAOEntity buscarPorId(int value) throws SQLException {
        return read.buscarPorId(value);
    }
    
    public List<? extends DAOEntity> listarPorAta(int value) throws SQLException {
        return read.listarPorAta(value);
    }
    
    public int salvar(DAOEntity newEntity) throws SQLException {
        return update.salvar(newEntity);
    }
    
    public void excluir(int value) throws SQLException {
        delete.excluir(value);
    }

}
