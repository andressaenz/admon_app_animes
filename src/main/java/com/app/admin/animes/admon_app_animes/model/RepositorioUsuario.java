package com.app.admin.animes.admon_app_animes.model;


import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RepositorioUsuario {

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


}
