package itesm.database.DAO_Implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import itesm.business.ConsultaBean;
import itesm.business.PacienteBean;
import itesm.business.SalaBean;
import itesm.database.Conexion;
import itesm.database.DAO_Interfaces.DAOConsulta;

public class DAOConsultaImpl extends Conexion implements DAOConsulta{

	@Override
	public void insertar(ConsultaBean consulta) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
	      
   	 	establishConnection();
        conn = getCon();
        
        String sql ="INSERT INTO consultas (fecha, hora_inicial, hora_final, id_empleado, id_sala, id_paciente, observaciones, estado)";
        sql+="VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, consulta.getFecha_consulta());
        ps.setString(2, consulta.getHora_inicial());
        ps.setString(3, consulta.getHora_final());
        ps.setString(4, consulta.getId_empleado());
        ps.setString(5, consulta.getId_sala());
        ps.setString(6, consulta.getId_paciente());
        ps.setString(7, consulta.getObservaciones());
        ps.setString(8, consulta.getEstado_consulta());
		
        ps.execute();

        if (conn != null) {
           try {
              closeConnection();
           } catch (Exception e) {
				e.printStackTrace();
           }
        }
		
	}
	
	public ArrayList<ConsultaBean> consultaGeneral() throws Exception{
		ArrayList<ConsultaBean> bufferConsultas = new ArrayList<ConsultaBean>();
		String sql = "";
		
	      Connection conn = null;
	      
	    	 establishConnection();
	         conn = getCon();
	        
        	 sql = "SELECT * FROM consultas";
	         PreparedStatement ps = conn.prepareStatement(sql);
	
	  
	         ResultSet rs = ps.executeQuery();

	         while (rs.next()) {
	        	ConsultaBean consulta = new ConsultaBean();
	        	
	        	consulta.setId_consulta(rs.getString(1));
	        	consulta.setFecha_consulta(rs.getString(2));
	        	consulta.setHora_inicial(rs.getString(3));	            
	        	consulta.setHora_final(rs.getString(4));
	        	consulta.setId_empleado(rs.getString(5));
	        	consulta.setId_sala(rs.getString(6));
	        	consulta.setId_paciente(rs.getString(7));
	        	consulta.setObservaciones(rs.getString(8));
	        	consulta.setEstado_consulta(rs.getString(9));
	        	
	            bufferConsultas.add(consulta);
	         }

	         if (conn != null) {
	            try {
	               closeConnection();
	            } catch (Exception e) {
					e.printStackTrace();
	            }
	         }
	      
	      return bufferConsultas;
		
	}

	@Override
	public ConsultaBean buscar(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<SalaBean> consultarDisponibles(String fecha, String hora_inicial, String hora_final) throws Exception {
		// TODO Auto-generated method stub
		// NO ES CORRECTO LA IMPLEMENTACION
		ArrayList<SalaBean> buffer_salas = new ArrayList<SalaBean>();
		String ret = "error";
		
		Connection conn = null;
	      
   	 	establishConnection();
        conn = getCon();
                
        String sql ="SELECT sala.id_sala, sala.nombre FROM sala left join ";
        sql += "(SELECT * FROM consultas WHERE fecha = ? AND ((hora_inicial between ? and ?) OR (hora_final between ? and ?))) consultas";
        sql += " ON sala.id_sala = consultas.id_sala WHERE sala.disponibilidad = 'disponible' AND consultas.id_sala IS NULL;";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, fecha);
        ps.setString(2, hora_inicial);
        ps.setString(3, hora_final);
        ps.setString(4, hora_inicial);
        ps.setString(5, hora_final);
        
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
        	SalaBean salaAux = new SalaBean();
        	salaAux.setId_sala(rs.getString(1));
        	salaAux.setNombre_sala(rs.getString(2));
           
	       	buffer_salas.add(salaAux);
        }
        
        if (conn != null) {
            try {
               closeConnection();
            } catch (Exception e) {
 				e.printStackTrace();
            }
        }
        
		return buffer_salas;
	}
	
	@Override
	public ArrayList<ConsultaBean> consultabyPro(String idPropietario) throws Exception{
		ArrayList<ConsultaBean> bufferCitas = new ArrayList<ConsultaBean>();
		String sql = "";
		
	      Connection conn = null;
	      
	    	 establishConnection();
	         conn = getCon();
	         sql = "SELECT consultas.id_consulta, consultas.fecha, consultas.hora_inicial, consultas.hora_final, consultas.id_empleado, consultas.id_sala, consultas.id_paciente, consultas.observaciones, consultas.estado ";
	         sql += "FROM paciente INNER JOIN consultas ";
	         sql += "WHERE paciente.id_propietario = ? AND paciente.id_paciente = consultas.id_paciente;";
	         
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ps.setString(1, idPropietario);
	  
	         ResultSet rs = ps.executeQuery();

	         while (rs.next()) {
	        	ConsultaBean consulta = new ConsultaBean();
	        	
	        	consulta.setId_consulta(rs.getString(1));
	        	consulta.setFecha_consulta(rs.getString(2));
	        	consulta.setHora_inicial(rs.getString(3));	            
	        	consulta.setHora_final(rs.getString(4));
	        	consulta.setId_empleado(rs.getString(5));
	        	consulta.setId_sala(rs.getString(6));
	        	consulta.setId_paciente(rs.getString(7));
	        	consulta.setObservaciones(rs.getString(8));
	        	consulta.setEstado_consulta(rs.getString(9));
	        	
	        	bufferCitas.add(consulta);
	         }

	         if (conn != null) {
	            try {
	               closeConnection();
	            } catch (Exception e) {
					e.printStackTrace();
	            }
	         }
	      
	      return bufferCitas;
	}
	
	@Override
	public void editar(ConsultaBean entidad) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ConsultaBean> consultar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
