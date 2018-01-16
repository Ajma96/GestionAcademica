package com.iesvdc.acceso.controller.filters;

import com.iesvdc.acceso.dao.Conexion;
import com.iesvdc.acceso.dao.DAOException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class AuthenticationService {
	public boolean authenticate(String authCredentials) {
                boolean valor=false;
                
		if (null == authCredentials)
			return valor;
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic"
				+ " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(
					encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
                        final StringTokenizer tokenizer = new StringTokenizer(
                                        usernameAndPassword, ":");
                        
                        final String username = tokenizer.nextToken();
                        final String password = tokenizer.nextToken();
                         // we have fixed the userid and password as admin
                        // call some UserService/LDAP here
                        Conexion conexion = new Conexion();
                        Connection con = conexion.getConexion();
                        String sql = "SELECT * FROM Usuarios WHERE username=? AND password=?";
                        PreparedStatement pstm = con.prepareStatement(sql);
                        pstm.setString(1, username);
                        pstm.setString(2, password);
                        ResultSet rs = pstm.executeQuery();

                        if (rs.next()) {
                            valor = true;
                        } /* else {
                            valor = false;
                        }*/
		} catch (DAOException | UnsupportedEncodingException | SQLException | NoSuchElementException e) {
			return false;
		}
		
		return valor;
	}
}
