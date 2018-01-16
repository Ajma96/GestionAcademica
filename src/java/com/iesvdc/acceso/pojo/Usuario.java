
package com.iesvdc.acceso.pojo;

/**
 * Esta clase nos ayuda a identificar usuarios y grupos en la base de datos.
 * @author Juangu <jgutierrez at iesvirgendelcarmen.coms>
 */
public class Usuario {
    static public int ADMIN = 0; // usuarios con privilegios
    static public int OPERARIO = 1; // usuarios básicos
    static public int DESCONOCIDO = -1; // no es un usuario del sistema
    
    String username;
    String password;
    int tipo;

    public Usuario() {
    }

    public Usuario(String username, String password, int tipo) {
        this.username = username;
        this.password = password;
        this.tipo = tipo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
}
