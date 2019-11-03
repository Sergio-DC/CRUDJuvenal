package itesm.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import itesm.business.EmpleadoBean;
import itesm.business.UsuarioBean;

public class DAOEmpleadoImpl extends Conexion implements DAOEmpleado{

	@Override
	public String insertarEmpleado(EmpleadoBean empleado) throws Exception {
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
     
		return "success";
	}

	@Override
	public ArrayList<EmpleadoBean> consultarEmpleados() throws Exception {
		String ret = "error";
		ArrayList<EmpleadoBean> buffer_empleados = new ArrayList<EmpleadoBean>();
		
	      Connection conn = null;
	      
	    	 establishConnection();
	         conn = getCon();
	         String sql = "SELECT id_empleado, nombre_completo, direccion, telefono, especialidad ,puesto, turno, estado FROM empleado";
	         Statement statement = conn.createStatement();
	         ResultSet rs = statement.executeQuery(sql);

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

}