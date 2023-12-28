

package com.mycompany.conexionmongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;



public class ConexionMongoDB {

   public static void main(String[] args) {
        MongoClient mongo = crearConexion();

        if (mongo != null) {
            DB db = mongo.getDB("CasaJavier");
            
            
            //INSERT 
            
//            System.out.println("BASE DE DATOS CREADA");
//               insertarUsuario(db, "personas", "Javier", "Sandoval");
//               insertarUsuario(db, "personas", "Javier", "Rivera");
             insertarUsuario(db, "personas", "Francisca", "Perez");

            //MOSTRAR
            
                   mostrarColeccion(db, "personas");
//                 actualizarDocumento(db,"personas","Javiera");
//                 mostrarColeccion(db, "personas");
//                 borrarDocumento(db,"personas","Javiera");

        }
    }
   
     
         
     

    public static MongoClient crearConexion() {
        System.out.println("PRUEBA DE CONEXION MONGODB");
        MongoClient mongo = null;

        mongo = new MongoClient("localhost", 27017);
        return mongo;
    }
    
    
   
      //INSERTAR
    public static void insertarUsuario(DB db, String coleccion, String nombre, String apellido) {

        DBCollection nombreColeccion = db.getCollection(coleccion);

        BasicDBObject documento = new BasicDBObject();
        documento.put("nombre", nombre);
        documento.put("apellido", apellido);

        nombreColeccion.insert(documento);
    }
    
    public static void mostrarColeccion(DB db, String coleccion){
        DBCollection nombreColeccion = db.getCollection(coleccion);
        
        DBCursor cursor = nombreColeccion.find();
        
        while(cursor.hasNext()){
            System.out.println("* " + cursor.next().get("nombre") + " - " + cursor.curr().get("apellido"));
        }
    }
    
    public static void actualizarDocumento(DB db, String coleccion, String nombre) {
        DBCollection nombreColeccion = db.getCollection(coleccion);
        
        // SENTENCIA CON LAS INFORMACION A REEMPLAZAR
        
        BasicDBObject actualizarApellido = new BasicDBObject();  
        actualizarApellido.append("$set", new BasicDBObject().append("apellido", "Ferro"));
        
        
        // BUSCA EL DOCUMENTO EN LA COLECCION
        BasicDBObject buscarPorNombre = new BasicDBObject();
        buscarPorNombre.append("nombre", nombre);
        
        //REALIZA EL UPDATE
        nombreColeccion.updateMulti(buscarPorNombre, actualizarApellido);
    }
    
    public static void borrarDocumento(DB db, String coleccion, String nombre){
        DBCollection nombreColeccion = db.getCollection(coleccion);
        
        nombreColeccion.remove(new BasicDBObject().append("nombre", nombre));
    }
}