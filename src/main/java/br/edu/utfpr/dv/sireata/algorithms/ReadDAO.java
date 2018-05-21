package br.edu.utfpr.dv.sireata.algorithms;

import java.util.Set;
import br.edu.utfpr.dv.sireata.dao.DAOEntity;
import java.sql.SQLException;
import java.util.List;

public interface ReadDAO {
    
    public DAOEntity buscarPorId(int value)throws SQLException;
    public List<? extends DAOEntity> listarPorAta(int value)throws SQLException;
}
