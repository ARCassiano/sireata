package br.edu.utfpr.dv.sireata.algorithms;

import br.edu.utfpr.dv.sireata.dao.DAOEntity;
import java.sql.SQLException;

public interface UpdateDAO {
    
    public int salvar(DAOEntity newEntity)throws SQLException ; 
    
}
