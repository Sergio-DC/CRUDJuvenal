package itesm.action_support;

import java.util.ArrayList;
import com.opensymphony.xwork2.ActionSupport;

import itesm.business.EmpleadoBean;
import itesm.business.UsuarioBean;
import itesm.database.DAOEmpleado;
import itesm.database.DAOEmpleadoImpl;
import itesm.database.DAOUsuario;
import itesm.database.DAOUsuarioImpl;

public class UsuarioAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private UsuarioBean usuario;
	private EmpleadoBean empleadoBean;
	private String mensajeError, qs_user_id;//Query String to get the url from the previous action

	private ArrayList<EmpleadoBean> buffer_empleado;
	private ArrayList<UsuarioBean> buffer_usuarios;
	private ArrayList<String> lista_empleados_frontend, list_grupos_privilegios_frontend, list_estado_frontend;
	
	public void setUsuario(UsuarioBean usuario) {this.usuario = usuario;}

	public UsuarioBean getUsuario() {return usuario;}
	
	public EmpleadoBean getEmpleadoBean() {return empleadoBean;}
	
	public ArrayList<String> getLista_empleados_frontend() {return lista_empleados_frontend;}

	public ArrayList<String> getList_grupos_privilegios_frontend() {return list_grupos_privilegios_frontend;}

	public ArrayList<String> getList_estado_frontend() {return list_estado_frontend;}
		
	public ArrayList<UsuarioBean> getBuffer_usuarios() {return buffer_usuarios;}

	public void setBuffer_usuarios(ArrayList<UsuarioBean> buffer_usuarios) {this.buffer_usuarios = buffer_usuarios;}

	public String getMensajeError() {return mensajeError;}
	
	public void setQs_user_id(String qs_user_id) {this.qs_user_id = qs_user_id;}
	
	
	
	public String validarLogin() {
		String respuesta;
		//1. Recuperar Tablas de variables de sesion (Usuario y Admin)
		DAOUsuario daoUsuario = new DAOUsuarioImpl();
		try {
			respuesta = daoUsuario.verificarUsuario(usuario);
			if(respuesta == ERROR)
				mensajeError = "Usuario o Password Incorrectos";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			respuesta = ERROR;
		}
		
		return respuesta;
	}

	public String crearUsuario() {
		
		//Verificamos que el password y el password reescrito sean iguales
		if(usuario.getPassword().contentEquals(usuario.getConfirmar_password())){
				DAOUsuario daoUsuario = new DAOUsuarioImpl();
				try {
					daoUsuario.insertarUsuario(usuario);
				} catch (Exception e) {
					e.printStackTrace();
					mensajeError = "Error al insertar el registro a la BD";
					return ERROR;
				}			
			return SUCCESS;
		}else { 
			mensajeError = "Los passwords no coinciden";
			return ERROR;
		}
	}
	
	public String eliminarUsuario() {
		//1. Verificamos que el usuario que nos estan pidiendo eliminar existe
		DAOUsuario daoUsuario = new DAOUsuarioImpl();
		UsuarioBean usuario = new UsuarioBean();
		usuario.setUsuarioID(this.qs_user_id);
		
		try {
			daoUsuario.eliminarUsuario(usuario);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			//En caso de que no encuentre el registro que se va eliminar se reedirige
			//a un jsp indicando al usuario el error
			return ERROR;
		}
	}
	
	public String llenarDropDownLists() {
		String respuesta;
		buffer_empleado = new ArrayList<EmpleadoBean>();
		
		//1. Recuperar el id del empleado(Opcional junto con su Nombre)
		DAOEmpleado daoEmpleado = new DAOEmpleadoImpl();
		
		try {
			buffer_empleado= daoEmpleado.consultarEmpleados(true);
			respuesta = SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			respuesta = ERROR;
		}
		
		this.lista_empleados_frontend = new ArrayList<String>();
		for(EmpleadoBean item : buffer_empleado) {
			lista_empleados_frontend.add(item.getId_empleado());
		}
		
		
		//Agregar datos en el list 'grupo' del jsp 'alta usuarios'
		this.list_grupos_privilegios_frontend = new ArrayList<String>();
		this.list_grupos_privilegios_frontend.add("Administrador");
		this.list_grupos_privilegios_frontend.add("Usuario");
		//Agregar datos en el list 'estado' del jsp 'alta usuarios'
		this.list_estado_frontend = new ArrayList<String>();
		this.list_estado_frontend.add("Activo");
		this.list_estado_frontend.add("Bloqueado");
		this.list_estado_frontend.add("Inactivo");
		
		
		return respuesta;
	}
	
	public String listarUsuarios() {
		//1. Obtener la tabla empleados con todos sus registros
		DAOUsuario daoUsuario = new DAOUsuarioImpl();
		try {
			this.setBuffer_usuarios(daoUsuario.consultarUsuarios());
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String buscarUsuario() {
		DAOUsuario daoUsuario = new DAOUsuarioImpl();
		try {
			this.usuario = daoUsuario.buscarUsuario(usuario);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensajeError = "Usuario no encontrado";
			return ERROR;
		}
	}
	public String actualizarEstado() {
		DAOUsuario daoUsuario = new DAOUsuarioImpl();
		
		try {
			daoUsuario.actualizarEstado(usuario);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String llenarDropDownLists_Estado() {
		this.list_estado_frontend = new ArrayList<String>();	
		
		this.list_estado_frontend.add("Activo");
		this.list_estado_frontend.add("Bloqueado");
		this.list_estado_frontend.add("Inactivo");
		
		return SUCCESS;
	}

}
