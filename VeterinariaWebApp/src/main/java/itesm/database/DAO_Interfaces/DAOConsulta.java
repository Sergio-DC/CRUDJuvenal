package itesm.database.DAO_Interfaces;

import java.util.ArrayList;

import itesm.business.ConsultaBean;
import itesm.business.SalaBean;

public interface DAOConsulta extends CRUD<ConsultaBean>{

	ArrayList<SalaBean> consultarDisponibles(String fecha, String hora_inicial, String hora_final) throws Exception;

}
