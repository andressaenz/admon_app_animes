package com.app.admin.animes.admon_app_animes.model;


import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RepositorioUsuario {

    // Metodo que guarda un nuevo usuario con el rol de admin
    public static void guardarNuevoUsuario(Usuario usuario, Connection conn){

        String sql = "INSERT INTO usuarios (usuario, contrasena, rol) VALUES (?,?,?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2,BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt()));
            stmt.setString(3,"admin");
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo que valida si el usuario existe, evita duplicidad
    public static boolean usuarioExiste(String usuario, Connection conn){

        String sql = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return rs.getInt(1)>0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo que verifica la contraseña ingresada
    public static boolean verificarContrasena(Usuario usuario, Connection conn){
        String sql = "SELECT contrasena FROM usuarios WHERE usuario = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, usuario.getUsuario());
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                String hashAlmacenado = rs.getString("contrasena");
                return BCrypt.checkpw(usuario.getContrasena(),hashAlmacenado);
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
