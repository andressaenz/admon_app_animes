package com.app.admin.animes.admon_app_animes.service;

import com.app.admin.animes.admon_app_animes.model.RepositorioUsuario;
import com.app.admin.animes.admon_app_animes.model.Usuario;
import com.app.admin.animes.admon_app_animes.util.PostgresConector;

import java.sql.Connection;

public class ServicioUsuario {

    // Metodo que registra un nuevo usuario, si este no existe previamente
    public static boolean registrarUsuario(Usuario usuario){
        try(Connection conn = PostgresConector.obtenerConexion()) {
            if (RepositorioUsuario.usuarioExiste(usuario.getUsuario(),conn)){
                return false;
            }
            RepositorioUsuario.guardarNuevoUsuario(usuario, conn);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Metodo que ayuda a iniciar sesion
    public static boolean iniciarSecion(Usuario usuario){
        try(Connection conn = PostgresConector.obtenerConexion()) {
            if(RepositorioUsuario.verificarContrasena(usuario,conn)){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
