package itesm.database.DAO_Implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import itesm.business.EmpleadoBean;
import itesm.database.Conexion;
import itesm.database.DAO_Interfaces.DAOEmpleado;

public class DAOEmpleadoImpl extends Conexion implements DAOEmpleado{

	@Override
	public void insertar(EmpleadoBean empleado) throws Exception {
		Connection conn = null;
	      
   	 	establishConnection();
        conn = getCon();
        String sql = "INSERT INTO empleado (id_empleado, nombre_completo, direccion, telefono, puesto, especialidad, turno, estado)";
        sql+="VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, empleado.getId_empleado());
        ps.setString(2, empleado.getNombre_completo());
        ps.setString(3, empleado.getDireccion());
        ps.setString(4, empleado.getTelefono());
        ps.setString(5, empleado.getPuesto());
        ps.setString(6, empleado.getEspecialidad());
        ps.setString(7, empleado.getTurno());
        ps.setString(8, empleado.getEstado());
        
        ps.execute();

        if (conn != null) {
           try {
              closeConnection();
           } catch (Exception e) {
				e.printStackTrace();
           }
        }
	}

	@Override
	public ArrayList<EmpleadoBean> consultarEmpleadosDisponibles() throws Exception {
		ArrayList<EmpleadoBean> buffer_empleados = new ArrayList<EmpleadoBean>();
		String sql = "";
		
	      Connection conn = null;
	      
	    	 establishConnection();
	         conn = getCon();
	        
        	 sql = "SELECT id_empleado, nombre_completo, direccion, telefono, especialidad ,puesto, turno, estado FROM empleado ";
        	 sql += "WHERE elegido = 0";         
	         
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery(sql);

	         while (rs.next()) {
	        	EmpleadoBean empleado = new EmpleadoBean();
	        	empleado.setId_empleado(((rs.getString(1))));
	        	empleado.setNombre_completo((rs.getString(2)));
	        	empleado.setDireccion((rs.getString(3)));	            
	        	empleado.setTelefono((rs.getString(4)));
	        	empleado.setEspecialidad(rs.getString(5));
	        	empleado.setPuesto((rs.getString(6)));
	        	empleado.setTurno(((rs.getString(7))));
	        	empleado.setEstado(((rs.getString(8))));
	            
	            buffer_empleados.add(empleado);
	         }

	         if (conn != null) {
	            try {
	               closeConnection();
	            } catch (Exception e) {
					e.printStackTrace();
	            }
	         }
	      
	      return buffer_empleados;
	}

	@Override
	public EmpleadoBean buscar(String id) throws Exception {
		Connection conn = null;
	      
   	 	establishConnection();
        conn = getCon();
        String sql = "SELECT * FROM empleado WHERE";
        sql+=" id_empleado = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        
        EmpleadoBean empleado = null;
        
        while (rs.next()) {
        	empleado = new EmpleadoBean();
           empleado.setId_empleado(rs.getString(1));
           empleado.setNombre_completo(rs.getString(2));
           empleado.setDireccion(rs.getString(3));
           empleado.setTelefono(rs.getString(4));
           empleado.setPuesto(rs.getString(5));
           empleado.setEspecialidad(rs.getString(6));
           empleado.setTurno(rs.getString(7));
           empleado.setEstado(rs.getString(8));
        }

        if (conn != null) {
           try {
              closeConnection();
           } catch (Exception e) {
				e.printStackTrace();
           }
        }
        
        return empleado;
	}

	@Override
	public void editar(EmpleadoBean empleado) throws Exception {
		Connection conn = null;
	      
   	 	establishConnection();
        conn = getCon();
        String sql = "UPDATE empleado SET nombre_completo = ?, direccion = ?, telefono = ?, puesto = ?, especialidad = ?, turno = ? WHERE id_empleado = ?";
                
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, empleado.getNombre_completo());
        ps.setString(2, empleado.getDireccion());
        ps.setString(3, empleado.getTelefono());
        ps.setString(4, empleado.getPuesto());
        ps.setString(5, empleado.getEspecialidad());
        ps.setString(6, empleado.getTurno());
        ps.setString(7, empleado.getId_empleado());
        
        ps.execute();

        if (conn != null) {
           try {
              closeConnection();
           } catch (Exception e) {
				e.printStackTrace();
           }
        }
		
	}

	@Override
	public ArrayList<EmpleadoBean> consultar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editarDisponibilidad(String id_empleado, int disponibilidad) throws Exception {
		Connection conn = null;
	      
   	 	establishConnection();
        conn = getCon();
        String sql = "UPDATE empleado SET elegido = ? WHERE id_empleado = ?";
                
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, Integer.toString(disponibilidad));
        ps.setString(2, id_empleado);
        
        ps.execute();

        if (conn != null) {
           try {
              closeConnection();
           } catch (Exception e) {
				e.printStackTrace();
           }
        }
	}

}
