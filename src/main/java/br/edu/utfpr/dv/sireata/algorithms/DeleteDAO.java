package br.edu.utfpr.dv.sireata.algorithms;

import java.sql.SQLException;

public interface DeleteDAO {
    
    public void excluir(int value)throws SQLException;
    
}
