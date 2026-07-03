package com.api_salud.api_salud;

import java.io.*;

public class ConvertidorSql {
    public static void main(String[] args) {
        String archivoEntrada = "catalogo_insumos2.sql";
        String archivoSalida = "insert_finales.sql";

        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] items = linea.split("\t");
                
                // Mapeo: Ajusta los índices [i] según el orden de columnas de tu archivo de texto original
                // Si el archivo original tiene menos columnas que la tabla, asignaremos NULL a los campos faltantes
                String codigo = (items.length > 0) ? "'" + items[0].replace("'", "''") + "'" : "NULL";
                String nombre = (items.length > 1) ? "'" + items[1].replace("'", "''") + "'" : "NULL";
                String id_grupo = (items.length > 2) ? items[2] : "NULL";
                String id_subgrupo = (items.length > 3) ? items[3] : "NULL";
                String id_partida = (items.length > 4) ? items[4] : "NULL";
                String id_centro_costo = (items.length > 5) ? items[5] : "NULL";
                String precio_compra = (items.length > 6) ? items[6] : "0";
                String precio_dist = (items.length > 7) ? items[7] : "0";
                String precio_don = (items.length > 8) ? items[8] : "0";
                String precio_ult = (items.length > 9) ? items[9] : "0";
                String id_tipo_salida = (items.length > 10) ? items[10] : "1"; // Valor por defecto
                String denominacion = (items.length > 11) ? "'" + items[11].replace("'", "''") + "'" : "NULL";
                String concentracion = (items.length > 12) ? "'" + items[12].replace("'", "''") + "'" : "NULL";
                String presentacion = (items.length > 13) ? "'" + items[13].replace("'", "''") + "'" : "NULL";
                String forma_farm = (items.length > 14) ? "'" + items[14].replace("'", "''") + "'" : "NULL";
                String petitorio = (items.length > 15 && items[15].equals("t")) ? "true" : "false";
                String tipo_sismed = (items.length > 16) ? "'" + items[16].replace("'", "''") + "'" : "NULL";
                String codigo_siga = (items.length > 17) ? "'" + items[17].replace("'", "''") + "'" : "NULL";
                String id_via = (items.length > 18) ? items[18] : "NULL";

                // Construcción de la sentencia con las columnas específicas de tu DDL
                String sql = "INSERT INTO igm_maestros.catalogo_bienes_insumos " +
                        "(codigo, nombre, id_grupo_farmacologico, id_subgrupo_farmacologico, id_partida, id_centro_costo, " +
                        "precio_compra, precio_distribucion, precio_donacion, precio_ult_compra, id_tipo_salida_bien_insumo, " +
                        "denominacion, concentracion, presentacion, forma_farmaceutica, petitorio, tipo_producto_sismed, codigo_siga, id_via) " +
                        "VALUES (" + codigo + ", " + nombre + ", " + id_grupo + ", " + id_subgrupo + ", " + id_partida + ", " + 
                        id_centro_costo + ", " + precio_compra + ", " + precio_dist + ", " + precio_don + ", " + precio_ult + 
                        ", " + id_tipo_salida + ", " + denominacion + ", " + concentracion + ", " + presentacion + ", " + 
                        forma_farm + ", " + petitorio + ", " + tipo_sismed + ", " + codigo_siga + ", " + id_via + ");";

                bw.write(sql);
                bw.newLine();
            }
            System.out.println("Conversión completada con éxito.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}