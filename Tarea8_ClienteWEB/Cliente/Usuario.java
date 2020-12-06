import com.google.gson.*;

public class Usuario {

  String email;
  String nombre;
  String apellido_paterno;
  String apellido_materno;
  String fecha_nacimiento;
  String telefono;
  String genero;
  byte[] foto;
    
  Usuario (String email,String nombre,String apellido_paterno,String apellido_materno,String fecha_nacimiento,String telefono,String genero){
    this.email = email;
    this.nombre = nombre;
    this.apellido_paterno = apellido_paterno;
    this.apellido_materno = apellido_materno;
    this.fecha_nacimiento = fecha_nacimiento;
    this.telefono = telefono;
    this.genero = genero;
    this.foto = null;
  }        

  @Override
  public String toString() {
    return "Nombre: "+this.nombre+" "+this.apellido_paterno+" "+this.apellido_materno+"\nEmail: "+this.email+"\n Telefono: "+this.telefono+"\nGenero: "+this.genero;
  }        

}
